package com.gdu.app13.service;

import java.util.Map;

public interface UserService {
	// map을 반환하면 jackson이 map으로 잘 바꿔주니까!
	public Map<String, Object> isReduceId(String id);  // id 중복체크, id 넘겨주기
	public Map<String, Object> isReduceEmail(String email);  // email 중복체크, email 넘겨주기
	public Map<String, Object> sendAuthCode(String email);  // 인증코드 발송(db로 갈 것도 읎다)
	
		
	
}
