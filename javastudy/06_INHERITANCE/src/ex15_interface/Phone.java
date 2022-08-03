package ex15_interface;

public abstract class Phone {	// abstract class 들어내고 interface 입력 가능 -> interface로 바뀜
								// SmartPhone 에서 extends 지우고 implements Phone, Computer 로 수정하면 다중구현 가능

	public abstract void call(); {
		//System.out.println("전화기능");
	}
	
	public abstract void sms(); {
		//System.out.println("문자기능");
	}
	
	
	
	
}
