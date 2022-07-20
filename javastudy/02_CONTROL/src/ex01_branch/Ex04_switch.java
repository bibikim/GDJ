package ex01_branch;


public class Ex04_switch {

	public static void main(String[] args) {
		
		// switch문
		// (조건을 쓰지 않고) 표현식의 결과 값에 따른 분기를 처리한다. (딱 떨어지는 값, 정확한 값에 의해 동작. 범위X)
		// 표현식의 결과 값은 double, boolean 데이터타입일 수 없다.
		// switch(표현식) {
		// case 값: 실행문; break;
		// case 값: 실행문; break;       // break는 switch문을 끝내기 위해 놓는 것. break 붙이지 않으면 case 무시하고 실행문을 계속 실행하기 때문에 꼭 넣어줘야함
		// default: 실행문;              // default는 if문의 마지막문 else(나머지) 같은거라 보면 됨. default 뒤에 실행할게 없으니까 break를 넣어줄 필요 없음. default도 생략 가능
		// }
		
		int step = 3;
		
		switch(step) {
		case 1: System.out.println("1단계"); break;
		case 2: System.out.println("2단계"); break;
		case 3: System.out.println("3단계"); break;
		default: System.out.println("잘못된 단계");       // 마지막(default)엔 break 없어도 됨.
		}
		
		// 연습.
		// 각 층별 관리자를 출력
		// 1 ~ 2층 : 전지현
		// 3 ~ 4층 : 한지민
		// 5 ~ 6층 : 박은빈
		// 나머지  : 박보검
		
		int floor = 5;
		String manager;
		
		switch(floor) {
		case 1:
		case 2: manager = "전지현"; break;              // case 값이 같을 땐 break 안 넣어줘도 됨.
		case 3:
		case 4: manager = "한지민"; break;
		case 5:
		case 6: manager = "박은빈"; break;
		default: manager = "박보검";
		}
		
		System.out.println(floor + "층 관리자는 " + manager + "입니다.");
		
		// 연습.
		// 짝수, 홀수
		int n = 2;
		switch(n % 2) {
		case 0: System.out.println("짝수"); break;
		default: System.out.println("홀수");
		}
	
		// 연습.
		// 1~4분기 출력하기 
		int month = 1;
		switch((month - 1) / 3 ) {
		case 0: System.out.println("1분기"); break;    // 1 2 3월 > month - 1 하면 0 1 2. 이걸 3으로 나누면 몫이 0
		case 1: System.out.println("2분기"); break;    // 4 5 6월 > month - 1 하면 3 4 5. 이걸 3으로 나누면 몫이 1
		case 2: System.out.println("3분기"); break;    // 7 8 9월 > month - 1 하면 6 7 8. 이걸 3으로 나누면 몫이 2
		case 3: System.out.println("4분기"); break;    // 10 11 12월 > month - 1 하면 9 10 11. 이걸 3으로 나누면 몫이 3
		}
		
		// 연습.
		// 점수에 따른 학점
		int score = 100;
		String grade;
		
		switch(score / 10) {               // score를 10으로 나누고 난 몫을 이용하는 표현식을 쓴다.
		case 10:
		case 9: grade = "A"; break;
		case 8: grade = "B"; break;
		case 7: grade = "C"; break;
		case 6: grade = "D"; break;
		default: grade = "F";
		}
		System.out.println(score + "점은 " + grade + "학점입니다");
		
		
		// 연습.
		// 등급(1,2,3)에 따른 권한 출력
		// 1등급 : 쓰기 실행 읽기
		// 2등급 : 실행 읽기
		// 3등급 : 읽기
		// 나머지 : 없음
		int level = 1;
		String right = "";  // right가 비어있는 문자열("")을 가지고 있음. 연습문제처럼 문자열들을 누적시키는 경우에는 비워둔다. 따라서 += 복합연산 사용.
		
		switch(level) {
		case 1: right += "쓰기";
		case 2: right += "실행";
		case 3: right += "읽기"; break;
		default: right += "없음";
		}
		System.out.println(right);                 // break를 걸어주지 않음으로써 case3까지는 끊기지 않고 계속 실행 됨.
		
		
		//right += "읽기";
		//right += "실행";
		//right += "쓰기";

		
	}

}
