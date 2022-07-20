package ex05_string;

public class Ex01 {

	public static void main(String[] args) {
		
		
		// 문자열 연결 연산자
		// 문자열이 포함된 + 연산은 연결
		
		String str1 = "구디" + "아카데미";
		String str2 = 100 + "번지";
		String str3 = 1 + 1 + "행사";  // 항상 연산은 왼쪽에서부터 오른쪽으로. 하나씩 진행해 나간다. 따라서 1+1은 2가 된다
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		
		// + 연산을 이용한 문자열 만들기
		// 빈 문자열("")을 + 해 줌.
		String str4 = 100 + "";
		System.out.println(str4);
		// String.valueOf(100)과 동일. ex02casting-Ex03 숫자데이터 문자열로 변환 주석.

	}

}
