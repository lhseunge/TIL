<aside>

JPA를 사용함에 있어 매핑 어노테이션의 숙지는 필수적이다. 

</aside>

# JPA의 대표 매핑 어노테이션 4가지

- 객체와 테이블 매핑 : @Entity, @Table
- 기본키 매핑 : @Id
- 필드와 컬럼 매핑 : @Column
- 연관관계 매핑 : @ManyToOne, @OneToMany

## @Entity

- JPA를 사용하여 DB Table과 Entity를 매핑하기 위해선 @Entity를 필수로 붙여야 한다.

### 속성

| 이름 | 설명 | 기본값 |
| --- | --- | --- |
| name | JPA에서 사용할 Entity 이름 지정. (보통 클래스 이름을 사용)
동일한 이름의 클래스가 있으면, 충돌 방지를 위해 설정해야 한다.  | 클래스 이름 |
- 기본 생성자는 필수로 생성해야 한다.
- final, enum, interface, inner 클래스에는 사용할 수 없다.
- 저장할 필드에 final을 사용할 수 없다.

- JPA는 Entity 객체를 생성할 때 기본 생성자를 사용한다.

> JAVA는 생성자가 하나도 없으면 기본 생성자를 자동으로 생성한다. 
그러나, 임의의 생성자를 만들면 기본 생성자를 만들지 않는다.
> 

## @Table

| 이름 | 설명 | 기본값 |
| --- | --- | --- |
| name | 매핑할 테이블 이름 | 엔티티 이름 |
| category | category 기능이 있는 DB에서 category 매핑 |  |
| schema | schema 기능이 있는 DB에서 schema 매핑 |  |
| uniqueConstraints | DDL 생성 시 유니크 제약조건을 만든다.
2개 이상의 복합 유니크 제약조건도 만들 수 있다. 
이 기능은 스키마 자동 생성 기능을 사용해서 DDL을 만들 때만 사용된다. |  |

## 다양한 매핑 사용

<aside>

특정 Entity에 추가 요구사항을 적용하기 위해서 Entity에 기능을 추가할 수 있다.

</aside>

1. 회원은 일반회원, 관리자로 구분해야 한다.
2. 회원 가입일과 수정일이 있어야 한다. 
3. 회원을 설명할 수 있는 필드가 있어야 한다. 이 필드는 길이 제한이 없다.

```java
@Entity
@Table(name="MEMBER")
public class Member {

		@Id
		@Column(name = "ID")
		private String id;
		
		@Column(name = "NAME")
		private String username;
		
		private Integer age;
		
		
		// 추가 
		@Enumerated(EnumType.STRING)
		private RoleType roleType;
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date createdDate;
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date lastModifiedDate;
		
		@Lob
		private String description;
		
}

public enum RoleType {
		ADMIN, USER
}
```

### 위의 코드 분석

1. roleType : 
    - Java의 enum을 사용해서 회원의 타입을 구분했다.
    - 일반 회원은 USER, 관리자는 ADMIN이다.
    - 위 처럼 Java의 enum을 사용하려면 @Enumerated 어노테이션을 사용해야 한다.
2. createdDate, lastModifiedDate : 
    - Java의 날짜 타입은 @Temporal을 사용해서 매핑한다.
3. description : 
    - 회원을 설명하는 필드는 길이 제한이 없다.
    - 따라서, 데이터의 VARCHAR 타입 대신에 CLOB 타입으로 지정해야 한다.
    - @Lob을 사용하면 CLOB, BLOB 타입을 매핑할 수 있다.
    
    > MariaDB에선 LONGTEXT 혹은 LONGBLOB으로 반영된다.
    > 

## Database 스키마 자동 생성

- JPA는 DB 스키마를 자동으로 생성하는 기능을 지원한다.
- 클래스의 매핑 정보를 보면 어떤 테이블의 어떤 컬럼을 사용하는지 알 수 있다.
- JPA는 이 매핑정보와 DB 방언을 사용해서 스키마를 생성한다.

- persistence.xml 혹은 application.properties에서 hibernate.hbm2ddl.auto 라는 설정을 추가하면 동작한다.
- **이 속성을 추가하면 애플리케이션 실행 시점에 DB 테이블을 자동으로 생성한다.**
- hibernate.show_sql 옵션으로 테이블 생성 DDL을 출력할 수 있다.
- 자동으로 생성하는 DDL은 DB 방언에 따라 달라지기 때문에 어느 DB를 선택하는지에 따라 타입이 바뀐다.

- 스키마 자동 생성은 테이블을 직접 생성하는 수고를 덜 수 있지만, 운영 환경에서 사용할 만큼 완벽하지 않다.
    - 개발 환경에서 사용하거나, 매핑을 어떻게 하는지 참고하는 등 개발 단계에서만 사용하는 것이 좋다.

- hbm2ddl.auto 속성

| 옵션 | 설명 |
| --- | --- |
| create | 기존 테이블을 삭제하고 새로 생성한다. DROP + CREATE |
| create-drop | create 속성에 추가로 애플리케이션을 종료할 때 생성한 DDL을 제거한다. 
DROP + CREATE + DROP |
| update | DB 테이블과 Entity 매핑정보를 비교해서 변경 사항만 수정한다.  |
| validate | DB 테이블과 Entity 매핑정보를 비교해서 차이가 있으면 경고를 남기고 애플리케이션을 실행하지 않는다. 
이 설정은 DDL을 수정하지 않는다.  |
| none | 자동 생성 기능을 사용하지 않으려면 해당 속성 자체를 삭제하거나, 유효하지 않은 옵션 값을 주면 된다.
(none은 유효하지 않은 옵션 값)  |

> **HBM2DDL 주의사항**
운영 서버에서 create, create-drop, update처럼 DDL을 수정하는 옵션은 절대로 사용해선 안된다.
오직 개발서버나, 개발 단계에서만 사용해야한다. 
이 옵션들은 운영 중인 DB의 테이블이나 컬럼을 삭제할 수 있다.
> 

개발 환경에 따른 추천 전략

| 개발 환경 | 추천 전략 |
| --- | --- |
| 개발 초기 단계 | create / update |
| 자동화 테스트 단계 혹은 CI 서버 | create / create-drop  |
| 테스트 서버 | update / validate |
| 스테이징 및 운영 서버 | validate / none |

> 이름 매핑 전략 변경 
Java는 카멜 표기법을 주로 사용하여 네이밍하고
DB는 언더 스코어를 주로 사용한다. 

회원 Entity를 매핑하려면 @Column.name 속성을 명시적으로 사용해서 이름을 지어주어야 한다. 
@Column(name = “role_type”)
String roleType;

hibernate.ejb.naming_strategy 속성을 사용하면 이름 매핑 전략을 변경할 수 있다.
hibernate는 ImprovedNamingStrategy 클래스를 제공하는데, 테이블 명이나 컬럼명이 생략되면 Java의 카멜 표기법을 테이블의 언더스코어 표기법으로 매핑한다. 

Java Entity ↔ DB Column
ex) roleType ↔ role_type
> 

## DDL 생성 기능

- 회원 이름을 필수로 입력해야하고, 10자를 초과하면 안 된다는 제약 조건이 추가되면
- 스키마 자동 생성을 통해 DDL에 제약 조건을 추가할 수 있다.

```java
@Entity
@Table(name="MEMBER")
public class Member {

		@Id
		@Column(name = "ID")
		private String id;
		
		@Column(name = "NAME"**, nullable = false, length = 10**) // nullable, length 추가
		private String username;
		
		...
}
```

- @Column 어노테이션의 속성을 변경하면 DDL에 제약조건을 추가할 수 있다.
    - nullable 속성 값을 false로 지정하면 자동 생성 DDL에 not null 제약조건을 추가할 수 있다.
    - length 속성 값을 사용하면 자동 생성 DDL에 문자 크기를 지정할 수 있다.

```java
@Entity
@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint
		name = "NAME_AGE_UNIQUE",
		columnName = {"NAME", "AGE"}
})
public class Member {

		...
		
}
```

```sql
ALTER TABLE MEMBER
		ADD CONSTRAINT NAME_AGE_UNIQUE UNIQUE (NAME, AGE)
```

- @Table의 uniqueConstraints 속성은 유니크 제약조건을 만들 수 있다.
    
    
- DDL 제약 조건을 생성하는 옵션들은 **DDL을 자동 생성할 때만 사용되고, JPA의 실행 로직에는 영향을 주지 않는다.**
- 이 기능을 사용하지 않고 직접 DDL을 만든다면 사용할 이유가 없다.
- 이 기능을 사용하면 개발자가 Entity만 보고도 다양한 제약조건을 파악할 수 있다는 장점이 있다.

## DB 기본 키 자동 생성 전략

- 기본 식별자를 직접 할당하는 방법 외에 자동 생성 값을 사용하는 방법도 있다.
    - ex) mysql(mariadb) - Auto Increment

### IDENTITY

- 기본 키 생성을 DB에 위임한다.

### SEQUENCE

- DB의 sequence를 이용하는 방법

### TABLE

- 키 생성 테이블을 사용하는 방법

- 자동 생성 전략이 다양한 이유
    - DB 밴더마다 지원 방식이 달라서
        - Oracle은 sequence를 사용.
        - Mysql은 Auto Increment 제공
