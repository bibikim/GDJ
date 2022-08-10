package ex02_writer;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONWriter {
	
	public static void m1() {
		
		// JSON ★★ 개발자라면 잘 알아야하는..!
		// 1. JavaScript Object Notation
		// 2. 자바스크립트 객체 표기법
		// 3. 객체 : { }
		// 4. 배열 : [ ]
		
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
		obj2.put("name", "방구뽕");
		obj2.put("age", 26);
		
		// 객체가 2개니까 배열로.
		JSONArray arr = new JSONArray();
		arr.put(obj1);
		arr.put(obj2);
		
		System.out.println(arr.toString());
		
	}
	
	public static void m3() {
		
						// r m1의 결과값
		String str = "{\"name\":\"구교환\",\"man\":true,\"age\":\"41\",\"height\":174.5}"; 	// JSON 데이터
					// ㄴ 공공 API에서 받아온 데이터의 모습
		
		JSONObject obj = new JSONObject(str);	// str은 {}로 묶인 객체니까 object (배열 놉)
		
		String name = obj.getString("name");	// object를 string으로 받으려고 하니까 오류가 남. 그래서 string 캐스팅을 해줘야 한다
		boolean man = obj.getBoolean("man");
		int age = obj.getInt("age"); 
		double height = obj.getDouble("height");
		// 이렇게 변수에 저장시키고 싶은 데이터 확인...?
		
		System.out.println(name);
		System.out.println(man);
		System.out.println(age);
		System.out.println(height);
		
	}

	public static void main(String[] args) {
		m3();

	}

}
