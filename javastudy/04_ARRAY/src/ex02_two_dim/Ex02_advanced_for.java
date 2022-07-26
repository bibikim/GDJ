package ex02_two_dim;

public class Ex02_advanced_for {

	public static void main(String[] args) {
		
		String[][] timeTable = {                            // 1차원배열 5개가 있는 2차원배열
				{"국어", "윤리", "수학", "영어"},
				{"미술", "수학", "과학"},
				{"체육", "사회", "수학", "영어"},
				{"국어", "한자", "문학", "수학", "영어"},
				{"음악", "국어", "수학", "영어"}			// 초기화	
		};

		// 일반 for문
		for(int i = 0; i < timeTable.length; i++) {
			for(int j = 0; j < timeTable[i].length; j++) {
				System.out.print(timeTable[i][j] + " ");
			}
			System.out.println();
		}

		
		// 향상 for문
		// 2차원 배열에서 여러 개의 1차원 배열이 나온다. 1차원 배열에선 여러 개의 요소가 나온다.
		for(String[] weekName : timeTable) {				// 2차원: 타임테이블  1차원: 윅네임  요소: 코스
			for(String course : weekName) {
				System.out.print(course + " ");
			}
			System.out.println();
		}
		
		
		// 2차원 공부는 선택 But, 1차원은 필수
		
		
		
		
	}

}
