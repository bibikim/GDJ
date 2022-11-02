<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>

	<div>
		<a href="${contextPath}/notice/list.no">공지사항</a>
	</div>

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
				<div>
					<a href="${contextPath}/member/join.me">회원가입</a>  
					<!-- 회원가입은 약관동의로 먼저 가야하지만 오늘은 그냥 회원가입 먼저 열리는걸로~ -->
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
		<div>
			<a id="cancel_link" href="${contextPath}/member/cancel.me">회원탈퇴</a>
			<script>
				$('#cancel_link').click(function(event){
						/* 취소 -> 탈퇴로 가는 링크 이동을 막아줘야 함. */
					if(confirm('정말로 탈퇴하시겠습니까?') == false) {
						/* 동작을 막는건 preventDefault. a링크의 디폴트 동작은 href=""로의 이동임. 이걸 막아야 함 */
						event.preventDefault();    // a 태그의 기본 동작(href로 이동)을 막는다.
						return;
					}
				});
			</script>
		</div>
	</c:if>

</body>
</html>