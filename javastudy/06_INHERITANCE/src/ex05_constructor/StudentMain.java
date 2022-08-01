package ex05_constructor;

public class StudentMain {

	public static void main(String[] args) {
		
		Student student = new Student(); 	// 서브클래스의 생성자를 호출
											// 결과를 보면 부른건 Student인데 Person도 불러짐. 자동으로 호출
											// 매개변수가 없는 타입(default)은 JVM이 자동으로 진행한다.
											// public Student ()   <== 매개변수 x
		
	}

}
