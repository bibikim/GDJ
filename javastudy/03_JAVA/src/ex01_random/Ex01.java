package ex01_random;

public class Ex01 {         						 // class 

	public static void main(String[] args) {    	 // class 안의 main이라는 method
		
		// 난수(Random number) 발생
		// Random 클래스, Math 클래스를 주로 활용한다.

		System.out.println(Math.random());
		
		// 		0.0 <= Math.random() < 1.0
		// ( =) 0%  <= Math.random() < 100%
		
		// 1. 확률 처리하기
		// 10% 확률로 "대박", 90% 확률로 "쪽박"
		if(Math.random() < 0.1) {
			System.out.println("대박");
		} else {
			System.out.println("쪽박");
		}
		
		
		// 2. (사용할)난수 값 생성
		
		// Math.random()					0.0 <= n < 1.0
		// Math.random() * 5				0.0 <= n < 5.0
		//   (int)(Math.random() * 5)         0 <= n < 5    casting 해서 소숫점 잘라버리기. 그럼 값은 0 1 2 3 4. 곱해준 숫자만큼 그 범위 안에서 값이 나온다
		//★★ (int)(Math.random() * 5) + 1     1 <= n < 6    값은 1 2 3 4 5. 1부터 5개의 난수가 발생 (+2하면 2부터 5개의 난수 발생)
		
		// 연습.
		// 주사위 2개 던지기.
		for(int n = 0; n < 2; n++) {
			int dice = (int)(Math.random() * 6) + 1;         // 1 <= n < 7.		값은 1 ~ 6	
			System.out.println("주사위 " + dice);
		}
		
		// 연습.
		// 6자리 숫자 인증번호 만들기
		// String code = "811214"
		String code = "";                                   // int로 하면 숫자가 더해지거나 앞자리가 0이 나오는 경우 때문에 string
		for(int n= 0; n < 6; n++) {							// for문이니까 증가문 ++ 때문에 n < 6?
			code += (int)(Math.random() * 10);              // += 연산으로 누적
		}
		System.out.println(code);	
		// 0.0 <= n < 10.0
		// 0   <= n < 10		(int)(Math.random() * 10)	 0~9까지 10개(곱한 숫자만큼)의 난수가 발생

		
		// (int)(Math.random() * 26) + 65									// 65에서 90 사이의 난수 발생
		System.out.println((char)((int)(Math.random() * 26) + 65));
		System.out.println((char)((int)(Math.random() * 26) + 'A'));		// 알파벳 인증번호를 만들 때 사용할 수 있다.
	
		// 연습.
		// 6자리 영문(대문자 + 소문자) 인증번호 만들기
		code = "";													// code 초기화
		for(int n= 0; n < 6; n++) {	
			if(Math.random() < 0.5) {								// 50%의 확률로 대,소문자를 랜덤으로.
				code += (char)((int)(Math.random() * 26) + 'A');	// A부터 26개
			} else {
				code += (char)((int)(Math.random() * 26) + 'a');	// a부터 26개
			}
		}
		System.out.println(code);
		
		
		
	}

}
