package ex01_one_dim;

public class Ex02_array {

	public static void main(String[] args) {
		
		
		// 배열의 초기화
		//int[] arr = new int[] {10, 20, 30, 40, 50};		// 이렇게 안 씀.
		int[] arr = {10, 20, 30, 40, 50};					// new int[] 지움. 초기화 정상 작동
		
		
		// 최대/최소
		int max = 0;
		int min = 100;		//범위를 0~100으로 잡았을 때.
		
		for(int i = 0; i < arr.length; i++) {
			if(max < arr[i]) {								//최대값보다 큰값이 나오면
				max = arr[i];								//그 값을 최대값으로 하겠다
			}	
		// max가 처음엔 0, arr[i]는 10  -> 10이 최대값
		// max가 10, arr[1]은 20		-> 20이 최대값. 결국 50이 최대값 됨
			if(min > arr[i]) {
				min = arr[i];
			}
		}
		System.out.println("최대: " + max);					
		System.out.println("최소: " + min);			// 최소값의 초기값은 0을 주면 안 됨. 제일 큰걸 가지고 가기때문에 반대값을 줘야 된다.
													// 따라서 max = 0을 주고, min = 100을 줘야 함
		
		//------------------------------------------------------------
		
		// 바운더리가 없을 땐, 첫번째 요소를 초기값으로.
		int maxi = arr[0];		
		int mini = arr[0];
		for(int i = 1; i < arr.length; i++) {		// i를 1로 바꿔준다
			if(maxi < arr[i]) {						// index 0과 1의 비교
				maxi = arr[i];
			}
			if(mini > arr[i]) {
				mini = arr[i];
			}
		}
		System.out.println("최대: " + maxi);					
		System.out.println("최소: " + mini);	
				
				
				
			
			
	}

}
