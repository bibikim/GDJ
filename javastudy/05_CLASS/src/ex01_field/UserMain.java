package ex01_field;

public class UserMain {
	//int a;			// 필드
						// 필드는 메소드에서도 사용 가능
	
	public static void main(String[] args) {
		//int b;		// 변수
		
		// 클래스(데이터타입) : User				첫 글자 대문자 = 클래스
		// 객체(변수 개념, 인스턴스) : user					아니면 = 객체
		
		//int a = 10;			// a는 변수. 8개 기본 타입은 모두 변수. 변수는 변수가 가진 값이 끝.
		//String s = "hi";		// s는 객체. 나머지는 다 객체. 객체는 객체가 가지고 있는 기능을 꺼낼 수 있다. 클래스타입은 뒤에 오는게 다 객체.
		//s.

		// 객체 선언
		//User user = null;			// 모든 클래스는 null값을 가진다
		// 동일패키지는 import 없이 클래스 사용 가능. ctrl+스페이스바는 다 하긴 해야 됨.
		
		// 객체 생성
		//user = new User();
		
		// 객체 선언과 생성을 한 번에
		User user = new User();							// new User(); - 메소드. 메소드(주황색)의 이름은 클래스(분홍색)의 이름과 같다.
		
		// 모든 User 객체는 필드값을 가지고 있다.
		// 마침표(.)를 이용해서 필드값을 호출한다.
		System.out.println(user.id);
		System.out.println(user.password);
		System.out.println(user.email);
		System.out.println(user.point);
		System.out.println(user.isVip);
		
		// 필드값 변경				 실무에서 이렇게 쓰진 않음. 문법상 가능하다 정도로만 알고 있자.
		user.id = "admin";
		user.password = "12345";
		user.email = "admin@web.com";
		user.point = 1000;
		user.isVip = (user.point >= 10000);
		System.out.println(user.id);
		System.out.println(user.password);
		System.out.println(user.email);
		System.out.println(user.point);
		System.out.println(user.isVip);
		
	
		
		
	}

}
