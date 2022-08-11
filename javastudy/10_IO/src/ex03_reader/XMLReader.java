package ex03_reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {

	public static void main(String[] args) {
		
		try {
		
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File("c:\\storage", "product.xml");
			Document document = builder.parse(file); // product.xml을 파싱(분석)한 document 객체.     org.w3c.dom
			
			// 최상위 요소(root)
			Element root = document.getDocumentElement();
			System.out.println("최상위 요소 : " + root.getNodeName()); 	// 최상위 요소(products) 밑에 product 3개를 childNode라고 부름
			
			List<Product> products = new ArrayList<Product>();
			
			
			// 최상위 요소의 자식 노드들
			NodeList nodeList = root.getChildNodes();	// 자식노드들 가져오기.       org.w3c.dom
			// ㄴ 배열같은애들이라 for문 가능
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i); // nodeList에 저장된 아이템. 줄바꿈(#text)과 <product>태그로 구성 (<product>태그 하나가 element)
				if(node.getNodeType() == Node.ELEMENT_NODE)  {		// 이 노드가 Element 인가? (줄바꿈 #text 제외되고 <product> 태그만 남음)
					NodeList nodeList2 = node.getChildNodes();		// <product> 태그의 자식노드 (줄바꿈 #text, <number>, <name>, <price> 태그)
					Product product = new Product(); // 
					for(int j = 0; j < nodeList2.getLength(); j++) {
						Node node2 = nodeList2.item(j);
						if(node2.getNodeType() == Node.ELEMENT_NODE) {
							//System.out.println(node2.getNodeName() +":" + node2.getTextContent());  //만들 땐 setTextContent, 불러들일 땐 getTextContent. 태그 사이의 텍스트 키워드
							switch(node2.getNodeName()) { // 스위치 3번 돈다
							case "number": product.setNumber(node2.getTextContent()); break;
							case "name" : product.setName(node2.getTextContent()); break;
							case "price" : product.setPrice(Integer.parseInt(node2.getTextContent())); break;
							}
						}
					}
					// ArrayList에 product 추가
					products.add(product);
				} 
			}// for문 안에 있는 노드는 각각의 프로덕트.

			// ArrayList 확인
			for(Product product : products) {
				System.out.println(product);
			}
			
			
		} catch(Exception e) {		// io예외 말고도 다른 예외들이 있어서 그냥 exception 처리
			e.printStackTrace();
		}
		
		
		
	}

}
