package ex07_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void printMovies(List<String> list) {
		
		for(int i = 0, length = list.size(); i < length; i++) {
			System.out.print(list.get(i));	// 현재 movies와 list는 같음!
			if(i < length - 1) {	// length - 1 : 마지막 요소의 인덱스
				System.out.print(" -> ");
			}
		}
		
		System.out.println();
		
		
		//for(int i = 0, length = list.size(); i < length; i++) {
		//	System.out.print(list.get(i));	// 현재 movies와 list는 같음!
		//	if(i < length - 1) {	// length - 1 : 마지막 요소의 인덱스
		//		System.out.print(",");
		//	}
		//}
		//System.out.println();
		
		// 마지막 요소의 인덱스 : 길이 -1
		
		
	}
	
	public static void main(String[] args) {
		
		List<String> movies = new ArrayList<String>();
		movies.add("모가디슈");
		movies.add("공조");
		movies.add("해리포터");
		movies.add("미비포유");
		movies.add("코코");
		
		printMovies(movies); 	// 모 -> 공 -> 해 -> 미 -> 코 출력

		// movies 리스트를 오름차순 정렬
		Collections.sort(movies);
		printMovies(movies);
		
		// movies 리스트를 내림차순 정렬
		Collections.reverse(movies);
		printMovies(movies);
		
		// 특정 요소의 인덱스 반환
		// 이진 검색(binary search)을 이용하므로 검색 속도가 매우 빠름
		// 단, 크기 순으로 정렬이 되어 있어야 한다.
		int idx = Collections.binarySearch(movies, "코코");
		System.out.println(idx);
		
		// binarySearch 원하는 데이터 찾는 메소드
		// 찾고자 하는 key값이랑 중앙에 있는 데이터랑 비교해서 내가 원하는 key값보다 크면 반쪽은 버리고 반쪽에서 다시 찾는.. 그런 메소드
		
	}

}
