package com.gdu.app12.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BbsService {

	public void findAllBbsList(HttpServletRequest request, Model model);
	public int addBbs(HttpServletRequest request);   // 원댓
	public int addReply(HttpServletRequest request);  // 대댓
	// ip를 알려면 request가 있어야 하므로 요기다가  request 보내는 거임
	public int removeBbs(int bbsNo);  // 지울 때는 원댓/답댓 구분 x, 삭제할 땐 no 보내기~
}
