<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 요청 파라미터
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	// 로그인 처리
	// id와 pwd가 동일하면 로그인 성공으로 처리
	if(id.equals(pwd)) {    				// 입력받은 id와 pwd가 동일하면
		session.setAttribute("id", id);
		session.setAttribute("pwd", pwd);   // 세션에 id와 pwd를 저장하겠다(서버의 메모리에 저장됨)
	}
	
	// 로그인 폼으로 돌아가기(자바에서 돌아가는건 forward 아니면 redirect)
	response.sendRedirect("01_form.jsp");
	
	
%>