package practice01;

import java.util.Scanner;

public class Ex01 {

	public static void main(String[] args) {
	
		// 정답은 github에
		// 정답 보고 따라 적는건 도움X
		// 정답 고고 어떻게 해결했는지 이해해보고
		// 보지 않고 다시 풀어보기.

		
		// 1. 점수와 학년을 입력받아 60점 이상이면 합격, 60점 미만이면 불합격을 출력하라.
		//		4학년인 경우 70점 이상이어야 합격.

		Scanner sc = new Scanner(System.in);
		
		System.out.println("점수를 입력하세요(0~100) >>> ");
		int score = sc.nextInt();
		System.out.println("학년을 입력하세요(1~4) >>> ");
		int grade = sc.nextInt();
			if(score >= 60 && score <=100 && grade < 4) {
				System.out.println("합격");
			} else if(score >= 70 && grade == 4) {
				System.out.println("합격");			
			} else {
				System.out.println("불합격");
			}
		
	
		

	
		
		
		
		
		
		
	}

}
