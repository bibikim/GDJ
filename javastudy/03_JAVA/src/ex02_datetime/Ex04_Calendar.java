package ex02_datetime;

import java.util.Calendar;

public class Ex04_Calendar {

	public static void main(String[] args) {
		
		// java.util.Calendar 클래스
		// 현재 날짜 또는 특정 날짜를 나타낼 때 사용
		// 날짜의 특정 요소(년, 월, 일, 시, 분, 초, ...)를 쉽게 사용할 수 있다.
	
		Calendar cal = Calendar.getInstance();	// 객체 cal은 현재 날짜와 시간으로 구성		
		//getIns까지만 쓰고 목록 ^자동완성^ 뜨면 엔터로 입력
		// 이유! 자동완성을 통해 import를 자동화할 수 있다. 반드시 자동완성으로 입력하기
		// Calendar는 class type, cal은 object(객체) ->OOP 객체를 기반으로 프로그래밍을 한다
		// int a에서 int는 타입, a는 변수. 8개의 타입을 빼고 나머지는 모두 class 타입이다.
		
		// 년, 월, 일, 요일
		int year = cal.get(Calendar.YEAR); 		// int year = cal.get(1);   - Calendar.YE 쓰면 뜨는 int ~ 입력.
		int month = cal.get(Calendar.MONTH) + 1;	// 월은 밀려서 나온다. 그래서 +1 해서 코드입력.
		int day = cal.get(Calendar.DAY_OF_MONTH);   //month 기준 day. 한달에 몇 번째 날이냐
		int weekNo = cal.get(Calendar.DAY_OF_WEEK);  // 일주일의 몇 번째 날짜냐
		
		System.out.println(year);
		System.out.println(month);		// 월은 반환된 값이 0 ~ 11 (주의가 필요). 0 = 1월, 6 = 7월
		System.out.println(day);
		switch(weekNo) {
		case 1: System.out.println("일요일"); break;
		case 2: System.out.println("월요일"); break;
		case 3: System.out.println("화요일"); break;
		case 4: System.out.println("수요일"); break;
		case 5: System.out.println("목요일"); break;
		case 6: System.out.println("금요일"); break;
		default: System.out.println("토요일"); 
		}
		System.out.println(weekNo);		// 요일번호 : 일(1), 월(2), ..., 토(7). if나 switch를 사용하면 무슨 요일인지 출력 가능
		
		// cal. 찍고 나면 뜨는 목록: 객체가 가지고 있는 기능 = 메소드(메소드: 클래스 안에 포함되어있는 경우, 아닌 경우는 함수라 부름)
		// (int field :  field값에 정수. int로 결과가 나올것)
		

		// 오전/오후, 시, 분, 초
		int ampm = cal.get(Calendar.AM_PM);				// 오전(0), 오후(1)
		int hour12 = cal.get(Calendar.HOUR);			// 시(1 ~ 12)
		int hour24 = cal.get(Calendar.HOUR_OF_DAY); 	// 시(0 ~ 23)
		int min = cal.get(Calendar.MINUTE);				// 분(0 ~ 59)
		int sec = cal.get(Calendar.SECOND);				// 초(0 ~ 59)
		switch(ampm) {
		case 0: System.out.println("오전"); break;
		case 1: System.out.println("오후"); break;
		}
		System.out.println(hour12);
		System.out.println(hour24);
		System.out.println(min);
		System.out.println(sec);
		
		// timestamp 캘린더로 타임스탬프 알아내는 방법
		long timestamp = cal.getTimeInMillis();
		System.out.println(timestamp);
		
		
		// ctrl + class = 해당 클래스의 소스를 확인해볼 수 있다.
		
		
		
		
	}

}
