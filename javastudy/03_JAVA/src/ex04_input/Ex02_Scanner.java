package ex04_input;

import java.util.Scanner;

public class Ex02_Scanner {

	public static void main(String[] args) {
		
		// ★ 26일 시험에 좀 나올 것
		// java.util.Scanner 클래스
		// 데이터타입별로 입력 받을 수 있는 메소드를 제공. int long doble string 다 따로따로 메소드를 갖는다.
		// int		: nextInt()
		// long		: nextLong()
		// double	: nextDouble()
		// String	: nextLine() - 공백 포함 입력, next() - 공백 포함 불가능
		
		
		Scanner sc = new Scanner(System.in); 	// 객체 sc는 System.in(키보드)으로부터 입력을 받는다.
		
		System.out.print("이름을 입력하세요 >>> ");
		String name = sc.next();
		// string을 입력 받을 때 next 메소드를 쓴다.
		
		System.out.print("나이를 입력하세요 >>> ");
		int age = sc.nextInt();			// int를 입력 받을 때 nextInt 메소드를 쓴다. 타입에 맞게끔 입력!
		
		System.out.println(name);
		System.out.println(age);
		
		sc.close();		// 생략이 가능하다. 키보드로부터 입력을 받았으니 닫으라는 뜻	
		// ctrl + f11 -> 콘솔에다가 입력하면 됨!

		
		// 연습. 
		// char 타입의 성별을 입력 받기      -  string 4 활용
		System.out.print("성별(남/여)을 입력하세요 >>> ");
		char gender = sc.next().charAt(0);		// charAt으로 출력
		System.out.println(gender);
		
		sc.close(); 	// 생략 가능.
	
		
	}

}
