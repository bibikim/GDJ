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
<script>

	var chatWindow;

	function fn_openChat(){
		$('#btn_open_chat').click(function(){
			chatWindow = window.open('${contextPath}/chat', 'chatting', 'width=670,height=670,top=100,left=500,menubar=no,history=no');							
		});
	}

	$(function(){
		fn_openChat();
	});
	
</script>
</head>
<body>

	<button id="btn_open_chat">채팅창 열기</button>

</body>
</html>