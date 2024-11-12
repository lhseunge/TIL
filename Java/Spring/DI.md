# [DI (Dependency Injection : 의존성 주입)](https://lhseunge.notion.site/Spring-DI-IoC-138f35ec632d802497f4f32de226a256)

IoC를 구현하기 위해 사용하는 디자인 패턴 중 하나.

이름대로 객체의 의존관계를 외부에서 주입시키는 패턴

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/085595b0-e6d1-4c87-8721-897a46fee3bd/image.png)

## DI의 3가지 조건

1. 클래스 모델이나 코드에 런타임 시점의 의존관계가 드러나지 않는다. 
    1. 그러기 위해서는 인터페이스에만 의존하고 있어야한다. 
2. 런타임 시점의 의존관계는 컨테이너 or 팩토리 같은 제3의 존재가 결정한다. 
3. 의존관계는 사용할 오브젝트에 대한 레퍼런스를 외부에서 주입해주며 만들어진다.

```java
public class A {    
		private B b;     

		public A() {        
				b = new B();    
		}
}
```

위 코드는 생성자 내부에서 필드를 초기화 하는 예제이다. 다음과 같은 문제점이 있다. 

1. 강한 결합
    - 만약 B 객체의 생성자에 변경이 생긴다면 (기본 생성자 메소드의 로직 변경 등) 모든 A 클래스가 영향을 받는다. → 유연성이 떨어진다.

## 의존성 주입 방법

1. 생성자 주입 (Constructor Injection)
    
    ```java
    public class Test {
    
    	private TestService testService;
    	
    	// 생성자 주입
    	public Test(TestService testService) {
    		this.testService = testService;
    	}
    }
    ```
    
    - 가장 권장되는 의존성 주입 방식
    - final 키워드를 사용할 수 있다.
        - 생성자 주입만 가능하다.
    - 불변성이 보장된다.
    - NPE가 발생하지 않는다.
2. 수정자 주입 (Setter Injection)
    
    ```java
    public class Test {
    
    	private TestService testService;
    
    	// 수정자 주입
    	@Autowired
    	public setTestService(TestService testService) {
    		this.testService = testService;
    	}
    }
    ```
    
    - NPE가 발생할 수 있다.
    - final 키워드를 사용할 수 없어, 불변성이 보장되지 않는다.
3. 필드 주입 (Field Injection)
    
    ```java
    public class Test {
    
    	// 수정자 주입
    	@Autowired
    	private TestService testService;
    }
    ```
    
    - 코드가 간결해진다.
    - 단일 책임 원칙을 위배한다.
    - final 키워드를 사용할 수 없어, 불변성이 보장되지 않는다.

## 요약

Spring 문서에 언급되는데, 의존성 주입을 할 때는 생성자 주입 방식을 사용하는 것이 좋다. 

불변성이 보장되고, NPE가 방지되는 장점을 갖고, 프레임워크에 의존적이지 않다. 

---

[[Spring] IoC(제어의 역전)와 DI(의존성 주입) 그리고 Spring](https://oneul-losnue.tistory.com/364)

[Spring DI(Dependency Injection) - 의존 관계 주입 핵심 정리](https://backendcode.tistory.com/249)
