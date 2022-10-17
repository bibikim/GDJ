<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
			<%-- pageContext EL 객체가 따로 있음. contextPath의 request에서 contextPath를 빼면 나타난당
			     자바에서 request.getContextPath() 와 동일한 역할을 함. 여기서 contextPath = /03_Mvx == ${contextPath}
			 	 contextPath 값을 변수 처리해주고 쓰는 것이 좋다 --%>
    
    <%-- index.jsp 는 웰컴파일로 동작할 수 있게 webapp 아래에 두어야 함! --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3><a href="${contextPath}/today.do">오늘은 며칠이져?</a></h3>  <!-- Today는 서블릿! 앞에 contextPath니까 -->
	<h3><a href="${contextPath}/now.do">지금 몇 시에여?</a></h3>
	<h3><a href="${contextPath}/input.do">더하기 하러 가라</a></h3>
	
	<!-- 파일 생성 순서.     index.jsp - Today.servlet - TodayService.class - result.jsp -->
	
</body>
</html>