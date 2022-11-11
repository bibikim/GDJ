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
<style>
	.notice:hover {
		background-color : beige;
	}
</style>
</head>
<body>

	<div>
		<a href="${contextPath}/ntc/write">공지작성</a>
	</div>
	
	<hr>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>공지번호</td>
					<td>공지제목</td>
					<td>조회수</td>
					<td>공지일자</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${notices}" var="n">
					<tr class="notice" data-notice_no="${n.noticeNo}">
						<td>${n.noticeNo}</td>
						<td>${n.title}</td>
						<td>${n.hit}</td>
						<td>${n.createDate}</td>
					</tr>
				</c:forEach>
				<script>
					// 행 단위 선택(클릭)
					$('.notice').click(function(){
						location.href='${contextPath}/ntc/detail?noticeNo=' + $(this).data('notice_no');   // this에 들어있는 data속성이 noticeNo이므로 ${no.noticeNo} 대체 가능
					})
				</script>
			</tbody>
		</table>
	</div>

</body>
</html>