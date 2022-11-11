package com.gdu.app12.util;

import java.security.MessageDigest;

import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

	// 크로스 사이트 스크립팅 방지
	public String preventXSS(String str) {
		
		str = str.replace("<", "&lt;");
		str = str.replace(">", "&gt;");
		str = str.replace("\"", "&quot;");
		str = str.replace("\'", "&#x27");  // '의 유니코드 값.
		// 들어올 때는 <script>로 들어오고, 나갈 때는 &lt;script&gt; -> web에서 뿌릴 때 <script>

		return str;
		
	}
	
	// SHA-256 암호화  시큐어해쉬알고리즘
	// 1. 입력 값을 256비트(32바이트) 암호화 처리하는 알고리즘
	// 2. 암호화는 가능하지만 복호화는 불가능
	// 3. 1바이트를 2글자로 표현하면 총 64글자(DB에 저장될 때 크기) 공간이 필요
	// 4. 모든 입력이 64글자 암호화 처리   - 몇글자를 입력하건 똑같은 길이의 암호화된 문자열이 나온다 -
	// 5. java.security 패키지(자바가 지원) 
	public String sha256(String str) {
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());  // 입력된 값 str을 바이트 단위로 바꾸기
		} catch(Exception e) {
			e.printStackTrace();
		}
		byte[] b = md.digest();  // 배열 b : 문자열 str이 암호화된 32바이트 크기의 배열. 여기에 저장된 글자 1byte씩 꺼내서 2글자로 바꿔줄거다
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < b.length; i++) {
			 sb.append(String.format("%2X", b[i]));   //  %2X : 2자리 16진수(0~F), %2x : 2자리 16진수(0~f)
		}
		// 16진수 (0~F) 
		// 4bit : 네자리의 숫자, 0000 ~ 1111까지 가질 수 있음. 
		
		return sb.toString();
		
	}
}
