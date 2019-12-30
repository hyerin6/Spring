package expert001_01;

public class Car {

	Tire tire;

	// 직접 의존성을 해결하는 경우 - Car 객체가 Tire를 직접 생산하는 
	// 즉, Tire에 대한 의존성을 자체적으로 해결하는 방식 
	public Car() {
		tire = new KoreaTire();
	}

	public String getTireBrand() {
		return "장착된 타이어 : " + tire.getBrand();
	}

}
