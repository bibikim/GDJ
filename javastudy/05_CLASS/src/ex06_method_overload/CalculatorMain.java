package ex06_method_overload;

public class CalculatorMain {

	public static void main(String[] args) {

		
		Calculator calculator = new Calculator();
		// ㄴex06에 있는거 불러야 됨 주의
		System.out.println(calculator.add(1, 1));
		System.out.println(calculator.add(1, 1, 1));
		System.out.println(calculator.add(1, 1, 1, 1));
		
		
		//ㄱ배열의 타입(int 브라켓)
		int[] arr = {1, 2, 3, 4, 5}; 		      
		System.out.println(calculator.add(arr));	// 15
			
	}

}
