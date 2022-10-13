<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	label {
		display: block;
	}
	label span {        /* 글자과 인풋상자가 정렬된 느낌을 주기 위해 각각 span을 주고 inline-block + 너비주기 */
		display: inline-block;
		width: 100px;
	}
</style>
</head>
<body>

	<%
		Date date = new Date();
		String today = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		// today의 EL 사용을 위해서 속성으로 저장
		pageContext.setAttribute("today", today);  // today라는 속성 이름으로 today를 저장하면 el 사용 가능
												   // 어딘가(4개의 영역)에 바인딩을 해놓아야 el 사용 가능한 것!
	%>
	<div>
		<form method="POST" action="<%=request.getContextPath()%>/ex03_binding/03_qna2.jsp">   
									<!-- 실무에서는 보통 ContextPath(/02_Jsp)를 표현식이나 EL로 변수 처리 함! -->
			<label for="created_data">
				<span>작성일</span>
				<input type="text" name="created_date" id="created_datd" value="${today}">
			</label>
			<label for="writer">
				<span>작성자</span>
				<input type="text" name="writer" id="writer">
			</label>
			<label for="title">
				<span>제목</span>
				<input type="text" name="title" id="title">
			</label>
			<label for="content">
				<span>내용</span>
				<input type="text" name="content" id="content">
			</label>
			<button>문의 남기기</button>
			<input type="reset" value="다시작성">
		</form>
	</div>

</body>
</html>