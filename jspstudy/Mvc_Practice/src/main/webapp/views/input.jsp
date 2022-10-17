<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 너비/높이 입력 폼 : 삼각형 버튼, 사각형 버튼 --%>
	<form action="${contextPath}/triangle.do">
		<div>
			너비<input type="text" name="width"><br>
			높이<input type="text" name="height"><br>
			<button>삼각형 넓이</button>
			<button>사각형 넓이</button>
		</div>
	</form>
	<br>
	<%-- 반지름 입력 폼 : 원 버튼 --%>
	<form action="${contextPath}/circle.do">
		<div>
			반지름<input type="text" name="radius"><br>
			<button>원형 넓이</button>
		</div>
	</form>

</body>
</html>