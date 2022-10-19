<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.boardNo}번 게시글</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	// 태그가 밑에 있고 스크립트가 위에 있으려면 ready() 필수!
	$(document).ready(function(){
		
		$('#btn_remove').click(function(event){
			if(confirm('삭제하면 복구가 불가능합니다. 게시글을 삭제할까요?')) {
				location.href = '${contextPath}/board/remove.do?boardNo=${board.boardNo}';
			} else {
				alert('취소되었습니다.');
			}
		});
		
		$('#btn_list').click(function(event){
			location.href='${contextPath}/board/list.do';
		});
	});

</script>
</head>
<body>
	<h1>${board.title}</h1>
	<pre>${board.content}</pre>   <!-- 내용에 줄바꿈 포함 되어 있으면 그것까지 반영해서 가져오는 태그 -->
	<div>${board.createDate}</div>
	
	<hr>
	
	<div>
		<input type="button" value="편집" id="btn_edit">
		<input type="button" value="삭제" id="btn_remove">
		<input type="button" value="목록" id="btn_list">
	</div>
</body>
</html>