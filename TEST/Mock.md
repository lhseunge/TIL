# Mock 객체

테스트코드를 작성하기 위하여, 아직 구현하지 않았거나, 테스트하기에 너무 복잡한 객체를 대체하는 **모조 객체**

## Mock 객체를 생성하는 방법

### Mockito

```java
public interface IVehicle {
	public boolean deliver();
}

---

public class Delivery{
	private IVehicle vehicle;
	public Delivery() {}
	public Delivery(IVehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public boolean makeADelivery() { << When. 테스트하고자 하는 코드
		// TODO 배달을 위한 준비
		... (중략)
		return vehicle.deliver(); 
	}
} 

---

public class DeliverTest extends TestCase {
	@Test
	public void testMakeADelivery_배달에 대한 케이스() throws Exception {
		// Given
		// 배달 수단에 대한 Mock 객체 생성
		IVehicle mockVehicle = Mockito.mock(IVehicle.class); << 배달수단 Interface
		
		// vehicle 인스턴스의 deliver() 메소드를 호출했을 때 true를 리턴하도록 정의
		Mockito.when(mockVehicle.deliver()).thenReturn(true);
		
		// 생성된 Mock 객체를 이용해 테스트하고자 하는 Deliver 테스트
		Delevery delivery = new Delivery(mockVehicle);
		
		// When
		// 테스트하고자 하는 코드 
		boolean result = delivery.makeADelivery();
		
		// Then
		assertTrue(result)
	}
}

--- 
// 구현체의 메소드가 호출될 시 반환시키고자 하는 return 값 정의
Mockito.when(instance.method()).thenReturn(result)
// Mock 객체를 (Mock객체를 의존중인)다른 객체에 주입하여 사용하기도 한다.

```
