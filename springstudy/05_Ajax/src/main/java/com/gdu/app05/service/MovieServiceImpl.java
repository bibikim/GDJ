package com.gdu.app05.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieServiceImpl implements MovieService {

	@Override
	public String getBoxOffice(String targetDt) {   // string째로 넘기면 jackson이 알아서 변환해줌.
		  // └> 결과 전체가 문자열로 나간다!

		
		
		
		// API 요청 및 응답하기	
		
		// key
		String key = "b56bbdb4c0755fa077d161c073814d94";
		
		// ApiURL
		String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=" + key + "&targetDt=" + targetDt;
						// apiURL = "?key=" + key + "&targetDt=" + targetDt;
	
		URL url = null;
		HttpURLConnection con = null;
		
		//String result = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			
			// API 요청
			url = new URL(apiURL);   // MalformedURLException 발생
			con = (HttpURLConnection)url.openConnection();  // IOException 발생
			
			con.setRequestMethod("GET");   // 대문자 필수 지정
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			
			
			// API 응답
			BufferedReader reader = null;
			if(con.getResponseCode() == 200) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				// 바이트 스트림을 문자 스트림으로 바꿔주는 StreamReader가 필요
			}
			
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			//result = sb.toString();
			
			reader.close();
			con.disconnect();
			
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}

		
		System.out.println(sb.toString());
		
		// API로부터 가져온 모든 텍스트 정보.
		return sb.toString();
	}
	


}
