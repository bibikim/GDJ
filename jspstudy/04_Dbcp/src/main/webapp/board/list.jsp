<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 전체 목록</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	// ready() 이벤트
	// 문서를 모두 확인한 뒤 처리하는 window.onload 이벤트를 대신하는 jQuery 이벤트
	
	$(document).ready(function(){      // 이벤트를 메소드로 사용~ ready()  
									   // 스크립트를 위에 두려면   ^^$(document).ready(function (){})^^ 는 필수!!!
		$('#btn_write').click(function(event) {
			location.href = '${contextPath}/board/write.do';   // 자바 스크립트에서 자바영역의 속성 사용 가능 ${contextPath}  // write하러 가기(write.do)
		});
		
	});
</script>
</head>
<body>

	<!-- 현재 request에 전체 게시글 저장된 상태로 넘어온 것! -->
	
	<h1>게시글 목록 보기</h1>
	<div>
		<input type="button" value="새 게시글 작성하러 가기" id="btn_write">
	</div>
	<hr>
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>게시글 번호</td>
					<td>제목</td>
					<td>작성일</td>
					<td>삭제</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boards}" var="board">  
					<tr>
						<td>${board.board_no}</td>
						<td><a href="${contextPath}/board/detail.do?board_no=${board.board_no}">${board.title}</a></td>
						<!-- └> ?board_no = ${board.board_no} 파라미터를 넘겨줘야 함.  ${board.board_no} == 게시글 번호 -->
						<!-- ★ 게시글을 누를 때마다 보드넘버를 파라미터로 보내주게끔 변수 설정을 해줘야한다 ★ -->
						<td>${board.create_date}</td>
						<td>
							<a href=""><i class="fa-solid fa-x"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	

</body>
</html>