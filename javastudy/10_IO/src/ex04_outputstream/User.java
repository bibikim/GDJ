package ex04_outputstream;

import java.io.Serializable;

// 스트림을 이용해서 객체를 전송하려면 직렬화를 해야 한다.
// 직렬화가 필요한 객체는 Serializable 인터페이스를 구현해야 한다.
// Serializable 인터페이스를 구현한 클래스는 serialVersionUID 필드가 필요하다. (예외처리할 때 얘기 나왔던 것.)
// 직렬화 시킨 객체들을 다시 모을 때 같은 값을 가진 애들끼리 다시 모아주는 용도로 쓰인다. 내가 하는건 읎다

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1830845902387248224L;
	private int userNo;
	private String name;
	private int age;
	
	
	public User(int userNo, String name, int age) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.age = age;
	}
	
	
	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", name=" + name + ", age=" + age + "]";
	}
	
	
	// User user = new User ( 1, "kim", 30); 위에는.. 이렇게 만들 수 있게 user라는 생성자 만든거
	// 객체는 3개..! 이걸 직렬화 시켜서 보내야 함.
	
	// 직렬화가 가능하려면 Serializable이라는 인터페이스를 구현해줘야 함
	
	
}
