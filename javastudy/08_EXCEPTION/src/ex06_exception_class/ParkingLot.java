package ex06_exception_class;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ParkingLot {

	private Car[] cars;
	private int idx;
	private Scanner sc;
	
	public ParkingLot() {
		cars = new Car[10];
		sc = new Scanner(System.in);
	}
	
	public void addCar() throws MyException {   // 소속이 runtime 밑에가 아니고 my(checked exception)이기 때문에 throws 생략 불가. 꼭 써야 함
		if(idx==cars.length) {
			throw new MyException("FULL", 1000);	// 에러코드 1000	
		}
	}
	
	public void deleteCar() throws MyException{
		if(idx==0) {
			throw new MyException("EMPTU", 2000); 	// 에러코드 2000
		}
	}
	
	
	public void manage() {
		while(true) {
			try {
				System.out.println("1.");
				int choice = sc.nextInt();
				switch(choice) {
				case 1: addCar(); break;
				case 2: deleteCar(); break;
				case 0: return;
				default : throw new RuntimeException("Bad Request");
				}
			} catch(MyException e) {
				System.out.println(e.getMessage() + "[" + e.getErrorCode() + "]");
			} catch(InputMismatchException e) {
				System.out.println("처리 명령은 정수만 가능");
			} catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	// runtimeException과 MyExecption은 형제관계 같은 동등한 관계이기 때문에 뭘 먼저 적든 상관 없다.
	// MyException e. => e에는 string message랑 errorCode 두개의 정보가 들어있음
	// 이렇게 별도로 에러코드를 보여주고 싶을 때! exception 클래스를 만들어서 쓰면 된다.
	
	public static void main(String[] args) {
		new ParkingLot().manage();
	}
	
	
	
	
}
