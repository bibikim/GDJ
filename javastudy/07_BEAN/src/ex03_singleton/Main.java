package ex03_singleton;

public class Main {

		// singleton 방법으로 객체 생성하기
	public static void main(String[] args) {

		// singleton 객체는 하나만 만들 수 있다.(하나만 생성된다)
		
		User user1 = User.getInstance();
		System.out.println(user1);
		
		User user2 = User.getInstance();
		System.out.println(user2);
		
		// user 1, 2로 두개를 불렀지만 값은 똑같다. 하나의 동일한 유저이고 이름만 1, 2로 두개를 가진 것.
		// 따라서 여러 개의 객체가 만들어지면 안되는 경우에 쓰인다.		
		
		// User user = new User();  외부에서는 새로운 객체를 생성할 수 없음.
		//   -> 사용 불가. 클래스에 만들어진 객체를 가져다 쓰는 것 밖에 못 한다.
		// 객체는 하나만 만들어두고 그거 쓰라고 하는 방식이 singleton
		
		// new User() -> 원래는 내용이 같든 안 같든 계속 새로 생기지만 singleton에서는 이걸 막아둠
		
	}

}
