package ex05_throws;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ParkingLot__throws {
	
	private Car[] cars;
	private int idx;
	private Scanner sc;
	
	public ParkingLot__throws() {
		cars = new Car[10];
		sc = new Scanner(System.in);
	}
	

	public void addCar() throws RuntimeException {

			if(idx==cars.length) {
				throw new RuntimeException("자리가 없으니까 그냥 돌아가");
				}
	}
	
	public void deleteCar() {
	
			if(idx==0) {
				throw new RuntimeException("EMPTY");
			} 
		
	}
	
	public void findCar() {
	
			if(idx==0) {
				throw new RuntimeException("EMPTY");
			} 
	}
	
	public void printAllCars() {

			if(idx==0) {
				throw new RuntimeException("EMPTY");
			}
	}
	
	
	
	public void manage() {
		
		while(true) {
			try {
				System.out.println("1.추가 2.제거 3.조회 4.전체목록 0.종료 >>> ");
				int choice = sc.nextInt();
				switch(choice) {
				case 1: addCar(); break;
				case 2: deleteCar(); break;
				case 3: findCar(); break;
				case 4: printAllCars(); break;		// throw에서 던져진 예외는 호출영역인 메인메소드쪽으로 왔다가 try블럭 안에 있으니까 예외발생
				case 0: return;
				default : throw new RuntimeException("Bad Request");
				}
			
			// 발생한 예외가 catch블록으로 던져서 예외가 잡히고 예외 메시지 출력
			} catch(InputMismatchException e) {		// 정수가 아닌 것을 입력받았을 때 뜨는 예외를 처리(무한루프에 빠져버림)
				sc.next();	// 잘못 입력된 것도 잡고 리얼 무한루프에 빠지는 것을 막아준다.
				System.out.println("처리 명령은 정수(0~4)입니다.");
				
			} catch(RuntimeException e) {			// 발생한 예외가 catch블록으로 던져서 예외가 잡히고 예외 메시지 출력
				System.out.println(e.getMessage());
			// inputmismatch예외는 자식클래스라서 부모클래스인 runtime예외보다 위에 위치시켜야 한다.	
			}
		}
	}
	// While문이 try-catch블럭 안에 있을 땐 예외가 발생하면 무한루프가 끝남
	// while문을 try-catch블럭 밖으로 빼면 예외가 발생해도 무한루프는 계속 되게 만들 수 있다.
	//		trycatch문을 while문 안으로 집어넣어라
	
	
	
	public static void main(String[] args) {
			new ParkingLot__throws().manage();
		
	}
	
	

	
	
	
}
