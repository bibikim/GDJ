package com.gdu.app05.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieServiceImpl implements MovieService {

	@Override
	public String getBoxOffice(String targetDt) {   // string째로 넘기면 jackson이 알아서 변환해줌.
		  // └> 결과 전체가 문자열로 나간다!

		
		
		
		// API 요청 및 응답하기	
		String key = "b56bbdb4c0755fa077d161c073814d94";
		String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=" + key + "&targetDt=" + targetDt;
		String result = null;
		
		try {
			
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			
			BufferedReader reader = null;
			if(con.getResponseCode() == 200) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			result = sb.toString();
			
			reader.close();
			con.disconnect();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println(result);
		
		return result;
	}
	


}
