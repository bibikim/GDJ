<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<!-- 실행은 프로젝트에 우클릭으로 run as 실행하면 됨! -->


		<!-- jsp에서 request에 저장된 데이터는 EL로 접근 가능하다 -->		
		<h1>${result}입니다</h1>         
		<!-- result: nowService와 todayService를 모두 확인 가능한 속성 -->
		
		<%-- <h2>${now }입니당</h2> --%>
		

</body>
</html>