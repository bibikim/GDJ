package ex02_writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONWriter {
	
	public static void m1() {
		
		// JSON ★★ 매우 중요. 개발자라면 잘 알아야하는..!
		// 1. JavaScript Object Notation
		// 2. 자바스크립트 객체 표기법
		// 3. 객체 : { }    하나의 데이터
		// 4. 배열 : [ ]    여러개의 데이터
		
		// JSON-Java(JSON in Java) 라이브러리
		// 1. 객체 : JSONObject 클래스(Map 기반으로 만들어져서 사용법이 비슷)  ★★잘 다뤄야 함
		// 2. 배열 : JSONArray 클래스(List 기반이라 arrayList와 사용법이 비슷) 
		// 패키지들이 org.json이라 import가 필요하다
		
		JSONObject obj = new JSONObject();
		obj.put("name", "구교환");
		obj.put("age", "41");
		obj.put("man", true);
		obj.put("height", 174.5);
		
		System.out.println(obj.toString()); 	// obj을 텍스트로 바꿔줘라!
		// key값을 JavaScript에서는 property라고 부름
	}
	
	public static void m2() {
		
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "우영우");
		obj1.put("age", 27);
		
		JSONObject obj2 = new JSONObject();
		obj2.put("name", "박은빈");
		obj2.put("age", 31);
		
		// 객체가 2개니까 배열에 집어넣는다.
		JSONArray arr = new JSONArray();
		arr.put(obj1);
		arr.put(obj2);
		
		System.out.println(arr.toString());
		
	}
	
	public static void m3() {
		
		// JSON 읽어보기
		
						// r m1의 결과값
		String str = "{\"name\":\"구교환\",\"man\":true,\"age\":\"41\",\"height\":174.5}"; 	// JSON 데이터
					// ㄴ 공공 API에서 받아온 데이터의 모습
		
		// JSONObject로 읽어서 인식시키고
		JSONObject obj = new JSONObject(str);	// str은 {}로 묶인 객체니까 object (배열 놉)
		
		// 각 property 값을 집어 넣어서 데이터 가져오기
		// 메소드가 타입과 결부되어 있음(getString,getInt,...)
		String name = obj.getString("name");	// object를 string으로 받으려고 하니까 오류가 남. 그래서 string 캐스팅을 해줘야 한다
		boolean man = obj.getBoolean("man");
		int age = obj.getInt("age"); 
		double height = obj.getDouble("height");
		// 이렇게 저장시키고 싶은 데이터 확인...?
		
		System.out.println(name);
		System.out.println(man);
		System.out.println(age);
		System.out.println(height);
		
	}
	
	public static void m4() {
		
		// 배열로 된 데이터를 정상적으로 읽어내는지 확인
		String str = "[{\"name\":\"우영우\",\"age\":27},{\"name\":\"박은빈\",\"age\":31}]";
		// 저장이 되어있는 데이터는 각 JSONObject 객체 2개 { }, { }
		
		JSONArray arr = new JSONArray(str);	// 2개의 객체가 들어가 있으니 읽어내려면 for문
		
		// 배열로 된 데이터를 정상적으로 읽어내는지 확인
		//System.out.println(arr.toString());
		
		// 일반for문
		for(int i = 0, length = arr.length(); i < length; i++) {	   // length() : 괄호가 붙어있으니까 메소드 >> 
			JSONObject obj = arr.getJSONObject(i);		// 각 jsonobject = 객체 1개	-> obj로 가져옴		// 자바의 length는 변수와 같은거라 ()가 없었음. 메소드가 X 
			String name = obj.getString("name");
			int age = obj.getInt("age");
			System.out.println(name + "," + age);

		}
		
		// 향상for문 : 기본적으로 데이터를 가져오는 그냥 get() 메소드로 동작. get() 메소드는 Object로 반환
		for(Object o :arr) {	// arr를 넣어버리면 향상for문은 object로 받아오는거라 앞에 JSONobject랑 타입이 맞지 않아 오류.
			JSONObject obj = (JSONObject) o; 	// 그래서 Object로 바꿔주고 JSONobeject를 for문 안에서 따로 선언한 후 캐스팅을 해준다
			String name = obj.getString("name");
			int age = obj.getInt("age");
			System.out.println(name + "," + age);
		}
		
	}
		
	
	public static void m5() {
		
		List<String> product1 = Arrays.asList("100", "새우깡", "1500");
		List<String> product2 = Arrays.asList("101", "양파링", "2000");
		List<String> product3 = Arrays.asList("102", "홈런볼", "2500");
		
		List<List<String>> list = Arrays.asList(product1, product2, product3);
		
		// list를 json String으로 만들어서 
		// C:\\storage\\product.json 파일에 write()
		
		// list는 JSONArray에 대응된다. 먼저 list를 제이슨어레이로 만들기
		JSONArray arr = new JSONArray();	// 내용이 없는 제이슨어레이
		
		for(List<String> line : list) {
			JSONObject obj = new JSONObject();	// line 하나를 JSONobject로 바꿔주는 작업
			obj.put("number", line.get(0));	// line의 첫번째 요소
			obj.put("name", line.get(1));
			obj.put("price", line.get(2));
			// arr에 obj에 집어넣기
			arr.put(obj);
			
			//System.out.println(arr.toString());
		}
		
		File file = new File("c:\\storage", "product.json");
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
				fw = new FileWriter(file); // file 전달
				bw = new BufferedWriter(fw);
				bw.write(arr.toString());	// arr의 데이터를 문자열로
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
		
	

	public static void main(String[] args) {
		m5();

	}

}
