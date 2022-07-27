package ex03_method;

public class Calculator {

	// 메소드(Method)
	// 함수의 개념과 동일
	// 클래스 내부에 포함된 함수는 메소드라고 부름
	
	// 계산 기능(기능을 메소드라 보면 됨)
	
	// add 메소드 정의
	// 1. int : 반환타입(add 메소드를 실행하면 int 타입의 결과값이 반환된다.)
	// 2. add : 메소드명(마음대로 지으면 됨)
	// 3. int a, int b : 매개변수(add 메소드를 호출할 때는 int 타입의 값 2개가 전달되어야 한다.)
	int add(int a, int b) {
		int result = a + b;
		return result;	// 반환값. 반환값의 결과는 int (result의 타입은 int)
	}
	// 들어가는 값(a, b)을 인수라고 하고, 함수의 결과로 나오는 것을 반환값이라 한다.
	
	// 2, 3 이 int ab에 저장되고 값이 result로 나와서 int로 return, calculator로 가서 answer가 나옴
	
	
	// sub 메소드 정의
	int sub(int a, int b) {
		int result = a - b;
		return result;		// result(결과를) return(반환)한다.
	}
		
	// mul 메소드 정의
	int mul(int a, int b) {
		int result = a * b;
		return result;
	}
		
	// div 메소드 정의 ( div(7, 2) --> 3.5 )    >> 인수는 정수, 결과는 double로. casting 필요
	double div(int a, int b) {
		double result = (double)a / b; 	// 둘중 하나를 casting
		return result;
	}
	
	
	
	
}
