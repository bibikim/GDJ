package com.gdu.app15.util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;

import org.springframework.stereotype.Component;

@Component
public class MyFileUtil {

	// 파일명 : UUID값을 사용  - java에서 지원
	// 경로명 : 현재 날짜를 디렉터리로 생성해서 사용 -> 업로드 할 때마다 현재날짜로 폴더 생성됨
	
	public String getFilename(String filename) {
		
		// 파일이름 받아오는 이유 -> 파일이름에 확장자가 포함되어 있으니간~ 앞에 이름은 지워버릴고얄
		// 파라미터로 전달된 filename의 확장자만 살려서 UUID.확장자 방식으로 반환
		// 1. unique 처리를 위해서.(디렉터리 하나에 똑같은 파일명.확장자가 올라올 수 있음 -> 따라서 사용자들이 올린 이름을 그냥 쓰는 것은 위험! 파일 이름의 중복을 막아주기 위해서 unique 처리를 위함
		//    └> 원래 올렸던 이름은 db에 저장하긴 하는데, 저장할 때 이름(UUID)도 같이 저장할 거임.
		// 2. encoding이 필요 없음.(uuid값으로 반환하면 숫자와 영문으로마나 저장되기 때문에 인코딩 노필요)
		
		// 확장자 예외 처리   ex) 리눅스파일의 확장자 .tar.gz 
		String extension = null;
		if(filename.endsWith("tar.gz")) {
			extension = "tar.gz";   // 확장자가 tar.gz로 끝나면 extension을 아예 그냥 tar.gz로 하자
		} else {
			
			// 파라미터로 전달된 filename의 확장자만 살려서 UUID.확장자 방식으로 반환
			String[] arr = filename.split("\\.");  // .로 파일 이름 다 절단내주세요~ 라는 뜻.  "\\."인 이유 -> split이 정규식(. == 모든글자)을 받아오기 때문에 마침표자체로 인식하라고 \ 붙이는 것  (\. or [.])
												   // ddaef.adfeg.acvh.txt 와 같은 파일명이 존재할 수 있어서 String[]을 사용
			// 확장자는 언제나 배열의 마지막 요소 length - 1
			extension = arr[arr.length - 1];
			
		}
		
		// UUID.확장자
		return UUID.randomUUID().toString().replaceAll("\\-", "") + "." + extension;   // 첨부된 파일을 uuid를 랜덤으로 붙이고 .과 확장자 붙여서 반환하는데 '-'은 안 쓰겠다(replace ""빈문자열).

	}
	
	// 오늘 경로
	public String getTodayPath() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;  // month는 0~11 이므로 +1
		int day = calendar.get(Calendar.DAY_OF_MONTH);  // 해당 month의 @@일으로 표시해야 돼서 day_of_month
		String sep = Matcher.quoteReplacement(File.separator);  // file.separator는 윈도우의 /나 리눅스의 \를 알아서 넣어주는 것
		return "storage" + sep + year + sep + makeZero(month) + sep + makeZero(day);  // storage 밑에 year 밑에 ....~ 
	}
	
	// 어제 경로   -> 하루 한번씩 업로드된 거 정리하기 위해 만듦...
	public String getYesterdayPath() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);   // 1일 전. Calendar.DATE가 오늘이니까 -1
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;  // month는 0~11 이므로 +1
		int day = calendar.get(Calendar.DAY_OF_MONTH);  // 해당 month의 @@일으로 표시해야 돼서 day_of_month
		String sep = Matcher.quoteReplacement(File.separator);  // file.separator는 윈도우의 /나 리눅스의 \를 알아서 넣어주는 것(구분자)
		return "storage" + sep + year + sep + makeZero(month) + sep + makeZero(day);  // storage 밑에 year 밑에 ....~ 
	}
	
	// 1~9 => 01~09
	public String makeZero(int n) {
		return (n < 10) ? "0" + n : "" + n;
 	}


	
	
	
}
