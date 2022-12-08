package com.gdu.rest.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.gdu.rest.domain.MemberDTO;

public interface MemberService {
	public Map<String, Object> register(MemberDTO member, HttpServletResponse response);   // 컨트롤러의 반환타입, 받아오는 매개변수를 맞춰주면 된다
	public Map<String, Object> getMemberList(int page); // 목록은 map에다 받아서 넘어오고 넘어올거 page 하나~
	public Map<String, Object> getMemberByNo(int memberNo); // 멤버 정보는 map에 받고 넘어올 파라미터는 멤넘
	public Map<String, Object> modifyMember(Map<String, Object> map, HttpServletResponse response); // member 정보가 다 넘어갔으니 memberDTO,,, response를 넘겨줘서 응답을 할 수 있게끔 response도 받기
											// map으로 받기로 바꿈
	public Map<String, Object> removeMemberList(String memberNoList);  // 컨트롤러가 받은거 그대로 넘긴다고 생각하면 3,1이 String으로 넘어오는 거임. 이걸 쪼개서 arrayList에 담는다 치면 memberNoList는 String인거임. 한덩어리로 일단 옴

}

