<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- lib 사용 가능한 이유 : pom.xml에 태그로 자르 작업이 되어 있다~ -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 이클립스에서와 달리 웰컴파일->시작페이지로 열리지 않음.. web.xml에 웰컴파일리스트가 없음. 스프링에선 직접 코드로 작업해야함.  : MvcController에서 함! -->
	
	<a href="${contextPath}/animal">동물보러가기</a>    <!-- 근데 여기는 왜 뒤에 확장자를 안 붙여주지.....? -->
	
	<hr>
	
	<a href="${contextPath}/flower">꽃보러가기</a>
	
	<hr>
	
	<a href="${contextPath}/animal/flower">동물보러 갔다가 꽃보러 가깅~</a>
	
	<hr>
	
	<a href="${contextPath}/want/animal?filename=animal5.jpg">animail5 보러가깅</a>
	
	<hr>
	
	<a href="${contextPath}/response">응답 만들어 받기</a>
	
	
</body>
</html>