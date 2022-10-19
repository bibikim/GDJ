<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>
	* {
		box-sizing: border-box;
		padding: 0;
		margin: 0;
		color: #333;
	}

	h1 {
		text-align: center;
	}
	.btn_write {
		text-align: center;
		width: 80px;
		height: 40px;
		background-color: skyblue;
		margin: 20px auto;
		line-height: 2.5;
		cursor : pointer;
	}
	.btn_write:hover {
		background-color: #cc99ff;
	}
	ul {
		list-style: none;
		display: flex;
		justify-content: space-between;
		width: 630px;
		margin: 30px auto;
	}
	a {
		text-decoration: none;
	}
	ul > li {
		width: 200px;
		height: 200px;
		padding-top: 10px;
		margin-top: 10px;
		margin-right: 10px;
		text-align: center;
		border: 1px solid gray;
		border-radius: 5px;
	}
	ul > li > a {
		display: block;
		width: 100%;   /* li만큼 사이즈 주기 */
		height: 100%;
	}
	
	ul > li:hover {
		background-color: orange;
	}
</style>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	
	$(document).ready(function(){
	
		$('#btn_write').click(function(event){
			location.href = '${contextPath}/board/write.do';
		})
	});
	
	
</script>
</head>
<body>

	<h1>게시글 목록 보기</h1>
	<div class="btn_write" id="btn_write">추가</div>
	<ul>
			<c:forEach items="${boards}" var="b">  <!-- 하나씩 뺄거 = b -->
				<li>
					<a href="${contextPath}/board/detail.do?boardNo=${b.boardNo}">
						<div>${b.title}</div>        <!-- 실제로는 getter를 부르는 거임 -> b.getTitle() -->
						<div>${b.createDate}</div>
					</a>
				</li>
			</c:forEach>
	</ul>

</body>
</html>