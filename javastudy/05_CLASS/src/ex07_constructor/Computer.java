package ex07_constructor;

public class Computer {

	// 생성자(Constructor)
	// 1. 객체 생성할 때 사용되는 특별한 메소드
	// 2. 특징
	//   1) 메소드 이름이 클래스 이름과 같다. (바꿀 수 X)
	//   2) 반환타입이 존재하지 않는다. (애초에 존재하지 X)
	// 3. 생성자는 필드 초기화 용도로 사용. (필드값을 초기화해서 객체를 생성할 때)
	
	// 디폴트 생성자(Default Constructor)
	// 1. 개발자가 생성자를 만들지 않으면 자바가 자동으로 생성하는 생성자 (개발자가 만들면 사용이 되지 않음)
	// 2. 아무 일도 안 하는 형태
	//	  반환타입 메소드명(매개변수) { }  => 보통의 메소드의 형태.
	//	            Computer() {}		   => 디폴트 생성자의 형태
	// ==	    new Computer()
	

	
	
	//	Computer () {							// 메소드로, 생성자로 인식
	//System.out.println("컴퓨터 생성");
	// }
	
	
	// 필드
	String model;
	int price;
	
	// 생성자
	Computer() {								// 매개변수 없는 상태
		
	}
	Computer(String pModel, int pPrice) {
		model = pModel;
		price = pPrice;
	}
	
	// 메소드
	void printComputerStatus() {
		System.out.println("모델: " + model);
		System.out.println("가격: " + price);
	}
	
	
	// new computer(); 필드값이 초기화 되지 않는 방법
	// new Computer("gram", 150); 필드값이 초기화 되는 방법   gram과 150이 인수
	// 메소드 호출할 때 전달하는게 인수, 받아서 저장하는게 매개변수
	
	
	
}
