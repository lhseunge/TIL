# IoC (Inversion of Control : 제어의 역전)

코드의 흐름이 제3자에게 위임되는 것

```java
public class A {
		private B b;     
		
		public A() {        
				b = new B();    
		}
}
```

A 클래스는 B 필드를 가지고 있고, 생성자 내부에서 직접 생성해(new) 필드를 초기화 하고 있다. 

→ 객체의 생명주기나 메소드의 호출을 개발자가 직접 제어하고 있다. 

→ 이 흐름을 외부에서 관리한다면?

```java
public class A {
	
	@Autowired    
	private B b; // 필드 주입방식
}
```

B 객체가 Spring Container에서 관리되고 있는 Bean이라면, `@Autowired`를 통해 객체를 주입받을 수 있다. 

→ 개발자가 객체를 직접 제어하지 않고, Spring Container에서 객체를 생성하여 해당 객체를 주입시켜 주었다. 

→ 이것이 제어의 역전이다. 

**객체의 제어를 개발자가 하지않고, Spring에게 위임**

## IoC가 가지는 장점

- 역할과 관심을 분리해 응집도를 높이고 결합도를 낮추며, 이에 따라 변경에 유연한 코드를 작성할 수 있다.
- 프로그램의 진행 흐름과 구체적인 구현을 분리시킬 수 있다.
- 개발자는 비즈니스 로직에 집중할 수 있다.
- 객체 간 의존성이 낮아진다.