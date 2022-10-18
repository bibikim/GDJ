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

	<%-- 너비/높이 입력 폼 : 삼각형 버튼, 사각형 버튼 --%>
	<div>
		<form id="frm_area">
			<div>
				너비<input type="text" name="width" id="width"><br>
				높이<input type="text" name="height" id="height"><br>
				<input type="button" id="triangle" name="triangle" value="삼각형 넓이" onclick="fn_tri()">
				<input type="button" id="rectangle" name="rectangle" value="사각형 넓이" onclick="fn_rect()">
			</div>
		</form>
		<br>
		<%-- 반지름 입력 폼 : 원 버튼 --%>
		<form action="${contextPath}/circle.do">
			<div>
				반지름<input type="text" name="radius"><br>
				<button>원형 넓이</button>
				<!-- 단순 <button> 도 submit을 해줌. type="submit" 생략되어 있는 것일 뿐.. -->
			</div>
		</form>
	</div>
	
	<script>
		function fn_tri() {
			var frm_area = document.getElementById("frm_area");
			frm_area.action = '${contextPath}/triangle.do';	   /* form에 action 주기. 즉 ''안으로 보내줘라!(경로) */
			frm_area.submit();  // 
		}
		
		function fn_rect() {
			var frm_area = document.getElementById("frm_area");
			frm_area.action = '${contextPath}/rectangle.do';	   /* form에 action 주기. 즉 ''안으로 보내줘라!(경로) */
			frm_area.submit();  // form 태그의 메소드 submit()
		}
		
	
	</script>
	
</body>
</html>