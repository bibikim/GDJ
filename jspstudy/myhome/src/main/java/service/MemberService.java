package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface MemberService {
	// 아이디, 비번 가지고 로그인/로그아웃 할 때 쓸 것
	public ActionForward login(HttpServletRequest request, HttpServletResponse response);
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response);
	public void register(HttpServletRequest request, HttpServletResponse response);
	public void cancel(HttpServletRequest request, HttpServletResponse response);
	   // 실무에서는 서비스인터페이스 안 만듦. 만들어진 작업,,
	
	
	// 아이디 중복 체크 - ajax 기능. 시간되면 한당
	
	
}
