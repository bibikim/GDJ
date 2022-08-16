package prac1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

	public static void main(String[] args) {
		
		// c:\\storage 로 보내라
		// 요고 잘 해야됨!!!!!!!!!!!
		
		try {
			
			// 접속
			String apiURL = "https://kma.go.kr/XML/weather/sfc_web_map.xml";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 접속 확인(200 코드 확인)
			if(con.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("API 접속 실패");
				return; // 프로그램 끝
			}
			
			// 웹페이지 자바로 읽어오기(바이트기반 입력스트림이니까 문자입력스트림으로 변환 필수)
			// 바이트 입력 스트림 -> 문자 입력 스트림 -> 버퍼 추가
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				// ㄴ 저어기 괄호 안쪽 코드부터 왼쪽으로 가면서 풀어보면 됨...
				//InputStream in = con.getInputStream();                |--내가 볼 부분---|
				//InputStreamReader rd = new InputStreamReader(in);		|---보고 지우기---|
			
			File file = new File("c:\\storage", "sfc_web_map.xml");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			// readLine() 메소드를 이용한 복사
			String line = null;
			while((line = br.readLine()) != null) {
				bw.write(line + "\n");
			}
			
			// 닫기
			bw.close();
			br.close();
			con.disconnect();
			
			/*
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[100];
			int readCnt = 0;
			
			while((readCnt = rd.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCnt);
			}
			
			
					bw.write(sb.toString());
					bw.close();
					rd.close();
					con.disconnect();
			*/
			
			
		} catch (MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch (IOException e) {
			System.out.println(e.getMessage()); 	// 예외 사유
			System.out.println("API 서버 오류");
		}

			 
	}

}
