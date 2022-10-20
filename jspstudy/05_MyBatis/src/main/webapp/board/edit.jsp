<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.boardNo}번 게시글 편집 화면</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function() {

		$('#frm_edit').submit(function(event){     // frm_edit가 서브밋 이벤트를 실행할 때!
			if($('#title').val() == '') {		   // $('#title').val() == 현재 입력 제목  <->  ${board.title} == 수정 전 제목
				alert('제목은 필수입니다.');	   // 서브밋 방지
				$('#title').focus();
				event.preventDefault();
				return;
			}
			/*
			if('${board.title}' == $('#title').val() && '${board.content}' == $('#content').val()) {
				alert('변경된 내용이 없습니다');   // if(이전 제목 == 현재 제목 && 이전 내용 == 새로 작성한 내용)
				event.preventDefault();			   // 서브밋 방지
				return;
			}
		   -------------------> 컴파일에러남. 어차피 실무에서 안 쓰니 날려~~~~~
			*/
		});
		
		$('#btn_list').click(function(event){
			location.href = '${contextPath}/board/list.do';
		});
	});

</script>
</head>
<body>

	<h1>게시글 편집 화면</h1>
	<div>
		<form id="frm_edit" method="POST" action="${contextPath}/board/modify.do">
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" value="${board.title}">
			</div>
			<div>
				<label for="content">내용</label>
				<textarea id="content" name="content" rows="5" cols="30">${board.content}</textarea>
			</div>
			<input type="hidden" name="boardNo" value="${board.boardNo}">
			<!-- ▶ 보낼 파라미터가 3개인 것!(ModifyService에 적어줄 파라미터) -->
			<div>
				<input type="submit" value="수정완료">
				<input type="reset" value="다시작성">
				<input type="button" value="목록" id="btn_list"> 
										<!-- 목록 버튼 누르면 리스트로 가도록 만들기 위해 id 주기 -->
			</div>
		</form>
		
	</div>

</body>
</html>