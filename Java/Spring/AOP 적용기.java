package com.toms.app.acra.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toms.app.acra.dto.AcraApiHistory;
import com.toms.app.acra.repository.AcraRepository;
import com.toms.app.common.repository.RequestMasterRepository;
import com.toms.app.member.dto.TomsUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class AcraAspect {

    private final AcraRepository acraRepository;
    private final RequestMasterRepository requestMasterRepository;

    public AcraAspect(AcraRepository acraRepository, RequestMasterRepository requestMasterRepository) {
        this.acraRepository = acraRepository;
        this.requestMasterRepository = requestMasterRepository;
    }

    @Pointcut("execution(* com.toms.app.acra.service.AcraApiClient.*(..))")
    public void acraApiClient() {}

    @Around("acraApiClient()")
    public Object logAcraApiHistory(ProceedingJoinPoint joinPoint) throws Throwable {


        TomsUserDetails userInfo = (TomsUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String message = null;

        try {
            return joinPoint.proceed();
        } catch (WebClientResponseException e) {
            log.info("exception >> {}", e.getResponseBodyAsString());
            message = e.getResponseBodyAsString();

        } catch (Exception e) {
            message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();

        } finally {

            String requestId = findRequestId(request);
            String reqKindCodeName = null;
            if (requestId != null) {
                reqKindCodeName = requestMasterRepository.getRequestMaster(requestId).getReqKindCodeName();
            }


            AcraApiHistory history = AcraApiHistory.builder()
                    .requestId(requestId)
                    .uri(String.valueOf(joinPoint.getArgs()[0]))
                    .registerUserId(userInfo.getUserId())
                    .reqKindCodeName(reqKindCodeName)
                    .isSuccess(message == null)
                    .reason(message)
                    .build();

            acraRepository.insertAcraApiHistory(history);
        }

        return null;
    }

    private String findRequestId(HttpServletRequest request) throws JsonProcessingException {

        log.info(Arrays.toString(request.getParameterMap().get("requestId")));
        log.info(String.valueOf(new ObjectMapper().readValue(Arrays.stream(request.getParameterMap().get("node")).toList().get(0), Map.class).get("requestId")));

        if (!"null".equals(Arrays.toString(request.getParameterMap().get("requestId")))) {
            return request.getParameterMap().get("requestId")[0];
        }

        if (!"null".equals(String.valueOf(new ObjectMapper().readValue(Arrays.stream(request.getParameterMap().get("node")).toList().get(0), Map.class).get("requestId")))) {
            return String.valueOf(new ObjectMapper().readValue(Arrays.stream(request.getParameterMap().get("node")).toList().get(0), Map.class).get("requestId"));
        }

        return null;
    }
}
