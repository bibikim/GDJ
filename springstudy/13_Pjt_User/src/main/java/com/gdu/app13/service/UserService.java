package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
	// map을 반환하면 jackson이 map으로 잘 바꿔주니까!
	public Map<String, Object> isReduceId(String id);  // id 중복체크, id 넘겨주기
	public Map<String, Object> isReduceEmail(String email);  // email 중복체크, email 넘겨주기
	public Map<String, Object> sendAuthCode(String email);  // 인증코드 발송(db로 갈 것도 읎다)
	public void join(HttpServletRequest request, HttpServletResponse response); 
	// 로그인을 시킨다 -> 세션이 기본! 세션은 request를 통해서 꺼낼 수 있기 때문에 request와 response 를 매개변수로 넘겨주고, 반환타입은 void -> 즉, 응답을 직접 만들어서 가니까 어디로 갈 jsp는 없는거야~
		
	
}
