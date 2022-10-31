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
 
 	<a href="${contextPath}/board/detail">🥑🍋🍒게시판🥑🍋🍒</a>
 	<!-- /board/detail : 매핑 이름. 컨트롤러의 @GetMapping의 매핑이름인거!!!!!!! -->
 	<!-- a 링크를 통한 이동은 GET방식 -->
 	
 	<hr>
 	
 	<a href="${contextPath}/notice/detail">공지사항</a>
 	<!-- /notice/detail : 매핑 이름. 컨트롤러의 @GetMapping의 매핑이름인거!!!!!!! -->
 	<!-- 일부러 매핑값을 복잡하게 만들 수도 있음. fake 경로! 고로 폴더 구조와 매핑은 상관 없음:) -->
 	<!-- 요청은 모두 매핑(.do)으로 하는 것. jsp 아님요 -->
 	
</html>