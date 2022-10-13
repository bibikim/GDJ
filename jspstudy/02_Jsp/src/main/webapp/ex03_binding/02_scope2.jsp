<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<div>a : ${a}</div>
	<div>b : ${b}</div>	
	<div>c : ${c}</div>
	<div>d : ${d}</div>

<!-- scope1에서 저장, scope2에서 확인.-->
<!-- 포워드했기때문에 pageContext는 확인할 수 없음. 왜냐하면 현재페이지(scope1)에서만 확인할 수 있는 것이기 때문에. request에 저장해둔 정보도 그대로 전달해주기 때문에 c까지 확인 가능 -->
<!-- 리다이렉트를 하면 request/pageContext.getAttribute("c/d")는 확인 불가. 리다이렉트는 request에 저장해둔 정보는 가져오지 못함  a, b만 확인 가능 -->
	
</body>
</html>