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

	<div>
		
		<form action="${contextPath}/member/register.me" method="post">
		<!-- 추가, 수정할 때는 post -->
			<div>
				<input type="text" name="id" placeholder="아이디">
				<!-- name 안주면 파라미터가 안 가요~~~~~~~~~ 꼭 주세요오오오 -->
			</div>
			<div>
				<input type="password" name="pw" placeholder="비밀번호486">
				<!-- name 안주면 파라미터가 안 가요~~~~~~~~~ 꼭 주세요오오오   name의 기준은 domain 기반으로 적어주는 거다. 다르게 주면 안됑 -->
			</div>
			<div>
				<input type="text" name="name" placeholder="이름">
			</div>
			<div>
				<input type="text" name="email" placeholder="이메일">
			</div>
			<!-- 사용자가 입력하는 정보는 4개니까 input 요소 4개  -> 얘네의 name들이 request로 전달되는 파라미터 4개임 -->
			<div>
				<button>회원가입</button>
				<input type="reset" value="입력초기화">
			</div>
		</form>
	</div>

</body>
</html>