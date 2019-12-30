package expert001_02;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import expert001_02.AmericaTire;
import expert001_02.Car;
import expert001_02.KoreaTire;
import expert001_02.Tire;


public class CarTest {
	
	@Test
	public void 자동차_코리아타이어_장착_타이어브랜드_테스트() {
		Tire tire1 = new KoreaTire();
		Car car1 = new Car(tire1);
		
		assertEquals("장착된 타이어 : korea tire", car1.getTireBrand());
	}
	
	@Test
	public void 자동차_미국타이어_장착_타이어브랜드_테스트() {
		Tire tire2 = new AmericaTire();
		Car car2 = new Car(tire2);
		
		assertEquals("장착된 타이어 : america tire", car2.getTireBrand());
	}
	
}