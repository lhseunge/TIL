# 연관관계 매핑

- Entity들은 대부분 다른 Entity들과 관계를 갖는다.
    - 객체는 주소값을 사용해서 관계를 맺고,
    - 테이블은 외래 키를 사용해서 관계를 맺는다.
    - 이 둘은 완전히 다른 특징을 갖는다.
    - **ORM에서 가장 어려운 부분은 객체 연관관계와 테이블 연관관계를 매핑하는 것이다.**

## 연관관계 매핑의 핵심

**회원과 팀 관계일 때**

- 방향 (direction) : [단방향, 양방향]
    - 단방향 : 회원 → 팀 or 팀 → 회원
    - 양방향 : 회원 ↔ 팀
- 다중성 (multiplicity) : [다대일 (n:1), 일대다 (1:n), 일대일 (1:1), 다대다 (n:m)]
    - 여러 회원이 한 팀에 소속된다.
    - 회원과 팀 관계는 다대일 (n:1) 관계.
    - 팀과 회원 관계는 일대다 (1:n) 관계.
- 연관관계 주인 (owner)
    - 객체를 양방향 연관관계로 만들면 연관관계의 주인을 정해야 한다.

## 단방향 연관관계

연관관계 중에선 다대일 단방향 관계를 가장 먼저 이해해야한다.

- 회원은 하나의 팀에만 소속될 수 있다.
- 회원과 팀은 다대일 관계이다.

![대대일 연관 관계 | 다대일 (N : 1), 단방향](attachment:961da0e7-9ea6-44a7-b43c-ecddf5dcc34b:image.png)

대대일 연관 관계 | 다대일 (N : 1), 단방향

- 객체 연관관계
    - 회원 객체는 Member.team 필드로 팀 객체와 연관관계를 맺는다.
    - 회원 객체와 팀 객체는 **단방향 관계**이다.
        - 회원은 Member.team 필드를 통해서 팀을 알 수 있지만 반대로 팀은 회원을 알 수 없다.
        - member.getTeam()을 통해 Team 조회 가능.
        - team은 member를 알 수 없다.
- 테이블 연관관계
    - 회원 테이블 TEAM_ID 외래 키로 테이블과 연관관계를 맺는다.
    - 회원 테이블과 팀 테이블은 **양방향 관계**이다.
        - 회원 테이블의 TEAM_ID 외래 키를 통해서 회원과 팀을 join할 수 있고, 반대도 가능하다.
        - 회원 테이블의 TEAM_ID 키로 MEMBER JOIN TEAM, TEAM JOIN MEMBER 둘 다 가능하다.
- 객체 연관관계와 테이블 연관관계의 가장 큰 차이
    - 참조를 통한 연관관계는 언제나 단방향 관계이다.
    - 객체간 연관관계를 양방향으로 만들려면 반대쪽에도 필드를 추가해서 참조를 보관해야한다.
        - A에서 B를 단방향 참조 하던 것을 B에서 A를 참조하도록 추가한다.
        - 이렇게 서로 참조하는 것을 양방향 참조라 한다.
        - **정확히 말하자면 양방향이 아니라 서로 다른 단방향 연관관계 2개 이다.**

### 객체 관계 매핑

<aside>

JPA를 사용해서 연관관계를 매핑하는 방법

</aside>

![그림 5.4 다대일 연관관계1 | 다대일(N:1), 단방향](attachment:810979e4-c822-4a41-8284-2cf7c987b060:image.png)

그림 5.4 다대일 연관관계1 | 다대일(N:1), 단방향

```java
@Entity
public class Member {

		@Id
		@Column(name = "MEMBER_ID")
		private String id;
		
		private String username;
		
		// 연관관계 매핑
		@ManyToOne
		@JoinColumn(name = "TEAM_ID")
		private Team team;
		
		...
		
}

@Entity
public class Team {

		@Id
		@Column(name = "TEAM_ID")
		private String id;
		
		private String name;
		
		...
		
}
```

- 예제 5.4에서 회원 Entity를 매핑하고, 예제 5.5에서 팀 Entity를 매핑했다.
    - **객체 연관관계** : 회원 객체의 Member.team 필드 사용
    - **테이블 연관관계** : 회원 테이블의 MEMBER.TEAM_ID 외래 키 컬럼을 사용
- **Member.team과 MEMBER.TEAM_ID를 매핑하는 것이 연관관계 매핑이다.**
- 연관관계 매핑 코드
    
    ```java
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
    ```
    
- 연관관계를 매핑하기 위한 어노테이션
    - @ManyToOne :
        - 다대일 관계 매핑을 나타낸다.
        - 연관관계 매핑 시 이렇게 다중성을 나타내는 어노테이션을 **필수로** 사용해야 한다.
    - @JoinColumn :
        - 외래 키를 매핑할 때 사용한다.
        - name 속성에는 매핑할 외래 키 이름을 지정한다.
        - 회원과 팀 테이블은 TEAM_ID 외래 키로 연관관계를 맺으므로 이 값을 지정하면 된다.
        - 이 어노테이션은 생략할 수 있다.

### @JoinColumn 속성

| 속성 | 가능 | 기본값 |
| --- | --- | --- |
| name | 매핑할 외래 키 이름 | 필드명_참조 테이블의 기본 키 컬럼명 |
| referenceColumnName | 외래 키가 참조하는 대상 테이블의 컬럼명 | 참조하는 테이블의 기본 키 컬럼명 |
| foreignKey(DDL) | 외래 키 제약조건을 직접 지정할 수 있다.
이 속성은 테이블을 생성할 때만 사용한다. |  |
| unique
nullable
insertable
updatable
columnDefinition
table | @Column의 속성과 같다.  |  |

> @JoinColumn을 생략하면 외래키를 찾을 때 기본 전략을 사용한다. 

@ManyToOne
private Team team

기본 전략 : 필드명_참조하는 테이블의 컬럼명 (TEAM_ID)
ex) team_TEAM_ID 라는 외래키를 사용한다.
> 

### @ManyToOne 속성

| 속성 | 기능 | 기본값 |
| --- | --- | --- |
| optional | false로 설정하면 연관된 Entity가 항상 있어야 한다.  |  |
| fetch | 글로벌 페치 전략을 사용한다.  | @ManyToOne=FetchType.EAGER
@OneToMany=FetchType.LAZY |
| cascade | 영속성 전이 기능을 사용한다.  |  |
| targetEntity | 연관된 Entity의 타입 정보를 설정한다. 
이 기능은 거의 사용하지 않는다. 
컬렉션을 사용해도 제네릭으로 타입 정보를 알 수 있다. |  |

> 다대일(@ManyToOne)과 비슷한 일대일(@OneToOne) 관계도 있다. 
단방향 관계를 매핑할 때 둘 중 어떤걸 사용할지는 반대편 관계에 달려있다.
한쪽이 일대다 관계일 때, 다대일 관계 사용.
한쪽이 일대일 관계일 때, 일대일 관계 사용
> 

## 연관관계 사용

### 저장

```java
// 팀1 저장
Team team1 = new Team("team1", "팀");
em.persist(team1);

// 회원1 저장
Member member1 = new Member("member1", "회원1");
member1.setTeam(team1); // 연관관계 설정 member1 -> team1

// 회원2 저장
Member member2 = new Member("member2", "회원2");
member2.setTeam(team1); // 연관관계 설정 member2 -> team1
```

> JPA에서 모든 Entity를 저장할 때 연관된 모든 Entity는 영속상태여야 한다.
> 

### 조회

연관관계가 있는 Entity를 조회하는 방법

1. 객체 그래프 탐색 (객체 연관관계를 사용한 조회) 
    1. member.getTeam() 메소드를 통해 member와 연관된 Team Entity를 조회할 수 있다.
2. 객체지향 쿼리 사용 (JPQL)

```java
private static void queryLogicJoin(EntityManager em) {
		
		String jpql = "select m from Member m join m.team t where t.name = :teamName";
		
		List<Member> resultList = em.createQuery(jpql, Member.class)
				.setParameter("teamName", "팀1")
				.getResultList();
				
		for (Member member : resultList) {
				log.info(member.getUsername());
		}
}
```

- :teamName 처럼 ‘:’ 로 시작하는 것은 파라미터를 바인딩받는 문법.

### 수정

```java
private static void updateRelation(EntityManager em) {
		
		//새로운 팀2
		Team team2 = new Team("team2", "팀2");
		em.persist(team2);
		
		//회원1에 새로운 팀2 설정
		Member member - em.find(Member.class, "member1");
		member.setTeam(team2);
		
}
```

- EntityManager는 update() 메소드가 없다.
- 조회한 Entity의 값만 변경해두면 트랜잭션이 커밋될 때 변경 감지 기능을 통해 DB에 반영한다.

### 연관관계 제거

```java
private static void deleteRelation(EntityManager em) {
		
		Member member - em.find(Member.class, "member1");
		member.setTeam(null);
		
}
```

### 연관된 Entity 삭제

```java
member1.setTeam(null); // 회원1의 연관관계 제거
member2.setTeam(null); // 회원2의 연관관계 제거 
em.remove(team); // 팀 삭제 
```

- 연관된 Entity를 삭제하려면, 기존에 있던 연관관계를 먼저 제거하고 삭제해야 한다.
- 그렇지 않으면 외래 키 제약조건으로 인해, DB에서 오류가 발생. (a foreign key constraint fails)

## 양방향 연관관계

팀에서 회원으로 접근하는 관계의 추가. 

회원 ↔ 팀 으로 접근하는 양방향 연관관계 매핑 

![5.5 양방향 객체 연관관계 ](attachment:8d29c76b-0d49-478e-a25f-2583022d81d3:image.png)

5.5 양방향 객체 연관관계 

- 일대다 관계는 여러건과 연관관계를 맺을 수 있으므로 Collection을 사용해야 한다.
- Team.members를 List Collection으로 추가
- 회원과 팀 ← 다대일 관계 (Member.team)
- 팀과 회원 ← 일대다 관계 (Team.members)

> JPA는 List를 포함하여 set, map 등 다양한 Collection을 지원한다.
> 

DB 테이블에선 외래 키 하나로 양방향으로 조회할 수 있다. 

→ 처음부터 양방향 관계이다. 

### 양방향 연관관계 매핑

```java
@Entity
public class Member {
		
		@Id
		@Column(name = "MEMBER_ID")
		private String id;
		
		private String username;
		
		@ManyToOne
		@JoinColumn(name = "TEAM_ID")
		private Team team;
		
}
```

회원 Entity는 변화가 없다. 

```java
@Entity
public class Team {
		
		@Id
		@Column(name = "TEAM_ID")
		private String id;
		
		private String name;
		
		// 추가
		@OneToMany(mappedBy = "team")
		private List<Member> members = new ArrayList<Member>();
		
}
```

- 팀과 회원은 일대다 관계이다.
- 따라서 List<Member> members를 추가.
- 그리고, 일대다 관계 매핑을 위해 @OneToMany 매핑 어노테이션을 사용
    - mappedBy 속성은 양방향 매핑일 때 사용한다.
        - 반대쪽 매핑의 필드 이름을 값으로 주면 된다.
        - 반대쪽 매핑이 Member.team 이므로 “team”을 값으로 주었다.

### 일대다 컬렉션 조회

```java
public void biDirection() {
		
		Team team = em.find(Team.class, "team1");
		List<Member> member = team.getMembers();

}
```

- 그래프 탐색을 통해 팀1에 속한 회원을 찾을 수 있다.

## 연관관계의 주인

@OneToMany의 mappedBy는 왜 필요한가??

엔티티를 양방향 연관관계로 설정

→ 객체의 참조는 둘인데, 외래 키는 하나다. (객체의 연관관계는 단방향 2개를 각각 설정)

JPA는 두 객체 연관관계 중 하나를 정해서 테이블 외래 키를 관리해야 하는데 이것을 **연관관계의 주인**이라고 한다.

### 양방향 매핑의 규칙 : 연관관계의 주인

**양방향 연관관계 매핑 시엔 두 연관관계 중 하나를 주인으로 정해야 한다.**

- 연관관계의 주인만이 DB 연관관계와 매핑되고, 외래 키를 관리 (등록, 수정, 삭제) 할 수 있다.
- 반면 주인이 아닌쪽은 읽기만 가능하다.

어떤 연관관계를 주인으로 정할지는 `mappedBy` 속성을 사용한다. 

- 주인은 mappedBy를 사용하지 않는다.
- 주인이 아니면 mappedBy를 사용해서 속성의 값으로 연관관계의 주인을 지정해야 한다.

그렇다면 Member.Team, Team.members 둘 중 어떤 것을 연관관계의 주인으로 정해야 할까?
