package ex15_interface;

public class Main {

	public static void main(String[] args) {
		
		// 메소드 호출 연습
		
		Phone p1 = new SmartPhone();
		p1.call();
		p1.sms();
		((Computer) p1).game();      // game은 Phone의 메소드가 아니기 때문에 Computer로 캐스팅해준 다음에 호출
		((Computer) p1).internet();
		
		
		Computer p2 = new SmartPhone();
		((Phone) p2).call();		// call은 Computer의 메소드가 아니기 때문에 Phone으로 캐스팅해준 다음에 호출
		((Phone) p2).sms();
		p2.game();
		p2.internet();
		
		SmartPhone p3 = new SmartPhone();
		p3.call();
		p3.sms();
		p3.game();
		p3.internet();
		
		
				
		
		
	}

}
