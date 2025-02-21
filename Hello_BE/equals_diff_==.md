# Question

- Java 의 `equals` 와 `==` 의 차이에 대해 설명해주세요.

---

# Answer

### `equals(Object obj)`

- 모든 클래스의 최상위 부모객체인 Object의 equals() 메소드를 갖습니다.
- `Object의 equals()` 는 객체와 인자가 동일한지 검증합니다.
    - `String의 equals()` 는 문자열과 메소드 인자간에 동일한 문자 시퀀스를 비교합니다.

### `==`

- `==` 는 비교 연산자간의 주소값을 비교합니다.

```java
String stringA = "test";
String stringB = "test";

stringA == stringB >> true
// 동일한 문자열 리터럴은 동일한 주소값을 부여받습니다. 

String stringA = "test";
String stringB = new String("test");

stringA == stringB >> false
// new로 객체가 메모리에 할당될 때는 새로운 주소값을 부여받습니다. 
```

## Reference

> 
> 

[Object (Java Platform SE 8 )](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-)

[String (Java Platform SE 8 )](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#equals-java.lang.Object-)
