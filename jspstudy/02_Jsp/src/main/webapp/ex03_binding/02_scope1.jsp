<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% 
		application.setAttribute("a", 1);	 // 애플리케이션 종료 전까지 유지
		session.setAttribute("b", 2);		 // 브라우저 닫기 전까지 유지
		request.setAttribute("c", 3);		 // 하나의 요청&응답 사이클 내에서 유지
		pageContext.setAttribute("d", 4);    // 현재 페이지에서만 유지
	%>
	
	<div>a : ${a}</div>
	<div>b : ${b}</div>	
	<div>c : ${c}</div>
	<div>d : ${d}</div>
	
	<%-- 포워드 : request 정보를 전달하는 이동 방식 --%>
	<%-- 
		request.getRequestDispatcher("02_scope2.jsp").forward(request, response);
	--%>
	
	<%-- 포워드 태그 : 포워드를 쉽게 하기 위해 jsp에서 만들어둔 액션 태그 --%>
	<%-- http://localhost:9090/02_Jsp/ex03_binding/02_scope1.jsp  //=> 포워드는 클라이언트 입장에서는 scope2가 안 보이지만 서버 내부에서는 1에서 2로 이동한 것임
		따라서, 보여지는 주소는 scope1 --%>
	<jsp:forward page="02_scope2.jsp"></jsp:forward>
	
	
	
	<%-- 리다이렉트 : request 정보를 전달하지 않는 이동 방식    ==> request에 보관해둔 정보는 전달하지 않기 때문에 c를 확인할 수 없는 것. --%>
	<%-- http://localhost:9090/02_Jsp/ex03_binding/02_scope2.jsp  // => 리다이렉트는 두번의 요청이 있는 것. scope2로 가라고 서버가 요청한 것이기 때문에
		 보여지는 주소가 scope2 --%>
	<%--
	<%
		response.sendRedirect("/02_Jsp/ex03_binding/02_scope2.jsp");
	%>
	--%>
	
</body>
</html>