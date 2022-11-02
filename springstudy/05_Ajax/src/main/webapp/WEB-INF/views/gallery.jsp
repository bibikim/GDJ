<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	 
	 <h1>예쁜 동물들 보고 가세요</h1>
	 <div>
	 	<!-- 
	 		절대 경로의 이미지를 img 태그로 표시하기
	 		1. 요청
	 			경로 + 이미지를 파라미터로 전송
	 		2. 응답
	 			이미지의 byte[]
	 			(컴퓨터에 저당된 이미지의 실체를 가져오면 됨. 모든건 바이트로 저장. 그림도 바이트로 저장. 받아오는게 byte[]이다)
	 	
	 	-->
	 	
	 	<!-- 태그 밑에다가 <script>를 짤 경우, .ready()가 필요 없다. -->
	 	<div id="galleries"></div>
	 	<script>
	 		for(let n = 1; n <= 10; n++) {
	 			$('<div>')     // 라인 하나당 이미지 하나만 넣기 위해 div로 싸주기~ 같이 반복돌려버령
	 					.append($('<img>')
	 					.attr('src', '${contextPath}/image/display?path=' + encodeURIComponent('C:\\GDJ\\images') + '&filename=animal'+ n + '.jpg')
	 					.attr('width', '200px'))
	 					.appendTo('#galleries');
	 		}
	 		/* $('#image').attr('src', '${contextPath}/image/display?path=' + encodeURIComponent('C:\\GDJ\\images') + '&filename=animal1.jpg'); */
			/* 이미지 이름이 중구난방인걸 다 꺼내오고 싶을땐 배열에 다 집어넣고 이치문 돌려야하나...? */
	 		
					
	 		// 이미지src를 자바스크립트로 가져옴. encodeURIComponent
	 		// 직접적으로 자바스크립트를 쓸 수 있느건 이벤트함수,, 
	 		// 파일이름에도 encode가 필요하면, encodeURIComponent('&filename=animal1.jpg')
	 	</script>
	 	
	 	
	 </div>
</body>
</html>