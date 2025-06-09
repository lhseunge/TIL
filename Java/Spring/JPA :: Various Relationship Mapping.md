# 다양한 연관관계 매핑

## 다대일

다대일의 반대는 항상 일대다 관계이다. (일대다의 반대도 항상 다대일)

DB 테이블의 일, 다 관계엣 외래 키는 항상 다쪽에 있다. 

### 다대일 단방향 [N:1]

회원의 Member.team으로 Team 엔티티를 참조할 수 있지만 반대로 팀에는 회원을 참조하는 필드가 없다.

→ 회원과 팀은 다대일 연관관계이다. 

### 다대일 양방향 [N:1, 1:N]

- **양방향은 외래 키가 있는 쪽이 연관관계의 주인이다.**
    
    일대다와 다대일 연관관계는 항상 다(N) 쪽에 외래 키가 있다. 
    
    여기서는 다쪽인 MEMBER 테이블이 외래 키를 가지고 있으므로 Member.team이 연관관계의 주인이다. 
    
    JPA는 외래 키를 관리할 때 연관관계의 주인만 사용한다.
    
    주인이 아닌 Team.members는 조회를 위한 JPQL이나 객체 그래프를 탐색할 때 사용한다. 
    
- **양방향 연관관계는 항상 서로를 참조한다.**
    
    어느 한쪽만 참조하면 양방향 연관관계가 성립하지 않는다. 
    
    항상 서로 참조하게 하려면 연관관계 편의 메소드를 작성하는 것이 좋다. 
     (회원의 setTeam, 팀의 addMember)
    
    편의 메소드는 한곳에만 작성하거나 양쪽 다 작성할 수 있는데 양쪽 다 작성하면 무한루프에 빠지므로 주의해야 한다. 
    
    예제 코드는 편의 메소드를 양쪽에서 다 작성해서 둘 중 하나만 호출하면 된다. 
    
    무한루프에 빠지지 않도록 검사하는 로직도 있다. 
    

## 일대다

일대다 관계는 다대일 관계의 반대 방향.

일대다 관계는 Entity를 하나 이상 참조할 수 있으므로,
 JAVA 컬렉션의 Collection, List, Set, Map 중에 하나를 사용해야 한다. 

### 일대다 단방향 [1:N]

하나의 팀은 여러 회원을 참조할 수 있는데 이런 관계를 일대다 관계라 한다.

팀은 회원을 참조, 반대는 참조하지 않으면 둘의 관계는 단방향이다. 

Team Entity의 Team.members로 회원 테이블의 TEAM_ID를 관리한다.
(보통 자신이 매핑한 테이블의 외래키를 관리한다. 

→ 반대쪽 테이블에 있는 외래 키를 관리한다.

→ 그럴 수 밖에 없는 것이 일대다 관계의 외래키는 항상 다쪽에 있다.

하지만 다쪽의 Member Entity에는 외래 키를 매핑할 수 있는 참조 필드가 없다. 

→ 반대쪽인 Team Entity에만 참조 필드인 members가 있다. 

→ 따라서 반대편의 외래 키를 관리하는 특이한 모습을 갖는다. 

```java
@Entity
public class Team {
		
		@Id
		@GeneratedValue
		@Column(name = "TEAM_ID")
		private Long id;
		
		private String name;
		
		@OneToMany
		@JoinColumn(name = "TEAM_ID") //MEMBER 테이블의 TEAM_ID (FK)
		private List<Member> members = new ArrayList<Member>();
		
		
}
```

```java
@Entity
public class Member {
		
		@Id
		@GeneratedValue
		@Column(name = "MEMBER_ID")
		private Long id;
		
		private String username;
		
}
```

일대다 단방향 관계를 매핑할 때는 @JoinColumn을 명시해야 한다.

그렇지 않으면 JPA는 연결 테이블을 중간에 두고 연관관계를 관리하는 조인 테이블 전략을 기본으로 사용해서 매핑한다. 

**일대다 단방향 매핑의 단점**

매핑할 객체가 관리하는 외래 키가 다른 테이블에 있다는 점이다. 

본인 테이블에 외래 키가 있으면 Entity의 저장과 연관관계 처리를 INSERT SQL 한 번으로 끝낼 수 있지만, 다른 테이블에 외래 키가 있으면 연관관계 처리를 위한 UPDATE SQL을 추가로 실행해야 한다. 

일대다 단방향은 Member Entity가 Team Entity를 모른다. ← Member Entity는 Team 외래 키를 갖고 있지 않다. 

→ Member Entity 저장 시 TEAM_ID 외래 키에 아무 값도 저장되지 않는다. 

→ Team Entity 저장 후 members 참조 값을 확인해서 MEMBER 테이블의 team_id를 UPDATE 한다.
