package ex06_constructor;

public class Student extends Person {	// extends Person 적어넣으면 05패키지의 경우와 달리 오류 뜸. 왜?
										// 서브클래스가 슈퍼클래스의 생성자를 호출하지 않았기 때문에. 왜 호출해야 됨?
										// 매개변수가 있는 경우이기 때문에 슈퍼생성자 호출이 필요하다.
	
	private String school;

	public Student(String name, String school) {		// person 생성할 땐 이름 줘야됨
										// 받아온 이름을 public person(String name)에 전달한다.
		super(name);					// 슈퍼클래스(Person)의 생성자를 super라고 부름
		this.school = school;			// 외부에서 name에 이름이 들어오면 그 값을 슈퍼네임에 전달, 전달해준 값이 퍼블릭펄슨 스트링네임에 전달
										// 그 네임은 필드값 this.name으로 전달이 되고 디.네는 private name;으로 가서 이름 저장
										// 따라서 getName하면 이름이 나올거임
										// source - generate constructor using field - 박스 체크하면 자동으로 만들어줌
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
	
	
}
