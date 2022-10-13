<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%--
		1. JSP의 Binding 영역 4개
			1) application : ServletContext와 같은 영역
			2) session     : HttpSession과 같은 영역
			3) request	   : HttpServletRequest와 같은 영역
			4) pageContext : 하나의 JSP 페이지에서 접근 가능한 영역
	--%>
	
	<% 
		application.setAttribute("a", 1);
		session.setAttribute("b", 2);
		request.setAttribute("c", 3);
		pageContext.setAttribute("d", 4);    // 각각의 속성을 binding 영역에 데이터 저장
	%>
	
	<%-- 표현식으로 접근 --%>
	<div>a : <%=application.getAttribute("a") %></div>
	<div>b : <%=session.getAttribute("b") %></div>
	<div>c : <%=request.getAttribute("c") %></div>
	<div>d : <%=pageContext.getAttribute("d") %></div>
	
	<%-- 표현언어(EL)를 이용해 속성 접근 --%>
	<div>a : ${a}</div>
	<div>b : ${b}</div>
	<div>c : ${c}</div>
	<div>d : ${d}</div>
	
	<%--
		2. 우선 순위
			1) 같은 이름의 속성이 서로 다른 영역에 저장된 경우 먼저 사용되는 우선 순위가 존재함
			2) pageContext > request > session > application 순으로 우선 순위
			3) 각 영역을 지정하는 표현 언어(EL)의 내장 객체가 존재함
				(1) pageScope           -> pageContext에 저장되어 있는걸 부르는 말
				(2) requestScope
				(3) sessionScope
				(4) applicationScope
				
				이 4개의 영역 안에 없는건 EL로 표현할 수 없음(일반 자바변수를 EL로 표기할 수 없음)
				즉, EL을 사용하려면 이 영역들 안에 저장되어 있어야 하는 것. 그래야 el로 접근해서 사용 가능
	--%>
	
	<% 
		application.setAttribute("movie", "기생충");   // 우선 순위가 가장 낮음
		session.setAttribute("movie", "범죄도시");
		request.setAttribute("movie", "모가디슈");
		pageContext.setAttribute("movie", "미나리");     // 우선 순위가 가장 높음
	%>
	<div>applicaiton's movie : ${movie }</div>
	<div>session's movie : ${movie }</div>
	<div>request's movie : ${movie }</div>
	<div>pageContext's movie : ${movie }</div>
	<!-- 저장을 다른데다가 한 것이지, 우선순위 적용 때문에 각각의 값이 호출이 안 된 것일 뿐(미나리로만 화면에 뿌려짐). 그런거 호출할 때 2의 3)을 사용하란 얘기 -->
	 
	<br><br>
	 
	<div>applicaiton's movie : ${applicationScope.movie }</div>
	<div>session's movie : ${sessionScope.movie }</div>
	<div>request's movie : ${requestScope.movie }</div>
	<div>pageContext's movie : ${movie }</div>
	 
	 
</body>
</html>