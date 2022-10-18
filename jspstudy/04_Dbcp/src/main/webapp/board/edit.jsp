<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.board_no}번 게시글 수정</title> 
<!-- BoardDetailService로부터 전달된 board값의 board_no -->
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>

   $(document).ready(function() {
      
	  $('#frm_edit').submit(function(event){
		  // 제목, 내용 모두 변경이 없는 경우
	 	  // 기존 제목, 내용   :  ${board.title}, ${board.content}   은 여기에
	 	  // 입력한 제목, 내용 :  $('#title').val(), $('#content').val()   수정한 제목/내용은 여기에
	 	  if('${board.title}' == $('#title').val() && '${board.content}' == $('#content').val()) {      // board.title은 내부적으로 board.getTitle()을 해냄.
	 		  alert('변경된 내용이 없습니다.');
	 		  event.preventDefault();
	 		  return;
	 	  }
	 	  
		  // 제목이 비어 있는 경우
	  	  if($('#title').val() == '') {
	  		  alert('제목은 필수입니다.');
	  		  event.preventDefault();
	  		  return;  // 코드 진행 막기
	  	  }
	  })
	  
	   
      $('#btn_list').click(function(event) {
         location.href = '${contextPath}/board/list.do';
      });
	  
	  
      
   });

</script>
</head>
<body>
	
	<h1>게시글 편집 화면</h1>
	<div>
		<form method="POST" action="${contextPath}/board/modify.do" id="frm_edit">
			<div>
				게시글 번호 : ${board.board_no}
				<!-- 번호 수정은 불가함 -->
				<input type="hidden" name="board_no" value="${board.board_no}">
				<!-- 포함은 시켜야되는데 눈에 보일 필욘 없으므로 hidden. hidden은 name과 value 속성이 필수 -->
				<!-- BoardModifyService에서 파라미터로 3개(제목/내용/게시글 번호)를 보냈으므로, 제목/내용/Board_no까지 와야해서 적어주는 코드 -->
			</div>
			<div>
				게시글 제목 : <input type="text" name="title" id="title" value="${board.title}">   
				<!-- 제목도 수정 가능하도록 input상자에 넣어주고 value값을 준다. -->
			</div>
			<div>
				게시글 내용<br>
				<textarea name="content" id="content" rows="5" cols="30">${board.content}</textarea>
			</div>
			<div>
				작성일자 : ${board.create_date}
				<!-- 작성일자 수정은 불가함 -->
			</div>
			<div>
				<input type="submit" value="수정">
				<!-- 수정 버튼이 form의 action을 가져가게 되는 것 -->
				<input type="button" value="목록" id="btn_list">
			</div>
		</form>
	</div>
	
</body>
</html>