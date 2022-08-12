package ex01_network;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

	public static void m1() {
		
		// URL
		// 1. Uniform Resource Locator
		// 2. 정형화된 자원의 경로 
		// 3. 웹 주소를 의미
		// 4. 구성
		//  프로토콜://     호스트      / 서버 경로 ? 파라미터=값&파라미터=값&파라미터값=...//&쿼리 = "날씨"가 인코딩된 데이터
		//     https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EB%82%A0%EC%94%A8
		//   1) https : secure http 하이퍼텍스트 전송 프로토콜(통신규약)
		//   2) 호스트 : 서버 주소
		//   3) 서버경로 : URL Mapping   서버 경로에 따라 다른 내부처리가 이루어진다
		//   4) 파라미터 : 서버로 전송하는 데이터
		
		
		try {
			
			// URL 처리를 위한 URL 클래스
			String apiURL = "https://search.naver.com/search.naver?query=날씨";
			URL url = new URL(apiURL);		// URL도 예외처리 필수!
			
			// URL 확인
			System.out.println("프로토콜 : " + url.getProtocol());  // 객체인 url의 프로토콜 확인
			System.out.println("호스트 : " + url.getHost());
			System.out.println("파라미터 : " + url.getQuery());
		
		} catch(MalformedURLException e) {
			System.out.println("API 주소 오류");
		}
	
	}
	
	public static void m2() {
		
		// HttpURLConnection 클래스
		// 1. 웹 접속을 담당하는 클래스
		// 2. URL 클래스와 함께 사용
		// 3. URLConnection 클래스의 자식 클래스
		// 4. URL 클래스의 openConnection() 메소드를 호출해서 HttpURLConnection 클래스 타입으로 저장 ㄴㄱ
		//             ㄴ  이걸 호출하면 URLConnection 임! 이걸 HttpURLConnection에 저장한단 얘기
		// 부모의 메소드를 호출하지만 실제 저장은 자신에게 한다
		
		// ★★ 외워자~
		try {
			
			// 접속
			String apiURL = "https://www.kurly.com";
			URL url = new URL(apiURL);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();     
			// url.openConnection은 URLConnection클래스(부모)라 HttpURLConnection(자식)에 저장할 수 없으니 가능하도록 다운캐스팅
			
			// HTTP 응답 코드
			// 1. 200 : 정상
			// 2. 40X : 요청이 잘못됨(사용자 잘못)
			// 3. 50X : 서버 오류(개발자가 코드 잘못 짠 것)
			
			System.out.println("응답 코드 : " + con.getResponseCode());  //요청
			System.out.println("정상 : " + HttpURLConnection.HTTP_OK); // ★ 접속이 제대로 됐는지 확인
			System.out.println("Not Found : " + HttpURLConnection.HTTP_NOT_FOUND);
			System.out.println("Internal Error : " + HttpURLConnection.HTTP_INTERNAL_ERROR);
			
			System.out.println("컨텐트 타입 : " + con.getContentType());
			System.out.println("요청 방식 : " + con.getRequestMethod());  //응답
			
			
			con.disconnect(); // 접속 해제(생략 가능)
			
		} catch (MalformedURLException e) {		// URL 클래스 때문. 주소가 잘못됐다는 예외
			System.out.println("API 주소 오류");
		} catch (IOException e) {	// HttpURLConnection 클래스 때문
			System.out.println("API 접속 오류");
		}
		
	}
	
	
	public static void m3() {
		

		// HttpURLConnection과 스트림 (스트림 관련 모든 메소드를 쓸 수 있음)
		
		try {
			
			String apiURL = "https://www.naver.com";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			// -------------------------------------------------------------------> 커넥션 만드는 과정 및 작업
			
			// 바이트 입력 스트림
			InputStream in = con.getInputStream(); // 바이트스트림(입력스트림)
				// 웹페이지 화면 ===con===inputstrem==▶ JAVA
				// 각종 태그(문자)로 구성된 텍스트 파일인 웹페이지
				//              웹페이지를 읽을 수 있는데, 바이트기반의 입력스트림인 InputStream으로 읽어오기 때문에
				// InputStream으로 받으면 다 깨지니까 InputStreamReader로 읽어줘야 한다.
			
			// 문자 입력 스트림으로 변환
			InputStreamReader reader = new InputStreamReader(in);
			
			// 모두 읽어서 StringBuilder에 저장
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[100];	// 100글자씩 처리
			int readCnt = 0; 	// 실제로 읽은 글자수
			
			while((readCnt = reader.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCnt);   // sb에 모두 저장
			}
				
			
			// StringBuilder의 모든 내용을 C:\\storage\\네이버.html로보내기
			File file =new File("C:\\storage\\", "naver.html");
			FileWriter  fw = new FileWriter(file);	// 데이터 내보내
			
			fw.write(sb.toString());		// 접속했던 웹페이지를 그대로 가져온 것
			
			fw.close();
			reader.close();
			con.disconnect();
			
		} catch (MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch (IOException e) {
			System.out.println("API 접속 및 연결 오류");
		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		m3();
	}

}
