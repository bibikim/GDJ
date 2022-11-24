<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Optional<String> opt = Optional.ofNullable(request.getParameter("title"));  // list.jsp에서 넘겨받은 파람
	String title = opt.orElse("Welcome");
	pageContext.setAttribute("title", title);   // EL사용을 위함(${title}) => 이게 없으면 <%=title 퍼센트> 로 쓰면 된다. 자바변수 걍 가져다 쓰기
	pageContext.setAttribute("contextPath", request.getContextPath());   // == <taglib prefix="c">, <c:set>
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>  <!-- 여기에 list.jsp에서 넘겨준 파라미터 title의 value가 화면에 뿌려진다! -->
<!-- 
    ※ ${contextPath}로 시작하는 값은 모두 매핑임. 고로 script의 src 값은 경로가 아닌 mapping 값이다
    
    --servlet-context.xml
	<resources mapping="/resources/**" location="/resources/" />
	==> 매핑이 /resources로 시작하는 모든 파일들의 실제 위치는 resources아래에 있다
	
		script src="${contextPath}/resources/** 
		==> mapping 값(경로x). 
		
		<script src="${contextPath}/resources/js/jquery-3.6.1.min.js">
		
		location="/resources/" 
		==> /resources로 시작하는 모든 매핑은(src), resources 폴더 가서 찾으라는 이야기(location)
	
-->
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>   
<script src="${contextPath}/resources/js/moment-with-locales.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.css">  <!-- css는 link로~ -->
</head>
<body>

		<div>
			<h1>어서와 내 블로그는 처음이지?!!</h1>
		</div>
		
</body>
</html>