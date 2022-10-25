package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 	네이버 이미지 캡차 API 레퍼런스

		1.key 발급 요청
		2.캡차 이미지 요청
		3.사용자 검증 요청

		-> 요청 3개 필요
*/

public interface NaverCaptchaService {
	public String getCaptchaKey();
	public Map<String, String> getCaptchaImage(HttpServletRequest request, String key);
	public void refreshCaptcha(HttpServletRequest request, HttpServletResponse response);
	public boolean validateUserInput(HttpServletRequest request);
	
	// 실제 인터페이스와 유사한 인터페이스.
	// 어떤 인터페이스 안에 메소드가 여러개 들어가 있는 경우
	// 이 인터페이스는 구현(implment)하는 클래스는 1개만 있으면 된다. 1개 안에 메소드가 3개 들어감. 서비스 1개에 메소드 3개!
	// 3개의 기능을 구현하기 위해서 1개의 서비스만 만듬. 대신 그 안에 메소드가 3개!
	
}
