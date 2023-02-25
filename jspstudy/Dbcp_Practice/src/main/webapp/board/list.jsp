<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function(){
		
		$('#btn_write').click(function(event) {
			location.href = '${contextPath}/board/write.jsp';
		})
	});

</script>
</head>
<body>

	<h1>게시글 목록 화면</h1>
	<div>
	<input type="button" id="btn_write" value="새 게시글 작성하러 가기">
	</div>
	<div>
		전체 게시글 ${board}개
		<table border="1">
			<thead>
				<tr>
					<td>글 번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>작성일</td>
					<td>삭제</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boards}" var="board">
					<tr>
						<td>${board.no}</td>
						<td>${board.title}</td>
						<td>${board.createdate}</td>
						<td></td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	

</body>
</html>