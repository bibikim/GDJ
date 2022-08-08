package ex04_hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	
	public static void m1() {
		
		// Map 생성★★★ 														자주 사용하는거니까 매우 중요!
		// Map<Key, Value>       r key와 value가 모두 스트링이당
		Map<String, String> dictionary = new HashMap<String, String>();
		
		// 추가  put(key, value)
		// 새로운 key값을 사용하면 추가
		dictionary.put("apple", "사과");	// put 메소드는 key값이 앞, value가 뒤. 저장하고 싶은 값은 사과. 사과를 저장하려면 apple이라는 key값 필요
		dictionary.put("banana", "바나나");
		dictionary.put("tomato", "토마토");
		dictionary.put("mango", "맹고");
		dictionary.put("grapefruit", "자몽");
		
		// 수정
		// 기존의 key값을 사용하면 수정
		dictionary.put("mango", "망고");
		
		// 삭제
		// 삭제할 요소의 key를 전달하면 삭제됨
		// 삭제된 value가 반환됨
		String removeItem = dictionary.remove("tomato");
		System.out.println(removeItem);		// 토마토. 지워진건 "토마토". 실제로 저장돼서 관리되는 것은 value인 한글 단어니까!
		
		// 값(Value) 확인
		System.out.println(dictionary.get("apple"));	// 사과.  value값 조회하고 싶을땐 정보 불러오는 get
		System.out.println(dictionary.get("peach"));	// null.  복숭아는 없으니까~ 
		
		// 확인
		System.out.println(dictionary);		// { }로 묶여있음. key와 value가 합쳐져서 하나가 된다. 데이터는 5개!
		
	}
	
	public static void m2() {
		
		
		// Value를 String으로 관리
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("title", "어린왕자");
		map1.put("author", "생텍쥐베리");
		map1.put("price", 10000 + "");		// 숫자+"" => 숫자에 빈문자열을 넣어서 텍스트로 바꿀 수 있음!
		System.out.println(map1);
		
		
		// Value를 Object로 관리	 -> 많이 사용하는 방식
		Map<String, Object> map2 = new HashMap<String, Object>(); 	// object는 모든걸 저장할 수 있다아!
		map2.put("title", "홍길동전");
		map2.put("author", "허균");
		map2.put("price", 20000 + "");
		System.out.println(map2);
	}
	
	public static void m3() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "소나기");
		map.put("author", "황순원");
		map.put("price", 20000);		// 3개의 Entry로 구성된 것
		
		// Entry 단위로 순회(for) 				 ★★이 방법으로 학습하면 될듯
		for(Map.Entry<String, Object> entry : map.entrySet()) {				// map에서 entry단위로 읽어라!
			System.out.println(entry.getKey() + ":" + entry.getValue());	// 순회를 entry 단위로 진행
		}
		
		// key를 이용한 순회(for)
		for(String key : map.keySet()) { // map에서 key만 뺀다!
			System.out.println(key + ":" + map.get(key));
		}
		// map을 만들 때 초기화라는게 없어서 put메소드로 만들어주면 된다.
		// Key와 Value를 합쳐서 Entry라고 부름. 하나의 단위로 보면 됨
		// 컬렉션에 저장된 요소에 접근하기 편리한 향상for문을 이용!
	}

	public static void m4() {
		
		// 연습. 			★★★ 이 예제 통해서 list, map 정확하게 알고 이해하기. 매우 중요 ★★★
		// title, author, price 정보를 가진 임의의 Map 3개를 만들고
		// 생성된 Map 3개를 ArrayList에 저장한 뒤,
		// ArryaList에 저장된 Map 3개를 for문으로 순회하시오.
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("title", "아몬드");
		map1.put("author", "손원평");
		map1.put("price", 15000);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("title", "파친코");
		map2.put("author", "이민진");
		map2.put("price", 12000);
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("title", "친밀한 이방인");
		map3.put("author", "정한아");
		map3.put("price", 15000);
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		
		// map자체도 순회가 가능하니까 map으로 for문 돌리기
		for(Map<String, Object> map : list) {	// List
			for(Map.Entry<String, Object> entry : map.entrySet()) {		// Map
				System.out.println(entry.getKey() + ":" + entry.getValue());		
			}
			System.out.println();
		}
	}
	
	// 컬렉션은 데이터 저장하는 법!
	// list -> add
	// map  -> put
	

	public static void main(String[] args) {
		m4();
	}

}
