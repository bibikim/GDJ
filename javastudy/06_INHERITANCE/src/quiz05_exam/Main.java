package quiz05_exam;

public class Main {

	public static void main(String[] args) {
		
		Exam exam = new Exam("중간고사");	// exam이름만 받아주면 됨
		exam.setScore();		// 국, 영, 수 점수 0 ~ 100 랜덤 생성
		
		Student student = new Student("emily");
		
		student.setExam(exam);
		student.info();
		// 학생 이름: emily
		// 중간고사 성적
		// 국어 : , 영어 : , 수학 : , 총점 : , 평균 : ,
		
		
	}

}
