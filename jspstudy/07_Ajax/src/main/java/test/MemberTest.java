package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.junit.Test;



	// service 동작 전반을 살펴보는 service단 테스트임!

public class MemberTest {

	@Test
	public void 회원목록테스트() {
		
		// 회원목록을 반환하는 MemberListService() 동작 전반을 살펴보는 테스트
		
		try {
			
			String apiURL = "http://localhost:9090/07_Ajax/member/list.do";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();   // 접속 완
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));   // inputStream으로 문자를 읽어오니까 inputStreamReder
			StringBuilder sb = new StringBuilder();    // sb에 들어있는 것 == members 배열에서 각 객체와 프로퍼티를 한줄씩 담음
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			JSONObject obj = new JSONObject(sb.toString());
			assertEquals(4, obj.getInt("count"));   // 4를 기대
			
			reader.close();
			con.disconnect();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 내가 정해놓은 json을 반환해주는 api 서비스 반환
		
	}

}
