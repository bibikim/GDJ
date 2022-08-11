package ex03_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVReader {

	public static void main(String[] args) {
		
		File file = new File("c:\\storage", "product.csv");
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			// 첫 행 읽어서 버리기
			br.readLine(); // readline 호출할 때마다 한줄씩 읽어온단 뜻
			
			// 한 줄 읽어서 Product 객체 생성하고 ArrayList에 저장하기
			List<Product> products = new ArrayList<Product>();
			String line = null;
			while((line = br.readLine()) != null) {   // null이 아니면 계속 반복
				String[] arr = line.split(",");		// ,로 분리하고 그걸 배열로 만들어 두고
				Product product = new Product();	// product 생성해서
				product.setNumber(arr[0]);		// 각 배열의 0 1 2 요소를 집어 넣기
				product.setName(arr[1]);
				product.setPrice(Integer.parseInt(arr[2]));
				products.add(product);
			}
			// ArrayList 확인
			for(Product product : products) {
				System.out.println(product);
			}
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

}
