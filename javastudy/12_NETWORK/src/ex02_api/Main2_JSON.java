package ex02_api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Main2_JSON {
	
	
	public static void m1() {
		

		// 전국종량제봉투가격표준데이터
		
		
		// API 주소 만드는 작업 (주소 + 요청 파라미터 작업)
		String apiURL = "http://api.data.go.kr/openapi/tn_pubr_public_weighted_envlp_api"; // 주소에 요청이 다 들어감. 
		
		try {	// 밑에처럼 주소창에 붙여서 보내는 방법이 GET 방식 <-> POST 방식(로그인, 회원가입 등 경우에 쓰는 방식)
		
			String serviceKey = "1bYcGDEwh81BJx5E4lJXOhR5aFY6NfZZ9o2Esn3khsPIZIM8uzs61raBrbdv2xPCWXzlmw0n6QJwVXUhmoT9Jg==";
			// api 활용신청 후 인증키를 하나 발급 받아야 한다. 그래야 작업 가능	
			
			// 요청변수들.. Request Parameter   --->   "파라미터=" + URLEncoder.encode("원본데이터", "UTF-8);
			apiURL += "?pageNo=0";	// 파라미터 처음은 ?~ 두번째부터는 &~
			apiURL += "&numOfRows=100";	// 숫자는 URLEncodr 굳이 안 해줘도 된다. 
			apiURL += "&type=json";	// xml로 읽어오겠다.
			apiURL += "&CTPRVN_NM=" + URLEncoder.encode("인천광역시", "UTF-8");
			apiURL += "&SIGNGU_NM=" + URLEncoder.encode("계양구", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TYPE=" + URLEncoder.encode("규격봉투", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_MTHD=" + URLEncoder.encode("소각용", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_PRPOS=" + URLEncoder.encode("생활쓰레기", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TRGET=" + URLEncoder.encode("기타", "UTF-8");
			apiURL += "&serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");
	
				
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
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");  // 데이터타입은 xml로 주라라고 하는것. 
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
			
			if(con.getResponseCode() == 200) {   // == HttpURLConnection.HTTP_OK
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}																				// 1번 처리 완.
			
			String line = null;     //
			while((line = reader.readLine()) != null) {
				sb.append(line);														// 2번 처리 완.
			}
			
			// 스트림 종료
			reader.close();
			
		} catch(IOException e) {
			System.out.println("API 응답 실패");
		} 
		
		
		// API로부터 전달받은 json 데이터
		String response = sb.toString();
		    //System.out.println(response);
		
		// JSON File 생성
		File file = new File("c:\\storage", "api1.json");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);  // response => xml데이터를 담은 파일 생성
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// 접속 종료
		con.disconnect();
		
		
	}
	
	
	public static void m2() {
		
		// JSON 파싱
		
		// JSONObject 클래스 : { }, 객체를 의미 --> { }안에 있는건 하나의 오브젝트로 보면 됨
		// JSONArray  클래스 : [ ], 배열을 의미
		
		// 아래는 JSONObject가 3개(객체가 3개) 있는 데이터임
		File file = new File("c:\\storage", "api1.json");
		
		StringBuilder sb = new StringBuilder();
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {	// 파일 읽어오기
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		JSONObject obj = new JSONObject(sb.toString()); // json데이터를 string으로 보내주기
		// obj는 첫번째 {}로 보면 됨. 전체!
		// obj의 property는 response 하나임
		obj.getJSONObject("response");  // reponse의 value. 두번째 중괄호 열고닫고까지만 = obj2라고 하겠다
		JSONObject obj2 = obj.getJSONObject("response");
		JSONObject obj3 = obj2.getJSONObject("header"); // obj3의 property는 2개(resultCode, resultMsg). value타입은 모두 string""
		String resultCode = obj3.getString("resultCode");
		String resultMsg = obj3.getString("resultMsg");
		
		System.out.println(resultCode + ","+ resultMsg);
		
		
	}
	
	
	public static String m3() {
		
		// 기상청41_단기예보 조회서비스
	
		String serviceKey = "1bYcGDEwh81BJx5E4lJXOhR5aFY6NfZZ9o2Esn3khsPIZIM8uzs61raBrbdv2xPCWXzlmw0n6QJwVXUhmoT9Jg==";
		
		StringBuilder urlsb = new StringBuilder();
		try {
			
			 urlsb.append("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
			 urlsb.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			 urlsb.append("&numOfRows=").append(URLEncoder.encode("10", "UTF-8"));
			 urlsb.append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"));
			 urlsb.append("&dataType=JSON");
			 urlsb.append("&base_date=20220819");
			 urlsb.append("&base_time=0600");
			 urlsb.append("&nx=58");
			 urlsb.append("&ny=125");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiURL = urlsb.toString();
		
		HttpURLConnection con = null;
		
		try {
			
			URL url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedReader rd = null;
		StringBuilder sb1 = new StringBuilder();
		
		try {
			
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line = null;
			while((line = rd.readLine()) != null) {
				sb1.append(line);
			}
			
			rd.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String data = sb1.toString(); 
		
		File file = new File("c:\\storage", "api2.json");
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(data);
			bw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return data;
		
	}
		
	public static void m4( ) {
		
		//String data = m3();
		JSONObject obj = new JSONObject(m3()); // 오브젝트의 프로퍼티는 response 한개.
		JSONObject response = obj.getJSONObject("response"); // response의 프로퍼티는 header, body 두개
		JSONObject body = response.getJSONObject("body");  // body의 오브젝트도 JSON이니까 getJSONObject()
		JSONObject items = body.getJSONObject("items"); // 프로퍼티 한개. item
		JSONArray item = items.getJSONArray("item");  // 배열. item 밑에는 []로 시작되니까 JSONArray로
		// ㄴ배열의 요소 하나하나는 { }로 묶인 JSONObject
		for(int i = 0; i < item.length(); i++) {
			JSONObject element = item.getJSONObject(i);   // 배열의 요소 element. { }요소 하나하나
			System.out.println(element.getString("category") + " : " + element.get("obsrValue"));
			
			// element.getString("category") 요소에서 스트링인 category를 가져오겠다.
		}
		
	}
	
	
	public static String m5() {
		
		// 소상공인시장진흥공단_상가(상권)정보
		
			String serviceKey = "1bYcGDEwh81BJx5E4lJXOhR5aFY6NfZZ9o2Esn3khsPIZIM8uzs61raBrbdv2xPCWXzlmw0n6QJwVXUhmoT9Jg==";
			
			StringBuilder urlsb = new StringBuilder();
			try {
				
				 urlsb.append("http://apis.data.go.kr/B553077/api/open/sdsc2/storeZoneOne");
				 urlsb.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
				 urlsb.append("&key=").append(URLEncoder.encode("9940", "UTF-8"));
				 urlsb.append("&type=json");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			String apiURL = urlsb.toString();
			
			HttpURLConnection con = null;
			
			try {
				
				URL url = new URL(apiURL);
				con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			BufferedReader rd = null;
			StringBuilder sb1 = new StringBuilder();
			
			try {
				
				if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
					rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else {
					rd = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				
				String line = null;
				while((line = rd.readLine()) != null) {
					sb1.append(line);
				}
				
				rd.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String data = sb1.toString(); 
			
			File file = new File("c:\\storage", "api3.json");
			
			try {
				
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(data);
				bw.close();
				
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			return data;
		}
		
		
	
	public static void m6() {
		
		JSONObject obj = new JSONObject(m5()); // 가장 바깥쪽 { } => 자체가 obj
		JSONObject header = obj.getJSONObject("header"); // obj에서 header의 오브젝트를 가져와라
		JSONArray columns = header.getJSONArray("columns");
		/*
		for(int i = 0; i < columns.length(); i++) {
			System.out.println(columns.getString(i));   // 가져오는 애들이 전부 string이니까 getString
		}
		*/
		JSONObject body = obj.getJSONObject("body");
		JSONArray items = body.getJSONArray("items");
		JSONObject item = items.getJSONObject(0); // items 아래에 있는 요소 중 첫번째 요소. {} 하나로 묶인..
		//System.out.println(item.toString());
		
		String[] property = {"trarNo", "mainTrarNm", "ctprvnCd", "ctprvnNm", "signguCd", "signguNm", "trarArea", "coordNum", "coords", "stdrDt"};
		// item.get도 전부 string이니까 string[]을 만들어서 칼럼의 배열과 연결하기
		
		Map<String, Object> map = new HashMap<String, Object>(); // 상권번호~데이터기준일자 : String으로 받고 item은 스트링도 있고 인티저도 있어서 Object로 받음
		for(int i = 0; i < columns.length(); i++) {
			map.put(columns.getString(i), item.get(property[i]));
		}
		System.out.println(map);
		
		
		//map.put("상권번호", item.getInt("trarNo"));
		//map.put("상권명", item.get("mainTrarNm"));
		// ------------------------------------------------ item.get~은 반환타입이 Object이기 때문에 뒤에 int, string 없어도 된다.
		//map.put(~~~) 쭉 적는건 비효율적이니까 배열로 돌리기
		//
		
	}
	
	
	public static String m7() {
			
			// 기상청 RSS
			
			// 제주특별자치도 서귀포시 중문동	
			String apiURL = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=5013061000";
			
			// 접속
			HttpURLConnection con = null;
				
			try {
				
				URL url = new URL(apiURL);
				con = (HttpURLConnection)url.openConnection();
				//con.setRequestMethod("GET");
				//con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
				// ㄴ요청한게 아니라 내려받은걸 옮겨옹는 거니까 필요없는 부분.
				
			} catch (MalformedURLException e) {
				System.out.println("API 주소 오류");
			} catch (IOException e) {
				System.out.println("API 서버 오류");
			}
				
			
			// 응답 스트림 생성 및 응답 데이터 받기
			BufferedReader reader = null;
			StringBuilder sb = new StringBuilder();
				
			try {
				
				if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
					reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else {
					reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				
				String line = null;
				while((line = reader.readLine()) != null) {
					sb.append(line);
				}
				
				reader.close();
				
			} catch (Exception e) {
				System.out.println("API 응답 실패");
			}
			
			// XML 파일 생성
			//String response = sb.toString();  >> 스트링 따로 생성 안하고 바로 sb.toString() 넣어줘도 가능
			File file = new File("c:\\storage", "api4.xml");
				
				try {
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(file));
					bw.write(sb.toString());
					bw.close();

				} catch (IOException e) {
					System.out.println("파일 생성 실패");
				}
			
				return sb.toString();
			}
		
		
	public static void m8() {

		// XML 문서를 JSONObject로 변환
		JSONObject obj = XML.toJSONObject(m7());
		    // System.out.println(obj.toString());
		
		JSONArray dataList = obj.getJSONObject("rss")
								.getJSONObject("channel")
								.getJSONObject("item")
								.getJSONObject("description")
								.getJSONObject("body")
								.getJSONArray("data");      // ->까지 가야 jsonarray 나온당..
		// System.out.println(dataList.getJSONObject(0));
		for(int i = 0; i < dataList.length(); i++) {
			JSONObject weather = dataList.getJSONObject(i);   // 하나하나를 weather에 저장
			System.out.println(weather.getInt("hour") + "시 : " + weather.getInt("temp") + "도, " + weather.getString("wfKor"));
		}
		
	}

	public static void main(String[] args) {
		m8();
		

	}

}
