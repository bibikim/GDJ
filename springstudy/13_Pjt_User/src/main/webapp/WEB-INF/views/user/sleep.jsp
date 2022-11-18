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
<!-- <script>
	
	$(function(){
		$('#frm_restore').submit(function(event){
			
			if($('#pw').val() == '') {
				alert('비밀번호를 입력하세요');
				event.preventDefault();
				return;
			}
			
		})
	})
	
</script> -->
</head>
<body>

	<div>
		<h1>휴면계정</h1>
	</div>
	
	<div>
		안녕하세요<br>
		${sleepUser.id}님은 1년 이상 로그인하지 않아 관련 법령에 의해 휴면계정으로 전환되었습니다.<br>
		<ul>
			<li>가입일 ${sleepUser.joinDate}</li>
			<li>마지막 로그인 ${sleepUser.lastLoginDate}</li>
			<li>휴면전환일 ${sleepUser.sleepDate}</li>    
		</ul>
	</div>
	
	<hr>
	
	<div>
		<div>
			휴면해제를 위해 휴면해제 버튼을 클릭해 주세요.
		</div>
		<form id="frm_restore" action="${contextPath}/user/restore" method="post">
		<!-- 	<div>
				<label for="pw">비밀번호</label>
				<input type="password" name="pw" id="pw">
				세션에 올라간 id,pw는 암호화 되어있고 사용자가 입력한 pw는 암호화 이전이기 때문에 비교 불가. 정보를 서버로 넘겨줘서 암호화한 후에 비교하게끔 해야함. 따라서 여기서는 비교 못함
			</div> -->
			<div>
				<button>휴면해제</button>
				<input type="button" value="취소" onclick="location.href='${contextPath}'">
			</div>
		</form>
	</div>

	
</body>
</html>