package ex09_this;

public class SchoolMain {

	public static void main(String[] args) {
	
		Student student = new Student();
		
	//	System.out.println(student);		// student라는 객체 출력
	//	student.printThis();				// @뒤에 나오는 게 16진수인 참조값
		
		
		// student는 this는 같다.      System.out.println(student); = System.out.println(this); 같은 결과값이 나옴
		// 내부에서는 객체 이름으로 접근하지만 외부에서는 객체값을 this로 접근한 것
		
		Student student1 = new Student();
		student1.setStuNo("11025");
		student1.setName("전지현");
		
		Student student2 = new Student("11026", "정우성");		// setter 안 불러도 됨 this로 만들어져 있으니깐...
		
		School school = new School(2);   // 2를 cnt로 전달. 학생 2명을 학교에 집어넣는거임
		// School school = new School();  default타입으로는 못 만듦
		school.addStudent(student1);
		school.printStudents();
		school.addStudent(student2);
		school.addStudent(student1);		// 3명째. 또 넣으려고 하면 인덱스 범위를 벗어났다는 오류가 뜬다
											// 	if(idx == students.length) ~~ 식을 넣어줌으로써 Full이 뜸
		
		
		
		
	}

}
