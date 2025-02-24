# Question

- `equals()`를 오버라이드할 때 `hashCode()`도 반드시 오버라이드해야 하는 이유는?

---

# Answer

### `equals()` 와 `hashCode()` 는 객체간의 비교를 할 때, 두 객체가 동일한지 확인하기 위해 사용됩니다.

### `equals()`

- `String`의 경우, `equals()` 는 값의 비교를 위해 사용됩니다.
- 그러나, 객체의 비교 (클래스 자료형)의 경우 `equals()` 는 주소값을 비교하게 됩니다.

```java
// CASE 1
String A = "TEST";
String B = "TEST";

// A.equals(B) >> true 
```

```java
// CASE 2
Person person1 = new Person("홍길동");
Person person2 = new Person("홍길동");

// person1.equals(person2) >> false
```

- 개발자는 `"홍길동"` 이라는 인자를 가진 동일한 객체를 생성했다고 생각할 수 있습니다.
- Java는 각기 다른 객체를 힙 메모리에 따로 저장하고 있어 둘을 동일한 객체로 보지 않습니다.
- 객체의 필드값을 기준으로 비교하기 위해선, `equals()` 메소드를 필드를 비교하도록 재정의 하여야합니다.

```java
// 객체 주소 비교가 아닌 Person 객체의 사람 이름이 동등한지 비교로 재정의 하기 위해 오버라이딩
public boolean equals(Object o) {
    if (this == o) return true; // 만일 현 객체 this와 매개변수 객체가 같을 경우 true
    if (!(o instanceof Person)) return false; // 만일 매개변수 객체가 Person 타입과 호환되지 않으면 false
    Person person = (Person) o; // 만일 매개변수 객체가 Person 타입과 호환된다면 다운캐스팅(down casting) 진행
    return Objects.equals(this.name, person.name); // this객체 이름과 매개변수 객체 이름이 같을경우 true, 다를 경우 false
}
```

### `hashCode()`

- `hashCode()` 는 객체가 가진 주소값을 해싱(Hashing)하여 해시코드를 만들어 반환합니다.
- 그렇기 때문에 서로 다른 두 객체는 똑같은 두개의 해시코드를 갖을 수 없습니다.
- 그러나, 해시코드는 주소값을 통해 생성되지만 주소값은 아닙니다.
- `hashCode()` 도 객체가 가진 필드를 사용해 해시코드를 생성할 수 있도록 재정의 하여야합니다.

```java
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
```

### Summery. `equals()` 와 `hashCode()` 를 같이 재정의해야하는 이유.

- Q. CASE 2 처럼 객체를 초기화 하고, 두 객체의 비교를 위해 `equals()` 만 초기화 했다면?
- A. 두 객체는 해시코드는 다르지만, 객체가 가진 값을 바탕으로 같은 객체라고 판단할 수 있습니다.

- 하지만 해시 값을 사용하는 Collection(HashMap, HashSet, HashTable)는 **위와 같은 상황에서 같은 객체라고 판단하지 않습니다**.
- HashCollection이 비교하는 로직

![image.png](attachment:c7a53007-f763-481c-8502-8ae5d622abd4:image.png)

- 이를 방지하기 위하여 `equals()` 와 `hashCode()` 는 객체 비교를 위해 같이 재정의하여 사용하는 것을 원칙으로 합니다.

### 추가) `hashCode()` 코드를 재정의 하였는데, 기존의 주소값 기반 해시코드가 필요할 경우?

- `java.lang.System.dentityHashCode()` 라는 메소드는 주소값을 통해 해시코드를 생성하는 원래의 `hashCode()` 기능을 수행합니다.
- `identityHashCode()` 는 주소값의 비교, `hashCode()` 는 필드의 비교를 위한 재정의 메소드로 사용하도록 유도하고 있습니다.

```java
Person person1 = new Person("홍길동");

System.identityHashCode(person) >> person의 주소값으로 생성된 해시코드 반환
```

## Reference

> 
> 

[☕ 자바 equals / hashCode 오버라이딩 - 완벽 이해하기](https://inpa.tistory.com/entry/JAVA-%E2%98%95-equals-hashCode-%EB%A9%94%EC%84%9C%EB%93%9C-%EA%B0%9C%EB%85%90-%ED%99%9C%EC%9A%A9-%ED%8C%8C%ED%97%A4%EC%B9%98%EA%B8%B0)

[Object (Java Platform SE 8 )](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#hashCode--)
