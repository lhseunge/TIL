# Question

- 개념을 서술해주세요.

---

# Answer

- `final` 키워드는 어디에 사용하냐에 따라 다른 의미를 갖습니다.
- 공통적으로 무언가를 제한하는데 의미를 갖습니다.

### 변수

- 변수에 `final` 을 붙이면 해당 변수는 수정할 수 없다는 의미를 갖습니다.
- 변수를 수정할 수 없기 때문에 초기화 값이 필수적입니다.
- 객체의 변수라면 생성자 또는 static 메소드를 통한 초기화는 허용됩니다.

```java
public class Service {

  public void variableFinal() {

    final int value = 4;
    value = 2; >> **컴파일 에러**
    
    final Person person = new Person("유재석");
    person.setName("강호동"); >> OK

    person = new Person("박명수"); >> 컴파일 에러
  }
}
```

### 메소드

- 메소드에 `final` 을 붙이면 `Override` 할 수 없다는 의미를 갖습니다.
- 부모 객체가 갖는 메소드를 보존하고 싶을 때 사용합니다.

```java
class Person {
	String name = "홍길동";
	
	public final void printName() {
		System.out.println(name);
	}
}

class Studend extends Person {
	String class = "클래스";
	
	
	아래 메소드는 재정의할 수 없습니다. 
	public void printName() {
	
	}
	
}
```

### 클래스

- 클래스에 `final` 을 붙이면 해당 클래스를 다른 클래스가 상속할 수 없습니다.
- 원본 클래스의 불변성을 유지하는데 도움을 줄 수 있습니다.
- `Integer`같은 `Wrapper class` 가 이에 해당합니다.

```java
public final class Integer extends Number
        implements Comparable<Integer>, Constable, ConstantDesc {
        
    ...
}

public class Person extends Integer { << // Cannot inherit from final 'java. lang. Integer'

}
```

### Summery

| 변수 | 메소드 | 클래스 |
| --- | --- | --- |
| 변수의 값 수정 불가능  | 메소드 `Override` 불가능 | 클래스 상속 불가능 |

## Reference

> 
>
