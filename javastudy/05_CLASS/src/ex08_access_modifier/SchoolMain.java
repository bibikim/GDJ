package ex08_access_modifier;

public class SchoolMain {

	public static void main(String[] args) {
	
		Student student = new Student();
		
	//	System.out.println(student);		// student라는 객체 출력
	//	student.printThis();				// @뒤에 나오는 게 16진수인 참조값
		
		
		// student는 this는 같다.      System.out.println(student); = System.out.println(this); 같은 결과값이 나옴
		// 내부에서는 객체 이름으로 접근하지만 외부에서는 객체값을 this로 접근한 것
		
		
		student.setStuNo("11025");
		student.setName("전지현");
		
		Student student2 = new Student("11026", "정우성");		// setter 안 불러도 됨 this로 만들어져 있으니깐...
		
		
		
	}

}
