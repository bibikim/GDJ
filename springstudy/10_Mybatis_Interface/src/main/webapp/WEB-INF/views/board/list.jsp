<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
<script>

	$(document).ready(function(){
		
		
		
	})
	

</script>
</head>
<body>

	<div>
		<a href="${contextPath}/brd/write">새 글 작성</a>
	</div>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boards}" var="board">   <!-- ${boards} : forward한 데이터 그대로 쓴다. -->
					<tr>
						<td>${board.boardNo}</td>
						<td><a href="${contextPath}/brd/detail?boardNo=${board.boardNo}">${board.title}</a></td>  
															 		<!-- ${board.boardNo} 를 파라미터값으로 받기 -->
						<td>${board.writer}</td>
						<td>${board.createDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	
	
	
	
	
	
	
	

</body>
</html>