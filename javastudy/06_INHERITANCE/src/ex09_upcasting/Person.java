package ex09_upcasting;

public class Person {

	public void eat() {
		System.out.println("먹는다");
	}	
		
	// Person 타입의 객체가 호출할 수 있도록 추가해 둔 메소드	
	// 실행되지는 않는다. 실행은 override된 게 실행되는 것.
	public void study() {}
	
	public void work() {}
		
	}
	

