<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>

	🌮웰컴~🌮 
	
	<br>
	
	<img src="${contextPath}/resources/images/hooray.jpg" width="200px">
	
	<hr>
	
	<h3>Member 관련 요청 3가지 방법</h3>
	
	<%-- Forward --%>
	<%-- 1. <a> 링크로 넘어오는 요청 --%>
	<div>
		<a href="${contextPath}/member/detail1?id=admin&pw=1234">전송</a>
		<%-- /member/detail : mapping값, id=&pw= : 파라미터 --%>
		<%-- a링크는 GET방식 : @GetMapping(""),  뒤에 주소로 붙음! --%>
	</div>
	
	<%-- 2. location.href=''로 넘어오는 요청 --%>
	<div>
		<input type="button" value="전송" id="btn">
	</div>
	<script>
		$('#btn').click(function(){
			// location.href='${contextPath}/member/detail2?id=admin&pw=1234';  // 파라미터 모두 전달
			 location.href='${contextPath}/member/detail2?';  // required=false, defaultValue="master", "1111"
			
			// location.href='${contextPath}/member/detail3?id=admin&pw=1234';
			// location.href='${contextPath}/member/detail3?';   // 전달되지 않고 null 전달
			
			// 파리미터를 전달하지 않았을 때(없을 때) @RequestParam은 어떻게 동작할까? -> 없으면 오류가 난다.
			// 파라미터 없을 때 Optional로 처리 했었음. 스프링에서는 @RequestParam()
			// 파라미터의 필수 여부를 required=false 통해서 조정할 수 있다.
		});
	</script>
	
	
	<%-- 3. <form action=""> 으로 넘어오는 요청 --%>
	<form action="${contextPath}/member/detail4" method="post">
		<div>
			<input type="text" name="id" placeholder="아이디">
		</div>
		<div>
			<input type="text" name="pw" placeholder="비번">
		</div>
		<button>전송</button>
	</form>

	<hr>
	
	<%-- Redirect --%>
	<div>
		<a href="${contextPath}/board/detail1?title=공지사항&hit=10">전송</a>
	</div>


</body>
</html>