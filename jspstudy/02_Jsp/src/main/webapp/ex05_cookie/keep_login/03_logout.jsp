<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 로그아웃 - session에 저장된 정보를 초기화하기
	session.invalidate();   // session에 저장된 번
	
	
	// 쿠키에 저장된 정보를 지우기   -> 로그아웃 시 자동로그인 막기
	Cookie cookie1 = new Cookie("id", "");
	cookie1.setMaxAge(0);
	response.addCookie(cookie1);
	Cookie cookie2 = new Cookie("pwd", "");
	cookie2.setMaxAge(0);
	response.addCookie(cookie2);
	
	
	// 로그인 폼으로 가기
	response.sendRedirect("01_form.jsp");
	
%>