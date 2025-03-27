# 영속성 컨텍스트란?

> JPA에서 가장 중요한 개념
> 

## **영속성 컨텍스트(Persistence Context)**

**Entity**를 영구히 저장하는 환경이라는 뜻

- 눈에 보이지 않는 논리적 개념
- Application과 DB 사이 객체를 보관하는 가상의 저장소
- EntityManager를 통해 Entity를 영속성 컨텍스트에 보관, 관리

## EntityManager

EntityManager를 통해 영속성 컨텍스트에 접근하고 관리.

- EntityManager를 생성하면 그 안에 영속성 컨텍스트가 있음.
- EntityManager는 내부적으로 Connection 사용하여 DB 접근
    
    ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/0f9a14ef-2942-437b-b5f6-d3734cec94d1/image.png)
    
- Entity 저장:
    
    ```java
    EntityManager.persist(entity)
    ```
    
    - Data를 영속성 컨텍스트에 저장
    - Entity를 영속화 하겠다는 의미.
    - **DB에 저장하는 것과는 다른 개념이다.**
- Spring에서 Entity Manager 여러 개, 영속성 컨텍스트 1개 존재
    - Entity Manager: 영속성 컨텍스트 =  N : 1
        
        ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/6cf9ff6d-8c98-4f44-aed9-60d71fe64660/image.png)
        

## EntityManagerFactory

- DB를 하나만 사용하는 APP은 일반적으로 하나의 EntityManagerFactory만 사용한다.
- EntityManager가 필요할 때 마다 EntityManagerFactory에서 EntityManager를 생성하여 사용한다.
- EntityManagerFactory를 생성하는 비용은 상당히 크다.
- 하지만 EntityManager를 생성하는 비용은 거의 들지 않는다.
- App 전체에 한 개의 EntityManagerFactory를 만들어서 사용하도록 설계되어 있다.

## Multi Thread 이슈

- EntityManagerFactory는 여러 Thread가 동시에 접근해도 안전하다.
- EntityManager는 여러 Thread가 접근 시 동시성 문재가 발생한다.
- **EntityManager는 절대 Thread간 공유를 해선 안된다.**
    - @PersistantContext Annotation을 사용하여 Thread간 공유를 막을 수 있다.

# Entity의 생명주기

> Entity는 EntityManager를 통해 영속성 컨텍스트에 보관, 관리, 제거된다.
> 

## EntityManager 생성

- `EntityManagerFactory`
    - 설정 파일(ex. Persistence.xml)에서 작성한 persistence-unit(DB 설정 정보)로 생성.
- `Entity Manager`
    - 위에서 생성한 `EntityManagerFactory` 에서 생성 가능

```java
// hello는 psersistence-unit 이름
EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

// 이를 통해 엔티티를 영속성 컨텍스트에 관리 가능
EntityManager em = emf.createEntityManager();
```

## Entity의 생명주기

### 비영속 (new)

```java
Member member = new Member(); // 엔티티(객체) 생성
```

- 객체 생성.
- Entity가 영속성 컨텍스트에 없는 상태.

### 영속 (managed)

```java
em.persist(member)
```

- Entity를 영속성 컨텍스트에 저장.
- **즉, 영속성 컨텍스트가 Entity를 관리**

### 준영속 (detached)

```java
em.detach(member)
```

- Entity가 영속성 컨텍스트에 저장되었다가 분리된 상태
    - Entity가 영속 상태에서 준영속 상태로 간 상태
- 영속성 컨텍스트가 Entity를 관리하지 않음
    - 영속성 컨텍스트가 제공하는 기능은 사용하지 못함.
- 준영속 상태로 만드는 방법
    
    ```java
    // 특정 Entity만 준영속 상태로 변환
    em.detach(entity);
    
    // 영속성 컨텍스트 초기화 
    em.clear();
    
    // 영속성 컨텍스트 종료 
    em.close();
    ```
    

### 삭제 (removed)

```java
em.remove(member);
```

- 영속성 컨텍스트에 있는 Entity를 삭제
- 이 때 DB에서도 삭제
    - Transaction이 끝나거나, EntityManager가 Flush되면 삭제된다.

## 영속성 컨텍스트의 장점

- 1차 캐시 사용 가능
- 동일성 보장
- Transaction을 지원하는 쓰기 지연
- 변경 감지
- 지연 로딩

## CRUD - Work flow

### 조회

- 영속성 컨텍스트는 내부에 1차 캐시를 갖고있다.
- 영속상태의 Entity는 모두 1차 캐시에 저장된다.

| 1차 캐시  | Map |
| --- | --- |
| @Id | Key |
| Entity Instant | Value |
- EntityManager.persist(Entity)는 1차 캐시에 데이터를 저장하고, 아직 DB에는 반영되지 않는다.
- 1차 캐시의 Key는 DB의 PK와 매핑된다.

```java
EntityManager.find(Member.class, “memberId”);
```

- 위 명령은 1차 캐시에서 식별자로 Entity를 찾고, 해당하는 데이터가 없으면 DB에서 조회한다.
- DB에서 조회된 데이터는 1차 캐시에 저장된다. (영속화)

```java
Member memberA = EntityManager.find(Member.class, “memberId”);
Member memberB = EntityManager.find(Member.class, “memberId”);

memberA == memberB -> true 
```

- 같은 식별자를 통해 두 개의 Entity를 초기화한다면, 1차 캐시의 데이터(Entity Instant)를 반환하여 두 Entity 객체는 동일성이 보장된다.

### 저장

- EntityManager가 persist()를 수행하면, 1차 캐시에 Entity를 저장과 동시에 EntityManager 내부의 쓰기 지연 SQL 저장소에 Insert SQL을 모아둔다.
- 이후 EntityManager가 Commit()을 수행하면 내부의 쓰기 지연 SQL 저장소의 SQL들을 DB로 Flush 한다.
- **이를 Transaction을 지원하는 쓰기 지연(transactional write-behind)라 한다.**

```java
em.transaction.commit() -> 쓰기 지연 SQL 저장소의 SQLs을 DB로 Flush-> DB.commit
```

### 수정

- JPA는 Entity를 수정할 때, Entity를 조회하고, 데이터를 변경하기만 하면 된다.

```java
Member memberA = em.find(Member.class, "memberA");

memberA.setUsername("modifyName");

// em.update(member); <- EntityManager는 update()라는 메소드를 갖고있지 않다. 
```

- Entity 수정을 위해 update()같은 메소드를 사용해야 할 것 같지만, 그런 메소드는 갖고있지 않다.
- Entity 객체의 데이터를 수정하고 EntityManager가 flush() 메소드를 수행하면 자동으로 update된다.
- **이를 변경 감지(dirty checking)라 한다.**

- 변경 감지는 1차 캐시의 스냅샷을 통해 이루어진다.
- Entity의 데이터를 변경 후 EntityManager.flush() 하게되면, Entity와 스냅샷을 비교하여 1차 캐시의 변경된 Entity를 찾는다.
- 변경된 Entity가 있으면, Update SQL을 쓰기 지연 SQL 저장소에 보낸다.
- 쓰기 지연 SQL 저장소의 SQL을 DB에 반영 이후 DB Transaction을 Commit 한다.

- 이렇게 변경 감지를 통해 실행된 Update SQL은 수정된 데이터만 update 하지 않는다.
- **JPA에서의 수정은 Entity의 모든 필드를 Update하고 이것이 기본 전략이다.**
- 이런 방식은 DB로 전달하는 데이터의 전송량이 증가하는 단점을 갖지만, 다음과 같은 장점을 갖는다.
    - 모든 필드를 수정하는 Update SQL은 항상 동일하다. (바인딩되는 데이터는 다름)
        - Application이 로딩되는 시점에 Update SQL을 미리 생성하고, 재사용할 수 있다.
    - DB에 동일한 SQL을 보내면 DB는 이전에 파싱해둔 쿼리를 재사용 할 수 있다.
- 필드가 많거나, 저장되는 데이터가 너무 크다면 수정된 데이터만으로 동적 Update SQL을 생성하는 전략을 선택하면 된다.
- 이 방법은 Hibernate의 확장 기능을 사용해야 한다.

```java
@Entity
@Table(name = "Member")
@org.hibernate.annotations.DynamicUpdate
public class Member { ... }
```

- Hibernate의 DynamicUpdate 어노테이션을 사용하면 변경된 데이터만 수정하는 동적 Update SQL을 생성한다.
    - 저장의 경우에도 존재하는 데이터만(null이 아닌) 필드만 SQL에 반영하는 @DynamicInsert 어노테이션도 있다.

> 참고 
상황에 따라 다르지만 Column이 30개 이상이 되면 @DynamicUpdate를 통한 수정이 더 빠르다고 한다. 
정확한 성능은 본인의 환경에서 직접 테스트를 해보는 것이다. 
권장하는 방법은 기본전략을 사용하되, 최적화가 필요할 정도로 느려지면 그 때 전략을 수정하는 것이다. 
* 한 테이블에 컬럼이 30개 이상이 된다는 것은 설계상 책임이 적절하게 분배되지 않았다고 볼 수 있다.
> 

### 삭제

```java
Memeber memberA = em.find(Member.class, "memberA");

em.remove(memberA);
```

- em.remove()에 삭제할 Entity를 넘겨주면 Entity를 삭제한다.
- 물론 Entity를 즉시 DB에서 삭제하지 않는다.
- 쓰기 지연 SQL 저장소에 Delete SQL을 등록하고,
- Transaction을 commit()하고 flush()를 호출하면 DB에 Delete SQL을 전달한다.
- em.remove()를 수행하는 순간 해당 Entity는 영속성 컨텍스트에서 제거된다.
- 이렇게 제거된 Entity는 재사용하지 않는 것이 권장된다.
