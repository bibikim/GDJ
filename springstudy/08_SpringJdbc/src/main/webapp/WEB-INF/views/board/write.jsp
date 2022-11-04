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
		
		$('#content').summernote({
			width: 800,   // 객체 형태로 여러가지 넣어줄 수 있음
			height: 400,
			lang: 'ko-KR',
			 toolbar: [        // 사용한 태그까지 데이터베이스에 저장된당
			    // [groupName, [list of button]]
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']]
			  ]
		});
		
		// 목록
		$('#btn_list').click(function() {
			location.href='${contextPath}/brd/list';
		})
		
		// 서브밋
		$('#frm_board').submit(function(event){
			if($('#title').val() == '' || $('#writer').val()== '') {  // 제목과 작성자의 입력값이 없을 때('' 비었을 때)
				alert('제목과 작성자는 필수입니다.');
				event.preventDefault();  // 서브밋 취소
				return;  // 더 이상 코드 실행할 필요 없음
			}
		});
		
	})
	

</script>
</head>
<body>


	<div>
		<h1>작성 화면</h1>
		<form id="frm_board" action="${contextPath}/brd/add" method="post">
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title">
			</div>
			<div>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer">
			</div>
			<div>
				<label for="content">내용</label>
				<textarea id="content" name="content"></textarea>
			</div>
			<div>
				<button>작성완료</button>
				<input type="reset" value="입력초기화">
				<input type="button" value="목록" id="btn_list">
			</div>
		</form>
	</div>

</body>
</html>