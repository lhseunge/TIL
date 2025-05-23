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

![그림 5.7 둘 중 하나를 연관관계의 주인으로 선택해야 한다. ](attachment:be8f8deb-2309-4473-97a9-388cda020fc8:image.png)

그림 5.7 둘 중 하나를 연관관계의 주인으로 선택해야 한다. 

- 회원 → 팀(Member.team) 방향
    
    ```java
    class Member {
    		@ManyToOne
    		@JoinColumn(name = "TEAM_ID")
    		private Team team;
    		...
    }
    ```
    
- 팀 → 회원 (Team.members) 방향
    
    ```java
    class Team {
    		@OneToMany
    		private List<Member> members = new ArrayList<Member>();
    }
    ```
    

**연관관계의 주인을 정하는 것 → 외래 키 관리자를 선택하는 것**

그림 5.7의 회원 Entity에 있는 Member.team을 주인으로 선택하면 자기 테이블에 있는 외래 키를 관리하면 된다.

하지만 Team.members를 주인으로 선택하면 물리적으로 전혀 다른 테이블의 외래 키를 관리해야 한다.

→ Team.members가 있는 Team Entity는 Team 테이블에 매핑되어 있는데 관리해야할 외래 키는 MEMBER 테이블에 있기 때문이다.

### 연관관계의 주인은 외래 키가 있는 곳

- 연관관계의 주인은 외래 키가 있는 곳으로 정해야 한다.
    
    → 회원 테이블이 외래 키를 가지고 있으므로 Member.team이 주인이 된다. 
    
- 주인이 아닌 Team.members에는 mappedBy = “team” 속성을 사용해서 주인이 아님을 설정한다.
    
    → mappedBy 속성의 값으로 연관관계의 주인 team을 주면 된다. 
    
    → 여기서 team은 Member Entity의 team 필드를 말한다. 
    

![그림 5.8 연관관계의 주인과 반대편](attachment:4d7b6912-d811-483a-8345-32ebc3282707:image.png)

그림 5.8 연관관계의 주인과 반대편

summary) 
연관관계의 주인만 DB 연관관계와 매핑되고, 외래 키를 관리할 수 있다.

주인이 아닌 반대편은 읽기만 가능하고 외래 키를 변경하지는 못한다.

> DB 테이블 다대일, 일대다 관계에선 항사 ‘다’ 쪽이 외래 키를 가진다.
→ @ManyToOne은 항상 연관관계의 주인이 되므로 mappedBy를 설정할 수 없다. 
→ @ManyToOne에는 mappedBy가 없다.
> 

## 양방향 연관관계 저장

```java
public void testSave() {
		
		// 팀1 저장
		Team team1 = new Team("team1", "팀");
		em.persist(team1);

		// 회원1 저장
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1); // 연관관계 설정 member1 -> team1
		em.persist(member1);

		// 회원2 저장
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1); // 연관관계 설정 member2 -> team1
		em.persist(member2);

}
```

위 코드는 예제 5.6 단방향 연관관계의 회원, 팀 저장 코드와 같다. 

DB에서 회원 테이블을 조회하면 TEAM_ID 외래 키에 팀에 기본 키 값이 저장되어 있다. 

```java
team1.getMembers().add(member1); // 무시
```

(그래프 탐색을 위해?) 위와 같은 코드가 필요할 것 같지만 Team.members 는 연관관계의 주인이 아니다. 

→ 주인이 아닌 곳에 입력된 값은 외래 키에 영향을 주지 않는다. DB에 저장되지 않고 무시된다. 

```java
member1.setTeam(team1); // 연관관계 설정
```

Member.team은 연관관계의 주인이다. 

em은 이곳에 입력된 값을 사용해 외래 키를 관리한다.

## 양방향 연관관계의 주의점

가장 흔한 실수는 연관관계의 주인에는 값을 입력하지 않고, 주인이 아닌곳에만 값을 입력하는 것이다. 

→ DB에 외래 키 값이 정상적이지 않다면 이것부터 의심하는 것이 좋다.

연관관계의 주인만 외래 키 값을 변경 할 수 있다.

team.members에 값을 입력하고 persist()해도 해당하는 member.team_id는 null.

### 순수한 객체까지 고려한 양방향 연관관계

연관관계의 주인에만 값을 설정하는건 순수한 객체사태에서 심각한 문제가 발생할 수 있다. 

**→ 객체 관점에서 양쪽 방향 모두 값을 입력해주는 것이 가장 안전하다.** 

```java
public void test순수한객체_양방향() {
		// 팀1
		Team team1 = new Team("team1", "팀1");
		Member member1 = new Member("member1", "회원1");
		Member member2 = new Member("member2", "회원2");
		
		member1.setTeam(team1);
		member2.setTeam(team1);
		
		List<Member> members = team1.getMembers();
		
}
```

Member.team에만 연관관계를 설정하고 반대 방향은 설정하지 않았다. 

→ 당연히 team1에 소속된 회원은 나오지 않는다. 

→ 양항뱡 관계는 양쪽 다 관계를 설정해야 한다. 

```java
team1.getMembers().add(member1);
team1.getMembers().add(member2);
```

```java
public void test순수한객체_양방향() {
		// 팀1
		Team team1 = new Team("team1", "팀1");
		Member member1 = new Member("member1", "회원1");
		Member member2 = new Member("member2", "회원2");
		
		member1.setTeam(team1); // 연관관계 설정 member1 -> team1
		team1.getMembers().add(member1); // 연관관계 설정 team1 -> member1

		member2.setTeam(team1); // 연관관계 설정 member2 -> team1
		team1.getMembers().add(member2); // 연관관계 설정 team1 -> member2
		
		List<Member> members = team1.getMembers();
		
}
```

양쪽 방항 모두 관계를 설정하여 결과도 기대값 대로 출력된다.

객체까지 고려하면 이렇게 양쪽 다 관계를 맺어야 한다. 

```java
public void testORM_양방향() {
		// 팀1
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		
		Member member1 = new Member("member1", "회원1");
		
		member1.setTeam(team1); // 연관관계 설정 member1 -> team1
		team1.getMembers().add(member1); // 연관관계 설정 team1 -> member1

		em.persist(member1);

		Member member2 = new Member("member2", "회원2");

		member2.setTeam(team1); // 연관관계 설정 member2 -> team1
		team1.getMembers().add(member2); // 연관관계 설정 team1 -> member2
		
		em.persist(member2);
		
		List<Member> members = team1.getMembers();
		
}
```

양쪽에 연관관계를 설정

- 순수 객체 상태에서도 동작
- 테이블 외래 키도 정상 입력
    - 외래 키의 값은 연관관계의 주인 Member.team의 값을 사용한다.

- Member.team: 연관관계의 주인, 이 값을 통해 외래 키를 관리
- Team.members: 연관관계의 주인이 아님, 데이터 저장 시 사용하지 않는다.

### 연관관계 편의 메소드

양방향 연관관계는 결국 양쪽 다 신경써야 한다. 

```java
member.setTeam(team);
team.getMembers().add(team);
```

위 코드를 각각 호출하면 실수가 생길 수 있다. (인자를 잘못 집어 넣는 등)

양방향 관계에서 두 코드는 하나인 것처럼 사용하는 것이 안전하다. 

```java
public class Member {

		private Team team;
		
		public void setTeam(Team team) {
				this.team = team;
				team.getMembers().add(this);
		}
}
```

setTeam() 메소드로 양방향 관계를 모두 설정하도록 변경했다. 

기존 연관관계를 설정하는 코드는 아래처럼 수정된다. 

```java
		// 연관관계 설정 setTeam()만으로도 양방향 관계 설정 가능 
		member1.setTeam(team1); // 연관관계 설정 member1 -> team1
		member2.setTeam(team1); // 연관관계 설정 member2 -> team1
	
		// 기존 코드 삭제  
		// team1.getMembers().add(member1); // 연관관계 설정 team1 -> member1
		// team1.getMembers().add(member2); // 연관관계 설정 team1 -> member2
		
```

이렇게 리팩토링하면 실수를 줄이며 좀 더 그럴듯한 양방향 연관관계를 설정할 수 있다. 

```java
public void testORM_양방향_리팩토링() {
		
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1); // 양방향 연관관계 설정 member1 -> team1
		em.persist(member1);

		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1); // 양방향 연관관계 설정 member2 -> team1
		em.persist(member2);
		
}
```

이렇게 한 번에 양방향 관계를 설정하는 메소드를 **연관관계 편의 메소드**라고 한다.

### 연관관계 편의 메소드 작성 시 주의사항

사실 위 연관관계 편의 메소드엔 버그가 있다. 

```java
member1.setTeam(teamA); //1
member1.setTeam(teamB); //2
Member findMember = teamA.getMember(); // member1이 조회된다. 
```

![그림 5.9 삭제되지 않은 관계 1](attachment:b0e8e2f6-fe67-4d16-b993-e09f17646215:image.png)

그림 5.9 삭제되지 않은 관계 1

- member1.setTeam(teamA) 메소드가 호출된 직후의 그림

![그림 5.10 삭제되지 않은 관계 2](attachment:535dee04-852f-461b-9e7c-001379bb086b:image.png)

그림 5.10 삭제되지 않은 관계 2

- member1.setTeam(teamB) 메소드가 호출된 직후의 그림

teamB로 변경할 때 teamA → member1 관계를 제거하지 않았다. 

연관관계를 변경할 때는 기존 팀이 있으면 기존팀과 회원의 연관관계를 제거하도록 코드를 수정해야 한다. 

```java
public class Member {

		private Team team;
		
		public void setTeam(Team team) {
		
				// 기존 팀과 관계를 제거 
				if (this.team != null) {
						this.team.getMembers().remove(this);
				}
		
				this.team = team;
				team.getMembers().add(this);
		}
}
```

이 코드는 객체에서 단방향 연관관계 2개를 양방향인 것처럼 보이게 많은 수고가 필요하다는 것을 보여준다. 

(DB에서는 외래 키 하나로 문제를 단순하게 해결한다.)

**정리하자면, 객체에서 양방향 연관관계를 사용하려면 로직을 견고하게 작성해야 한다.** 

> 그림 5.10 에서 teamA → member1 관계가 제거되지 않아도 DB 외래 키를 변경하는 데는 문제가 없다. 
왜냐하면 teamA → member1 관계를 설정한 Team.members 는 연관관계의 주인이 아니기 때문이다. 
→ 연관관계의 주인인 Member.team의 참조를 teamB로 변경했으므로 DB에 외래 키는 teamB를 참조하도록 정상 반영된다. 

그리고 이후에 새로운 영속성 컨텍스트에서 teamA를 조회해서 getMembers()를 호출하면 DB 외래 키가 끊어져 있으므로 아무것도 조회되지 않는다. (여기까지 보면 문제가 없는 것으로 보인다.)

문제는 관계를 변경하고 영속성 컨텍스트가 살아있는 상태에서 teamA의 getMembers()를 호출하면 member1이 반환된다. (객체 입장에선 변경되지 않음)

따라서 변경된 연관관계는 앞서 설명한 것처럼 관계를 제거하는 것이 안전하다.
>

