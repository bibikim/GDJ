package ex04_builder;

public class Main {

	public static void main(String[] args) {
	

		User user = User.builder()
				.userNo(1)
				.id("kkyo")
				.email("kkyo@gmail.com")
				.build();   // : 이대로 만들어주세요. 끝!의 의미로 마지막에 넣어줌
		
		System.out.println(user);
		
	
		
	}

}
