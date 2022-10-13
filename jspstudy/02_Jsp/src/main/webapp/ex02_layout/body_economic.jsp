<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

	<%--  
		1. 동적 레이아웃
			1) 포함할 페이지에 변경되는 부분이 있음
			2) <jsp:include> 액션 태그를 사용    -> jsp라는 프리픽스값에 include 지시어
			3) <jsp:param> 액션 태그를 이용해 파라미터를 전달함
			
			==> 포함시킬 파일이 변경되는 부분이 있느냐 없느냐에 따라 다름.
				대개 header는 동적, footer는 정적 레이아웃을 사용
	--%>
	<% request.setCharacterEncoding("UTF-8"); %>
	<jsp:include page="header.jsp">
		<jsp:param value="경제" name="title"/>    
	</jsp:include>
		<!-- value의 값을 제목으로 씀, name의 값은 파라미터. 경제라는 텍스트를 담아서 보내주겠다. HttpSeverletRequest로 보내줌(jsp에는 내장되어 있어 그냥 사용 가능) -->
		<!--header.jsp가 title이라는 파라미터를 받음 -->

	<section>
		<div>경제1</div>
		<div>경제2</div>
		<div>경제3</div>
		<div>경제4</div>
	</section>

	<%-- 
		2. 정적 레이아웃
			1) 포함할 페이지에 변경되는 부분이 없음
			2) <%@ include %> 지시를 사용
	--%>


	<%@ include file="footer.jsp" %>