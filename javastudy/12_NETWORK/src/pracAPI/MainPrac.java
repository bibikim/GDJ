package pracAPI;

import java.awt.dnd.DragGestureListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainPrac {

	public static void m1() {
		

		StringBuilder urlsb = new StringBuilder();
		String serviceKey = "1bYcGDEwh81BJx5E4lJXOhR5aFY6NfZZ9o2Esn3khsPIZIM8uzs61raBrbdv2xPCWXzlmw0n6QJwVXUhmoT9Jg==";
		
		try {
			
			urlsb.append("http://ws.bus.go.kr/api/rest/busRouteInfo/getRouteInfo");
			urlsb.append("?ServiceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			urlsb.append("&busRouteId=").append(URLEncoder.encode("100100112", "UTF-8"));
			urlsb.append("&resultType=xml");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		
		String apiURL = urlsb.toString();
		
		HttpURLConnection con = null;
		
		try {
			
			URL url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-type", "application/xml; charset=UTF-8");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedReader rd = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line = null;
			while((line = rd.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			rd.close();
			
		} catch (Exception e) {
			System.out.println("api 응답 실패");
		}
		
		String respond = sb.toString();
		
		File file = new File("C:\\storage", "apiPRAC.xml");
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(respond);
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void m2() {
		
		// XML 파싱
		File file = new File("c:\\storage", "apiPRAC.xml");
		
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		m1();
		

	}

}
