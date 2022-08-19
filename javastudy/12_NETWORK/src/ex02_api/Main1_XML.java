package ex02_api;

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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main1_XML {
	
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
		String apiURL = "http://api.data.go.kr/openapi/tn_pubr_public_weighted_envlp_api"; // 주소에 요청이 다 들어감. 
		
		try {	// 밑에처럼 주소창에 붙여서 보내는 방법이 GET 방식 <-> POST 방식(로그인, 회원가입 등 경우에 쓰는 방식)
		
			String serviceKey = "1bYcGDEwh81BJx5E4lJXOhR5aFY6NfZZ9o2Esn3khsPIZIM8uzs61raBrbdv2xPCWXzlmw0n6QJwVXUhmoT9Jg==";
			// api 활용신청 후 인증키를 하나 발급 받아야 한다. 그래야 작업 가능	
			
			// 요청변수들.. Request Parameter   --->   "파라미터=" + URLEncoder.encode("원본데이터", "UTF-8);
			apiURL += "?pageNo=0";	// 파라미터 처음은 ?~ 두번째부터는 &~
			apiURL += "&numOfRows=100";	// 숫자는 URLEncodr 굳이 안 해줘도 된다. 
			apiURL += "&type=xml";	// xml로 읽어오겠다.
			apiURL += "&CTPRVN_NM=" + URLEncoder.encode("인천광역시", "UTF-8");
			apiURL += "&SIGNGU_NM=" + URLEncoder.encode("계양구", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TYPE=" + URLEncoder.encode("규격봉투", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_MTHD=" + URLEncoder.encode("소각용", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_PRPOS=" + URLEncoder.encode("생활쓰레기", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TRGET=" + URLEncoder.encode("기타", "UTF-8");
			apiURL += "&serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");
			
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
			
			if(con.getResponseCode() == 200) {   // == HttpURLConnection.HTTP_OK
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
		
		
		// API로부터 전달받은 xml 데이터
		String response = sb.toString();
		    //System.out.println(response);
		
		// XML 파싱
		File file = new File("c:\\storage", "api1.xml");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);  // response => xml데이터를 담은 파일 생성
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// 접속 종료
		con.disconnect();
		
		// xml 분석
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file); // xml로 parsing. file 전체를 doc이라고 부른다! 
			
			Element root = doc.getDocumentElement();   // 최상위 태그 : <response>
			System.out.println(root.getNodeName()); 
			
			NodeList nodeList = root.getChildNodes();  // 자식 태그 list에 담고 for문으로 뽑아보기 : <header>, <body>
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);  // node => <header>와 <body>
				System.out.println("  " + node.getNodeName());
				
				NodeList nodeList2 = node.getChildNodes();	// <header>의 자식태그(<resultCode>, <resultMsg>), <body>의 자식태그(<items>, <numOfRows>, <pageNo>, <totalCount>)
				for(int j=0; j < nodeList2.getLength(); j++) {
					Node node2 = nodeList2.item(j);
					System.out.println("    " + node2.getNodeName());
					
					if(node2.getNodeName().equals("items")) {	// <items> 태그 대상으로만 하는거라 if문 + equals
						NodeList items = node2.getChildNodes();
						for(int k = 0; k < items.getLength(); k++) {
							Node item = items.item(k); 	// <items>의 자식태그 : <item>
							System.out.println("      " + item.getNodeName());
							
							NodeList itemchildren = item.getChildNodes();
							for(int l = 0; l < itemchildren.getLength(); l++) {
								Node itemchild = itemchildren.item(l);
								System.out.println("        " + itemchild.getNodeName() + ":" + itemchild.getTextContent());
							}
						}
					}
			
				}
			}
					
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
	}
			
	// Content-Type
	// 자원의 종류를 나타내는 표기법. 
	// text/html   or   application/xml   or   application/json  +  ; charset=UTF-8
	
	
	public static void m2() {
		
		// 보건복지부_코로나 19 감염현황 조회 서비스
		
		// API 주소 만들 때 StringBuilder 사용하기
		// 인코딩 예외 처리
		// GET방식이 기본이라 생략 가능
		
		// API 주소
		StringBuilder urlsb = new StringBuilder();
		try {
			
			String serviceKey = "bEQBRPHjt0tZrc7EsL0T8usfsZ1+wT+5jqamBef/ErC/5ZO6N7nYdRmrwR91bh5d3I1AQeY5qdbJOF6Kv0U1CQ==";
			// 서비스키 받고, 서비스키도 같이 파라미터 값으로 넣어줘야 한다..
			
			urlsb.append("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson");
			urlsb.append("?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8"));
			urlsb.append("&startCreateDt=" + URLEncoder.encode("20220809", "UTF-8"));
			urlsb.append("&endCreateDt=20220812");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String apiURL = urlsb.toString();   // 스트링빌더로 누적시킨 데이터들을 String으로 바꿔주기
		
		// API 주소 접속
		URL url = null;
		HttpURLConnection conn = null;
	
		try {
			
			url = new URL(apiURL);
			conn = (HttpURLConnection)url.openConnection();	
			conn.setRequestMethod("GET");
			conn.setRequestProperty("ContentType", "XML");
	
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 입력 스트림 생성
		// 1. 서버가 보낸 데이터를 읽어야 하므로 입력 스트림이 필요
		// 2. 서버와 연결된 입력 스트림은 바이트 스트림이므로 문자 스트림으로 변환해야 함
		BufferedReader rd = null;
		StringBuilder sb2 = new StringBuilder();
		
		try {
			
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			String line = null;
			while((line = rd.readLine()) != null) {   // BufferedReader에서만 지원하는 readLine() 메소드
				sb2.append(line + "\n");
			}
			
			rd.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String data = sb2.toString();
		
		File file = new File("c:\\storage", "api2.xml");
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(data);
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void m3() {   ///// 이 부분 중점적으로 공부 ㅠ_ㅠ
		
		// xml 파싱
		File file = new File("c:\\storage", "api2.xml");
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();
			
			StringBuilder sb = new StringBuilder();
			// <item>...</item> 태그 하나가 하루. 특정 날짜의 데이터로 구성되어 있음. 5일이면 node 5개
			// 태그 이름으로 요소 찾기
			NodeList items = root.getElementsByTagName("item");	 // element^s^ : s가 들어가면 반환타입이 list. 요소가 여러개인 경우
			for(int i=0; i < items.getLength(); i++) {
				Node item = items.item(i);   // nodeList 하나의 node인거. <items>의 하위. 컬렉션 list에서의 get과 같음. 
				//System.out.println(item.getNodeName());   // 0809~0812 4개의 node <item> 네개
				NodeList itemChildren = item.getChildNodes();  // item의 자식노드들
				
				for(int j=0; j < itemChildren.getLength(); j++) {
					Node itemChild = itemChildren.item(j);   // item의 모든 자식 데려옴
					if(itemChild.getNodeName().equals("stateDt")) { // 노드의 이름을 가져와서(getNoNa) 노드의 이름이 stateDt면(.equals)
							//System.out.println(itemChild.getTextContent()); // 날짜 나옴
					// Node stateDt == <stateDt>20220812</stateDt>
					// stateDt.getNodeName() == stateDt (태그 이름)
					// stateDt.getTextContent() == 20220812  (태그 내부 텍스트) 태그 사이의 콘텐트를 보여준다
				
					sb.append(" 날짜 : ").append(itemChild.getTextContent());
					}
					else if(itemChild.getNodeName().equals("decideCnt")) {
						sb.append(" 확진자 수 : ").append(itemChild.getTextContent());
					}
					else if(itemChild.getNodeName().equals("deathCnt")) {
						sb.append(" 사망자 수 : ").append(itemChild.getTextContent());
					}
				}
				sb.append("\n");	// if문 블럭을 switch case로 바꾸면 코드 더 깔끔쓰
				
				//Node stateDt = itemChildren.item(4);  // item 밑에 5번째 자식노드, 즉 i는 4
				// xml 문서에서 줄바꿈도 노드로 치기 때문에 위에 코드는 위험성이 있다.

			}
			System.out.println(sb.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
		
	public static void main(String[] args) {
		m3();

	}

}
