<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%-- 로그인 성공 시 session에 id, pwd 저장된 상태임 --%>
	<%
		Object id = session.getAttribute("id");
		Object pwd = session.getAttribute("pwd");
	%>
	
	<% if(id != null && pwd != null) { %>
		${id}님 환영합니다&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
		<%-- ${id}는 session에 저장된 id임(위에 선언된 변수 id 아니다)  => EL 사용 가능한 것은 4개의 영역에 저장된 속성! --%>
		<input type="button" value="로그아웃" id="btn_logout">
	<% } else { %>
		<div>
			<form method="POST" action="02_login.jsp">
				<div>
					<input type="text" name="id" placeholder="아이디">     <!-- name은 파라미터가 된다 -->
				</div>
				<div>
					<input type="password" name="pwd" placeholder="비밀번호">
				</div>
				<div>
					<button>로그인</button>
				</div>
			</form>
		</div>
	<% } %>
	<!--  로그인 성공하면 환영합니다.      실패하면 로그인 창 다시 띄우기 -->
	
	<script>
		document.getElementById('btn_logout').onclick = function(evnet) {
			location.href = '03_logout.jsp';
		}
	</script>
</body>
</html>