package ex05_method;

public class CarMain {

	public static void main(String[] args) {

		// 객체 생성
		Car car= new Car(); 		// d만들어진 시점에서 필드는 0인 상태
		
		car.addOil(50);				// 50이 들어감. addOil로 들어감. -> int o에 50이 전달. >> 인수는 매개변수로 전달된다 
		car.addOil(5);				// 두 번 넣었으니 총 55
		car.addOil(100);			// oil 만땅은 60이므로 도합 60이 넘어가면 무쓸모! 따라서 여기까지 만땅 상태..
		
		car.pushAccel();
		car.pushAccel();
		car.pushBrake();			// 악셀 2번, 브레이크 1번
		
		car.pushAccel();
		car.pushAccel();
		car.pushBrake();			// 악셀 2번, 브레이크 1번
									// 악셀 4번 > (+)속도 100, 브레이크 2번 > (-)50
		
		
		// 현재 기름 양과 속도 보기
		car.panel();				// 계기판에 적힌걸 볼 수 있는 메소드
		// or
		System.out.println("기름 " + car.oil + ", 속도 " + car.speed); 		
		//System.out.println("속도 " + car.speed);
		
		
	}

}
