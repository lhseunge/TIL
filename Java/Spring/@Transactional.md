# Spring :: @Transactional

## Spring에서 Transaction 적용

Spring에선 Annotation 방식의 Transaction 처리를 지원한다. 

`@Transactional`을 선언하여 사용한다. 

Class 또는 Method 위에 `@Transactional`을 붙이면, Transaction 기능이 적용된 Proxy 객체가 생성되며, Transaction 수행 여부에 따라 Commit 또는 Rollback 작업을 수행한다. 

### @Transactional의 동작 원리

`@Transactional`은 Spring AOP를 통해 구현되어있다. 

- Class 혹은 Method에 `@Transactional`이 선언되면, 해당 Class에 Transaction이 적용된 Proxy 객체 생성.
- Proxy 객체는 `@Transactional`이 포함된 Method가 호출되면, Transaction을 시작하고, Commit 또는 Rollback을 수행한다.
- CheckedException 또는 예외가 발생하지 않으면, **Commit**.
- UncheckException이 발생하면, **Rollback**.

### 주의점

1. **우선순위** 
    
    `@Transactional`은 우선순위를 가지고 있다. 
    
    <aside>
    
    Class Method → Class → Interface Method → Interface
    
    </aside>
    
    → 공통적인 Transaction 규칙은 Class, 예외적인 규칙은 Method에 선언하는 식으로 구성할 수 있다. 
    
    또한, Interface보다 Class에 사용하는 것을 권고 하고있다. 
    
    > **S**pring recommends that you only annotate concrete classes (and methods of concrete classes) with the [@Transactional](http://twitter.com/Transactional) annotation, as opposed to annotating interfaces. You certainly can place the [@Transactional](http://twitter.com/Transactional) annotation on an interface (or an interface method), but this works only as you would expect it to if you are using interface-based proxies. The fact that Java annotations are not inherited from interfaces means that if you are using class-based proxies (proxy-target-class=”true”) or the weaving-based aspect (mode=”aspectj”), then the transaction settings are not recognized by the proxying and weaving infrastructure, and the object will not be wrapped in a transactional proxy, which would be decidedly bad.
    
    > Spring은 인터페이스에 주석을 추가하는 대신 @Transactional 주석으로 구체적인 클래스 (및 구체적인 클래스의 메서드)에만 주석을 달도록 권장합니다. 인터페이스 (또는 인터페이스 메소드)에 @Transactional 어노테이션을 배치 할 수 있지만 이는 인터페이스 기반 프록시를 사용하는 경우 예상 한 대로만 작동합니다. Java 주석이 인터페이스에서 상속되지 않는다는 사실은 클래스 기반 프록시 (proxy-target-class = “true”) 또는 위빙 기반 측면 (mode = “aspectj”)을 사용하는 경우 트랜잭션 설정이 다음과 같음을 의미합니다. 프록시 및 위빙 인프라에 의해 인식되지 않으며 객체가 트랜잭션 프록시에 래핑되지 않으므로 확실히 나쁠 것입니다.
    - Interface나 Interface의 Method에 적용할 수 있지만, Interface 기반 Proxy에서만 유효한 Transaction 설정이 된다.
    - Java Annotation은 Interface로부터 상속되지 않기 때문에, Class 기반 Proxy 또는 AspectJ기반에서 Transaction 설정을 인식 할 수 없다.
    
2. **Transaction의 Mode**
    
    `@Transactional`은 2가지 Mode가 있다.
    
    1. Proxy Mode (Default)
    2. AspectJ Mode
    
    Proxy Mode는 다음과 같은 주의점이 있다. 
    
    - 반드시 public Method에 적용되어야 한다.
        - 다른 접근제한자를 가진 Method에 선언하더라도 동작하지 않는다.
        - Non-public Method에 적용하고 싶다면 AspectJ Mode도 고려해야 한다.
    - `@Transactional`이 적용되지 않은 public Method가 `@Transactional`이 적용된 Method를 호출할 경우,  동작하지 않는다.

### @Transactional 설정

| 속성 | 타입 | 설명 |
| --- | --- | --- |
| value | String | 사용할 Transaction 관리자  |
| propagation | enum: Propagation | 선택적 전파 설정 |
| isolation | enum: Isolation | 선택적 격리 설정 |
| readOnly | boolean | 읽기 & 쓰기 / 읽기 전용 Transaction |
| timeout | int (sec) | Transaction Timeout 설정 |
| rollbackFor | Throwable로 부터 얻을 수 있는 Class 객체 배열 | Rollback이 수행되어야하는, 선택적인 예외 Class의 배열 |
| rollbackForClassName | Throwable로 부터 얻을 수 있는 Class 이름 배열 | Rollback이 수행되어야하는, 선택적인 예외 Class 이름의 배열 |
| noRollbackFor | Throwable로 부터 얻을 수 있는 Class 객체 배열 | Rollback이 수행되지 않아야하는, 선택적인 예외 Class의 배열 |
| noRollbackForClassName | Throwable로 부터 얻을 수 있는 Class 이름 배열 | Rollback이 수행되지 않아야하는, 선택적인 예외 Class 이름의 배열 |

### 다중 Transaction Manager

필요에 따라 다수의 독립된 Transaction Manager를 사용할 수 있다. 

`@Transactional`의 value 속성에 사용할 PlatformTransactionManager를 지정하면 된다. 

<aside>

PlatformTransactionManager는 Spring이 제공하는 Transaction 관리 Interface이다. 

Interface의 구현체에는 JDBC, Hibernate 등이 있는데, 이는 Transaction이 실제 작동할 환경이다.

→ 반드시 알맞은 PlatformTransactionManager 구현체가 정의되어 있어야 한다. 

</aside>

다중 Transaction Manager는 value 속성에 Bean의 Id 또는 qualifier 값을 지정한다.

```java
public class TransactionService {

    @Transactional("transactionManagerA")
    public void getUser() { ... }
    
    @Transactional("transactionManagerB")
    public void addUser() { ... }
}
```

---

[[Spring] 트랜잭션과 @Transactional 정리](https://developer-nyong.tistory.com/16)

[[Spring] @Transactional의 이해](https://imiyoungman.tistory.com/9)

[10. Transaction Management](https://docs.spring.io/spring-framework/docs/3.0.x/spring-framework-reference/html/transaction.html)

[@Transactional 은 어느 Layer 에 두는게 맞을까?](https://medium.com/webeveloper/transactional-%EC%9D%80-%EC%96%B4%EB%8A%90-layer-%EC%97%90-%EB%91%90%EB%8A%94%EA%B2%8C-%EB%A7%9E%EC%9D%84%EA%B9%8C-807f50610f0b)
