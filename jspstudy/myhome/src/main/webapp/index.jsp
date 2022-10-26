<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 로그인 응답 성공했다면 세션에다가 login 이란걸 저장해두고 여기까지 온다. -->
<!-- 세션에 들어가 있는 로그인을 el로 사용 가능 -->
	<c:if test="${login == null}">
		<%-- 회원가입이 안되어 있다면 로그인 폼을 보여주겠다 --%>
		<div>
			<form method="post" action="${contextPath}/member/login.me">  <!-- 중간 맵핑 member -->
				<div>
					<input type="text" name="id" placeholder="아이디">
				</div>
				<div>
					<input type="password" name="pw" placeholder="패스워드">
				</div>
				<div>
					<button>로그인</button>
				</div>
			</form>
		</div>
	</c:if>

	<!-- login에는 Member를 가지고 있음. 아이디.이름.비밀번호.메일 등 다 꺼내볼 수 있음 -->
	<c:if test="${login != null}">
		<div>
			${login.name}님 어서오세요
			<input type="button" value="로그아웃" onclick="location.href='${contextPath}/member/logout.me'">
		</div>
	</c:if>

</body>
</html>