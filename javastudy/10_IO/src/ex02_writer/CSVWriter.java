package ex02_writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class CSVWriter {

	public static void main(String[] args) {
		
		// CSV
		// 1. Comma Separate Values
		// 2. 콤마로 분리된 값들
		// 3. 확장자 .csv인 파일(기본 연결프로그램-엑셀, 메모장으로 오픈 가능)   일반 txt 파일과 다를거 없는 파일
		// 다량의 데이터를 받아볼 때 많이 씀.
	
		// c:\storage\product.csv
		// 제품번호, 제품명, 가격\n
		// 100,새우깡,1500\n
		// 101,양파링,2000\n
		// 102,홈런볼,2500\n
		
		List<String> header = Arrays.asList("제품번호", "제품명", "가격");  // 스트링 배열의 List 
		List<String> product1 = Arrays.asList("100", "새우깡", "1500");
		List<String> product2 = Arrays.asList("101", "양파링", "2000");
		List<String> product3 = Arrays.asList("102", "홈런볼", "2500");
		
		// 위에 네개를 List에 또 넣은 것임.
		List<List<String>> list = Arrays.asList(header, product1, product2, product3);	// List안에 List. 1차원 배열 4개가 여러개 있다.
		
		File file = new File("C:\\storage", "product.csv");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for(int i=0, length = list.size(); i < length; i++) {
				List<String> line = list.get(i); // line = [100, 새우깡, 1500], ... 한 줄씩인것. 데이터를 3개씩 갖고있는.
				// arrayslist에서는 값을 확인할때 get(i).     get(i)가 각 한줄씩인 것!
				for(int j = 0, size = line.size(); j < size; j++) { 
					if(j == size - 1) {		// size - 1 : 배열의 마지막 요소
						bw.write(line.get(j) + "\n"); // 마지막 요소라면 데이터요소값 + 줄바꿈
					} else {
						bw.write(line.get(j) + ",");	// get(j)는 line 하나의 각각의 요소
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	// 결과로 나오는 게 CSV의 형태라고 알고 있으면 된다. 
	// 데이터가 많을 땐 CSV가, 많지 않을땐 JSON이 좋다.

}
