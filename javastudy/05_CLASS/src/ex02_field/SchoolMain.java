package ex02_field;

public class SchoolMain {

	public static void main(String[] args) {
		
		School school = new School();			// school타입(클래스) school객체 = 객체 생성
		school.name = "경인중학교";				// 학교 이름 넣어 주기
		
		Student student1 = new Student();		// Student타입 student1객체 = 객체1 생성 
		student1.stuNo = "11025";				// 학번, 이름 넣기
		student1.name = "우영우";
		Student student2 = new Student();		// Student타입 student2객체 = 객체2 생성 
		student2.stuNo = "11026";
		student2.name = "최수연";
		
		school.students[0] = student1;			// 스쿨클래스에서 student를 배열로 잡았으니 각각 [0][1]에 넣어주고
		school.students[1] = student2;		
		// 배열엔 index와 for문이 따라 나온다						
		for(int i = 0; i < school.students.length; i++) {			//	school.students // 배열
			System.out.println(school.students[i].stuNo);           // school.students[i] -> 이게 학생 1명이자 타입.
			System.out.println(school.students[i].name); 
																	// NullPointException: 아무것도 없는데 부르려고 할 때 뜨는 오류..
		}

		// 위치 옮기고 싶은 줄 드래그 -> alt + 방향키 줄 이동
		
		
		
		
		

	}

}
