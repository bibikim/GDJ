package ex07_constructor;

public class ComputerMain {

	public static void main(String[] args) {
		// 객체 생성 가능(디폴트 생성자로써) 
		
		Computer myCom = new Computer("gram", 150);	// 내가 만들지 않았지만 내부적으로 만들어져 있다
		myCom.printComputerStatus();
		
		
		Computer yourCom = new Computer();			// (); -> 인수가 없는 상태. 매개변수가 없는 생성자를 찾는 방법이 필요
													// new Computer(); 생성자로 만들 수 없음. 메소드기 때문에 메소드 overload로 만들자
													// 메소드 이름(뉴컴)이 같고 매개변수가 다르기 때문에 오버로딩 가능
													// Computer메소드에 Computer() {} 만들어줌으로써 오류 해결
		yourCom.printComputerStatus();				// 인수 없으니까 자동 초기화값 null, 0 나옴
		
		
		
		
		// 정보 은닉
		// 필드값을 숨기고 메소드를 통해서 접근할 수 있게 하는 것
		// 안 쪽에 있는 정보를 직접 접근하는건 안됨
		// 메소드를 통해서 접근하면 결과물을 내어 주겠다
		// 접근 제어자  public - 누구나 접근 가능한 권한
		//				private - 아무도 못 본다. 필드값: private/ method(생성자) : public
		//				default - 같은 패키지에 있으면 클래스가 달라도 볼 수 있다. 

		
		
		
	}

}
