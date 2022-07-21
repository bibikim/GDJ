package ex02_datetime;

import java.sql.Date;

public class Ex03_Date {

	public static void main(String[] args) {
		
		// java.sql.Date 클래스  -  java.sql이라는 패키지의 Date 클래스
		// 데이터베이스의 날짜 표시 방식에 맞춰 놓은 클래스
		// Oracle 데이터베이스의 날짜 타입("/", "-")과 매칭해서 사용

		Date now = new Date(System.currentTimeMillis());
		// Date ctrl + 스페이스바 -> javq.sql -> new Date(ctrl+스페바 -> Date(long Date = long타입의 date 입력 = timestamp 써라)
		System.out.println(now);
		
	
	}

}
