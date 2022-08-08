package quiz03_random;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		// practice01 프젝의 랜덤 숫자 생성하는 문제를 Set으로 적용시켜 풀어보기

		Scanner sc = new Scanner(System.in);
		
		System.out.println("몇 개의 랜덤을 생성할까요? >>>");
		int cnt = sc.nextInt();
		
		if(cnt < 1 || cnt > 100) {
			System.out.println("다음에는 1~100  사이로 입력하세요!");
			return;
		}
		
		int[] arr = new int[cnt];
		
		// 중복 제외를 위해 Set 이용하기
		Set<Integer> set = new HashSet<Integer>();
		while(set.size() < cnt) { // 세트에 저장된게 cnt 이하라면,
			set.add((int)(Math.random()* 100 + 1));
		}
		
		int idx = 0;
		for(Integer n : set) {			// 정수값을 썼기 때문에 세트에 들어간걸 하나씩 빼서
			arr[idx++] = n;				// arr에 집어넣음
		}
		
		for(int i = 0; i <cnt; i++) {
			System.out.println(arr[i] + " ");
			if( (i+1) % 10 == 0) {
				System.out.println(); 	// 줄 바꿈
			}
		}
		
	}

}
