<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 요청 파라미터
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String chkKeepLogin = request.getParameter("chk_keep_login");
	
	// id="min", pwd="1234"이면 로그인 성공
	if(id.equals("min") && pwd.equals("1234")) {
		
		// 로그인 유지하기를 체크했다면 chkKeepLogin이 null이 아님
		// 로그인 유지하기를 체크했다면 로그인 정보를 쿠키에 저장(브라우저를 닫아도 정보를 유지하기 위해서) 
		// --> 실무용 아님미다~~ 세션/쿠키 연습용으로 작성하는 코드
		if(chkKeepLogin != null) {
			Cookie cookie1 = new Cookie("id", id); // 쿠키이름 "id"에 id 저장하긩
			cookie1.setMaxAge(60*60*24*7);
			response.addCookie(cookie1);
			Cookie cookie2 = new Cookie("pwd", pwd); // 쿠키이름 "id"에 id 저장하긩
			cookie2.setMaxAge(60*60*24*7);
			response.addCookie(cookie2);
		}
		// 로그인 유지하기를 체크하지 않았다면 일반 로그인 처리(로그인 정보를 세션에 저장)
		else {
			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
		}
		
		// 로그인 실패시 작성할 건 뭐 없성
	}

	// 로그인 폼으로 가기
	response.sendRedirect("01_form.jsp");
%>