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
</head>
<body>
	<%-- 
		모든 jsp 페이지 위에는 session이라는 공간이 있다. 거기서 loginUser(서비스Impl에서 선언)라고 하는 데이터를 꺼내서 사용할 수 있다. 
		세션에 올라가있는 정보 jsp에서 꺼내서 쓸 때는 ${} EL로 확인 가능 
	--%>
	
	<!-- 
		* 로그인이 안 된 상태 
	
		naver 로그인창 보면, 내가 있던 창에서 login창을 누르면 뒤에 붙여서 보내는 url 파라미터가 있는데, (login?url=https%3A%2F%2Fvibe.naver.com%2Ftoday)
		로그인을 완료하면 원래 있던 창으로 다시 돌아가게끔 하기 위한 것. 
	-->
	<c:if test="${loginUser == null}">
		<a href="${contextPath}/user/agree">회원가입 페이지</a>
		<a href="${contextPath}/user/login/form?url=${requestURL}">로그인 페이지</a>
	</c:if>
	
	<!-- 로그인이 된 상태 -->
	<c:if test="${loginUser != null}">
		<div>
			<a href="${contextPath}/user/mypage">${loginUser.name}</a>님 반갑습니다.
		</div>
		<a href="${contextPath}/user/logout">로그아웃</a>
		<a id="lnk_retire" href="${contextPath}/user/retire">회원탈퇴</a>
		<!-- 탈퇴 : user테이블에서는 delete, retireUser테이블에서는 insert (<-이게 트리거는 아닌데~ 트리거도 뭔지 공부 함 더하장) -->
		<script>
			$('#lnk_retire').click(function(event){
				if(confirm('탈퇴하시겠습니까?') == false) {
					event.preventDefalut();  /* event.preventDefault() 대상 => <a>태그의 기본이벤트인 링크이동(href 실행)을 막는 것 */
					return;
				}
			});
		</script>
	</c:if>
</body>
</html>