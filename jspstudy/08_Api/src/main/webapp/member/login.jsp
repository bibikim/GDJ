<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());    // <c:set ~~>이 하는 일
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function(){
		$('#btn_refresh').click(function(){
			$.ajax({
				type: 'get',
				url: '${contextPath}/member/refreshCaptcha.do',
				/* 응답 */
				dataType: 'json',
				success: function(resData){   // resData : {"dirname" : "", "filename" : ""}
					$('#ncaptcha').attr('src', '../' + resData.dirname + '/' + resData.filename)    // 이미지의 속성을 바꾸겠다==이미지의 경로를 바꾸겠다 (src같은 경우는 attr(), prop() 노상관)
					$('#key').val(resData.key);   // 클릭할 때마다(이미지가 달라질 때마다) id=key의 val속성을 바꿔줌
				}
			})
		})
	});


</script>
</head>
<body>
	
	<div class="wrap">
	
		<h1>로그인</h1>
		<form action="${contextPath}/member/validateCaptcha.do" method="post">
		<!-- aciton이 있어야 요청이 된다 -->
			<div>
				<input type="text" name="id" id="id" placeholder="아이디">
			</div>
			<div>
				<input type="password" name="pw" id="pw" placeholder="패스워드">
			</div>
			<div>
				<p>아래 이미지를 보이는 대로 입력해주세요.</p>
				<div style="display: flex;">
					<div>
						<img src="../${dirname}/${filename}" id="ncaptcha">
						<!-- 실제로는 절대 경로 사용 못 함. -->
					</div>
					<div>
						<input type="button" value="새로고침" id="btn_refresh">
						<!-- 그림이 달라지면 캡차키도, 캡차이미지도 다 달라지기 때문에 getKey, getImage 다 다시 해야함. 화면깜빡임없이(ajax처리) -->
					</div>
				</div>
			</div>
			<div>
				<input type="text" name="value" placeholder="자동입력 방지문자">   <!-- // 사용자 입력은 value -->
				<input type="hidden" name="key" id="key" value="${key}">  
				
				<!-- 이미지가 나오면 이미지에 맞는 키를 가지고 있어야 함. 이미지별로 키가 다르기 때문에. 새로고침 할때마다 키가 계속 달라진다. 매번 다르기 때문에 그때그때 저장해줘야 함. 그래서 map에 담은거얄 -->
			</div>
			<div>
				<button>로그인</button>  
				<!-- └> 서브밋(로그인)할때 위에 name속성 날아감 -> request로 파라미터형식으로 날아갈거임. -->
			</div>
		</form>
	</div>
	
	<!-- 
		login.jsp에서 캡차이미지 찾아가는 경로!
						webapp
						   member
						     login.jsp
						   ncaptcha
						     xxx.jpg
			하나 올라가고 거기서 ncaptcha(dirname)로 가서 xxx(filename) 가져오기
	
	 -->
	
</body>
</html>