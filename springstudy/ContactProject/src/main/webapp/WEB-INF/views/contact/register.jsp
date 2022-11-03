<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="{pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	
	$(document).ready(function(){
		
		$('#btn_list').click(function(){
			location.href='${contextPath}/list';
		})
		
	})
	
	
	
</script>
</head>
<body>

	<h1>연락처 등록</h1>
	<div>
		<label for="name">이름*</label>
		<input type="text" name="name" id="name">
	</div>
	<div>
		<label for="name">전화*</label>
		<input type="text" name="name" id="name">
	</div>
	<div>
		<label for="name">주소*</label>
		<input type="text" name="name" id="name">
	</div>
	<div>
		<label for="name">이메일*</label>
		<input type="text" name="name" id="name">
	</div>
	<div>
		<label for="name">비고</label>
		<input type="text" name="name" id="name">
	</div>
	<div>
		<input type="button" id="btn_regi" value="연락처 저장하기">
		<input type="button" id="btn_list" value="전체연락처">
	</div>

</body>
</html>