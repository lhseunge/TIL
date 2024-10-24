# Cache: Spring Cache 
# 스프링 프레임워크와 Cache 통합

스프링은 Cache 추상화를 제공해주고 있다. 

Spring Boot를 사용하여 구현체를 쉽게 사용할 수 있다. 

## Dependencies

### build.gradle

```java
implementation 'org.springframework.boot:spring-boot-starter-cache'// 스프링 application 내부 캐시
implementation 'org.springframework.boot:spring-boot-starter-data-redis'// redis
implementation 'com.github.ben-manes.caffeine:caffeine'// caffenie
```

# Cache 추상화 적용 방법 (@EnableCaching)

1. @Configuration 클래스에 @EnableCaching을 추가한다. 
2. CacheManager 인터페이스를 구현하는 Bean을 등록한다. 
- **스프링의 Cache 추상화 어노테이션들은 CacheManager를 통해 Cache를 사용한다.**

### 스프링이 제공하는 CacheManager

| ConcurrentMapCacheManager | 간단하게 ConcurrentHashMap을 사용하는 CocurrentMapCache 를 사용한다.  |
| --- | --- |
| SimpleCacheManager | 프로퍼티를 이용해서 사용할 캐시를 직접 등록해주는 방법(기본적으로 제공하는 캐시가 없다)
이는 스프링 Cache 인터페이스를 구현해서 캐시 클래스를 직접 만드는 경우 테스트에서 사용하기에 적당하다. |
| CompositeCacheManager | 하나 이상의 캐시 매니저를 사용하도록 지원해주는 혼합 캐시 매니저다.
여러 캐시 저장소를 동시에 사용해야 할 때 쓴다. cacheManagers 프로퍼티에 적용할 캐시 매니저 빈을 모두 등록해주면 된다. |
| NoOpCacheManager | 캐시를 사용하지 않는다. 캐시가 지원되지 않는 환경에서 동작할 때 캐시 관련 설정을 제거하지 않아도 에러가 나지 않게 해준다. |
| JCacheCacheManager | 자바 표준 JSR107 기반으로 동작하는 구현체를 위한 캐시매니저 |
| RedisCacheManager | Redis 지원하는 캐시매니저 |
| EhCacheCacheManager | EhCache 지원하는 캐시매니저 |
| CaffeineCacheManager | Caffeine 지원하는 캐시매니저 |

# Cache 사용방법

스프링 캐시는 기본적으로 **메소드 단위의 AOP**로 구현되어 있다.

메소드에 아래 어노테이션들을 사용하여 메소드 반환값을 캐싱할 수 있다. 

캐싱된 경우 메소드는 아예 실행되지 않는다는 점을 유의하자.

캐시 어노테이션의 옵션은 SpEL 문법이 사용된다.

## Summary

메소드의 파라미터가 Key

메소드의 리턴 값이 Value

# Cache Annotation

## @Cacheable

캐시에 데이터가 없을 때, 기존 로직을 수행하고 값을 캐싱한다. 

캐시에 데이터가 있다면, 캐시의 데이터를 반환한다. 

```java
@Cacheable(value = "member")
Public Member getMember(int memberId) {
		
}
```

### options

- **key**
    - 캐싱을 수행하려는 메소드에 파라미터가 여러개일 때 특정 키 값을 사용하고 싶을 때 지정할 수 있다.
        
        ```java
        @Cacheable(value = "member" key = "#memberQuery.memberId")
        Public Member getMember(MemberQuery memberQuery) {
        		
        }
        ```
        

- condition
    - 파라미터의 특정 조건일 경우에만 캐싱을 하고 싶다면 조건을 지정할 수 있다.
        
        ```java
        @Cacheable(value = "member" 
        		key = "#memberQuery.memberId" 
        		condition = "#memberQuery.isAudmin == 'true'")
        Public Member getMember(MemberQuery memberQuery) {
        		
        }
        ```
        

## @CachePut

메소드의 결과를 캐시에 저장한다.

실행 결과를 캐시에 데이터는 저장하지만, 캐시 데이터를 반환하지 않고, 기존 로직을 수행한다는 점이 @Cacheable과 다르다. 

## @CacheEvict

캐시의 삭제를 담당한다. 

기본적으로 key에 해당하는 캐시를 삭제하지만 옵션 에 따라 전체 캐시 데이터를 삭제할 수 있다.

또, 해당 메소드의 실행 이후 캐시가 삭제 되지만 옵션에 따라 캐시를 삭제하고 메소드 로직을 수행 한다.

### options

- allEntries (default = false)
    - 전체 캐시 데이터를 삭제할 것인지 지정할 수 있다.
    - 기본적으로 key에 해당하는 캐시 데이터만 삭제한다.
        
        ```java
        @CacheEvict(value = "member", allEntries = true)
        Public Member getMember(MemberQuery memberQuery) {
        		
        }
        ```
        
    
- beforeInvocation (default = false)
    - 메소드 실행 전 후로 캐시를 삭제할 지 지정할 수 있다.
    - 기본적으로 메소드 로직 수행 후. 캐시 데이터를 삭제한다.
        
        ```java
        @CacheEvict(value = "member", beforeInvocation = true)
        Public Member getMember(MemberQuery memberQuery) {
        		
        }
        ```
