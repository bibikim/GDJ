package ex06_method_overload;

public class Calculator {

	// 메소드 오버로딩
	// 1. 같은 이름의 메소드가 2개 이상 존재한다.
	// add가 3개인 것을 add메소드가 가 오버로딩 되어 있다고 말함
	// 2. 같은 이름을 가지고, 다른 매개변수를 가져야 오버로딩 할 수 있다.
	// 3. 반환타입은 오버로딩과 상관이 없다. 같아도 되고 달라도 된다.	
	
	
	int add(int a, int b) {					// 매개변수 2개
		return a + b;
	}
	
	int add(int a, int b, int c) {			// 3개
		return a + b + c;
	}
	
	int add(int a, int b, int c, int d) {	// 4개
		return a + b + c + d;
	}
	
	
	int add(int[] arr) {		// 여기서 arr과 main메소드 있는 arr은
		int total = 0;			// 같은 이름의 다른 변수. 다른 변수이지만 내용은 같아서 동일한 취급을 함.
		for(int n : arr) {		// 향상 for문 사용
			total += n;
		}
		return total;
	}
	
	
	// 		for(int i = 0; i <5; i++) 
	
}

