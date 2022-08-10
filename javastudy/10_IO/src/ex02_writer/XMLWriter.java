package ex02_writer;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLWriter {

	public static void main(String[] args) {
		
		// XML
		// 1. eXtensible Markup Language
		// 2. 확장 마크업 언어
		// 3. 표준 마크업 언어인 HTML의 확장 버전
		// 4. 정해진 태그(<>) 외 사용자 정의 태그 사용(정해진 이름이 아닌 사용자가 태그에 이름을 붙여 씀)
		
		/*                          XML 데이터의 생김새
		  	<product>
		  		<number>100</number>		-> Element. 각각의 태그를 element라 부름
		  		<name>새우깡</name>
		  		<price>1500</price>			-> 크게 보면 <product> 박스 하나도 element라고 봄
		 	</product>
		  	<product>
		  		<number>101</number>
		  		<name>양파링</name>
		  		<price>2000</price>
		 	</product>
		  	<product>
		  		<number>102</number>
		  		<name>홈런볼</name>
		  		<price>2500</price>
		 	</product>
		 */
		
		try {
			
			// Document 생성(문서 생성)
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();  // 문서의 구성요소를 객체로 보는..
			document.setXmlStandalone(true);
			
			List<String> product1 = Arrays.asList("100", "새우깡", "1500");
			List<String> product2 = Arrays.asList("101", "양파링", "2000");
			List<String> product3 = Arrays.asList("102", "홈런볼", "2500");
			
			List<List<String>> list = Arrays.asList(product1, product2, product3);
			
			for(List<String> line : list) {	// 전체 내용에서 한줄씩 가져온다
				// 태그 생성
				Element product = document.createElement("product");  // 여기서 태그 이름을 정해주는 것. => <Product>
				Element number = document.createElement("number"); // line의 첫번째 요소 (100)
				number.setTextContent(line.get(0));		// line의 첫번째 요소 (100)
				Element name = document.createElement("name");
				name.setTextContent(line.get(1));		// line의 두번째 요소 (새우깡)
				Element price = document.createElement("price");
				name.setTextContent(line.get(2));		// line의 세번째 요소 (1500)
				// 태그 배치
				document.appendChild(product);	// 문서에 product를 추가
				product.appendChild(number);	// product에 number, name, price 추가
				product.appendChild(name);
				product.appendChild(price);
				
			}
			
			// XML 생성
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("encoding", "UTF-8");
			transformer.setOutputProperty("indent", "true");
			
			Source source = new DOMSource(document);
			File file = new File("C:\\storage", "product.xml");
			StreamResult result = new StreamResult(file);
			
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
		}


}
