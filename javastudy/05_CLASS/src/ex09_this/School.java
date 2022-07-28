package ex09_this;

public class School {

	// 필드
	private Student[] students; 	// 이 배열은 미완성. 선언만 했음
	private int idx;				// 인덱스가 필요함. 인덱스는 원래 0으로 시작하니까 손 안 봐도 됨
		  	  // ㄴ students 배열의 인덱스. students 배열에 저장된 학생수와 같다.
	
	
	// 생성자						  위 배열을 사용하기 위해 생성자 만들기
	public School(int cnt) {		  
		students = new Student[cnt];	// 2명 저장할 수 잇음. new student(2)니까. 배열의 길이만큼(0, 1)
	}
	

	//메소드
	public void addStudent(Student student) {	// student1이 여기 student로 들어왔다
	
		if(idx == students.length) {			// 인덱스가 배열의 길이와 같아지면 더 이상 사용할 수 없는 상태로 만들기
			System.out.println("Full");
			return;								// 더 이상 이 메소드는 실행하지 X. 그럼 밑에 식 실행X
		}
		students[idx++] = student;				// 그 student가 앞에 배열로 넘어갔다.
	}											// student1이 그대로 들어가는게 아니고 0이라서 idx뒤에 ++
	
	
		// = 위와 같은 코드
		//if(idx == students.length) {
		//	System.out.println("Full");
		//} else {
		//	students[idx++] = student;
		//}

	public void printStudents() {
		/*
		for(int i = 0; i < idx; i++) {		// 학생수가 없으면(0) 안 돌아가기 때문에 오류가 안 남.
			System.out.println(students[i].getName() + ", " + students[i].getStuNo());
		}
		*/
		
		for(Student student : students) {
			if(student != null) {
			System.out.println(student.getName() + ", " + student.getStuNo());
			}
		}
		
	}
		
		
	
	
	
	
	
}
	
	

