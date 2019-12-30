package expert001_03;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Tire tire = new KoreaTire();
		Car car = new Car();
		car.setTire(tire);

		System.out.println(car.getTireBrand());

	}

}

/* 과연 이런 방식은 어떤 이점이 있을까?
 * 의존성 주입을 적용할 경우 Car는 그저 Tire 인터페이스를 구현한 어떤 객체가 들어오기만 하면 정상적으로 작동하게 된다. 
 * 의존성 주입을 하면 확장성도 좋아지는데, 나중에 새로운 타이어 브랜드가 생겨도 Car.java 코드를 
 * 변경할 필요 없이 사용할 수 있기 때문이다. (또한 다시 컴파일할 필요도 없다.)
 * 실제 제품화하게 되면 더 많은 코드를 재배포할 필요가 없게 구성해야만 코드 재컴파일과 재배포에 대한 부담을 덜 수 있다. 
 * 이것은 인터페이스를 구현(준수)했기에 얻는 이점이라고 볼 수 있다. 
 */
