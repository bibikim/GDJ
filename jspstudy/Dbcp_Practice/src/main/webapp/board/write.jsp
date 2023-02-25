<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function(){
		
		
		
	});

</script>
</head>
<body>

 <h1>게시글 작성 화면</h1>
 <div>
 	<form method="post" action="${contextPaht}/board.add.do" id="frm_write">
 		
 		 <div>
 			<label for="writer">작성자</label><br>
 			<input type="text" id="writer" name="writer">
 		</div>
 		<div>
 			<label for="title">제목</label><br>
 			<input type="text" id="title" name="title">
 		</div>
 		<div>
 			<label for="content">내용</label><br>
 			<textarea id="content" name="content" rows="6" cols="40"></textarea>
 		</div>
 		
 	</form>
 </div>

</body>
</html>