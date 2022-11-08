<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
<script>

	$(document).ready(function(){
		
		var frm = $('#frm_btn');
		
		// 편집화면으로 이동                                               // $ 붙일 때마다 jquery 함수 부르는거라 자꾸 부르면 성능에 안 좋아서 변수처리 후 변수로 부르는게 나음.
		$('#btn_edit').click(function(event){
			frm.attr('action', '${contextPath}/brd/edit');
			frm.submit();
		})
		
		// 삭제
		// 삭제하는 메소드는 get이 좋지 않은 방법. 주소만 알면 자기 것이 아닌 글도 삭제가 가능해지니까 post방식(주소창에 정보전달x). 즉 location을 쓰면 안된다.
		// post방식으로 삭제되도록 단순 클릭 말고!
		$('#btn_remove').click(function(event){
			if(confirm('삭제할까요?')) {
				frm.attr('action', '${contextPath}/brd/remove');   // 삭제할까요? true -> submit을 한다!! : action에 속성을 잡아준다.
				frm.submit();
				return;
			} 
		})
		
		
		// 목록
		$('#btn_list').click(function(){
			location.href= '${contextPath}/brd/list';
		})
		
		
	})
	

</script>
</head>
<body>

	
	<ul>
		<li>글 번호 : ${board.boardNo}</li>
		<li>제목 : ${board.title}</li>
		<li>작성자 : ${board.writer}</li>
		<li>작성일 : ${board.createDate}</li>
		<li>수정일 : ${board.modifyDate}</li>
	</ul>
	
	<div>
		${board.content}
	</div>
	
	<hr>
	
	<div>
		<form id="frm_btn" method="post"> 
		 	<input type="hidden" value="${board.boardNo}" name="boardNo">   <!-- hidden보이지 않기땜에 name하고 value를 다 줘야 함 -->
			<input type="button" value="편집" id="btn_edit"> 
			<!-- 편집하러 갈 때 : 편집할 게시글번호를 가져온다, 디비로 가서 편집할 게시글을 가져와서 화면에 뿌린다 -->
			<input type="button" value="삭제" id="btn_remove"> 
			<!-- 삭제하려면 보내는 데이터(즉 boardNo)가 있어야 함! -> hidden으로 주자 -->
			<input type="button" value="목록" id="btn_list"> 
		</form>
	</div>
	
	
	<!-- 
		삭제/편집 모두 post 방식이 좋음. 주소만 알면 자기 것이 아닌 글도 삭제/편집이 가능해지니까..
		
		<form>안에 input type="submit"이 2개일 수 없고 하나의 form에 action도 2개의 주소를 줄 수 없음(당연함. 하나밖에 못 씀)
		
			-▶ 해결방법 : 1. 삭제form과 편집form로 두개 만든다. 각각에 맞는 action을 준다. input type="submit"으로 동작시킨다(action을 동작시키는 건 submit이니까)
		            	  2. 버튼 전용 form을 만들고 input type="button"을 동작시킨다. 스크립트에서 form에 action 속성( .attr('action', '매핑') )을 준다. 그리고 submit을 해준다!
		            	   └> 2번의 방법이 조금 더 좋당
		
		
		하나의 form에 n가지의 서브밋이 나올 수 있음 (편집-> 편집요청, 삭제-> 삭제요청)
	-->

</body>
</html>