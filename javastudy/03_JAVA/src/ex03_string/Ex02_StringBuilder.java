package ex03_string;

import java.util.Scanner;

public class Ex02_StringBuilder {

	public static void main(String[] args) {
		
		// java.lang.StringBuilder 클래스
		
		StringBuilder sb = new StringBuilder();
		sb.append(1);
		sb.append(true);
		sb.append('T');
		sb.append(3.14);
		sb.append("hello");			// append 추가
		System.out.println(sb);
		
		// hello가 포함되었는가?
		// >>  sb.contains("hello");	안 됨
		// 첫 번째 글자 가져오기
		// >>  sb.charAt(0, 1);			안 됨
		sb.substring(0, 1);
		System.out.println(sb.substring(0, 1));
										// sb != String, == StringBuilder
		
		// 동등 비교
		System.out.println(sb.equals("1trueT3.14hello"));

		// StringBuilder로 만든 문자열은 반드시 마지막에 String으로 변환해야 함
		String result = sb.toString();		// toString : 스트링빌더의 메소드
		System.out.println(result);
		System.out.println(result.equals("1trueT3.14hello"));
											// StringBuilder : append하고 toString 해주기!
		
		// String과 StringBuilder의 성능 테스트
		// abcdefghijklmnopqrstuvwxyz
		// StringBuilder가 더 빠르다. 성능이 더 좋기때문에 자주 쓰는 클래스.
		String alphabet1 = "";
		long begin1 = System.nanoTime();
		for(int ch = 0; ch <= 1000; ch++) {
			alphabet1 += ch;
		}
		long end1 = System.nanoTime();
		System.out.println((end1 - begin1) + alphabet1);       // 알파벳1값을 출력하는 동안 걸린 시간(nanoTime)
		
		// ----- append가 연산에 걸린 시간이 더 적다. 스트링빌더가 더 빨리 만든다.
		
		StringBuilder sb2 = new StringBuilder();
		long begin2 = System.nanoTime();
		for(int ch = 0; ch <= 1000; ch++) {
			sb2.append(ch);
		}
		long end2 = System.nanoTime();
		String alphabet2 = sb2.toString();
		System.out.println((end2 - begin2) + alphabet2);
		// --01234...1000 앞에 숫자 보면 됨. 스빌이 더 적게 나온다.
		
		
		// 연습. 
		// 대문자 6자리로 구성된 인증코드 작성하기
		StringBuilder sbCode = new StringBuilder();
		for(int n = 0; n < 6; n++) {
			sbCode.append((char)((int)(Math.random() * 26) + 'A'));
		}
		String code = sbCode.toString();
		System.out.println("인증코드 :" + code);
		

		
		// 연습. 
		// 1 2 3 4 5 6 7 8 9 10 만들기
		StringBuilder sbPaging = new StringBuilder();
		for(int n = 1; n <= 10; n++) {
			sbPaging.append(n + " ");
		}
		String paging = sbPaging.toString();
		System.out.println(paging);
		

	
		
		
		}
		
		
		
	}

