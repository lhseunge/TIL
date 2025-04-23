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
>

