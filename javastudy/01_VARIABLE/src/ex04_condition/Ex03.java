package ex04_condition;

public class Ex03 {

	public static void main(String[] args) {
		
		// 조건 연산자
		// 조건을 만족하는 경우와 그렇지 않은 경우 모두를 처리하는 연산
		// 형식
		//     조건 ? 만족하는 경우 : 만족하지 않은 경우 
		
		int score = 50;
		String result = (score >= 60) ? "합격" : "불합격";
		System.out.println(result);
		
		// 연습.
		// 순위가 1이면 "금메달", 순위가 2이면 "은메달", 순위가 3이면 "동메달"
		// 나머지 순위는 "없음"
		int rank = 5;
		String medal = (rank == 1) ? "금메달" : (rank == 2) ? "은메달" : (rank == 3) ? "동메달" : "없음";  // 동일한 연산자를 여러번 중첩해서 식을 세우면 됨.
		System.out.println(medal);
				
		// 연습.
		// 홀수는 "홀수", 짝수는 "짝수"
		// 힌트.
		// 홀수 - 2로 나눈 나머지가 1인 수 (나머지 연산자는 %, 몫은 /)
		// 짝수 - 2로 나눈 나머지가 0인 수
		int n = 19;
		String type = (n % 2 == 0) ? "짝수" : "홀수" ;
		System.out.println(type);
		
		// 연습.
		// 홀수는 "홀수", 짝수는 "짝수", 3의 배수는 "3의배수"
		// 힌트.
		// 3의배수 - 3으로 나눈 나머지가 0인 수(단, 0은 고려하지 않는다.)
		int a = 3;
		String type2 = (a % 3 == 0) ? "3의배수" : (a % 2 == 1) ? "홀수" : "짝수";  // 배수엔 짝수도 있고 홀수도 있기 때문에 3의배수를 먼저 체크해야한다.
		System.out.println(type2);
		
		// 연습.
		// 주민등록번호 뒷 7자리 중 첫 번째 숫자가 1,3,5이면 "남자", 2,4,6이면 "여자"
		int serial = 1234567;
		String gender = ((serial / 1000000) % 2 == 0) ? "여자" : "남자";  // 100만으로 나눴을 때 몫을 생각 + 1,3,5와 2,4,6은 홀짝으로 생각해서 나눴을 때 나머지를 생각해서 연산.
		System.out.println(gender);
		
		
		
		// 나혼자 연습.
		// 홀짝과 4의배수, 5의배수
		int four = 20;
		String type3 = (four % 4 == 0) ? "4의배수" : (four % 5 == 0) ? "5의배수" : (four % 2 == 1) ? "홀수" : "짝수";
		System.out.println(type3);
		
		

	}

}
