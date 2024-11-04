@Builder 어노테이션은 객채 생성에 사용될 Builder 클래스를 만들어주는 어노테이션이다. 

그러나 상속관계를 갖고 있는 객체에서는 @Builder를 사용하면 컴파일 에러가 발생한다. 

(자식 객체에서 부모 객체의 필드는 사용할 수 없다.)

이런 상속관계를 갖는 객체의 Builder 클래스를 만들어주기 위해 @SuperBuilder 어노테이션이 제공된다. 

부모 자식 객체 모두 @SuperBuilder를 적용하면, 자식 객체의 Builder 클래스에서 부모 객체의 필드를 사용할 수 있게된다. 

```java
@SuperBuilder
public class master {
		private String masterField;
}

@SuperBuilder
public class slave extends master {
		private String slaveField;
}

// use 
Slave.Builder()
	.masterField()
	.slaveField()
	.Build();
```
