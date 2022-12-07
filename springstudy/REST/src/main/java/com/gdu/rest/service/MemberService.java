package com.gdu.rest.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.gdu.rest.domain.MemberDTO;

public interface MemberService {
	public Map<String, Object> register(MemberDTO member, HttpServletResponse response);   // 컨트롤러의 반환타입, 받아오는 매개변수를 맞춰주면 된다
}
