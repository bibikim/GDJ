package ex02_field;

public class SchoolMainPractice {

	public static void main(String[] args) {

		School school = new School(); 			// 학교를 만들고 학교 안에 학생 만들어서 집어넣기
		school.name = "상일중학교";
		
		Student student3 = new Student(); 		//모든 객체의 클래스 타입은 참조값
		student3.name = "차은우";
		student3.stuNo = "302920";
		Student student4 = new Student(); 		// 모든 객체 안에는 주소만 있다. 다 참조값으로 움직인대..
		student4.name = "구교환";
		student4.stuNo = "302921";
		
		school.students[0] = student3;
		school.students[1] = student4;
		
		for(int i = 0; i < school.students.length; i++) {
			System.out.println(school.students[i].name);
			System.out.println(school.students[i].stuNo);
		}
		

	}

}
