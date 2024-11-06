# Spring :: PSA

# 서비스 추상화 (Service Abstraction)

서비스 추상화는 서비스에 필요한 부분의 정보만을 제공하고, 불필요한 정보는 포함하지 않는다는 설계 원칙이다. 

Spring에서 추상화를 이용하여 특정 기술의 구현부를 숨기고, 개발자들에게 필요한 부분만을 제공해 편의성을 제공해 주는 것을 서비스 추상화 라고 한다.

개발자가 JDBC를 사용하려고 할 때, JDBC의 구현부 모두를 알 필요는 없다. 

# PSA (Portable Service Abstraction)

서비스 추상화를 통해 개발자에서 제공되는 기술을 다른 기술로 간단하게 변경할 수 있게 해주는 추상화 구조.

개발자는 기술 스택이 변경 되더라도 코드의 변경을 신경쓰지 않게 해준다.

# Spring이 제공하는 PSA

## Spring MVC

Spring에선 `@Controller` 를 통해 Http 요청을 받는 컨트롤러를 만들 수 있다. 

원래라면 `HttpServlet` 클래스를 상속 받고, `doGet()` `doPost()` 등 메소드를 오버라이딩 해야한다. 

하지만 `@GetMapping` `@PostMapping` 등 어노테이션을 붙여 Http 요청을 받을 수 있다. 

Spring MVC에서 Spring Webflux로 의존성을 변경하면 Tomcat에서 netty 기반으로 바로 전환된다. 

## @Transactional

데이터 변경이 필요한 작업을 할 때 사용하면 Transaction, commit, rollback 등의 작업을 보다 쉽게 관리할 수 있다. 

개발자는 필요에 따라 다른 DB Interface를 사용하고 Transaction 관리를 위해 각각 다른 transactionManager를 사용해야 한다. 

@Transactional은 각각의 DB Interface 별 TransactionManager의 구현체를 선택하기 때문에 개발자는 JDBC를 사용하던 JPA를 사용하던 어노테이션만 붙이면 Transaction관리를 쉽게 할 수 있게된다. 

![TransactionManager.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/bddc17fc-f7bb-44c5-a042-3d5bdc56a0e7/TransactionManager.png)

TransactionManager는 PlatformTransactionManager의 하위로 다양하게 구현되어 있고 @Transactional은 이를 사용하기 때문에 @Transaction만 사용하는 것으로 Transaction 관리를 할 수 있다.

## Spring Cache

캐싱도 PSA가 사용된다. 

개발자는 사용할 캐시 라이브러리에 따라 CacheManager를 선택하고 @Cacheable 등 어노테이션을 사용하면 라이브러리 상관 없이 캐싱을 적용할 수 있다. 

---

[[Spring] 스프링 PSA](https://memodayoungee.tistory.com/137)

[[Spring] PSA란 무엇인가? (Portable Service Abstraction)](https://ittrue.tistory.com/214)

[[Spring] PSA(Portable Service Abstraction)란?](https://dev-coco.tistory.com/83)

[Spring PSA](https://velog.io/@bernard/Spring-PSA)

[[Spring] Spring의 핵심기술 PSA - 개념과 원리](https://sabarada.tistory.com/127)
