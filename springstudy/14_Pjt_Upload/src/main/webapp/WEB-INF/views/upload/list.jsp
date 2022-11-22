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
</head>
<body>
<!-- jsp는 DTO 기반으로 동작 -->
	<div>
		<div>
			<a href="${contextPath}/upload/write">작성</a>
		</div>
		
		<hr>
		
		<div>
			<table border="1">
				<thead>
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>작성일</td>
						<td>첨부개수</td>
						<!-- var=> upload에 첨부개수가 포함되어있엉 -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${uploadList}" var="upload">
						<tr>
							<td>${upload.uploadNo}</td>
							<td><a href="${contextPath}/upload/detail?uploadNo=${upload.uploadNo}">${upload.title}</a></td>
							<td>${upload.createDate}</td>
							<td>${upload.attachCnt}</td>
							<!-- dto에 있는 필드들 불러올 수 있는것. -->
							<!-- select의 결과로 DTO에 uploadNo ~ attachCnt 모두 들어와있어야 함. -->
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</div>
	
</body>
</html>