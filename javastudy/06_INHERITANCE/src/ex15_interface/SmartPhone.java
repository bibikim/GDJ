package ex15_interface;



			//상속(extends) 먼저, 구현(implements) 나중
public class SmartPhone extends Phone implements Computer {

	@Override
	public void call() {
		System.out.println("전화기능");
	}
	@Override
	public void sms() {
		System.out.println("SMS기능");
	}
	@Override
	public void game() {
		System.out.println("게임기능");
		
	}
	@Override
	public void internet() {
		System.out.println("인터넷기능");
		
	}
	
	// 부모가 둘이면 스마트폰의 사용 가능한 타입은 3가지
	// 폰타입, 컴퓨터타입, 스마트폰타입 전부 가넝
	
	
	
}
