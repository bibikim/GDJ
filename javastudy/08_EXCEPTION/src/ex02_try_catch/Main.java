package ex02_try_catch;

import java.io.File;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void m1() {
		try {
			String[] hobbies = new String[3];
			hobbies[1] = "swimming";
			hobbies[2] = "running";				// 배열에 null값 포함되어있음
			for(String hobby : hobbies) {		// 향상 for문 = 하나하나 비교할 때, idx연산 안 쓸 때 유용
				System.out.println(hobby.substring(0, 2));
			}
		} catch(NullPointerException e) {		// catch(클래스타입 객체(객체이름은 e로 통일해서 쓴다))
			System.out.println("NullPointerException 발생");
		}							
	}		// (RuntimeException e), (Exception e)로도 예외처리 가능!
			// 왜냐하면 두개 다 null~예외의 부모클래스이기 때문에 상속받아서.

	public static void m2() {
		try {
			String input = "20,21, ,22,23,24,25";
			String[] inputs = input.split(",");			// 6개로 쪼개서 배열을 만들어줘라
			int[] ages = new int[inputs.length];		// inputs = {"20", "21", ... "25"} <- 이렇게 쪼개기
			for(int i = 0; i < inputs.length; i++) {	// ages = {20, 21, ... 25} <- "" 떼주기
				ages[i] = Integer.parseInt(inputs[i]);  
				System.out.println("변환 값: " + ages[i]);
			}	
		} catch(NumberFormatException e) {		// Runtime~, Exception~ 다 가능.
			System.out.println("NumberFormatException 발생");   // 공백 부분
		} catch(Exception e) {
			System.out.println("Exception 발생");
		}	
			// 예외가 여러 개인 경우, 여러개의 catch블럭을 달아줄 수 있다. 다중catch블럭 가능.
			// 순서대로(위>아래) 예외처리가 되기 때문에 자식 먼저 배치, 부모를 나중에 배치 해야 한다.
			// 자식에서 예외처리가 안 되면 밑으로 내려와 부모에서 예외처리가 되게끔 배치
		
	}
	
	public static void m3() {
		// 어떤 예외가 발생하는지 확인해서
		// try - catch문 넣기 (Exception, RuntimeException은 사용하지 않기)
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("정수1 >>> ");
			int a = sc.nextInt();
			System.out.println("정수2 >>> ");
			int b = sc.nextInt();
			System.out.println(a + "+" + b + "=" + (a + b));
			System.out.println(a + "-" + b + "=" + (a - b));
			System.out.println(a + "*" + b + "=" + (a * b));
			System.out.println(a + "/" + b + "=" + (a / b));
			System.out.println(a + "%" + b + "=" + (a % b));
			sc.close();
		} catch(InputMismatchException e) {			// 이 예외는 java.util이기 때문에 ctrl+space로 import 필수
			System.out.println("InputMismatchException 발생");
		} catch(ArithmeticException e) {
			System.out.println("ArithmeticException 발생");
		}
	
	}

	public static void m4() {
		
		try {
			File file = new File("C:\\sample.txt");
			FileReader fr = new FileReader(file); 	// try-catch문이 없으면 실행이 불가한 Checked Exception
		} catch (Exception e) {
			// TODO: handle exception				// try-catch문이 없으면 오류 뜸. 예외처리를 했는지 안 했는지 확인
		}											// try-catch문을 넣어줘야 실행
	}
	// 예외처리를 했는지 확인하는 
	
	public static void main(String[] args) {
		m4();

	}

}
