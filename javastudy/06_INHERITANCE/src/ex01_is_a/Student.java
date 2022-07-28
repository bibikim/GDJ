package ex01_is_a;

// 서브클래스 (Student)
// extends 키워드를 사용
// 슈퍼클래스 Person의 모든 메소드를 자기 것처럼 사용 가능

// 후손(sub)은 자기 super클래스와 슈퍼클래스의 슈퍼, 슈퍼클래스의 슈퍼의 슈퍼 ... 다 쓸 수 있다.
// 끝없이 상속 관계 만들어낼 수 있다.


public class Student extends Person {			// ★★ extends Person을 넣어서 student와 person을 상속시킴
												// extends에 대신 is a 넣었을 때 말이 되면 상속 완.?

	public void study() {
		System.out.println("공부한다.");
	}
	
	
	
}
