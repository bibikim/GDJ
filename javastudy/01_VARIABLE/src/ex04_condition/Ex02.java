package ex04_condition;

public class Ex02 {

	public static void main(String[] args) {
		
		// 논리 연산자
		// 논리 AND : &&, 모두 true이면 true/하나라도 false이면 false 두 가지 이상의 조건이 사용될 때 사용
		// 논리 OR  : ||, 하나라도 true이면 true/모두 false이면 false
		// 논리 NOT : ! , true는 false로/false는 true로 반대로 바꿔주는 연산
		
		int a = 10;
		int b = 10;
		
		boolean result1 = (a == 10) && (b == 10);  // true. 논리 연산할 땐 식을 ()로 묶어주는 것이 좋음.
		boolean result2 = (a == 10) || (b == 10);  // true
		boolean result3 = (a == 10) && (b == 20);  // false
		boolean result4 = (a == 10) || (b == 20);  // true
		
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		
		int c = 10;
		
		boolean result5 = !(c == 10);  // false
		boolean result6 = !(c == 20);  // true
		
		System.out.println(result5);
		System.out.println(result6);
		
		// 연습.
		// 변수 n을 10 증가시킨 뒤 n이 100보다 크다면 true, 아니면 false
		int n = 95;
		boolean result7 = (n += 10) > 100;
		System.out.println(n);          // 105
		System.out.println(result7);    // true
		
		// 연습.
		// 변수 x를 1 증가시킨 뒤 x가 10과 같으면 true, 아니면 false
		int x = 9;
		boolean result8 = (++x) == 10;
		System.out.println(x);         // 10
		System.out.println(result8);   // true
		
		// Short Circuit Evaluation
		// 논리 AND : false가 발생하면 더 이상 진행하지 않는다. 최종 결과는 false이므로.
		// 논리 OR  : true가 발생하면 더 이상 진행하지 않는다. 최종 결과는 true이므로.
		int i = 10;
		int j = 10;
		boolean result9 = (i == 20) && (++j == 11);  // (i == 20)에서 false가 결과로 나왔기 때문에 뒤에 ++j 식은 연산이 진행되지 않음.
		System.out.println(result9);  // false
		System.out.println(j);        // 따라서 11이 아닌 10이라는 결과가 나옴.
		
		boolean result10 = (i == 10) || (++j == 11);  // OR연산이 i==10이라는 true를 만났기 때문에 뒤에 연산 진행하지 않음.
		System.out.println(result10);
		System.out.println(j);  // 따라서 11이 아닌 10이라는 결과가 나옴.
		
		// 보통 연산을 뒤로 뺀다. 앞에서 연산을 안 할 수도 있기 때문..?
		
		
		
		
		
	}

}
