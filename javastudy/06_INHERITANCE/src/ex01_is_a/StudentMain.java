package ex01_is_a;

public class StudentMain {

	public static void main(String[] args) {
		
		Student student = new Student();
		
		student.eat();
		student.sleep();
		student.walk();			// ---^ Person에 있는 거지만 상속이 되었기 때문에 호출할 수 있다.
		student.study();
		
		

	}

}
