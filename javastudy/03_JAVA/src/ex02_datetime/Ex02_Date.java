package ex02_datetime;

import java.util.Date;

public class Ex02_Date {

	public static void main(String[] args) {
		
		
		// 클래스 명명 규칙
		// 패키지 다음에 마침표(.)를 적고 클래스명을 적는다. (공식적인 클래스 이름을 부르는 방법) 패키지까지 포함해서 불러내야한다.

		java.lang.System.out.println("Hello");          // 이게 원래 시스템 클래스를 부르는 방법.
		
		// 예외 규칙
		// java.lang 패키지에 소속된 클래스들은 패키지를 생략할 수 있다. (나머지는 생략 불가. 패키지 다 적어줘야 됨)

		// java.util 패키지에 있는 Date 클래스를 사용하고 싶다면
		// 1. java.util.Date		        (원래 방법이지만 거의 안 씀.)
		// 2. import jave.util.Date;        (import 가져와서 쓰겠다는 뜻)
		//    Date
		
		// Date								Date + ctrl + 스페이스바 -> 맨 위에 패키지 밑에 import~ 들어옴
		
		// 모든 클래스들의 import는 자바가 자동완성 해줌.
		// 클래스를 넣을 땐, 잘 모르겠는거면 ctrl + 스페이스바 (클래스가 자바랭이면 생략 가능한데 아니면 import 넣어줘야 함)
	
		
		/////////////////////////////////////////////////////////////
		
		// 클래스와 객체 (하나의 클래스를 가지고 (수많은) 객체를 만든다. 
		// 1. 클래스 : 객체를 만들기 위한 설계도
		// 2. 객체   : 클래스를 이용해서 만든 실제 객체
		// 3. 일반적인 객체 생성 방법
		//	  클래스 객체 = new 클래스();
		
		// 메소드 사용
		// 1. 클래스를 이용해서 호출
		//		System.out.println()
		//		system.currentTimeMillis()
		//		System.nanoTime()
		//		Math.random()
		// 2. 객체를 만들어서 호출하는 방법(객체가 있어야 사용할 수 있는 것들은 호출해야)
		//		String str = new String("Hello");    // 클래스 String, 객체 str  Hello라는 문자를 가지고 있는 str객체가 만들어짐
		//		str.equals("Hello")
		//		  Date now = new Date(); 				// 클래스 Date, 객체 now
		//		  now.getMonth()
		//		    StringBuiler sb = new Stringbuiler(); // 클래스 Stringbuiler, 객체 sb
		//		    sb.append()
		
		//	ex) 붕어빵 붕1 = new 붕어빵();
		//	    붕어빵 붕2 = new 붕어빵();
		//	    붕어빵 붕3 = new 붕어빵();
		//	    붕어빵 붕1 = new 붕어빵();
		//	    붕어빵 붕1 = new 붕어빵(); 			-> 클래스 하나로 객체 여러 개 만듦
		
		// Date 클래스와 now 객체 (객체 이름은 내가 정해주면 됨)
		Date now = new Date();
		System.out.println(now);
			
		
	}

}
