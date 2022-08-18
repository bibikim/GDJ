package pracAPI;

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

public class Main {

	public static void m1 () {
		
		String serviceKey = "1bYcGDEwh81BJx5E4lJXOhR5aFY6NfZZ9o2Esn3khsPIZIM8uzs61raBrbdv2xPCWXzlmw0n6QJwVXUhmoT9Jg==";
		
		StringBuilder urlsb = new StringBuilder();
		try {
			
			 urlsb.append("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
			 urlsb.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			 urlsb.append("&numOfRows=").append(URLEncoder.encode("10", "UTF-8"));
			 urlsb.append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"));
			 urlsb.append("&dataType=XML");
			 urlsb.append("&base_date=20220818");
			 urlsb.append("&base_time=1100");
			 urlsb.append("&nx=59");
			 urlsb.append("&ny=124");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiURL = urlsb.toString();
		
		HttpURLConnection con = null;
		
		try {
			
			URL url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
		
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
		
		File file = new File("c:\\storage", "api3.xml");
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(data);
			bw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void m2() {
		
		File file = new File("c:\\storage", "api3.xml");
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();
			
			NodeList items = root.getElementsByTagName("item");
			for(int i = 0; i < items.getLength(); i++) {
				Element item = (Element)items.item(i);   // Node -> Element 타입으로 다운캐스팅 (다중 for문 대신 타겟을 지정하는 방법)
				
				//NodeList categories = item.getElementsByTagName("category");  // getElementsByTagName 무조건 반환이 리스트임. 하나든 여러개든
									// 사실은 카테고리 하나밖에 없다
				//Node category = categories.item(0);
									// 그래서 아이템0
				
				Node category = item.getElementsByTagName("category").item(0);      // 바로위에 두줄을 한줄로 코드 수정하면 깔끔
				Node obsrValue = item.getElementsByTagName("obsrValue").item(0);
				
				String strCategory = null;
				switch(category.getTextContent()) {
				case "PTY": strCategory = "강수형태"; break;
				case "REH": strCategory = "습도"; break;
				case "RN1": strCategory = "강수량(1시간)"; break;
				case "T1H": strCategory = "기온"; break;
				case "UUU": strCategory = "동서바람성분"; break;
				case "VEC": strCategory = "풍향"; break;
				case "VVV": strCategory = "남북바람성분"; break;
				case "WSD": strCategory = "풍속"; break;
				}
				System.out.println(strCategory + ":" + obsrValue.getTextContent());
				
			}
			
			// 노드에는 태그 말고 다른 것들도 많이 포함되는데
			// 노드 밑에 태그 자체를 의미하는 element가 따로 있다
			// 따라서 다운캐스팅해서 엘러먼트인 상태에서 작업을 하면 getElementsByTagName() 써서 자식 노드들을 조회할 수 있다.
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void main(String[] agrs) {
		m2();
	}
	
	
	

}
