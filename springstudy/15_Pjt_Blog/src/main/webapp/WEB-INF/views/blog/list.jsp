<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!-- header.jsp로 넘겨줄 파라미터 title를 < jsp:param > 태그로 -->
<jsp:include page="../layout/header.jsp">
	<jsp:param value="블로그목록" name="title"/>
</jsp:include>

	<h1>블로그 목록(전체 ${totalRecord}개)</h1>	
	<div>
		<%-- <c:if test="${loginUser != null}">    로그인 한 사람만 작성버튼 눌러서 작성페이지 넘어갈 수 있다 / 관리자만 작성가능 : ${loginUser.id == 'admin'} --%>
			<input type="button" value="블로그 작성하기" onclick="location.href='${contextPath}/blog/write'">
		<%-- </c:if> --%>
	</div>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>제목</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${blogList}" var="blog" varStatus="vs">
					<tr>
						<td>${beginNo - vs.index}</td>   <!-- index값 써서 순번 만들어쥑 -->
						<td>${blog.title}</td>
						<td>${blog.hit}</td>
						<td>${blog.createDate}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						${paging}
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
	
	
	





<!-- footer jsp 따로 할거면 닫아주는 body/html은 그쪽페이지에서 닫아줭 -->
</body>
</html>
