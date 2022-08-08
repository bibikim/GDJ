package ex01_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void m1() {
		
		// 생성
		// 1. 제네릭(Generic) 기반 : 타입 적어주는 방식이라 보면 됨
		// 2. 생성할 때 데이터타입을 결정(구체화) : 어레이리스트가 있으면 어떤걸 저장할거냐
		
		List<String> list = new ArrayList<String>();	// ctrl+스페이스 list인터페이스 , arraylist자바유틸
			// ㄴ 스트링을 저장할 때 쓰는 배열 리스트가 생성된 것!
			// <E>가 제너릭. 안에 타입 적어주면 됨. String/int... 저장할 때 쓰는 방식
		
		// 요소 추가
		// 1. 인덱스 지정이 없으면 순서대로 저장
		// 2. 인덱스 지정도 가능
		// add 메소드. 추가메소드
		list.add("월");
		list.add("화"); // 인덱스 지정을 안 한거라 월, 화 순서대로 저장됨. 월은 idx 0, 화는 1
		
		list.add(0, "일"); 	// 인덱스 지정한 것. 일, 월, 화 순서대로 저장되고 일 월 화(idx는 0 1 2) 됨!
		
		// 요소 제거
		// 1. boolean remove(Object obj) : obj 제거. 성공하면 true 반환
		// 2. Object remove(int index) : idx 위치의 요소를 제거. 제거한 요소 반환
		boolean result = list.remove("일");		// 제거 성공하면 true, 실패시 false
		System.out.println(result);
		String removeItem = list.remove(0);		// 제거된 요소 보여줌
		System.out.println(removeItem);			// 화만 남음.
		
		// 요소 수정
		list.set(0, "일"); // (idx지정, 수정할 요소)	 화 -> 일

		
		// 리스트 확인
		System.out.println(list); 	// [일, 월, 화] 출력 완.
		
		
	}
	
	public static void m2() {
		
		// 리스트 초기화
		// 1. 배열을 리스트로 변환하는 과정
		// 2. 초기화로 만든 리스트에는 값을 바꿀 수 없음. 고정된 크기의 list를 가진다. 삭제나 추가같은 작업 불가
		List<String> list = Arrays.asList("일", "월", "화", "수"); // Arrays ctrl+space, .찍고 asList 선택
				// asList(T...a) -> List<T> ... 갯수가 정해지지 않았다는 키워드
				// asList(String... a) -> String...a 스트링 여러개가 들어갈 수 있다. 일 월 화 수,,,
		
		// 리스트 길이
		int size = list.size();
		System.out.println(size);	// 0~3 4개
		
		
		// 개별 요소
		String element1 = list.get(0); // 첫번째 요소
		String element2 = list.get(1);
		String element3 = list.get(2);
		String element4 = list.get(3);
		System.out.println(element1);
		System.out.println(element2);
		System.out.println(element3);
		System.out.println(element4);
		
		
		
		// for문 순회
		// size() 메소드 호출을 한 번만 진행
		
		//String[] arr = {"일","월","화","수"};
		//for(int i = 0; i < arr.length; i++) {
		//	System.out.println(arr[i]);		// 배열은 길이를 arr.length -> length라는 필드값(변수)을 참조. 변수임
			// ㄴ 필드값을 순회하는건 단순 변수 참조라서 ㄱㅊ
		// }
		//for(int i = 0; i < list.size(); i++) {	// arraylist는 길이를 list.size 사이즈라는 메소드를 사용
		//	System.out.println(list.get(i)); }
			// 메소드를 반복하는건 계속서 메소드를 리스트 길이만큼(4번) 호출하는거라 성능이 떨어짐
			// 그래서 메소드를 한번만 호출할 수 있게끔 식을 고쳐야 해! 
			// ▼ ▼ ▼
			
		for(int i = 0, length = list.size(); i < length; i++) {	
			System.out.println(list.get(i));
		}   // size 메소드 호출을 한 번만해서 값을 저장하고 그 값을 여러번 참조하게끔.
		
		// 향상 for문 순회
		for(String element : list) {
			System.out.println(element);
		}
	}
	
	
	public static void m3() {
		
		// 제너릭
		// 1. 참조 타입만 사용 가능
		// 2. 기본 타입이 필요한 경우 기본 타입의 Wrapper Class를 사용
		// 기본타입을 클래스화한 8개의 클래스. 뭘 저장하든 타입은 다 제너릭
		
		// List<int> list = new ArrayKist<int>();    오류남
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);	// 정수를 저장할 수 있단 얘기
		System.out.println(list);
		
		// 비어있는 리스트인가?
		boolean result = list.isEmpty();	// 비었으면 true 아니면 false
		System.out.println(result);
		
		// 특정 요소를 포함하고 있는가?
		if(list.contains(4)) {
			System.out.println("4를 포함한다.");
		} else {
			System.out.println("4를 포함하지 않는다.");
		}
	}
	
		// 결론 : 어레이리스트는 사용법이 다른 배열이다~
	
	
	public static void main(String[] args) {
		
		m3();
		

	}

}
