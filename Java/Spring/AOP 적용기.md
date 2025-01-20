# 요구사항

1. webClient Method가 호출될 때 DB에 log를 적재 해야한다. 
2. 성공, 실패 여부 상관 없이 적재하고, 실패 시 실패 사유가 저장되어야 한다. 
3. 실패 사유는 미들 서버의 response로 한다. 

# 개요

`serviceInterface` 의 구현체는 webClient Call을 담당하는 `WebClientInterface`의 메소드를 호출한다. 

```java
public class serviceInterfaceImpl implements ServiceInterface {

    private final WebClientInferface webClientInferface;
    
    @Override
    public List<ResponseData> getUsers() {
        return webClientInferface.get("/users", "", "", ResponseData.class);
    }
    
}
```

```java
public interface WebClientInterface {

    <T> List<T> getList(String uri, String param, String value, Class<T> responseClass);
    <T> T get(String uri, String param, String value, Class<T> responseClass);
    <T> T post(String uri, Object pObject, Class<T> responseClass);

}

public class WebClientInterfaceImpl implements WebClientInterface {

    @Override
    public <T> T get(String uri, String param, String value, Class<T> responseClass) {

        log.debug("Acra API request param => {}", value);

        return webClient.method(HttpMethod.GET)
                .uri(uriBuilder -> {
                    UriBuilder path = uriBuilder.path(uri);
                    return path.queryParam("filter", param)
                            .build(value);

                })
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.TEXT_PLAIN)
                .retrieve()
                .bodyToMono(responseClass)
                .block();
    }
    
    @Override
    public <T> T post(String uri, Object pObject, Class<T> responseClass) {
    
    ... 
    
}
```

# 해결

먼저 고려한 방법은 DB Connection을 담당하는 Class를 주입받거나 private Method를 선언해 WebClient 구현체에 포함하려고 하였다. 

그러나, WebClientInterface의 SRP 원칙이 깨지는 것을 방지하고 싶었고 이 방법 택하지 않았다. 

다음으로는 WebClientInterface의 Method를 AOP PointCut로 등록하고, AOP 내부에서 처리하고자 하였다. 

아래는 Aspect 클래스의 구현 코드이다. 

```java
@Aspect
@Component
public class HistoryAspect {

    @Pointcut("execution(* com.temp.service.WebClientService.*(..))")
    public void webClient() {}
    
    @Around("webClient()")
    public Object logWebClientHistory(ProceedingJoinPoint joinPoint) throws Throwable {

        String message = null;

        try {
        
            return joinPoint.proceed();
            
        } catch (WebClientResponseException e) {
        
            message = e.getResponseBodyAsString();

        } catch (Exception e) {
        
            message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();

        } finally {
        
		        History history = new History(message);

            historyRepository.insertWebClientHistory(history);
            
        }

        return null;
    }

}
```

1. WebClientService 아래의 Method를 PointCut으로 설정하였다.
2. 해당 PointCut에 Advice의 동작시점을 Around로 지정한다. 
3. try 블록안에서 joinPoint.proceed()를 실행하여 WebClientResponseException 를 Catch.
4. Exception의 response를 message 변수에 저장하여, 사유를 기록한다. message의 기본값은 null
5. 그 외 Exception은 INTERNAL SERVER ERROR로 취급.
6. finally 블록에서 webClient의 성공 실패 여부와 관계 없이 History 객체 초기화 후 Insert 한다.

# 리뷰

이번 요구사항은 Spring의 핵심 기능 중 하나인 AOP를 활용하여 개발하였다. 

AOP는 공통으로 사용될 메소드를 간단하게 관리할 수 있어 생산성을 크게 증대시킬 수 있었다. 

그러나, 단점또한 존재하였는데, 먼저 PointCut이 될 Method에선 코드 수행 이후 AOP가 수행되는지 바로 알 수 없다. 

구체적인 주석을 통해 AOP 로직이 어떻게 동작할 지 작성할 필요가 있다. 

그리고, PointCut이 늘어날 수록 Debug하기 어렵다는 단점을 갖는다. 

이렇듯 장단점을 갖는다.

# Summary

1. AOP는 간편하게 공통 메소드를 정의하고 관리할 수 있다.
2. PointCut이 될 Method에는 어떠한 명시도 없기 때문에 Method 전후로 AOP가 동작하는 지 알 수 없다.
3. PointCut이 늘어날 수록 Debug하기 어렵다.
