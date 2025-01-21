Starters는 편리하게 사용하기 좋은 Dependency의 묶음이다. 

Dependency를 찾는 과정을 줄이고, Spring과 관련된 기술을 원스톱으로 이용할 수 있다.

예를 들어 Spring과 DB Access를 위해 JPA를 사용할 때, `spring-boot-starter-data-jpa` Dependency를 포함하면 된다.

**Table 1. Spring Boot application starters**

| 이름 | 설명 |
| --- | --- |
| `spring-boot-starter` | 자동 구성 지원, 로깅 및 YAML을 포함한 핵심 스타터 |
| `spring-boot-starter-activemq` | Apache ActiveMQ를 사용한 JMS 메시징을 위한 스타터 |
| `spring-boot-starter-amqp` | Spring AMQP 및 Rabbit MQ 사용을 위한 스타터 |
| `spring-boot-starter-aop` | Spring AOP 및 AspectJ를 사용한 관점 지향 프로그래밍을 위한 스타터 |
| `spring-boot-starter-artemis` | Apache Artemis를 사용한 JMS 메시징을 위한 스타터 |
| `spring-boot-starter-batch` | Spring Batch 사용을 위한 스타터 |
| `spring-boot-starter-cache` | Spring Framework의 캐싱 지원을 사용하기 위한 스타터 |
| `spring-boot-starter-data-cassandra` | Cassandra 분산 데이터베이스와 Spring Data Cassandra를 사용하기 위한 스타터 |
| `spring-boot-starter-data-cassandra-reactive` | Cassandra 분산 데이터베이스와 Spring Data Cassandra Reactive를 사용하기 위한 스타터 |
| `spring-boot-starter-data-couchbase` | Couchbase 문서 지향 데이터베이스와 Spring Data Couchbase를 사용하기 위한 스타터 |
| `spring-boot-starter-data-couchbase-reactive` | Couchbase 문서 지향 데이터베이스와 Spring Data Couchbase Reactive를 사용하기 위한 스타터 |
| `spring-boot-starter-data-elasticsearch` | Elasticsearch 검색 및 분석 엔진과 Spring Data Elasticsearch를 사용하기 위한 스타터 |
| `spring-boot-starter-data-jdbc` | Spring Data JDBC 사용을 위한 스타터 |
| `spring-boot-starter-data-jpa` | Hibernate와 함께 Spring Data JPA를 사용하기 위한 스타터 |
| `spring-boot-starter-data-ldap` | Spring Data LDAP 사용을 위한 스타터 |
| `spring-boot-starter-data-mongodb` | MongoDB 문서 지향 데이터베이스와 Spring Data MongoDB를 사용하기 위한 스타터 |
| `spring-boot-starter-data-mongodb-reactive` | MongoDB 문서 지향 데이터베이스와 Spring Data MongoDB Reactive를 사용하기 위한 스타터 |
| `spring-boot-starter-data-neo4j` | Neo4j 그래프 데이터베이스와 Spring Data Neo4j를 사용하기 위한 스타터 |
| `spring-boot-starter-data-r2dbc` | Spring Data R2DBC 사용을 위한 스타터 |
| `spring-boot-starter-data-redis` | Spring Data Redis 및 Lettuce 클라이언트와 함께 Redis 키-값 데이터 저장소를 사용하기 위한 스타터 |
| `spring-boot-starter-data-redis-reactive` | Spring Data Redis Reactive 및 Lettuce 클라이언트와 함께 Redis 키-값 데이터 저장소를 사용하기 위한 스타터 |
| `spring-boot-starter-data-rest` | Spring Data REST 및 Spring MVC를 사용하여 REST를 통해 Spring Data 저장소를 노출하기 위한 스타터 |
| `spring-boot-starter-freemarker` | FreeMarker 뷰를 사용하여 MVC 웹 애플리케이션을 구축하기 위한 스타터 |
| `spring-boot-starter-graphql` | Spring GraphQL을 사용하여 GraphQL 애플리케이션을 구축하기 위한 스타터 |
| `spring-boot-starter-groovy-templates` | Groovy Templates 뷰를 사용하여 MVC 웹 애플리케이션을 구축하기 위한 스타터 |
| `spring-boot-starter-hateoas` | Spring MVC 및 Spring HATEOAS를 사용하여 하이퍼미디어 기반 RESTful 웹 애플리케이션을 구축하기 위한 스타터 |
| `spring-boot-starter-integration` | Spring Integration을 사용하기 위한 스타터 |
| `spring-boot-starter-jdbc` | HikariCP 연결 풀과 함께 JDBC를 사용하기 위한 스타터 |
| `spring-boot-starter-jersey` | JAX-RS와 Jersey를 사용하여 RESTful 웹 애플리케이션을 구축하기 위한 스타터. 대안[`spring-boot-starter-web`](https://docs.spring.io/spring-boot/reference/using/build-systems.html#spring-boot-starter-web) |
| `spring-boot-starter-jooq` | JDBC를 사용하여 SQL 데이터베이스에 액세스하기 위해 jOOQ를 사용하기 위한 스타터. [`spring-boot-starter-data-jpa`](https://docs.spring.io/spring-boot/reference/using/build-systems.html#spring-boot-starter-data-jpa)또는[`spring-boot-starter-jdbc`](https://docs.spring.io/spring-boot/reference/using/build-systems.html#spring-boot-starter-jdbc) |
| `spring-boot-starter-json` | JSON 읽기 및 쓰기를 위한 스타터 |
| `spring-boot-starter-mail` | Java Mail 및 Spring Framework의 이메일 전송 지원을 사용하기 위한 스타터 |
| `spring-boot-starter-mustache` | Mustache 뷰를 사용하여 웹 애플리케이션을 구축하기 위한 스타터 |
| `spring-boot-starter-oauth2-authorization-server` | Spring Authorization Server 기능을 사용하기 위한 스타터 |
| `spring-boot-starter-oauth2-client` | Spring Security의 OAuth2/OpenID Connect 클라이언트 기능을 사용하기 위한 스타터 |
| `spring-boot-starter-oauth2-resource-server` | Spring Security의 OAuth2 리소스 서버 기능을 사용하기 위한 스타터 |
| `spring-boot-starter-pulsar` | Apache Pulsar에 Spring을 사용하기 위한 스타터 |
| `spring-boot-starter-pulsar-reactive` | Apache Pulsar Reactive에 Spring을 사용하기 위한 스타터 |
| `spring-boot-starter-quartz` | Quartz 스케줄러 사용을 위한 스타터 |
| `spring-boot-starter-rsocket` | RSocket 클라이언트 및 서버를 구축하기 위한 스타터 |
| `spring-boot-starter-security` | Spring Security 사용을 위한 스타터 |
| `spring-boot-starter-test` | JUnit Jupiter, Hamcrest 및 Mockito를 포함한 라이브러리를 사용하여 Spring Boot 애플리케이션을 테스트하기 위한 스타터 |
| `spring-boot-starter-thymeleaf` | Thymeleaf 뷰를 사용하여 MVC 웹 애플리케이션을 구축하기 위한 스타터 |
| `spring-boot-starter-validation` | Hibernate Validator와 함께 Java Bean Validation을 사용하기 위한 스타터 |
| `spring-boot-starter-web` | Spring MVC를 사용하여 RESTful을 포함한 웹 애플리케이션을 빌드하기 위한 스타터입니다. 기본 임베디드 컨테이너로 Tomcat을 사용합니다. |
| `spring-boot-starter-web-services` | Spring 웹 서비스 사용을 위한 스타터 |
| `spring-boot-starter-webflux` | Spring Framework의 Reactive Web 지원을 사용하여 WebFlux 애플리케이션을 구축하기 위한 스타터 |
| `spring-boot-starter-websocket` | Spring Framework의 MVC WebSocket 지원을 사용하여 WebSocket 애플리케이션을 구축하기 위한 스타터 |

애플리케이션 스타터 외에도 다음 스타터를 사용하여 [프로덕션에 적합한](https://docs.spring.io/spring-boot/how-to/actuator.html) 기능을 추가할 수 있습니다.

**Table 2. Spring Boot production starters**

| 이름 | 설명 |
| --- | --- |
| `spring-boot-starter-actuator` | 애플리케이션을 모니터링하고 관리하는 데 도움이 되는 프로덕션 준비 기능을 제공하는 Spring Boot의 Actuator를 사용하기 위한 스타터 |

마지막으로 Spring Boot에는 특정 기술적 측면을 제외하거나 바꾸고 싶을 때 사용할 수 있는 다음과 같은 스타터가 포함되어 있습니다.

**Table 3. Spring Boot technical starters**

| 이름 | 설명 |
| --- | --- |
| `spring-boot-starter-jetty` | Jetty를 내장된 서블릿 컨테이너로 사용하기 위한 스타터입니다. 대안[`spring-boot-starter-tomcat`](https://docs.spring.io/spring-boot/reference/using/build-systems.html#spring-boot-starter-tomcat) |
| `spring-boot-starter-log4j2` | 로깅을 위해 Log4j2를 사용하기 위한 스타터. 대안[`spring-boot-starter-logging`](https://docs.spring.io/spring-boot/reference/using/build-systems.html#spring-boot-starter-logging) |
| `spring-boot-starter-logging` | Logback을 사용하여 로깅을 위한 스타터. 기본 로깅 스타터 |
| `spring-boot-starter-reactor-netty` | Reactor Netty를 내장형 반응형 HTTP 서버로 사용하기 위한 스타터입니다. |
| `spring-boot-starter-tomcat` | Tomcat을 임베디드 서블릿 컨테이너로 사용하기 위한 스타터. 기본 서블릿 컨테이너 스타터는 다음과 같이 사용됩니다.[`spring-boot-starter-web`](https://docs.spring.io/spring-boot/reference/using/build-systems.html#spring-boot-starter-web) |
| `spring-boot-starter-undertow` | Undertow를 임베디드 서블릿 컨테이너로 사용하기 위한 스타터입니다. 대안[`spring-boot-starter-tomcat`](https://docs.spring.io/spring-boot/reference/using/build-systems.html#spring-boot-starter-tomcat) |
