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
	
	<h1>😻😻 예쁜 동물 좀 보세요~ 요기에요 요기</h1>
	<img src="resources/images/animal1.jpg" width="200px">
	
	<h1>저도 봐 주셍</h1>  <!-- 태그 수정은 새로고침 가넝ㅇㅇ -->
	<img src="assets/images/animal3.jpg" width="200px">
	
	<!-- 
		webapp 하위라는 경로는 똑같은데 resources에 있는건 보이고, assets에 있는건 안 보인다.
		??????왜????? 
		servlet-context.xml 파일에 그렇게 작업을 해둠 <resources mapping="/resources/**" location="/resources/"/>
		
		asstes도 나오게 하고 싶다면 resources 태그를 추가하는 작업을 해주면 된다
		<resources mapping="/assets/**" location="/assets/" />
	-->
	
	
</body>
</html>