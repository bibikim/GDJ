package ex04_throw;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// throw
		// 1. 예외 객체를 만들어서 직접 throw 할 수 있다. (던지면 catch가 잡읍) : new 객체
		// 2. 자바는 예외로 인식하지 않지만 실제로는 예외인 경우에 주로 사용된다.
		
		
		Scanner sc = new Scanner(System.in); 	
		try {
			System.out.println("나이 입력 >>> ");
			String strAge = sc.nextLine();
			int age = Integer.parseInt(strAge);	
			if(age < 0 || age > 100) {  // 나이 0~100을 정상으로 잡음
				throw new RuntimeException("나이는 0 이상 100 이하만 가능합니다."); // throw -> catch, new @@ -> e
			}									// ㄴ 필드 String message; 에 저장
			System.out.println(age >= 20 ? "성인" : "미성년자");
		} catch (Exception e) {		// 던져진 new RuntimeException를 e가 받음 (예외객체의 메세지는 객체e가 갖고 있다)
			System.out.println(e.getMessage());
		} finally {
			sc.close();		// 실제로 finally는 자원을 반납할 때 주로 사용된다. 
			System.out.println("finally 블록 실행");
			// 예외가 있어도 닫아주고, 없어도 스캐너를 닫아준다
		}
					
	}

		
		
		
		
		
	}


