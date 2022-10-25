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
				success: function(resData){  // resData : {"dirname" : "", "filename" : ""}
					$('#ncaptcha').attr('src', '../' + resData.dirname + '/' + resData.filename)    // 이미지의 속성을 바꾸겠다==이미지의 경로를 바꾸겠다 (src같은 경우는 attr(), prop() 노상관)
				}
			})
		})
	});


</script>
</head>
<body>
	
	<div class="wrap">
	
		<h1>로그인</h1>
		<form>
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
				<input type="text" name="user_input" placeholder="자동입력 방지문자">
			</div>
			<div>
				<button>로그인</button>
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