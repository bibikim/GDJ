<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>

	<table border="1">
		<thead>
			<tr>
				<td>공지번호</td>
				<td>제목</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${notices}" var="notice">
				<tr>
					<td>${notice.noticeNo}</td>   <!-- getNoticeNO() 을 알아서 해준다. -->
					<td>${notice.title}</td>	  <!-- getTitle() -->
					<td>${notice.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
		<!-- <tfoot> 페이징 처리 하는 곳! -->
		<tfoot>
			<tr>
				<td colspan="3">
					<!-- 이전 블록 : 1, 2, 3 페이지(pagePerBlock)는 이전 블록이 없다. -->
					<!-- 블록단위로 페이지 옮기기 -->
					<c:if test="${page > pagePerBlock}">
						<a href="${contextPath}/notice/list.no?page=${beginPage - 1}">&lt;이전블록</a>
														<!-- beginPage(4페이지의 경우)의 이전 페이지(3페이지)를 기대하니까 -->
					</c:if>
					<!-- 이전페이지 : 1페이지는 이전 페이지가 없다. -->
					<c:if test="${page != 1 }">
						<a href="${contextPath}/notice/list.no?page=${page - 1}">&lt;이전</a>
					</c:if>
					<!-- 1 2 3   -▶ 페이징 숫자 뿌리기! -->
					<!-- begin : 시작값, end : 끝나는값, step : 증가값.  1씩 증가하는 값이 변수 p -->
					<c:forEach begin="${beginPage}" end="${endPage}" step="1" var="p" >
						<%-- ${p}&nbsp;&nbsp; --%>
						<!-- 현재 페이지는 링크가 걸리지 않는다. -->
						<c:if test="${page == p}">
							${p}
						</c:if>
						<!--  -->
						<c:if test="${page != p}">  <!-- 현재 페이지가 p가 아니면 링크를 걸어주겠다 -->
							<a href="${contextPath}/notice/list.no?page=${p}">${p}</a>
							<!-- 1페이지를 클릭했다? -> 1페이지. 2페이지를 클릭했다? -> 2페이지. 파라미터로 ${p}를 받으면 해당페이지인 ${p} 보여준다 -->
						</c:if>
					</c:forEach>
					<c:if test="${Page != totalPageCnt}">
					<!-- endPage 안되는 이유: endPage는 3임. 페이징 3개씩 보여주는 그 엔드페이지니까 총 페이지 갯수를 변수로 받아와야한다. -->
						<a href="${contextPath}/notice/list.no?page=${page + 1}">&gt;다음</a>
					</c:if>
					<!-- 다음 블록 : 마지막 블록(endPage가 totalPageCnt와 같은 경우)은 다음 블록이 없다. -->
					<c:if test="${endPage != totalPageCnt}">
						<a href="${contextPath}/notice/list.no?page=${endPage + 1}">다음블록</a>						
					</c:if>
				<!-- 페이징을 작업하고 나서 상세보기에서 목록으로 갈 때 원래 보던 페이지로 돌아가게 해주는 코드도 짜볼 수 있다. 
					 그러면 페이지가 파라미터로 계속 넘나들어야 되는 것! -->
				</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>