package ex08_access_modifier;

public class Student {

	// this
	// 1. 현재 객체(만들어진 객체)의 참조값
	// 2. 현재 객체의 멤버(필드, 메소드)를 호출할 때 사용	this.stuNo, this.name가 가능하다.. (필드용으로 주로 사용)
	// 3. 생성자 내부에서 다른 생성자를 호출할 때 사용
	
	// 필드										 필드-생성자-메소드 순으로 만드는 것이 좋다
	private String stuNo;	// = this.stuNo
	private String name;	// = this.name 
	
	// 생성자(는 메소드니까 public)
	public Student() {								// Student student = new Student(); 때문에 만들어 놓은 것
		
	}
	public Student(String stuNo, String name) {
		this.stuNo = stuNo;
		this.name = name;
	}
	
	
	// 메소드
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	// Source - Generate getters and setters - 체크박스 다 클릭 - generate 버튼 => 이클이 작성해줌ㅎ
	// 이름은 반드시 정해진 이름을 사용해야 함.
	// 그래서 getter setter는 밑에 처럼 손으로 안 씀. 
	
	
}
	
	
	// 메소드
	//public void printThis() {
	//	System.out.println(this); 		// this: 클래스 내부에서 활용이 가능한 키워드
	//	System.out.println(this.stuNo);	// 2의 내용!
	//	System.out.println(this.name);	// this.@@@ 으로도 접근 가능하다
	// }
	
	//public void setStuNo(String stuNo) {
		//stuNo = pStuNo;
	//	this.stuNo = stuNo; 	// 객체의 멤버가 되기 때문에 필드값
	//}
	//public void setName(String name) {
	//	this.name = name;
	//}
	//public String getStuNo() {
	//	return stuNo;			// return this.stuNo; 도 가능하지만 여기서 this 쓰는건 굳이다
	//}
	//public String getName() {
	//	return name;
	//}
	
	
