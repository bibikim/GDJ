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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- ㄴ> jquery.cookie.min.js : jquery가 정의가 되어 있어야 jquery cookie 사용 가능하므로 순서 중요 -->
<script>
	
	$(function() {
		
		fn_login();
		fn_displayRememberId();
		
	})
	
	function fn_login() {  // 로그인은 폼의 서브밋이 들어갈 때 동작
		
		$('#frm_login').submit(function(event){  // submit의 펑션에는 서브밋 취소할 수 있게 event 달아주기
			
			if($('#id').val() == '' || $('#pw').val() == '') {
				alert('아이디와 패스워드를 모두 입력하세요.');
				event.preventDefault();   // submit을 막음
				return;	 // 아래 코드 진행을 막음(submit이 막혔든 아니든 밑에 코드를 진행시키기 때문에 return 필요!)
			}
		
			// 쿠키는 클라이언트(내pc)에 저장됨. -> 아이디저장에 체크가 되어있으면~ rememberid란 이름으로 id="id"를 쿠키에 보관
			if($('#rememberId').is(':checked')) {
				$.cookie('rememberId', $('#id').val());  // jquery.cookie.min.js 파일이 지원 - ('쿠키이름', 쿠키값) : rememberId라는 이름으로 사용자가 입력한 id를 쿠키에 저장
			} else {
				$.cookie('rememberId', '');
			}
			
		});
	}
	
	// id 저장을 했으면 다음날 로그인하러 갔을 때 입력창에 아이디 표시
	function fn_displayRememberId() {
		
		let rememberId = $.cookie('rememberId');
		if(rememberId == ''){	// 아이디저장 체크를 하지 않았으면!
			$('#id').val('');
			$('#rememberId').prop('checked', false);
		} else {
			$('#id').val(rememberId);
			$('#rememberId').prop('checked', true);
		}
	}
	// 크롬 - F12 - Application - cookies - 하위 url 에서 쿠키 확인 가능(sessionId의 value값이 달라짐)        3C791EEF45E60BEAD88C7EB6EBB23EB0
	// 해당 세션의 id값이 브라우저 단위로 주어짐(브라우저가 닫히면 세션이 닫힌 것)
	
	
	// 로그인 유지를 하겠다 -> 쿠키의 SessionID value를 2군데 저장(1. cookie(쿠키에 저장된건 브라우저 꺼져도 안 없어짐), 2. DB) -> 영구적이라고 할 수는 없지만 꽤 오랜시간 저장 가능.
	// 쿠키에 id/pw를 그대로 저장해두는건 위험하니까 sessionId라는 식별자로 value에 저장(쿠키의 sessionId와 db에 저장된 id와 비교)
	// 쿠키에 들어가있는 id와 DB에 들어가 있는 id를 비교해서 일치하는 정보가 있으면 로그인 유지!
	// 쿠키 기반으로 동작함
	
</script>
</head>
<body>

	<div>
	
		<h1>로그인</h1>
		<!-- 
			서비스와 서비스Impl에서 로그인 시 session에 로그인 정보 올려두기!!!! 
		-->
		
		<form id="frm_login" action="${contextPath}/user/login" method="post">
			
			<input type="hidden" name="url" value="${url}">
			
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id">
			</div>
			
			<div>
				<label for="pw">비밀번호</label>
				<input type="password" name="pw" id="pw">
			</div>
			
			<div>			
				<button>로그인</button>
			</div>
			
			<div>			
				<label for="rememberId">
					<input type="checkbox" id="rememberId">
					아이디 저장
				</label>
				<label for="keepLogin">
					<input type="checkbox" name="keepLogin" id="keepLogin"> 
					<!--  keepLogin이라는 파라미터가 keep이라는 값을 가지고 서버로 간다 -> /user/login request에 담겨서 간다 -> login()으로 간다 -->
					<!-- value="keep" 는 필요 없어짐. 체크 유무는 null 유무로 체크가능하기 때문에 -->
					로그인 유지
				</label>
			</div>
		
		</form>
			
		<div>
			<a href="${contextPath}/member/findId">아이디 찾기</a>
			<a href="${contextPath}/member/findPw">비밀번호 찾기</a>
		</div>
	
	</div>
	
</body>
</html>