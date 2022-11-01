<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#frm_board {
		width: 480px;
		margin: 0 auto;
	}
	label {
		display: inline-block;
		width: 80px;
	}

</style>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function(){
		
		fn_ajax1();
		fn_ajax2();
		fn_ajax3();
		
	});
	
	function fn_ajax1() {
		
		$('#btn1').click(function(){
			$('#result').empty();
			$.ajax({
				/* 요청 */
				type: 'get',
				url: '${contextPath}/board/detail1',
				data: $('#frm_board').serialize(),
				/* 응답 */
				dataType: 'json',
				success: function(resData) {
					$('<ul>')
					.append($('<li>').text(resData.title))
					.append($('<li>').text(resData.content))
					.appendTo('#result');   // 앞이 자식, () <- 안에가 부모
				},
				error: function(jqXHR){
					$('#result').text(jqXHR.status);  
				}
			})
			
		}); //click
	} // function
	
	
	function fn_ajax2() {
		
		//  No mapping for GET 오류 : 요청을 했는데 받지 못했다는 오류. url값을 잘못 써줬을 때.
		
		$('#btn2').click(function(){
			$('#result').empty();
			$.ajax({
				/* 요청 */
				type: 'get',
				url: '${contextPath}/board/detail2',
				data: $('#frm_board').serialize(), // 보내는 데이터
				/* 응답 */
				dataType: 'json',  // 받아오는 데이터의 타입
				success: function(resData){
					$('<ul>')
					.append($('<li>').text(resData.title))  // text로 들어갈 값은 resData의 title값
					.append($('<li>').text(resData.content))
					.appendTo('#result');
				},
				error: function(jqXHR){
					if(jqXHR.status == 500) {
						alert('한비는 필수입니다.');
					}
				}
			}); // ajax
			
		}); //click
	} // function
	
	
	function fn_ajax3() {
		
		$('#btn3').click(function(){
			$('#result').empty();
			$.ajax({
				/* 요청 */
				type: 'get',
				url: '${contextPath}/board/detail3',
				data: $('#frm_board').serialize(),
				/* 응답 */
				dataType: 'json',
				success: function(resData) {
					$('<ul>')
					.append($('<li>').text(resData.title))
					.append($('<li>').text(resData.content))
					.appendTo('#result');
				},
				error: function(jqXHR){
					if(jqXHR.status == 500) {
						alert('한비는 필수입니다.');
					}
				}
				
			})
			
		}); //click
	} // function

	
</script>
</head>
<body>

	<form id="frm_board">
		<div>
			<label for="title">제목</label>
			<input type="text" name="title" id="title">
		</div>
		<div>
			<label for="content">내용</label>
			<input type="text" name="content" id="content">
		</div>
		<div>
			<input type="button" value="전송1" id="btn1">
			<input type="button" value="전송2" id="btn2">
			<input type="button" value="전송3" id="btn3">
		</div>
	</form>
	
	<hr>
	
	<div id="result"></div>

</body>
</html>