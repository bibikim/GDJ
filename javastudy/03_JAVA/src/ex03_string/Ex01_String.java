package ex03_string;

public class Ex01_String {

	public static void main(String[] args) {
				
		String str1 = "hello"; 	// 저장된 주소값(참조값)에 가야 hello가 있다
		String str2 = "hello";	
		
		/*
		str1	0x123
		str2	0x123
		
				hello	0x123		hello는 123번지에 있다.
		
		*/
		System.out.println(str1 == str2);		// 같음
		
		
		String str3 = new String("hi");
		String str4 = new String("hi");                   // *참고 핑크색 class, 주황색 method
		
		/*
		str3	0x123
		str4	0x456
		
				hi		0x123
				hi		0x456	메모리에 같은 내용의 텍스트가 2개가 생긴 것. 다른 스트링의 참조값으로 존재.
		*/
		System.out.println(str3 == str4);		// 같지 않음
												// 문자열을 비교할 땐 == 을 쓰지 않음. 안 되는 거임.  t/f뜨지만 안되는거.
		
		// 1. 문자열 동등비교
		boolean result1 = str1.equals(str2); 	// 참조값 비교x 저장된 문자열을 비교하는거.
		boolean result2 = str3.equals(str4);	// 문자열 비교할 땐 equals라는 메소드를 사용한다.
		System.out.println(result1);			// 클래스를 활용한 메소드, 객체를 활용한 메소드. 두 가지 방법이 있음
		System.out.println(result2);			// str1~4는 객체. 따라서 객체를 활용한 메소드
		
		if(str1.equals(str2) ) {
			System.out.println("str1, str2는 같아요");
		} else {
			System.out.println("str1, str2는 달라요");
		}
		
		if(!str3.equals(str4)) {				// ! 는 not 의 의미. ! ~ equals = 같지 않다.  >> 가급적 쓰지 않는다.
			System.out.println("str3, str4는 달라요");
		} else {
			System.out.println("str3, str4는 같아요");
		}
		
		// 2. 대소문자를 무시한 문자열 동등 비교
		String str5 = "Hello World";
		String str6 = "hELLo wORLD";
		System.out.println(str5.equals(str6));    		// false. 한글자만 달라도 다른 문자열로 인식.
		
		boolean result3 = str5.equalsIgnoreCase(str6);	// equalsIgnoreCase 대소문자 무시하고 비교하라.
														//upper case 대문자, lower case 소문자. case=대소문자
		
		// 3. 문자열 길이 반환
		String name = "우연아";
		int length = name.length();
		System.out.println("글자수 : " + length);		// 공백(띄어쓰기)도 글자수 인식된다. 공백도 문자
		
		
		System.out.println("연아야 파혼해");
		System.out.println("그 결혼 반댈세");
		System.out.println("엉니 가지마.......");
		
		// 4. 특정 위치의 문자(char)만 반환
		// 특정 위치 
		// 인덱스(index)라고 함		★★
		// 글자마다 부여된 정수값	★★
		// 0으로 시작. 				★★	첫번째 글자는 index가 0, 두번째는 1, ...
		System.out.println(name.charAt(0));			// char가 어디에(At) 있는지 반환
		System.out.println(name.charAt(1));
		System.out.println(name.charAt(2));
	
		// "a" = 스트링 타입
		// 'a' = 변수char 타입			-둘은 다른 것
		
		// 5. 문자열의 일부 문자열(String)을 반환	★★
		//  1) substring(begin) : 인덱스 begin(포함)부터 끝까지 반환
		//  2) substring(begin, end) : 인덱스 begin(포함)부터 인덱스 end(불포함)까지 반환
		// substring(0, 1) 	>> 0인 첫번째 글자만 반환. 0 포함 1 불포함
		System.out.println(name.substring(0, 1));
		System.out.println(name.substring(1));		// 0~1
		System.out.println(name.substring(0, 3));	// 0~2
		
		// 6. 특정 문자열을 찾아서 해당 인덱스(int)를 반환
		//   1) indexOf
		//		(1) 발견된 첫 번째 문자열의 인덱스를 반환. (동일한 패턴이 여러 개 있는 경우. ex 우영우)
		//		(2) 발견된 문자열이 없는 경우 -1을 반환
		//   2) lastIndexOf
		//		(1) 발견된 마지막 문자열의 인덱스를 반환
		//		(2) 발견된 문자열이 없는 경우 -1을 반환
		int idx1 = name.indexOf("우");
		int idx2 = name.indexOf("연아");
		int idx3 = name.lastIndexOf("우");
		int idx4 = name.lastIndexOf("연아");
		System.out.println(idx1);
		System.out.println(idx2);
		System.out.println(idx3);
		System.out.println(idx4);
	
		// 예제) 192.168.101네트워크 주소.91호스트 주소(last~이용)
		
		// 7. 문자열이 특정 패턴으로 시작하는지 여부를 boolean(true, false) 반환
		//		starWith(문자열)
		if(name.startsWith("김")) {
			System.out.println("김씨입니다.");
		}else {
			System.out.println("김씨가 아닙니다.");
		}
		
		// 8. 문자열이 특정 패턴으로 끝나는지 여부를 boolean(true, false) 반환
		//		endWith(문자열)
		String filename = "image.jpg";	//jpg, png로 끝나면 이미지로 가정
		if(filename.endsWith("jpg") || filename.endsWith("png")) {
			System.out.println("이미지입니다.");
		} else {
			System.out.println("이미지가 아닙니다.");
		}
		
		// 9. 문자열이 특정 패턴을 포함하는지 여부를 boolean(true, false) 반환
		String email = "hanbizzz@naver.com";
		if(email.contains("@") && email.contains(".")) {
			System.out.println("이메일입니다.");
		} else {
			System.out.println("이메일이 아닙니다.");
		}
		
		// 10. 불필요한 공백 제거(좌우 공백)
		String message = "  안녕  하세요  ";  		 // 안녕 앞에 공백, 하세요 뒤에 공백 제거
		System.out.println(message.trim());			 // 공백만 제거
		System.out.println(message.trim().length()); // 공백 제거와 글자수까지

		// 11. 대소문자 변환하기
		// toUpeerCase() : 대문자로 변환하기
		// toLowerCase() : 소문자로 변환하기
		String source = "best of best";
		System.out.println(source.toUpperCase());	// 소스 자체는 변하지 않는다. 변환을 하면 이렇게 바뀐다지 자체는 바뀌지 않음
		System.out.println(source.toLowerCase());
		
		// 12. 찾아 바꾸기
		// replace(old, new) : old를 new로 변환하기
		source.replace("best", "worst");     	//를 string replaced에 옮겨 담아라가 밑에.
		String replaced = source.replace("best", "worst");
		System.out.println(source);		 		// source는 그대로, 변환을 하면 이렇게 바뀐다는 것
		System.out.println(replaced);			// source 자체가 바뀌는 것이 아니다
		
		// 주의. replaceAll() 메소드는 특정 문자열을 찾아서 변환하는 것이 아님.
		String ip = "192.168.101.91";
		String replacedIp = ip.replaceAll(".", "_");    // 192_168_101_91을 기대 
		System.out.println(replacedIp);					// 변환하면 ___________
														// 마침표는 모든 문자를 뜻함. "."은 문자열(string)을 말하는게 X 
														// 기대하는 값을 출력하려면 "\\." 하면 됨
		
		String pass = "hanbizzz";
		String replacedPass = pass.replaceAll("z", "x");
		System.out.println(replacedPass);
		
		// 13. 빈 문자열인지 여부를 검사한 뒤 boolean(true, false) 반환
		String id = " ";
		if(id.isEmpty()) {
			System.out.println("빈 문자열");
		} else {
			System.out.println("빈 문자열 아님"); 		// 공백도 문자열. 데이터 들어있어요~
		}
		
		
		String iu = " ";
		if(iu.trim().isEmpty()) {						// 공백을 빈 것으로 처리하고프면 공백 제거 trim 메소드를 쓴다.
			System.out.println("빈 문자열");
		} else {
			System.out.println("빈 문자열 아님"); 		
		}
		
		
		// 연습. 
		//파일이름을 파일명과 확장자로 분리
		// 단, jpg, git, png 이미지인 경우에만 작업을 진행한다.
		String fullName = "apple.jpg";					// a의 인덱스는 0,  .을 lastIndexof(".")
		String fileName = "";			// apple		// 마지막 . 다음의 문자열
		String extName = "";			// .jpg
		int idxOfDot = fullName.lastIndexOf(".");
		fileName = fullName.substring(0, idxOfDot);
		extName =  fullName.substring(idxOfDot + 1);
		System.out.println(fileName);
		System.out.println(extName);
		
		
		// 연습. 
		// 문자열 "abc12345def67890ghijk"에서
		// 아라비아 숫자 1234567890을 제외하고 한 글자씩 화면에 출력하시오.
		String str = "abc12345def67890ghijk";
		for(int i = 0; i <= str.length() - 1; i++) {
			System.out.println(str.charAt(i));
		}
		
		for(int i = 0; i < str.length(); i++) {                 // -1 없애고 =를 없앰  ex <=4 == <5
			if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				continue;
			}
			System.out.println(str.charAt(i));					// 이 방식의 코드를 보편적으로 사용. <5 방식
		}
		
		// str의 인덱스 범위 : 0 ~ str.length() - 1     길이-1가 마지막 인덱스 숫자
		// && 사잇값 구할 때 사용. n>=0 && n<=9
		
		
		for(int i = 0; i < str.length(); i++) {    
			char ch = str.charAt(i);					// charAt이 3번이나 불리니까 깔끔하게 하기 위해 변수 ch에 넣어놓기.
			if(ch >= '0' && ch <= '9') {
				continue;
			}
			System.out.println(ch);					// 이 방식의 코드를 보편적으로 사용. <5 방식
		}
		
		
		
		
		
		
		}
		
		
	}

