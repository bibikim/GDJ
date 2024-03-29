package ex01_branch;

public class Ex03_else_if {

	public static void main(String[] args) {
		
		// else if문
		// 조건이 여러 개 사용되는 경우에 각 조건을 (하나씩) 처리한다. 필요한 만큼 줄줄이 쓸 수 있음.
		// if(조건) {
		//		실행문
		// } else if(조건) {
		//		실행문
		// } else if(조건) {
		//		실행문
		// } else {      //<-마지막 if(조건) 생략 가능.
		//	

		// 나이에 따른 결과 출력
		// 0 ~ 7   : 미취학아동
		// 8 ~ 13  : 초등학생
		// 14 ~ 16 : 중학생
		// 17 ~ 19 : 고등학생
		// 20 ~    : 성인
		
		int age = 5;
		
		if(age < 0 || age > 100) { 
			System.out.println("잘못된 나이");
		} else if(age <= 7) {
			System.out.println("미취학아동");
		} else if(age <= 13) {
			System.out.println("초등학생");
		} else if(age <= 16) {
			System.out.println("중학생");
		} else if(age <= 19) {
			System.out.println("고등학생");
		} else {
			System.out.println("성인");
		}
		
		
		// 연습.
		// 월에 따른 계절 출력
		// 봄   : 3 ~ 5
		// 여름 : 6 ~ 8
		// 가을 : 9 ~ 11
		// 겨울 : 12 ~ 2
		// 잘못된 월
		int month = 12;
		
		if(month < 1 || month > 12) {
			System.out.println("잘못된 월");
		} else if(month == 12 || month <=2) {
			System.out.println("겨울");
		} else if(month <= 5) {
			System.out.println("봄");
		} else if(month <= 8) {
			System.out.println("여름");
		} else {
			System.out.println("가을");
		}
			
		// 나머지 연산을 활용한 modular 연산
		int mod = month % 12;                        // 나머지 값을 이용한 연산.
		if(month < 1 || month > 12) {
			System.out.println("잘못된 월");
		} else if(mod <= 2) {                        // 나머지 값을 이용한 조건. 12를 12로 나누면 나머지 값이 0, 1, 2 (0을 12로 보고 겨울로 출력하는 거임)
			System.out.println("겨울");
		} else if(mod <= 5) {
			System.out.println("봄");
		} else if(mod <= 8) {
			System.out.println("여름");
		} else {
			System.out.println("가을");
		}
		
		// 연습.
		// 점수에 따른 학점
		// score	grade
		// 100 ~ 90   A
		// 89 ~ 80    B
		// 79 ~ 70    C
		// 69 ~ 60    D
		// 59 ~ 0     F
		int score = 77;
		char grade;
		
		if(score < 0 || score > 100) {
			 grade = 'X';
		} else if(score >= 90) {
			 grade = 'A';
		} else if(score >= 80) {
			 grade = 'B';
		} else if(score >= 70) {
			 grade = 'C';
		} else if(score >= 60) {
			 grade = 'D';
		} else {
			 grade = 'F';
		}
		System.out.println("점수는 " + score + "점");
		System.out.println("학점은 " + grade + "학점");
		
		// 연습.
		// 오직 일 수만 고려
		// 1일이 수요일이다.
		// n일 후 무슨 요일인지 출력하기.
		int day = 10;
		int n = 1;
		String weekName;  // 목요일
		
		day += n;
		
		if(day % 7 == 0) {
			weekName = "화";
		} else if(day % 7 == 1) {             // 7로 나눴을 때 나머지가 1이면 수, 2면 목, 3이면 금, 4 토, 5 일, 6 월, 0 화
			weekName = "수";
		} else if(day % 7 == 2) {
			weekName = "목";
		} else if(day % 7 == 3) {
			weekName = "금";
		} else if(day % 7 == 4) {
			weekName = "토";
		} else if(day % 7 == 5) {
			weekName = "일";
		} else {
			weekName = "월";
		}
			System.out.println(weekName + "요일");
			
		
	}

}
