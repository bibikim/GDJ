<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	
	$(function() {
		
		// 첨부 삭제    -> 포스트로 바꾸기! form 태그 추가해서 method="post" 줘서 바꾸면 됨.
		$('.btn_attach_remove').click(function(){
			if(confirm('해당 첨부파일을 삭제할까요?'))
				location.href='${contextPath}/upload/attach/remove?uploadNo=' + $(this).data('upload_no') + '&attachNo=' + $(this).data('attach_no');   // get방식 요청
			/* attachNo를 받아오기 곤란할 때..? */
			/* data- 속성값을 넣어주고 삭제할 첨부번호를 넣어주기. 꺼내 쓸 때는 this(클릭 대상)의 data속성('속성이름') 꺼내쓰기 */
		});
		
	});
	
</script>
</head>
<body>

	<div>
		<h1>업로드 게시판 정보</h1>
		<ul>
			<li>제목 : ${upload.title}</li>
			<li>내용 : ${upload.content}</li>
			<li>작성일 : ${upload.createDate}</li>
			<li>작성일 : <fmt:formatDate value="${upload.createDate}" pattern="yyy-MM-dd HH:ss"/></li>
			<li>수정일 : ${upload.modifyDate}</li>
		</ul>
	</div>
	
	<hr>
	
	<div>
		<h3>첨부목록</h3>
		<!-- 다중 첨부 파일이 list로 들어오니까 c:foreach 반복문 돌려서 표시 -->
		<c:forEach items="${attachList}" var="attach">
			<div>
				<a href="${contextPath}/upload/download?attachNo=${attach.attachNo}">${attach.origin}</a>
				<input type="button" value="삭제" class="btn_attach_remove" data-upload_no="${upload.uploadNo}" data-attach_no="${attach.attachNo}">
			</div>
		</c:forEach>
	</div>
	
	
</body>
</html>