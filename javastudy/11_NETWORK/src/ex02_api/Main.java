package ex02_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Main {
	
	// 요청 
	// 1. request
	// 2. 클라이언트 --> 서버 측으로 보내는 데이터.        <-> 반대 과정은 응답
	
	// 파라미터
	// 1. Parameter
	// 2. 보내는 데이터(변수 개념)
	
	// 응답
	// 1. Response
	// 2. 서버 --> 클라이언트 에게 보내주는 것.
	
	// 요청과 응답은 모두 Java의 클래스로 만들어져 있당
	
	public static void m1() {
		// 공공데이터 수집을 위한 객체지향 프로그램 구현을 공부중... 0_0
		
		// 전국종량제봉투가격표준데이터
		
		
		// API 주소 만드는 작업 (주소 + 요청 파라미터 작업)
		String apiURL = "http://api.data.go.kr/openapi/tn_pubr_public_weighted_envlp_api"; // 주소에 요청이 다 들어감. (요청변수들.. Request Parameter)
		
		try {	// 밑에처럼 주소창에 붙여서 보내는 방법이 GET 방식 <-> POST 방식(로그인, 회원가입 등 경우에 쓰는 방식)
		
			String serviceKey = "1bYcGDEwh81BJx5E4lJXOhR5aFY6NfZZ9o2Esn3khsPIZIM8uzs61raBrbdv2xPCWXzlmw0n6QJwVXUhmoT9Jg==\r\n";
			// api 활용신청 후 key값을 하나 발급 받아야 한다. 그래야 작업 가능	
			apiURL += "?pageNo=0";	// 파라미터 처음은 ?~ 두번째부터는 &~
			apiURL += "&numOfRows=100";	// 숫자는 URLEncodr 굳이 안 해줘도 된다. 
			apiURL += "&type=xml";	// xml로 읽어오겠다.
			apiURL += "&CTPRVN_NM=" + URLEncoder.encode("인천광역시", "UTF-8");
			apiURL += "&SIGNGU_NM=" + URLEncoder.encode("계양구", "UTF-8");
			apiURL += "&WEGHTED_ENVLP_TYPE=" + URLEncoder.encode("규격봉투", "UTF-8");
			apiURL += "&WEGHTED_ENVLP_MTHD=" + URLEncoder.encode("소각용", "UTF-8");
			apiURL += "&WEGHTED_ENVLP_PRPOS=" + URLEncoder.encode("생활쓰레기", "UTF-8");
			apiURL += "&WEGHTED_ENVLP_TRGET=" + URLEncoder.encode("기타", "UTF-8");
			/* 필요 없음.... 위에것만 있어도 데이터 잘 읽어온당
			apiURL += "&PRICE_1=0";
			apiURL += "&PRICE_1_HALF=0";
			apiURL += "&PRICE_2=0";
			apiURL += "&PRICE_2_HALF=0";
			apiURL += "&PRICE_3=0";
			apiURL += "&PRICE_5=160";
			apiURL += "&PRICE_10=" + URLEncoder.encode("310", "UTF-8"); // 숫자를 URLEncoder 하려면 String으로 보내줘야하므로 "" 처리를 한다.
			apiURL += "&PRICE_20=0";
			apiURL += "&PRICE_30=0";
			apiURL += "&PRICE_50=0";
			apiURL += "&PRICE_60=0";
			apiURL += "&PRICE_75=0";
			apiURL += "&PRICE_100=3060";
			apiURL += "&PRICE_120=0";
			apiURL += "&PRICE_125=0";
			apiURL += "&CHRG_DEPT_NM=" + URLEncoder.encode("청결지도팀", "UTF-8");
			apiURL += "&PHONE_NUMBER=" + URLEncoder.encode("032-450-5464", "UTF-8");
			apiURL += "&REFERENCE_DATE=" + URLEncoder.encode("2020-02-01", "UTF-8");
			apiURL += "&instt_code=" + URLEncoder.encode("B551295", "UTF-8");
			 */
			apiURL += "&serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");
			// 			"파라미터=" + URLEncoder.encode("원본데이터", "UTF-8);
			// 이정도 분량은 StringBuilder를 사용하면 성능이 좋아진다.
				
		} catch(UnsupportedEncodingException e) {
			System.out.println("인코딩 실패");   // e.printStackTrace();
		} 
		
		// API 주소 접속
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");   // 접속 방식이 GET 방식이다라는 것을 알려주는 것. default이긴 함
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");  // 데이터타입은 xml로 주라라고 하는것. 
			// 'Content-Type' >> 리소스의 미디어타입(html, json, xml)을 나타낸다
			
		} catch(MalformedURLException e) {
			System.out.println("API 주소 오류(주소 틀림)");
		} catch(IOException e) {
			System.out.println("API 주소 접속 실패");
		}
		
		
		// 입력 스트림(응답) 생성     ->클라이언트가 읽어주는 것 
		// 1. 응답 성공 시 정상 스트림 사용, 실패 시 에러 스트림 사용
		// 2. 응답 데이터는 StringBuilder에 저장
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			
			if(con.getResponseCode() == 200) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}																				// 1번 처리 완.
			
			String line = null;     //
			while((line = reader.readLine()) != null) {
				sb.append(line + "\n");														// 2번 처리 완.
			}
			
			// 스트림 종료
			reader.close();
			
		} catch(IOException e) {
			System.out.println("API 응답 실패");
		} 
		
		String response = sb.toString();
		System.out.println(response);
		
		// 접속 종료
		con.disconnect();
		
	}
			
	// Content-Type
	// 자원의 종류를 나타내는 표기법. 
	// text/html   or   application/xml   or   application/json  +  ; charset=UTF-8
		
	public static void main(String[] args) {
		m1();

	}

}
