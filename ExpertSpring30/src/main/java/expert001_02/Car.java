package expert001_02;

public class Car {

	Tire tire;
	
	// 외부에서 생산된 tire 객체를 Car 생성자의 인자로 주입(장착)하는 형태로 구현 
	public Car(Tire tire) {
		this.tire = tire;
	}

	public Car() {
		tire = new KoreaTire();
	}

	public String getTireBrand() {
		return "장착된 타이어 : " + tire.getBrand();
	}

}
