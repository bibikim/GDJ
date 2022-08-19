package pracAPI;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainPrac {

	public static void m1() {
		

		StringBuilder urlsb = new StringBuilder();
		
		try {
			
			urlsb.append("http://api.visitkorea.or.kr/openapi/service/rest/DataLabService");
			urlsb.append("?ServiceKey=1bYcGDEwh81BJx5E4lJXOhR5aFY6NfZZ9o2Esn3khsPIZIM8uzs61raBrbdv2xPCWXzlmw0n6QJwVXUhmoT9Jg==");
			urlsb.append("&pageNo=1");
			urlsb.append("&numOfRow=10");
			urlsb.append("&MobileOS=ETC");
			urlsb.append("&MobileApp=" + URLEncoder.encode("AppTest", "UTF-8"));
			urlsb.append("&startYmd=20210513");
			urlsb.append("&endYmd=20210513");
			urlsb.append("&_type=xml");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiURL = urlsb.toString();
		
		HttpURLConnection con = null;
		
		try {
			
			URL url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-type", "xml");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		

	}

}
