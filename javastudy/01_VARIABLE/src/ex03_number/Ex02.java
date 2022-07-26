package ex03_number;

public class Ex02 {

	public static void main(String[] args) {
		
		// 증감 연산
		// 1씩 증가하거나 감소하는 연산이다.
		// 증가는 ++, 감소는 --를 사용한다.
		
		// 전위 연산(pre operator)
		// ++a
		// 변수 a의 값을 1 증가시키고 사용한다.
		int a = 1;
		int b = ++a;
		System.out.println(a);
		System.out.println(b);
		
		// 후위 연산(post operator)
		// a++
		// 변수 a의 값을 사용하고나서 1 증가시킨다.
		int x = 1;
		int y = x++;  // x를 y에 저장한 뒤에 x를 1증가 시켜 2가 된다. 따라서 y는 1
		System.out.println(x);
		System.out.println(y);
		
		// 연습
		int i = 1;
		int j = 1;
		int result = i++ + --j;
		System.out.println(i);  // 2
		System.out.println(j);  // 0 > 1 감소시킨 후에 j의 값을 사용하기 때문에 -1 + 1 = 0
		System.out.println(result);  // 1(변수 i의 값 사용) + 0 = 1

	}

}
