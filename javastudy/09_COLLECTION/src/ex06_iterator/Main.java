package ex06_iterator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void m1() {
		
		// Iterator 인터페이스
		// 1. 특정 컬렉션(interface Collection)에 등록해서 사용	  // list, set
		// 2. 순회할 때 사용(for문 대용)
		// 3. 주요 메소드
		//	 1) hasNext() : 남아있는 요소가 있으면 true 반환.     -> 요소가 남아있으면 가져와라
		//	 2) next()	  :	요소를 하나 반환.
		// 요소가 얼마나 남아있는지 모르는 상태에서 쓰는거고 한번에 끝나는게 아니기때문에 while문을 사용한다
		// 4. 주로 Set에서 사용
		
		Set<String> set = new HashSet<String>();
		
		set.add("보쌈");
		set.add("닭발");
		set.add("바질페스토");
		set.add("알리오올리오");
		
		// set를 조회할 반복자
		Iterator<String> itr = set.iterator();    // 메소드 이름 = 인터페이스 이름. 세트에 등록하는거니까 set.
		
		// hasNext() : 남아 있는 요소가 있으면
		// next()    : 그 요소를 꺼냄
		while(itr.hasNext()) {
			String element = itr.next();		// iterator에서 하나의 요소를 빼내오면 그건 하나의 요소.
			System.out.println(element);
		}
		// itr은 set에 저장된 요소들이 담긴 주머니를 뒤적이는 손이라고 생각하면 됨.
		// 뒤적이다가 하나의 요소를 잡으면 hasNext(), 그걸 빼오면 next()
		
	}
	
	
	public static void m2() {
		
		// HashMap과 Iterator			-- 해시맵은 이터레이터를 못 쓴다. 컬레션이랑은 다르다. 컬렉션/맵 이렇게 나뉘니까
		//									Set에 key를 저장해서 사용하는 방법이 있다.
		// 1. keySet() 메소드로 key만 Set에 저장한다.
		// 2. key를 저장한 Set에 Iterator를 등록해서 사용한다.
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("page", 1);
		map.put("column", "제목");
		map.put("query", "날씨");
		
		Set<String> keys = map.keySet(); // 1번
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()) {
			String key = itr.next();		// key들
			Object value = map.get(key);	
			System.out.println(key + ":" + value);
		}
	}
		
	// HashMap을 순회하는 방법 - for문, 향상for문, Iterator
	
	
	
	
	public static void main(String[] args) {
		m2();
		
	}

}
