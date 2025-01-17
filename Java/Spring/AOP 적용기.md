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

        return acraWebClient.method(HttpMethod.GET)
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
    
    ... 
}
```

# 요구사항

webClient Method가 호출될 때 DB에 log를 적재 해야한다. 

# 해결

먼저 고려한 방법은 DB Connection을 담당하는 Class를 주입받거나 private Method를 선언해 WebClient 구현체에 포함하려고 하였다. 

그러나, WebClientInterface의 SRP 원칙이 깨지는 것을 방지하고 싶었고 이 방법 택하지 않았다. 

다음으로는 WebClientInterface의 Method를 AOP JoinPoint로 등록하고, AOP 내부에서 처리하고자 하였다.
