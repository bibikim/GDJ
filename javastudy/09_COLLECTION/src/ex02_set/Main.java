package ex02_set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	
	public static void m1() {
		
		// 세트 생성
		// 세트 인터페이스 기반의 구현클래스는 HashSet
		Set<String> set = new HashSet<String>();
		
		// 요소 추가
		set.add("일");
		set.add("월");
		set.add("화");
		set.add("수");
		set.add("수"); 	// 중복 저장 시도. Set에서는 기존의 요소하고 넣으려는 요소하고 비교해서 같으면 넣지 않는다.
						// 요소의 중복 저장 불가!
		
		// 요소 제거
		boolean result = set.remove("일");
		System.out.println(result);
		 //인덱스를 받아서 삭제하는 방법은 없음
		 //객체(값)을 받아서 삭제하는 거만 가능
		
		
		
		// 세트 확인
		System.out.println(set);	// 순서가 없다(index가 없다). 고로 인덱스가 사용되는 메소드가 없다.
	}

	
	public static void m2() {
		
		// 세트의 초기화
		// 리스트를 세트로 변환하는 방식
		Set<String> set = new HashSet<String>(Arrays.asList("일","월","화","수"));
		
		// 세트의 길이
		int size = set.size();
		System.out.println(size);
		
		// 향상 for문 가능(인덱스가 없으므로 일반 for문 불가능)
		for(String element : set) {
			System.out.println(element);
		}
		
	}

	
	public static void m3() {
		
		Set<Integer> set1 = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		Set<Integer> set2 = new HashSet<Integer>(Arrays.asList(3, 4, 5, 6, 7));
		Set<Integer> set3 = new HashSet<Integer>(Arrays.asList(6, 7));
		
		// 교집합
		// 교집합 결과는 retainAll()메소드를 호출한 Set에 저장
		//set1.retainAll(set2);		// set1에 저장됨
		//System.out.println(set1);
		
		// 합집합
		// 합집합 결과는 addAll()메소드를 호출한 Set에 저장
		//set1.addAll(set2);
		//System.out.println(set1);
		
		// 차집합
		// 차집합 결과는 removeAll() 메소드를 호출한 Set에 저장
		set1.removeAll(set2);	
		System.out.println(set1);
		
		// 부분집합 여부 판단
		boolean result1 = set1.containsAll(set3);	// set3가 set1의 부분집합이면 true
		boolean result2 = set2.containsAll(set3);	// set2가 set2의 부분집합이면 true
		System.out.println(result1);
		System.out.println(result2);
		
		
	}

	
	public static void m4() {
		
		// 중복 요소가 있는 리스트 -> 세트로 변환 -> 다시 리스트로 변환     (하면 중복이 사라진다!)
		
		List<String> list = new ArrayList<String>();
		list.add("일");
		list.add("월");
		list.add("화");
		list.add("화");
		
		System.out.println(list);
		
		Set<String> set = new HashSet<String>(list);		// list 전달 > 세트로 변환!
		
		// list.clear();	// list 요소 모두 제거
		
		list = new ArrayList<String>(set);	// 중복인 '화'가 없어짐
		
		System.out.println(list);	
		
		// 세트와 리스트는 같은 Collection 인터페이스 소속.
		
		
	}
	
	
	
	public static void main(String[] args) {
		m4();

	}

}
