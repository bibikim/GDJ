package pracAPI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class KoficMain {

	public static void main(String[] args) {
		// Divide and Conquer 방식으로 코드 짜보기........ 메소드 하나 당 하나의 기능만. 기능별로 분리해서 짜는 방식
		

		//영화진흥위원회 API
		
		String key = "b56bbdb4c0755fa077d161c073814d94";
		
		Scanner sc = new Scanner(System.in);
		System.out.println("날짜(yyyymmdd) >>> ");
		String targetDt = sc.next();
		
		
		try {
			key = URLEncoder.encode(key, "UTF-8");
			targetDt = URLEncoder.encode(targetDt, "UTF-8");
			
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("인코딩 실패", e);
		}
		
			
		String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=" + key +"&targetDt=" + targetDt;
		       // 파라미터가 별로 없어서 걍 스트링으로 주고 파라미터도 뒤에다 덧붙임. 파라미터의 순서는 상관 없음!
		
		String response = getResponse(apiURL);	// 메소드화
		createFile(response); // file만드는 메소드. 반환타입 x 전달은 스트링
		
	}
	
	
	public static String getResponse(String apiURL) {   // 반환타입 String이니까 static String,  매개변수 String apiURL
		
		HttpURLConnection con = getConnection(apiURL);
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return readBody(con.getInputStream());  // 정상 스트림 넘겨주기   - ioexception 필요
			} else {
				return readBody(con.getErrorStream());  // 비정상 스트림 넘겨주기
			} // readBody의 매개변수는 inputstream, readbody는 반환타입이 스트링이어야 함.
			  // getResponse메소드가 String을 반환하니까
		} catch (IOException e) {
			throw new RuntimeException("API 요청 오류");
		}
	}
	
	public static HttpURLConnection getConnection(String apiURL) {    // 반환타입 http~, 메소드 getConnection
		
		try {
			
			URL url = new URL(apiURL);
			return (HttpURLConnection)url.openConnection();   // 반환하면 반환하는 데이터가 돌아가는 곳이 getConnection(apiURL) , 그러면 con으로 들어간다
			
		} catch (MalformedURLException e) {
			throw new RuntimeException("API 주소 오류", e); // 메시지와 예외 사유(e)를 같이 보내기
		} catch (IOException e) {
			throw new RuntimeException("API 연결 오류", e);
		}
		
	}
	
	public static String readBody(InputStream in) {
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(in))){         // try-catch-resources 문
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
			
		} catch (IOException e) {
			throw new RuntimeException("API 응답 오류");
		}
		
	}
	
	public static void createFile(String response) {   // 파일 생성
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("c:\\storage\\boxoffice.xml"))) {    // file없이 스트링으로 전달해도 된다!!
			bw.write(response);
			bw.flush();
			//  ㄴ 스트림에 남아있고 안 간게 있으면 모든걸 보내주는 것. 해도 그만 안해도 그만.
		} catch(IOException e) {
			throw new RuntimeException("파일 생성 오류", e);
		}
		
	}
	

}
