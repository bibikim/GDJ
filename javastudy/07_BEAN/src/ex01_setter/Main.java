package ex01_setter;

public class Main {

	public static void main(String[] args) {
		
		// Bean - 값을 가지고 있는 객체 (정보를 bean이라고 부르는거)
		
		User user = new User();
		user.setUserNo(1);
		user.setId("kkyo");
		user.setEmail("kkyo@gmail.com");
		
		
		System.out.println(user);		// 위에 값들 보려면 User클래스에서 오버라이드 toString 필요!
		
		// 여기서 user 를 bean 또는 VO라고 부른다.

	}

}
