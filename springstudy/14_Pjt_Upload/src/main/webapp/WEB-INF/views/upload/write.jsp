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
	
	$(function() {
		fn_fileCheck();
	})
	
	function fn_fileCheck() {
		
		$('#files').change(function() {		// 첨부가 새로 바뀌었다~ 새로 들어왔다~~
			
			// 첨부 가능하 파일의 최대 크기
			let maxSize = 1024 * 1024 * 10  // 10MB, 1024*1024 = 1MB
			
			// 첨부된 파일 목록
			let files = this.files;   // 첨부된 파일 목록 확인 가능, this = $('#files')
		
			// 첨부된 파일 순회
			for(let i = 0; i < files.length; i++) {
				
				// 크기 체크
				if(files[i].size > maxSize) {  // 크기가 저장되어있던 속성..?
					alert('10MB 이하의 파일만 첨부할 수 있습니다.')
					$(this).val(''); // value = ''로 첨부된 파일으 모두 없애줌.
					return;
				}
			}
			
		})	
		
	}
	
</script>
</head>
<body>

	<div>
		<h1>작성화면</h1>
		<form action="${contextPath}/upload/add" method="post" enctype="multipart/form-data">  
										    	<!-- 파일첨부할 때 약속. 파일첨부하는 폼은 이 두개의 속성과 값이 필수 -->
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" required="required">
			</div>
			<div>
				<label for="content">내용</label>
				<input type="text" id="content" name="content">
			</div>
			<div>
				<label for="files">첨부</label>
				<input type="file" id="files" name="files" multiple="multiple">
				<!-- multiple 속성이 있어야 다중첨부 가능 -->
			</div>
			<div>
				<button>작성완료</button>
				<input type="button" value="목록" onclick="location.href='${contextPath}/upload/list'">
			</div>
		
		</form>
	</div>
	
</body>
</html>