package ex11_static;

public class MyMath {

	// static
	// 1. 정적 요소
	// 2. 객체 생성 이전에 메모리에 미리 만들어지는 공유 요소
	// 3. 클래스에서 1개만 만들어짐
	// 4. 클래스를 이용해서 호출하기 때문에 클래스 변수(필드), 클래스 메소드라고 부름.
	
	
	// 필드
	public static final double PI = 3.141592; 		// 이게 클래스 변수. 일반 변수와 다르다는걸 나타내기 위해 변수이름이 기울어짐
	// final 마지막 값이란 뜻. 못 바꾼다. 그래서 필드에서 프라이빗이 아닌 public. 안 바뀌는 값이니까.
	// static final-> s f 
	
	
	
	
	// 메소드
	public static int abs(int n) {					// static하나만 붙여주면 클래스가 되는것. static메소드가 됨
		return (n >- 0) ? n : -n;
	}
	
	
	public static int pow(int a, int b) {
		// a의 b제곱 반환
		// for문 구현
		int result = 1;
		for(int cnt = 0; cnt < b; cnt++) {
			result *= a;
		}
		return result;
	}
		
		/* ==
		 public static double pow(double a, double b) {
		 	return Math.pow(a, b);
		 */

	
	
	
}
