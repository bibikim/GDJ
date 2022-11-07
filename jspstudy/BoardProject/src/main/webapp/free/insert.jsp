
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function(){
	
		
		$('#btn_list').click(function(event){
			location.href='${contextPath}/free/list.do';
		});
		
		
	})
	
</script>
</head>
<body>
	
	<h1>자유게시판 게시글 작성화면</h1>
	<div>
		<form id="frm_free" method="POST" action="${contextPath}/free/insert.do">
			<div>
				<input type="text" id="writer" name="writer" placeholder="작성자">
			</div>
			<div>
				<input type="text" id="title" name="title" placeholder="제목">
			</div>
			<div>
				<textarea rows="5" cols="30" id="content" name="content" placeholder="내용"></textarea>
			</div>
			
			<div>
				<input type="submit" id="btn_insert" value="작성완료">
				<input type="reset" id="btn_reset" value="다시작성">
				<input type="button" id="btn_list" value="목록">
			</div>
		</form>
	</div>
</body>
</html>