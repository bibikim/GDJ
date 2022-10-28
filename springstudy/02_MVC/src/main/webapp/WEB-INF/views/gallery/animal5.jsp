<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%-- 	
		파라미터로 전달했느냐 속성으로 전달했느냐에 따라 꺼내보기가 다름     
		속성으로 전달되었다면, 그냥 EL ${} 열어서 보면 되고,
		파라미터로 전달했다면, ${param.@@} 로 해서 봐야됨!
	 --%>
	
	
	<%--요청에 싣고온 데이터는 ${}로 부르기 가능. filename은 파라미터임. 파라미터 꺼내보는 방법은 ${param.@@@}  --%>
	<h1>🐹보고 싶은 동물 animal5🐹 ${param.filename}</h1>
	<img src="${contextPath}/resources/images/${param.filename}" width="200px">
	<%-- 파라미터로 전달된 filename ( -> 파라미터를 이용한 포워드 ) --%> 
	
	
</body>
</html>