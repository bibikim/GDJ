package ex02_two_dim;

public class Ex01_array {

	public static void main(String[] args) {
		
		// 2차원 배열
		// 1. 1차원 배열을 여러 개 관리하는 자료 구조
		// 2. 1차원 배열들의 길이는 서로 다를 수 있음
		
		// 2차원 배열의 선언 및 생성
		// 1. 2차원배열의 선언
		//		int[][] arr;
		// 2. 2차원배열의 생성      ex 좌석 예약 같은거  프로그래밍할 때 사용 가능
		//		1) arr = new int[3][2]; 	- 3행 2열의 구조(김밥 3줄, 각 2개씩 조각)	조각이 같은 길이
		//		2) arr = new int[3][];		- 3행 n열의 구조(김밥 3줄, 모두 다르게 조각)	하나씩 꺼내서 잘라야 됨. 조각이 다른 길이
		//		  arr[0] = new int[5];		- 1번째 1차원 배열(1번째 김밥은 5조각)		- 1차원 배열에 몇 개의 조각을 낼 것이냐, 1차원 배열에 생성이 필요
		//		  arr[1] = new int[3];		- 2번째 1차원 배열(2번째 김밥은 3조각)
		//		  arr[2] = new int[8];		- 3번째 1차원 배열(3번째 김밥은 8조각)
		
		// 2차원 배열의 요소
		// arr[0] - 이름 / arr[0] - 두번째 요소
		// arr[0][0], arr[0][1]  행ㅣ과 열ㅡ의 index i, j
		// 1. 인덱스를 2개 사용
		//		몇 번째 1차원배열인가? 해당 배열의 몇 번째 요소인가?
		// 2. 2차원배열이 관리하는 1차원배열
		//	  arr[0]
		//	  arr[1]
		//	  arr[2]
		// 3. 각각의 1차원배열이 관리하는 배열의 요소
		//	  arr[0][0], arr[0][1]            - 앞에[0]가 몇 번째 1차원배열이냐 / 뒤에[0]가 해당 배열의 몇번째 요소냐
		//	  arr[1][0], arr[1][1]
		//	  arr[2][0], arr[2][1]
		
		
		// 3행 2열 2차원배열
		int[][] arr1 = new int[3][2];
		
		for(int i = 0; i < arr1.length; i++) {			// i : 몇 번째 1차원 배열인가?
			for(int j = 0; j < arr1[i].length; j++)	{	// j : 1차원배열 몇 번째 요소인가?  arr1[i] : ??
				System.out.print(arr1[i][j] + " ");		
			}
			System.out.println();
		}
	
		
		// 3행 n열 2차원배열
		int[][] arr2 = new int[3][];
		arr2[0] = new int[5];	// 1번째 1차원배열의 길이는 5
		arr2[1] = new int[4]; 	// 2번째 1차원배열의 길이는 4
		arr2[2] = new int[8]; 	// 3번째 1차원배열의 길이는 8
		
		for(int i = 1; i < arr2.length; i++) {
			for(int j = 0; j < arr2[i].length; j++) {
				System.out.print(arr2[i][j] + " ");
			}
			System.out.println();
		}

		 // 행 열이 고정됐건 고정되지 않았건 코드가 동일
		
		
		
		
		
	}

}
