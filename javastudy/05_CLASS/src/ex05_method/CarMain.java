package ex05_method;

public class CarMain {

	public static void main(String[] args) {

		// 객체 생성
		Car car= new Car(); 		// d만들어진 시점에서 필드는 0인 상태
		
		car.addOil(50);				// 50이 들어감. addOil로 들어감. -> int o에 50이 전달. >> 인수는 매개변수로 전달된다 
		car.addOil(5);				// 두 번 넣었으니 총 55
		car.addOil(100);
		
		car.pushAccel();
		car.pushAccel();			// 악셀을 3번 밟은걸 표현함.
		

		car.pushBrake();			// 브레이크 2번
		
		car.panel();				// 계기판에 적힌걸 볼 수 있는 
		
		System.out.println("기름 " + car.oil); 		// 따라서 속도 25*2
		System.out.println("속도 " + car.speed);	// 기름 -1*2
		
		
	}

}
