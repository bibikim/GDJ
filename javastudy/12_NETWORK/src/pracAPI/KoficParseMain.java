package pracAPI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class KoficParseMain {

	public static void main(String[] args) {
		
		// 영화 한 편 : Movie 객체
		// 영화 전체  : List<Movie> 리스트
		
		File file = new File("c:\\storage", "boxoffice.xml");
		List<KoficMovie> movies = new ArrayList<KoficMovie>();
		
		try {
			
		
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);  // 박스오피스 전체가 doc
			
			NodeList boxOfficeList = doc.getElementsByTagName("dailyBoxOffice");  // 문서에서 데일리박스오피스라는 태그를 가진걸(10개) 가져오겠다
			
			for(int i = 0; i < boxOfficeList.getLength(); i++) {
				
				Element boxOffice = (Element)boxOfficeList.item(i);// getElements 메소드를 쓰기 위해 Element로 다운캐스팅. 하나의 데일리박스오피스
				String movieCd = boxOffice.getElementsByTagName("movieCd").item(0).getTextContent();  // 하나의 노드의 textContent 가져와라
				//               -----------------------------------------> NodeList
				//               --------------------------------------------------> Node
				String movieNm = boxOffice.getElementsByTagName("movieNm").item(0).getTextContent();
				String openDt = boxOffice.getElementsByTagName("openDt").item(0).getTextContent();
				String salesAcc = boxOffice.getElementsByTagName("salesAcc").item(0).getTextContent();
				String audiAcc = boxOffice.getElementsByTagName("audiAcc").item(0).getTextContent();    
				// 숫자인데 스트링(텍스트)으로 처리함. 숫자로 바꾸고 싶으면 parseInteger 
				
				// 위에 String 다섯개의 변수를 하나의 변수로 모아야 한다. 하나의 객체로 빌더패턴 이용해서~
				KoficMovie movie = KoficMovie.builder()
						.movieCd(movieCd)
						.movieNm(movieNm)
						.openDt(openDt)
						.salesAcc(salesAcc)
						.audiAcc(audiAcc)
						.build();    // 빌더패턴. 마지막에 build()로 짓자!
				
				// 한편 나왔으니 10편 모두를 저장할 arrayList 만들기		
				movies.add(movie); // -> 객체를 하나 만들어서 List에 add 하기
				
			} // for문 끝
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(KoficMovie movie : movies) {
			System.out.println(movie);
		}
		
	}

}
