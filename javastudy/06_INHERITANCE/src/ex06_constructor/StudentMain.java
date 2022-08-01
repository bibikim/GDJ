package ex06_constructor;

public class StudentMain {

	public static void main(String[] args) {
		
		Student student = new Student("tom", "goodee");    // new 쓰고 ctrl + 스페이스바 하면 생성할 수 있는거 볼 수 있음
														   // 
		System.out.println(student.getName());
		System.out.println(student.getSchool());
		
		Alba alba = new Alba("jessica", "seoul univ", "library");
		System.out.println(alba.getName());
		System.out.println(alba.getSchool());
		System.out.println(alba.getCompany());
		

		
		
		
	}

}
