package pracAPI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class RSSMain {
	
	public static void m1() {
	
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
			sb.append(line + "\n");
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
	
	}
	
	
	public static void m2() {
		
		File file = new File("c:\\storage", "api4.xml");
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			StringBuilder sb = new StringBuilder();
			
			Element root = doc.getDocumentElement();
			Node title = root.getElementsByTagName("title").item(0);
			sb.append(title.getTextContent()).append("\n");
			Node pubDate = root.getElementsByTagName("pubDate").item(0);
			sb.append(pubDate.getTextContent()).append("\n");
			// title 태그가 여러개가 아니고 하나기 때문에 인덱스 0 (첫번째)
			// pubDate 태그도 마찬가지.
			// 밑에 달린 자식 노드가 있는 것도 아니고 title하고 Date만 따면 되니까 list를 만들거나 for문을 돌릴 것 없다
			
			NodeList dataList = root.getElementsByTagName("data");
			for(int i = 0; i < dataList.getLength(); i++) {
				Element data = (Element)dataList.item(i);
				Node hour = data.getElementsByTagName("hour").item(0);
				Node temp = data.getElementsByTagName("temp").item(0);
				Node wfKor = data.getElementsByTagName("wfKor").item(0);
				sb.append(hour.getTextContent() + "시   " + temp.getTextContent() + "도   " + wfKor.getTextContent() + "\n");
				// 시,도,날씨상태별로 sb.append 3줄 코드로도 가능
			}
			
			System.out.println(sb.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		m2();

	}

}
