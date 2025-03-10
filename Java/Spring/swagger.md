# Spring boot Swagger 적용 (springdoc)

# Swagger

RestAPI를 설계, 빌드, 문서화 하는데 사용되는 라이브러리

## Spring 프로젝트에 Swagger 적용하기

Spring 프로젝트에서 swagger를 사용하기 위한 라이브러리는 대표적으로 두가지가 있음.

1. springfox swagger
2. springdoc openAPI UI

### 두 라이브러리의 차이점은?

- Springfox는 해당 라이브러리의 선발주자로서, 서칭 시 데이터를 찾기 좋다는 장점이 있지만, 2020년을 기점으로 업데이트되고 있지 않다.
- Springdoc은 webflux라는 방식의 웹 개발을 지원하도록 개발되었다.
- Springdoc은 설정파일을 통해 그룹간 api 정렬이 가능하다.

## Dependency

```java
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4'
```

## Config

```java
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .version("v1.0.0")
                .title("title")
                .description("description");

        return new OpenAPI().info(info);
    }
}
```

---

[Swagger, 다 같은 놈이 아니었다. (Springfox vs Springdoc)](https://velog.io/@ychxexn/Swagger-다-같은-놈이-아니었다.-Springfox-vs-Springdoc)

[[Spring Boot] Springdoc 라이브러리를 통한 Swagger 적용](https://colabear754.tistory.com/99)
