package practice01;

import java.util.Scanner;

public class Ex05 {

	public static void main(String[] args) {


		
		// 5. 학생 3명의 점수를 입력 받아 평균과 1등, 꼴등 각각의 이름을 출력하라.
		// ?????? 이해하고 다시 풀기..
		
		Scanner sc = new Scanner(System.in);
		
			String[] names = {"뽀로로", "에디", "크롱"};
			int[] score = {names.length};
			
			for(int i = 0; i < score.length; i++) {
				score[i] = (int)(Math.random() * 101);
			}
			
			int total = score[0];
			int max = score[0];
			int min = score[0];
			int top = 0;
			int bottom = 0;
	
			for(int i = 1; i < score.length; i++ ) {	// 비교를 해야되니까 1번에 있는 사람과 2번에 있는 사람을 비교하겠다.
				total += score[i];
				if(max < score[i]) {
					max = score[i];
					top = i;							// 어떠한 점수를 가진 사람의 이름을 i(1 2 3) 라고 이름 붙인거 이름이라생각하면됨
				}
				if (min > score[i]) {
					min = score[i];
					bottom = i;
				}
			}

		
	}

}
