<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/jquery-ui.min.css">
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.min.js"></script> 
<!-- jquery-ui를 쓰려면 jquery-3.6.1.min.js가 위에 있어야 한다. 순서 중요!  -->
<script>
	
	$(document).ready(function(){
		
		$('#targetDt').datepicker({
			dateFormat: 'yymmdd'    // 실제로는 yyyymmdd로 적용
		});
		
		$('#btn').click(function(){
			alert($('#targetDt').val());
		})
		
	});
	
</script>
</head>
<body>

	<div>
		<label for="targetDt">조회 날짜</label>
		<input type="text" id="targetDt">  
		<%-- form이 없음! 서브밋 안할거임! serialize() 안할거임! 따라서 name 필요 없음! --%>
		<input type="button" value="조회" id="btn">
	</div>
	
	<hr>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순위</td>
					<td>영화제목</td>
					<td>개봉일</td>
					<td>일일 관객수</td>
					<td>누적 관객수</td>
				</tr>
			</thead>
			<tbody id="boxOfficeList"></tbody>
			<!-- ajax는 가져와서 화면에 뿌려라 식이라 바디는 이게 끝..! -->
		</table>
	</div>
</body>
</html>