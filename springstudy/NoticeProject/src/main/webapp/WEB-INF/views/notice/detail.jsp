<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	/* DB 갔다올 필요 없이 상세보기 / 편집화면 보여주기 ! ! ! ! ! ! ! ! ! */

	$(function() {
		
		$('#edit_area').hide();    // 편집 영역 숨김
		
		$('#btn_edit').click(function () {
			$('#edit_area').show();     // 편집 버튼 누르면 편집 영역 보여주기
			$('#detail_area').hide();	// 그럼과 동시에 상세보기 영역 숨김
		})
	});
</script>
</head>
<body>

	<div id="detail_area">
		<h1>공지번호 ${notice.noticeNo}</h1>   <!-- 객체 notice에 실려있는 정보들 가져오기 -->
		<h1>제목 ${notice.title}</h1>
		<h1>내용 ${notice.content}</h1>
		<h1>조회수 ${notice.hit}</h1>
		<h1>작성일 ${notice.createDate}</h1>
		<h1>수정일 ${notice.modifyDate}</h1>
		<div>
			<!-- 삭제버튼이 form의 action과 method를 먹는 애들! -->
			<form id="frm_remove" action="${contextPath}/ntc/remove" method="post">
				<input type="hidden" name="noticeNo" value="${notice.noticeNo}">  <!-- 편집과 마찬가지로 삭제할 범위가 필요하다 -> hidden -->
				<input type="button" value="편집" id="btn_edit">
				<input type="submit" value="삭제">
				<input type="button" value="목록" onclick="location.href='${contextPath}/ntc/list'">
			</form>
			<script>
				$('#frm_remove').submit(function(event) {   // ★★ form은 click 아님!!!! .submit() 할 때 뭘 하겠다~~~ 해야됨
					if(confirm('공지를 삭제할까요?') == false) {
						event.preventDefault();   // 서버로 데이터를 보내서 작업하는 것(submit)을 막겠다
						return;    // 코드 진행 막기
					}
				})
			</script>
		</div>
	</div>

	<!-- 편집할 때 쓸 폼이랑 같은 부분쓰 -->
	<div id="edit_area">
		<form action="${contextPath}/ntc/modify" method="post">
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" value="${notice.title}" required="required">
			</div>
			<div>
				<label for="content">내용</label><br>
				<textarea id="content" name="content" rows="5" cols="30">${notice.content}</textarea>
			</div>
			<div>
				<input type="hidden" name="noticeNo" value="${notice.noticeNo}"> <!-- 수정하기 위해 어떤 글을 수정할건지 글번호가 필요! -->
				<button>공지수정하기</button>
				<input type="reset" value="입력초기화">
				<input type="button" value="목록" onclick="location.href='${contextPath}/ntc/list'">
			</div>
		</form>
	</div>

</body>
</html>