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
		
		$('#btn_write').click(function(event){
			location.href= '${contextPath}/post/write.po';
		});
		
		
	});

</script>
</head>
<body>

	<div>
		<form>
			<table border="1">
				<caption>총 게시글 : ${count}개</caption>
				<thead>
					<tr>
						<td>순번</td>
						<td>작성자</td>
						<td>제목</td>
						<td>작성일</td>
					</tr>
				</thead>
				<tbody>
					<c:if test="${count == 0}">
						<tr>
							<td colspan="4">작성된 게시글이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${count != 0}">
						<c:forEach items="${posts}" var="post">
							<tr>
								<td>${post.postNo}</td>
								<td>${post.writer}</td>
								<td>${post.title}</td>
								<td>${post.creDate}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<input type="button" id="btn_write" value="새 글 작성">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>

</body>
</html>