package ex01_one_dim;

public class Ex04_advanced_for {

	public static void main(String[] args) {
		
		String[] friends = {"라이언", "프로도", "어피치"};

		for(int i = 0; i < friends.length; i++) {		//이 식을 외워서 쓰믄 됨. freinds부분만 바꾸고
			System.out.println(friends[i]);				//arr배열의 이름, 배열의 인덱스,
		}

		// 일반 for문
		// 1번째 친구 - 라이언, 2번째 친구 - 프로도, 3번째 친구 - 어피치
		for(int i = 0; i < friends.length; i++) {
			System.out.println((i+1) + "번째 친구 - " + friends[i]);	
		}
				// ㄴ 1,2,3번째는 index + 1 한 값과 같으니까 i+1로 넣어 출력!
		
		// 향상 for문(advanced for문)
		// 배열과 컬렉션에 저장된 요소에 접근하기 편리(+ index가 없는 경우)
		// for(변수타입 변수이름 : 배열이름) { 
		//			반복문 내용 
		// }
		// 배열의 같은 타입(여기선 String)의 변수 선언, 그 변수에 값이 차례대로 처음부터 끝까지 대입
		for(String friend : friends) {		// friends 배열의 모든 요소를 하나씩 String friend로 옮긴다. 앞에가 변수, 뒤에가 배열(index 없음)
			System.out.println(friend);		// 왜냐면 변수를 하나씩 옮겼으니까 ds가 아닌 d 
		}
		
		
		
	}

}
