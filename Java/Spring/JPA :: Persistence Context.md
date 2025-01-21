# 영속성 컨텍스트란?

> JPA에서 가장 중요한 개념
> 

## **영속성 컨텍스트(Persistence Context)**

**Entity**를 영구히 저장하는 환경이라는 뜻

- 눈에 보이지 않는 논리적 개념
- Application과 DB 사이 객체를 보관하는 가상의 저장소
- EntityManager를 통해 Entity를 영속성 컨텍스트에 보관, 관리

## Entity Manager

Entity Manager를 통해 영속성 컨텍스트에 접근하고 관리.

- Entity Manager를 생성하면 그 안에 영속성 컨텍스트가 있음.
    - Entity Manager Factory를 통해 요청이 올 때 마다 Entity Manager 생성
    - Entity Manager는 내부적으로 Connection 사용하여 DB 접근
        
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
        

# Entity의 생명주기

> Entity는 Entity Manager를 통해 영속성 컨텍스트에 보관, 관리, 제거된다.
> 

## Entity Manager 생성

- `EntityManagerFactiry`
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
