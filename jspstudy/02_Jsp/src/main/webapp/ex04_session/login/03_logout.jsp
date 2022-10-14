<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 로그아웃 - session에 저장된 정보를 초기화하기
	session.invalidate();   // session에 저장된 번
	
	// 로그인 폼으로 가기
	response.sendRedirect("01_form.jsp");
	
%>