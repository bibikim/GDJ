<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<h1>회원 상세 보기</h1> 
		
		<div>제목 ${title} ${board.title}</div>   <%-- ${board.id}는 board.getId()를 호출한다. 따라서 getter를 만들어놔야 정상적으로 돌아간다. --%>
		<div>조회수 ${hit} ${board.hit}</div> 	<%-- ${board.pw}는 board.getPw()를 호출한다. 따라서 getter를 만들어놔야 정상적으로 돌아간다. --%>
		
		
</body>
</html>