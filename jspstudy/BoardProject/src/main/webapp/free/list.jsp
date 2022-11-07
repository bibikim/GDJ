
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

	$(document).ready(function() {
	
		$('#remove_click').click(function(event){
			alert('게시글이 삭제되었습니다.');
			event.preventDefault();
		});

		
	});
</script>
</head>
<body>
	
	
	<a href="${contextPath}/free/insertPage.do">작성하러 가기</a>
	
	<hr>
	
	<div class="main">
		<table border="1">
			<thead>
				<tr>
					<td>게시글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>삭제</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${frees}" var="free">
					<tr>
						<td>${free.freeNo}</td>
						<td><a href="${contextPath}/free/detail.do?freeNo=${free.freeNo}">${free.title}</a></td>
						<td>${free.writer}</td>
						<td>${free.hit}</td>
						<td><a href="${contextPath}/free/remove.do?freeNo=${free.freeNo}" id="remove_click">X</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
</body>
</html>