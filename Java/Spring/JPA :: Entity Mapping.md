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
		... 작성중 ... 
}
```
