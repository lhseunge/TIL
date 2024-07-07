Lombok 라이브러리가 제공하는 **생성자를 만들어주는 어노테이션들**

# @AllArgsConstructor

모든 필드 인자를 받는 생성자를 만들어준다.

이 어노테이션은 클래스의 모든 필드를 한번에 초기화 할 수 있다.

```java
@AllArgsConstructor
public class Member() {
		private String name;
		private String email;
		
		// @AllArgsConstructor는 아래 코드를 작성해준다 
		public Member(String name, String email) {
				this.name = name;
				this.email = email;
		}
}
```

# @RequierdArgsContructor

`final` 이나 `@NonNull` 이 선언된 필드만 인자로 받는 생성자를 만들어준다.

이 어노테이션은 클래스가 의존하는 필드를 간단하게 초기화할 수 있다.

```java
@RequierdArgsConstructor
public class Member() {
		private final String name;
		private String email;
		
		// @RequierdArgsConstructor는 아래 코드를 작성해준다 
		public Member(String name) {
				this.name = name;
		}
}
```

# @NoArgsConstructor

인자를 갖지 않는 기본 생성자를 만들어준다.

```java
@NoArgsConstructor
public class Member() {
		private String name;
		private String email;
		
		// @NoArgsConstructor는 아래 코드를 작성해준다 
		public Member() {}
}
```

# Options

- staticName : 정적 팩토리 메소드를 생성
- access : 접근제한자를 지정
- onConstructor : 생성자에 어노테이션을 작성
- force : final 필드가 선언된 경우 컴파일 타임에 기본값을 0 / null / false 로 설정

### staticName

모든 생성자 어노테이션에 사용할 수 있다. 

```java
@AllArgsConstructor(staticName = "of")
public class Member() {
		private String name;
		private String email;
		
		// @AllArgsContructor에 staticName 옵션을 사용했을 때 
    public static Member of(String name, String email) {
       return new Member(name, email);
    }
}
```

### access

생성자의 접근 제한자를 설정한다. 

- PUBLIC: 기본값이며, 모든 클래스에서 생성자에 접근 가능 (NONE)
- PROTECTED: 같은 패키지의 클래스와 상속받은 클래스에서 생성자에 접근 가능
- PACKAGE: 같은 패키지의 클래스에서 생성자에 접근 가능 (MODULE, DEFAULT)
- PRIVATE: 해당 클래스 내부에서만 생성자에 접근 가능

```java
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Member() {
		private String name;
		private String email;
		
    public Member() {}
}
---
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Member() {
		private String name;
		private String email;
		
    Member() {}
}
---
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member() {
		private String name;
		private String email;
		
	  protected Member() {}
}
---
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Member() {
		private String name;
		private String email;
		
	  private Member() {}
}
```

### force

@NoArgsConstructor에만 사용 가능

옵션을 지정할 경우, 필드의 primitive type에 맞는 기본 값이 설정된다. 

```java
@NoArgsConstructor(force = true)
public class Member() {
		private String name;
		private String email;
}
>> 
public class Member() {
		private String name = null;
		private String email = null;
}
```

### onConstructor :: onX (experimental)

Lombok을 통해 생성될 생성자에 작성할 어노테이션을 설정해준다.

Project Lombok 공식 문서에 아직 실험중인 기능으로 소개된다. 

사용에 주의하거나 지양해야한다. 

```java
@NoArgsConstructor(onConstructor=@__(@Inject))
public class Member() {
		private String name;
		private String email;
		
		// @NoArgsConstructor(onConstructor=@__(@Inject)) 를 통해 생성될 생성자
		@Inject
    Member() {}
}
```

---

[@NoArgsConstructor, @RequiredArgsConstructor, @AllArgsConstructor](https://projectlombok.org/features/constructor)

[onX](https://projectlombok.org/features/experimental/onX)
