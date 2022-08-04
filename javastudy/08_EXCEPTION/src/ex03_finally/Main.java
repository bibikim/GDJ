package ex03_finally;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// finally 블록
		// 1. try-catch문 마지막에 추가하는 블록
		// 2. 언제나 마지막에 실행되는 블록.   예외가 발생 하든 하지 않든 마지막에.
		// 3. 생략가능
		
		Scanner sc = new Scanner(System.in); 	
		try {
			System.out.println("나이 입력 >>> ");
			String strAge = sc.nextLine();
			int age = Integer.parseInt(strAge);		
			System.out.println(age >= 20 ? "성인" : "미성년자");
		} catch (Exception e) {
			System.out.println("예외 발생");
		} finally {
			sc.close();		// 실제로 finally는 자원을 반납할 때 주로 사용된다. 
			System.out.println("finally 블록 실행");
			// 예외가 있어도 닫아주고, 없어도 스캐너를 닫아준다
		}
					
	}

		
		
		
		
		
	}


