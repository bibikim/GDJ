package com.gdu.app05.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app05.domain.Member;

public interface MemberService {

	// 요청 파라미터 ( detail1 ~ detail3 )
	public String execute1(HttpServletRequest request, HttpServletResponse response);		// string 반환   - detail1()
	public Member execute2(String id, String pw);           // 변수로 받아와서 json 반환     - detail2()
	public Map<String, Object> execute3(Member member);     // 				   json 반환     - detail3()
	
	// 요청 JSON ( JSON 데이터를 받을 수 있는 것 -> Jackson이 Member member or Map 형태로 받아올 수 있음 )
	// Jackson이 반대의 일도 하는 것!
	public Member execute4(Member member);     // json으로 보내서 json 반환   - detail4()
	
}
