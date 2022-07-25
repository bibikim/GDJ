package ex02_datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ex05_LocalDateTime {

	public static void main(String[] args) {
		
		// java.time.LocalDateTime 클래스
		// JDK 1.8부터 사용 가능한 클래스 (8버전. 내가 쓰는건 1.11ver)
		// 특정 날짜 요소 사용이 가능
		// 날짜의 패턴 지정이 가능
		
		LocalDateTime now = LocalDateTime.now();	// 현재 날짜와 시간. 객체 이름 now
		
		// 특정 날짜 요소 사용
		int year = now.getYear();			// 캘린더랑 메소드가 다름
		int month = now.getMonthValue();	// 1 ~ 12. 캘린더처럼 +1 필요 없음
		int day = now.getDayOfMonth();		// 1 ~ 31
		int hour = now.getHour();
		int min = now.getMinute();
		int sec = now.getSecond();
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(hour);
		System.out.println(min);		
		System.out.println(sec);		
		
		// 패턴
		// 패턴의 적용 결과는 문자열 String
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("a h:mm yyyy-MM-dd");    // 처음보는 클래스다 싶으면 ctrl + 스페이스바 헤서 import하기
		String date = dtf.format(now);		// 패턴이 들어간 객체 dtf
		System.out.println(date);
		
		
		
		
		

	}

}
