<aside>

JPA를 사용함에 있어 매핑 어노테이션의 숙지는 필수적이다. 

</aside>

# JPA의 대표 매핑 어노테이션 4종류

- 객체와 테이블 매핑 : @Entity, @Table
- 기본키 매핑 : @Id
- 필드와 컬럼 매핑 : @Column
- 연관관계 매핑 : @ManyToOne, @OneToMany

## @Entity

- JPA를 사용하여 DB Table과 Entity를 매핑하기 위해선 @Entity를 필수로 붙여야 한다.

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

- **위의 코드 분석**
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

<aside>

기본 키 생성을 DB에 위임하는 전략

</aside>

> IDENTITY 전략은 DB Insert 후 기본 키 값을 조회할 수 있다. 
→ Entity에 식별자 값을 할당하려면 JPA는 추가로 DB를 조회 해야 한다. 

JDBC의 Statement.getGeneratedKeys()를 사용하면 데이터 저장과 동시에 생성된 키 값을 얻어올 수 있다.
hibernate는 이 메소드를 이용해 DB와 한번만 통신한다. 

IDENTITY 전략은 Entity를 DB에 저장해야 식별자를 구할 수 있다.
→ persist() 호출 즉시 Insert SQL이 나간다. 
**→ 쓰기 지연이 동작하지 않는다.**
> 

**주 사용 DB**

- Mysql (Mariadb)
- PostgreSQL
- SQL Server
- DB 2

### SEQUENCE

<aside>

DB의 sequence를 이용하는 방법

</aside>

> SEQUENCE 전략은 DB의 시퀀스를 통해 기본 키를 생성한다.
DB sequence는 유일한 값을 순서대로 생성하는 특별한 DB Object이다. 

sequence 전략 최적화
sequence 전략은 DB 시퀀스를 통해 식별자를 조회하는 추가 작업이 필요하다.  (DB와 2번 통신한다.)

1. 식별자를 구하려고 DB 시퀀스를 조회한다. 
→ select board_seq.NEXTVAL from dual 
2. 조회할 시퀀스를 키 값으로 DB에 저장한다.
→ insert Into BOARD … 

JPA는 시퀀스 접근 횟수를 줄이기 위해 @SequenceGenerator.allocationSize를 사용한다. 
→ allocationSize 값 만큼 한 번에 시퀀스 값을 증가시키고 그만큼 메모리에 시퀀스 값을 할당.
→ allocationSize = 50 : 시퀀스를 한 번에 50 증가시킨 다음 1 ~ 50은 메모리에서 할당한다. 
51이 되면 시퀀스를 100으로 증가시키고, 51 ~ 100 사이의 값을 메모리에서 식별자로 할당한다.

위 방법은 시퀀스 값을 선점해여 여러 JVM이 동시에 접근해도 키 값이 충돌하지 않는 장점이 있다.
→ 시퀀스가 증가할 때 값이 한번에 많이 증가한다.
→ 이 점이 부담스럽거나 Insert 성능이 크게 중요하지 않다면, allocationSize를 1로 설정하면 된다. 

hibernate.id.new_generator_mapping 속성을 True로 설정해야 위의 최적화 방법이 적용된다.
해당 설정이 아닐경우 hibernate는 과거에 사용하던 방법으로 키를 생성하는데, 

시퀀스 값을 애플리케이션에서 할당한다. ****
> 

@SequenceGenerator (시퀀스 생성 어노테이션)

| 속성 | 설명 | 필수값 |
| --- | --- | --- |
| name | 식별자 생성기 이름
GeneratorValue의 generator 속성의 값으로 매핑 |  |
| sequenceName | DB에 등록되어있는 시퀀스 이름  | “hibernate_sequence” |
| initialValue | 시퀀스의 시작 Index
ddl 생성시에만 사용됨 | 1 |
| allocationSize | 시퀀스 1회 호출당 증가하는 수 | 50 |

매핑할 DDL

```sql
create sequence <sequenceName> 
		start with <initialValue> increment by <allocationSize>
```

> @SequenceGenerator는 @GeneratedValue 옆에 사용해도 된다.
> 

```sql
@Entity
public Class Board {

		@Id
		@GeneratedValue(...)
		@SequenceGenerator(...)
		private Long id;
		
}
```

### TABLE

<aside>

키 생성 테이블을 사용하는 방법

</aside>

- Table 전략은 키 생성 테이블을 만들어 DB sequence를 흉내내는 전략이다.
- DB의 Table을 사용하기 때문에 모든 종류의 DB에 적용할 수 있다.

```sql
create table MY_SEQUENCES (
		sequence_name varchar(255) not null,
		next_val bigint,
		primary key (sequence_name)
)
```

- sequence_name 컬럼을 시퀀스 이름으로,
- next_val 컬럼을 시퀀스 값으로 사용한다.
- 컬럼의 이름은 변경할 수 있지만 여기선 기본 값이다.

```java
@Entity
@TableGenerator(
		name = "BOARD_SEQ_GENERATOR",
		table = "MY_SEQUENCES".
		pkColumnValue = "BOARD_SEQ", allocationSize = 1)
public class Board {

		@Id
		@GeneratedValue(strategy = GenerationType.TABLE,
				generator = "BOARD_SEQ_GENERATOR")
		private Long id;

		...
}
```

- @TableGenerator로 테이블 키 생성기 등록 (BOARD_SEQ_GENERATOR)
- 위에서 생성한 MY_SEQUENCES 테이블을 키 생성 테이블로 매핑.
- TABLE 전략을 사용하기 위해 @GeneratedValue.strategy를 GenerationType.TABLE 로 설정.
- @GeneratedValue.generator에 키 생성기를 지정 (BOARD_SEQ_GENERATOR)
    - 앞으로 할당될 Id의 식별자 값은 BOARD_SEQ_GENERATOR 가 할당한다.

- **TABLE 전략은 테이블을 사용한다는 점을 제외하면 SEQUENCE 전략과 내부 동작 방식이 같다.**

| sequence_name | next_val |
| --- | --- |
| BOARD_SEQ | 2 |
| MEMBER_SEQ | 10 |
| PRODUCT_SEQ | 50 |
| … | … |
- @TableGenerator.pkColumnName에서 지정한 “BOARD_SEQ”가 컬럼으로 추가된다.
- 키 생성기를 사용할 때마다 next_val의 값이 증가한다.

- MY_SEQUENCES에 해당하는 시퀀스가 없으면 JPA가 자동으로 값을 생성한다.
    - 값을 미리 넣어둘 필요가 없다.

@TableGenerator 속성 정리 

| 속성 | 기능 | 기본값 |
| --- | --- | --- |
| name | 식별자 생성기 이름 | 필수 |
| table | 키 생성 테이블 명 | hibernate_sequence |
| pkColumnName | 시퀀스 컬럼 명 | sequence_name |
| valueColumnName | 시퀀스 값 컬럼 명 | next_val |
| pkColumnValue | 키로 사용할 값 이름 | 엔티티 이름 |
| initialValue | 초기 값, 마지막으로 생성된 값이 기준이다. | 0 |
| allocationSize | 시퀀스 한 번 호출에 증가하는 수
(성능 최적화에 사용됨) | 50 |
| catalog, schema | DB catalog, schema 이름 |  |
| uniqueConstraints(DDL) | 유니크 제약 조건을 지정할 수 있다.  |  |
- 매핑할 DDL

| {pkColumnName} | {valueColumnName} |
| --- | --- |
| {pkColumnValue} | {initialValue} |

> TABLE 전략은 값을 조회하면서 select 쿼리 후 update 쿼리를 사용. 
→ sequence 전략과 비교했을 때 DB와 한번 더 통신하는 단점을 갖음.

최적화를 하려면 @TableGenerator.allocationSize를 사용하면 됨. (SEQUENCE 전략처럼 사용하면 됨)
> 

### AUTO 전략

<aside>

AUTO 전략은 DB 방언에 따라 IDENTITY, SEQUENCE, TABLE 전략 중 한가지를 자동으로 선택한다.

</aside>

- Oracle → SEQUENCE 전략
- Mysql → IDENTITY 전략

- **AUTO 전략은 @GeneratedValue.strategy의 기본값이다.**

- AUTO 전략은 DB를 변경해도 코드를 수정할 필요가 없다는 장점을 갖는다.
    - 개발 초기 혹은 프로토 타입 개발 시 유용하다.

- AUTO 전략 사용 시 SEQUENCE, TABLE 전략이 선택되면 시퀀스나 키 생성 테이블을 만들어 두어야 한다.
    - 스키마 자동 생성 기능을 사용한다면, 하이버네이트가 기본값을 사용해서 적절한 시퀀스나 키 생성 테이블을 자동으로 만들어 준다.

- 자동 생성 전략이 다양한 이유
    - DB 밴더마다 지원 방식이 달라서
        - Oracle은 sequence를 사용.
        - Mysql은 Auto Increment 제공

### 기본 키 매핑 정리

- 영속성 컨텍스트는 Entity를 식별자 값으로 구분하므로, Entity를 영속 상태로 만들기 위해선 식별자 값이 필수적이다.
- 이를 위한 식별자 할당 전략은 다음과 같다.
    - **직접 할당**
        - em.persist()전에 애플리케이션에서 값을 직접 할당.
        - 만약 식별자 값이 없이 em.persist()하면 에러.
    - **SEQUENCE**
        - DB 시퀀스에서 식별자 값을 획득한 후 영속성 컨텍스트에 저장.
    - **TABLE**
        - DB 키 생성 테이블에서 식별자 값을 획득한 후 영속성 컨텍스트에 저장.
    - **IDENTITY**
        - DB에 엔티티를 저장하고 식별자 값을 획득한다.
        - 이후 영속성 컨텍스트에 저장한다.
        - IDENTITY 전략은 테이블에 데이터가 저장돼야 식별자 값을 획득할 수 있다.

> **권장하는 식별자 선택 전략**
DB 식별자는 아래 조건을 만족해야 한다. 
1. null을 허용하지 않는다.
2. 유일해야 한다.
3. 변하면 안된다.

테이블의 기본 키를 선택하는 전략은 2가지가 있다. 
* 자연 키 (natural key)
- 비즈니스에 의미가 있는 키
ex) 주민등록번호, 이메일, 전화번호

* 대리 키 (surrogate key)
- 비즈니스와 관련 없는 임의로 생성된 키 (대체 키라고도 부른다)
ex) Oracle 시퀀스, Auto-increment, 키 생성 테이블의 값

**두 전략 중 대리 키를 사용하는 것이 권장된다.**

이유는?
전화번호는 없을 수 있거나, 변경될 수 있고,
주민등록번호의 경우 여러가지 이유로 변경될 수 있다.
**→ 기본키는 변하면 안된다는 원칙에서 위배된다.** 

비즈니스 변할 수 있다.
만약 주민등록번호를 기본 키로 사용하는 애플리케이션에 정부 정책 등의 이유로 주민등록번호를 저장하지 못하게 된다면?
DB 뿐만 아니라 애플리케이션의 비즈니스 로직도 변경될 수 있다.
대리 키를 사용했다면 수정부가 많지 않을 수 있다.
이유는? 
대리 키는 비즈니스와 무관하여 변경될 여지가 적기 때문에 자연 키의 단점에서 자유롭다.
자연 키는 필요에 따라 유니크 제약조건을 걸어 사용하는게 권장된다.

정리.
**→ JPA는 모든 Entity에 일관된 방식으로 대리 키 사용을 권장한다.**
>
