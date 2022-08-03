package ex05_lombok;

public class Main {

	public static void main(String[] args) {
		
		
		
		
		// getset 방법
		User user1 = new User();
		user1.setUserNo(1);
		user1.setId("kkyo");
		user1.setEmail("kkyo@gmail.com");
		System.out.println(user1);
		
		// constructor로 만드는 방법
		User user2 = new User(1, "kkyo", "kkyo@gmail.com");
		System.out.println(user2);
		
		// build패턴으로 만드는 방법
		User user3 = User.builder()
				.userNo(1)
				.id("kkyo")
				.email("kkyo@gmail.com")
				.build();
		System.out.println(user3);
	

	}

}
