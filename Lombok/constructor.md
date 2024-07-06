Lombok 라이브러리가 제공하는 **생성자를 만들어주는 어노테이션들**

## 종류

## @AllArgsConstructor

인자를 갖지 않는 기본 생성자를 만들어준다.

```java
@AllArgsConstructor
public class member() {
		private String name;
		private String email;
		
		// @AllArgsConstructor는 아래 코드를 작성해준다 
		public member(String name, String email) {
				this.name = name;
				this.email = email;
		}
}
```

## @RequierdArgsContructor

`final` 이나 `@NonNull` 이 선언된 필드만 인자로 받는 생성자를 만들어준다.

이 어노테이션은 클래스가 의존하는 필드를 간단하게 초기화할 수 있다.

```java
@RequierdArgsConstructor
public class member() {
		private final String name;
		private String email;
		
		// @RequierdArgsConstructor는 아래 코드를 작성해준다 
		public member(String name) {
				this.name = name;
		}
}
```

## @NoArgsConstructor

인자를 갖지 않는 기본 생성자를 만들어준다.

```java
@NoArgsConstructor
public class member() {
		private String name;
		private String email;
		
		// @NoArgsConstructor는 아래 코드를 작성해준다 
		public member() {}
}
```

---

[@NoArgsConstructor, @RequiredArgsConstructor, @AllArgsConstructor](https://projectlombok.org/features/constructor)
