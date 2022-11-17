<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="requestURL" value="${pageContext.request.requestURI}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	$(function() {
	
		$('#btn_check_pw').click(function() {
			
			$.ajax({
				
				// id, pw 보내니까 post
				type: 'post',
				url: '${contextPath}/user/check/pw',
				data: 'pw=' + $('#pw').val(),
				
				dataType: 'json',
				success: function(resData) {
					if(resData.isUser) {
						location.href = '${contextPath}/user/mypage';  // location으로 이동 -> get 매핑
					} else {
						alert('비밀번호를 확인하세요.');   // ajax라 굳이 이동할 필요 없으니 걍 alert창!
					}
				}
				
			})
		})
		
	})
	

</script>
</head>
<body>
	
	<div>
		<div>개인정보보호를 위해서 비밀번호를다시 한 번 입력하세요.</div>
		
		<div>
			<label for="pw">비밀번호</label>
			<input type="password" id="pw">
		</div>
		
		<div>
			<input type="button" value="취소" onclick="history.back()">
			<input type="button" value="확인" id="btn_check_pw"> 
			<!-- 비밀번호랑 id 두개 보내서 
				btn_check_pw버튼을 누르면 DB 가야됨! => ajax 처리 -->
		</div>
	</div>
	
	
</body>
</html>