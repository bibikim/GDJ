package ex02_loop;

public class Ex01_for {

	public static void main(String[] args) {
		
		// for문
		// 연속된 숫자를 생성할 때 주로 사용한다.        얼마에서 얼마까지 사용하는구나 정도로 생각하자.
		// 배열과 함께 자주 사용된다.
		// for(초기문; 조건문; 증감문(++ or --)) {
		// 		실행문
		// }
		
		// 1 ~ 10. 초기문으로 1을 변수에 저장
		for(int n = 1; n <= 10; n++) {          //++n도 가능.
			System.out.print(n);
		}

		//초기문 -> 조건문(만족하면) -> 실행문 -> 증감문 -> 조건문 -> 실행문 -> 증감문  
		//이 순서로 반복. 초기문은 한번만
		
		System.out.println();      // 줄 바꿈. ()안에 출력할게 없고 줄만 바꾸고 싶을 때.

		// 연습.
		// 10 ~ 1
		for(int n = 10; n >=1; n--) {
			System.out.print(n);
		}
		System.out.println();
		
		// 연습.
		// 구구단 7단 출력. (1 ~ 9)      >> 7을 고정시키고 1부터 9까지 증가시키기
		for(int n = 1; n < 10; n++) {
			System.out.println("7x" + n + "=" + (7*n));
		}
		
		// 연습.
		// 1 ~ 100 사이의 모든 3의 배수만 출력하기
		for(int n = 1; n <=100; n++) {
			if(n % 3 == 0) {
				System.out.print(n + " ");
			}
		}
		System.out.println();
		
		// 참고 예제.
		int wallet = 0;
		wallet += 1000;
		wallet += 2000;
		wallet += 3000;
		System.out.println(wallet);
		
		// 연습.
		// 1 ~ 100 모든 정수 더하기 (5050)
		int total = 0;
		for(int n = 1; n <= 100; n++) {
			total += n;
		}
		System.out.println("전체 합: " + total);
		
		// 연습.
		// begin ~ end 사이의 모든 정수 더하기
		// begin과 end 중 누가 큰 지 모르는 상황
		// begin을 end보다 항상 작은 값으로 바꾼 뒤 begin ~ end 모두 더하기
		// begin이 end보다 크다면 begin과 end를 교환. (임시 변수 temp을 이용)
		int begin = 100;                        // begin을 1로 잡고 end를 100으로 잡았다면
		int end = 1;							// 밑에 if문은 필요 없는 것
		
		if(begin > end) {
			int temp;
			temp = begin;
			begin = end;
			end = temp;
		}
		int sum =0;
		for(int n = begin; n <= end; n++) {
			sum += n;
		}
		System.out.println(sum);
		
		
		// 연습.
		// 평점(1~5)에 따른 별(★) 출력하기
		int point = 2;
		//String star;
		//star = "★";
		//star = "★";                // 두 번 넣어도 결과는 별 하나. 따라서 이 문제에선 쓸 수 없음.
		
		String star = ""; 
		for(int n = 1; n <= point; n++) {
			star += "★";                // 포인트 갯수만큼 별 넣는 연산
		}
		System.out.print(star);
		
		
		// for(int n = 0; n < point; n++) {
		//		star += "★";
		// }
		// System.out.print(star);               위랑 같은 식. 0부터 시작하는 경우에는 조건문에서 등호를 빼준다.
		
		
		
		
		
		
		
		
		
		
		
	}

}
