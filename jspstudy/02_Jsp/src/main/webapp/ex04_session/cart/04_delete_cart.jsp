<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// session에 저장된 cart 제거하기    장바구니만 비우면 되므로 전부 다 지우는 invalidate() 불가
	session.removeAttribute("cart");  // cart라는 attribute만 지우고 로그인정보같은건 그대로.
	
	// 장바구니 목록으로 이동
	response.sendRedirect("03_cart_list.jsp");
%>