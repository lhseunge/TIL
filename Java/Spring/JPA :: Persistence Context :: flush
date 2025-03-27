# 개요

<aside>

**flush()는 영속성 컨텍스트의 변경 내용을 DB에 반영한다.** 

</aside>

# flush()의 기능

1. 변경 감지를 동작하여 영속성 컨텍스트의 모든 Entity를 스냅샷과 비교하여 수정된 Entity를 찾는다. 
    1. 수정된 Entity를 바탕으로 Update SQL을 만들어 쓰기 지연 SQL 저장소에 등록한다. 
2. 쓰기 지연 SQL 저장소에 등록된 쿼리를 DB에 전송한다. (등록, 수정, 삭제)

## 영속성 컨텍스트를 flush 하는 방법

### 1. flush 직접 호출

```java
em.flush();
```

- em.flush() 메소드를 직접 호출해서 영속성 컨텍스트를 강제로 flush 한다.
- 테스트를 수행하거나 Jpa와 다른 프레임워크를 같이 사용하는 케이스 외에는 거의 사용하지 않는다.

### 2. transaction commit시 flush 자동 호출

- Entity 변경 사항을 DB에 전달하지 않고, transaction만 commit하면 변경 사항이 DB에 반영되지 않는다.
    - transaction을 Commit하기 전에 꼭 flush해서 영속성 컨텍스트의 변경 사항을 DB에 반영해야 한다.
- JPA는 이런 문제를 예방하기 위해 transaction을 commit할 때 flush를 자동으로 호출한다.

### 3. 객체지향 쿼리 실행 시 flush 자동 호출

- JPQL이나 Criteria 등 객체지향 쿼리 실행 시 flush가 자동으로 호출된다.

```java
em.persist(memberA);
em.persist(memberB);
em.persist(memberC);

quest = em.createQuery("select m from Member m", Member.class);
List<Member> members = query.getResultList();
```

- memberA, memberB, memberC 들을 영속 상태로 만들어도 이 Entity들은 아직 DB에 반영되지 않았다.
- 이 때 JPQL을 실행하면 SQL로 변환하여 DB로 전달하지만 persist()한 Entity는 flush 되지 않아서 조회 결과에 포함되지 않는다.
- JPA는 이런 문제를 예방하기 위해 JPQL을 실행할 때도 flush를 자동으로 호출한다.
    - 따라서 위의 케이스에서 memberA, memberB, memberC도 조회 결과에 포함된다.

## flush Mode

- EntityManager에 flush모드를 직접 지정하려면 javax.persistence.FlushModeType을 사용하면 된다.
    - FlushModeType.AUTO : commit이나 query를 실행할 때 flush (기본값)
    - FlushModeType.COMMIT : commit할 때만 flush

## 주의점

- flush는 영속성 컨텍스트의 Entity를 지우는 명령이 아니다.
- flush는 영속성 컨텍스트의 변경 내용을 DB에 동기화 하는 것이 flush이다.
