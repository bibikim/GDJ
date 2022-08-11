package ex03_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class JSONReader {  	// ★★ 중요!! JSON 위주로 공부하기

	public static void main(String[] args) {
		
		File file = new File("c:\\storage", "product.json");
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);	// "\n"은 없어도 됨. 한줄로 있어도 읽어서 저장 가능하니까
			}
			String str = sb.toString();
			JSONArray arr = new JSONArray(str); // string 전달해주는 json 배열
			
			List<Product> products = new ArrayList<Product>();
			for(int i = 0, length = arr.length(); i < length; i++) {
				JSONObject obj = arr.getJSONObject(i);	// 배열 아래 요소들은 JSONobject로.
				Product product = new Product();
				product.setNumber(obj.getString("number")); // 넘버가 string이니까 getString
				product.setName(obj.getString("name"));
				product.setPrice(Integer.parseInt(obj.getString("price")));
				products.add(product);
				
			}
			// arraylist 확인
			for(Product product : products) {
				System.out.println(product);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
