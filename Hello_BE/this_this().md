# Question

- this 참조 변수와 this() 메소드의 차이점을 알려주세요!

---

# Answer

### this

- `this` 는 인스턴스가 자기 자신을 참조하는데 사용하는 변수입니다.

```java
public class Person {
	private String name;
	private int age;
	private String address;
	
	public Person(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}
}

// 생성자의 매개변수와 인스턴스 변수(클래스가 갖고 있는 변수)의 이름이 같을 경우 구분해야 합니다.
// this 참조 변수를 사용하면 해당 클래스의 인스턴스 변수에 접근할 수 있습니다.  
```

- `this` 참조 변수는 인스턴스 영역에서만 사용할 수 있습니다.
- 모든 인스턴스 메소드에는 `this` 참조변수를 숨겨진 지역 변수로서 갖고있습니다.

### this()

- `this()` 는 생성자 내부에서만 사용할 수 있습니다.
- `this()` 에 인수를 전달하면, 해당하는 생성자를 찾아 호출합니다.

```java
public class Person {
	private String name;
	private int age;
	private String address;
	
	// 1. new Person()이 호출되면 ?
	public Person() {
		this("이현승", 30, "서울");
	}
	
	// 2. 해당하는 생성자가 호출된다.
	public Person(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}
}
```

## Reference

> 
>
