package prac1;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SfcWebMain {
	
	
	public static void m1() {
		
		File file = new File("c:\\storage", "sfc_web_map.xml");
		
		try {
			
			StringBuilder sb = new StringBuilder();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement();   // <current xmlns="current"> 태그
			System.out.println(root.getNodeName());  // 태그 이름으로 current
			System.out.println(root.getAttribute("xmlns")); // 속성 값(attribute)으로 current
		
			Element weather = (Element)root.getElementsByTagName("weather").item(0);  // <weather hour="15" day="18" month="08" year="2022">
			sb.append(weather.getAttribute("year") + "년 ");
			sb.append(weather.getAttribute("month") + "월 ") ;
			sb.append(weather.getAttribute("day") + "일 ");
			sb.append(weather.getAttribute("hour") + "시\n");
			
			NodeList locals = root.getElementsByTagName("local");
			for(int i = 0; i < locals.getLength(); i++) {
				Element local = (Element)locals.item(i);   // local 하나나. 각각의 local
				sb.append(local.getTextContent() + ":");  // local 태그는 지역 이름을 저장함
				sb.append(local.getAttribute("ta") + "℃");
				sb.append(local.getAttribute("desc") + "\n");
//				Attribute = 속성
//						ㄴ 태그 하나에 속성이 여러 개인 경우에 사용
//							JAVA에서는 getAttribute()메소드를 쓰면 된다.
//				    		저장시키는 데이터는 텍스트거나 특정 속성 값임
//         local.getAttribute("ta");   >> ta라는 속성(온도)이 가지고 있는 값(container)를 가져와라
				
			
			}
			
			System.out.println(sb.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	public static void main(String[] args) {
		m1();

	}

}
