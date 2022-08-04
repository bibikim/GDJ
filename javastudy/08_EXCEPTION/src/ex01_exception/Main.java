package ex01_exception;

import java.util.Scanner;

public class Main {
	
	
	public static void m1() {		// static붙이는 이유 : 안 붙이면 오류남. m1을 호출하는 애가 static(밑에메인)이라
									// static이어야 호출이 가능하다. static은 static만 부를 수 있다

		// NullPointException : null값이 어떤 메소드를 호출할 때 발생
		String[] hobbies = new String[5];  //취미를 다섯개 저장. 다섯개의 null값
		
		hobbies[1] = "수영";
		hobbies[2] = "러닝";
		hobbies[3] = "영화";
		hobbies[4] = "집콕";
	
		for(int i = 0; i < hobbies.length; i++) {
			if(hobbies[i].equals("수영")) {				// hobbies[0]의 값은 현재 null인데 equals"수영"을 호출하면서
														// nullPointException 발생
				System.out.println("취미가 저와 같군요");
			}											// 예외뜰 때, 밑에서부터 위로 보면서 예외를 확인해보면 됨
		}

	
	}					
	
	
	public static void m2() {		

		// NullPointException 회피
		String[] hobbies = new String[5];  //취미를 다섯개 저장. 다섯개의 null값

			hobbies[1] = "수영";
			hobbies[2] = "러닝";
			hobbies[3] = "영화";
			hobbies[4] = "집콕";

			for(int i = 0; i < hobbies.length; i++) {
				// 자바 실행은 왼->오이기 때문에 걸러줘야 되는게 있으면 앞쪽에 조건을 걸어줘야 한다.
				// 따라서 밑에 두 줄은 같은 코드가 아님. 
				if(hobbies[i] != null && hobbies[i].equals("수영")) { 	 // hobbies[i]가 null값이 아니고, 수영이랑 동일하면
				//if(hobbies[i].equals("수영") && hobbies[i] != null) 			
					System.out.println("취미가 저와 같군요");
				}											
			}


	}					


	public static void m3() {
		
		// NumberFormatException : String을 Number타입으로 변경할 때 발생(바꿀 수 없는게 전달되었을 때 발생)
		
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 입력(필수) >>> ");
		String name = sc.nextLine();			// sc.nextLine(); 엔터를 입력받게 하는 작업
		System.out.println("나이 입력(선택) >>> ");
		String strAge =  sc.nextLine();			// 입력 없이 Enter만 누르면 strAge는 빈 문자열을 가짐
		int age = Integer.parseInt(strAge);  	// "5" 를 5로 바꿔주는 작업
		
		
		System.out.println("이름: " + name + ", 나이: " + age + "살");
	}
	
	
	
	public static void m4() {
		
		// NumberFormatException 회피
		
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 입력(필수) >>> ");
		String name = sc.nextLine();			
		System.out.println("나이 입력(선택) >>> ");
		String strAge =  sc.nextLine();			
		int age;		//비어있는 문자열을 확인하는 작업
		if(strAge.isEmpty()) {
			age = 0;	// 문자열이 비어있으면 0으로 하겠다.
		} else {
			age = Integer.parseInt(strAge);  	// "5" 를 5로 바꿔주는 작업
		}
		
		
		
		System.out.println("이름: " + name + ", 나이: " + age + "살");
	}
	
	
	
	
	

	public static void main(String[] args) {
		m3();

	
		
		
		
		
		
	}

}
