<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<form action="${contextPath}/ntc/add" method="post">
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" required="required">
			</div>
			<div>
				<label for="content">내용</label><br>
				<textarea id="content" name="content" rows="5" cols="30"></textarea>
			</div>
			<div>
				<button>공지등록하기</button>
				<input type="reset" value="입력초기화">
				<input type="button" value="목록" onclick="location.href='${contextPath}/ntc/list'">
			</div>
		</form>
	</div>

</body>
</html>