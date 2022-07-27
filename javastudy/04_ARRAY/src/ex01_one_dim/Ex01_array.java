package ex01_one_dim;

public class Ex01_array {

	public static void main(String[] args) {
		
		// 배열(Array)
		// 1. 여러 개의 변수를 하나의 이름으로 관리하는 자료 구조
		// 2. 구성 요소
		//   1) 인덱스 : 각 변수의 위치 정보. 0으로 시작
		//   2) 배열명 : 모든 변수를 관리하는 하나의 이름
		// 3. 각 변수는 배열명에 대괄호[]와 인덱스를 붙여서 구분
		
		// 배열 선언 및 생성		- 배열을 선언하고 생성해야 배열 완.
		// 1. 배열 선언
		//    int[] arr;			- 둘 다 같은 표현이지만 위에걸 쓴다. int배열 타입. int타입 X
		//    int arr[];
		// 2. 배열 생성
		//    관리할 변수의 갯수를 지정해야됨. 이게 생성
		//    arr = new int[3];
		// 3. 배열 선어 및 생성
		//    int[] arr = new int[3];	3개를 관리하겠다고 정했기 때문에 3개의 int를 관리. 세 개 다 arr이라 부를 수 있다.
											// 이름은 같아도 구분을 해야하기 때문에 배열명과 인덱스 붙임
		// 배열 요소
		// 1. 배열로 관리되는 각각의 변수. -  int[] arr = new int[3];  변수가 3개
		// 2. 모든 배열 요소의 호출
		//    arr[0]						- 첫 번째 배열의 요소. 첫 번째 변수
		//    arr[1]
		//    arr[2]						- 세 개 변수 각각의 이름임. 인덱스로 구분! 
		// 3. 배열 요소는 자동으로 초기화된다.(어떤 값을 가진다.) 	/ 일반 변수는 어떤 값을 가지지 않는다. 초기화 X  	- ex) int a;
		//    값이 없음을 의미하는 0, 0.0, false, null 값을 가진다.	/ boolean에서 0은 false, string에서 값이 없음은 null
		
		// 배열의 장점
		// * 변수 3개가 있는 상황
		// 일반 변수						배열
		// int a, b, c;						int[] arr = new int[3];
		// System.out.println(a);			for(int i = 0; i < 3; i++) {
		// System.out.println(b);				System.out.println(arr[i]);     arr[i] => 0, 1, 2
		// System.out.println(c);			}
						
		// 각각을 출력할 때 이름이 다르니 왼쪽처럼 모두 각각 출력할 수 밖에 없는데 
		// for문을 이용해서 배열하면 간단. 자료가 많을수록 장점이 됨.
		
		int[] arr = new int[3];	
		
		for(int i = 0; i < 3; i++) {		// 배열 쓸 때 변수는 i j k 를 주로 씀
			System.out.println(arr[i]);		// 생성만 하고 초기화 하지 않았기 때문에 자동초기화가 없었다면(일반 변수였다면) 
		}									// 오류가 나지 않는 상황. 이미 값을 가지고 있다는 것을 의미
											// int이기 때문에 초기화값 (0, 0, 0)
		
		arr[0] = 100; 						// 첫번째 요소에 100을 주고싶다면 이렇게
		arr[1] = 50;						// 두번째 		  50	
		arr[2] = 80;						// 세번째		  80	
		
		for(int i = 0; i < 3; i++) {				// 3은 배열의 갯수. int[]안에 숫자와
			System.out.println(arr[i]);				// for문 안에 범위 숫자와 동일해야됨.
	}												// 따라서 100 50 80 출력.
	
		
		//int total = 0;
		//total += arr[0];
		//total += arr[1];
		//total += arr[2];
	
		// 위에 세개의 배열의 평균 구하기
		int total = 0;
		for(int i = 0; i < 3; i++) {
			total += arr[i];				// arr[0], [1], [2] 누적시켜 더한다
		}
		double average = (double)total / 3;
		// double average = (total / 3.0); 	- 결과를 저장하는 값이 double이기 때문에 double로 casting하거나 소숫점을 붙인다.
		System.out.println("평균 : " + average + "점");
		
		
		// arr.length => 배열의 길이를 가져와서 적어라. 그러면 배열의 변수를 일일히 고칠 필요 없음. 배열 생성하는 곳만 변경
		// for문 안의 3을 arr.length로 바꾸면 종속되지 않는 코드라 할 수 있다.
		
		
		// --------------------------------------------- 위에 예문을 간단한 arr.length로 바꾼 연산. 
		arr[0] = 100; 						
		arr[1] = 50;				
		arr[2] = 80;						
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);				
	}
			
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		double aver = (double)sum / arr.length;
		System.out.println("평균 : " + aver + "점");
		
		
		// 가입창에 약관 동의 체크 버튼같은 것도 String 배열로 구현한 것
		
		
		
		}
}
