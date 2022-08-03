package ex03_singleton;

public class User {
	
	// static 필드
	// 객체 생성 이전에 메모리에 미리 만들어 두는 필드
	private static User user = new User();		// default타입.    // User(); = 생성자 호출
																	// = public User() { }

	private User() {	// User 객체 생성은 User 내부에서만 가능하다.
		
	}
	
	// static메소드(클래스 메소드)
	// 클래스가 생성될 때 함께 생성되는 메소드
	// 객체 생성 이전에 만들어지기 때문에 객체로 접근하지 않는다. 
	// 클래스이름으로 접근한다. 
	// User.getInstance() 로만 접근 가능. 클래스이름(User)으로 접근
	public static User getInstance() {	// 오직 이 방법으로만 user를 호출할 수 있음
		return user;			// User user = User.getInstnace();      ex)Calendar가 이렇게 생김					
	}
	
	
}
