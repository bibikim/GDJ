package com.gdu.rest.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.gdu.rest.domain.MemberDTO;

public interface MemberService {
	public Map<String, Object> register(MemberDTO member, HttpServletResponse response);   // 컨트롤러의 반환타입, 받아오는 매개변수를 맞춰주면 된다
	public Map<String, Object> getMemberList(int page); // 목록은 map에다 받아서 넘어오고 넘어올거 page 하나~
	public Map<String, Object> getMemberByNo(int memberNo); // 멤버 정보는 map에 받고 넘어올 파라미터는 멤넘
}

