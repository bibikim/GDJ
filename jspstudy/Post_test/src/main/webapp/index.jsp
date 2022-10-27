<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	
	$(document).ready(function(){
		
		$('#post_go').click(function(event){
			location.href = '${contextPath}/post/list.po';
		});
	});

</script>
</head>
<body>
	
	
		<input type="button" id="post_go" value="게시판으로 가기">



</body>
</html>