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
<script>
	
	$(document).ready(function(){

		$('#btn1').click(function(){ fn_ajax1(); })
		$('#btn2').click(function(){ fn_ajax2(); })

	});

	
	function fn_ajax1(){
		
		$('#result').empty();
		$.ajax({
			/* 요청 */
			url: '${contextPath}/contact/detail1',
			// JSON 데이터를 서버로 보낼 때는 반드시 post방식을 사용해야 함.
			type: 'post',
			// data에 파라미터가 없을 주의! 문자열 json이라 쳐도 보내는 파라미터 이름이 있어야 하는데 없는 상태.
			// 파라미터가 없다? -> 파라미터로 전달되지 않기 때문에 주소창을 이용한 get방식이 불가능
			data: JSON.stringify({   // JSON.stringify : 자바스크립트 객체를 JSON문자열로 변환해서 데이터를 만들어줌
				'id': $('#id').val(),
				'pw': $('#pw').val(),
			}),
			// 서버로 보내는 JSON 데이터의 MIME-TYPE을 작성해줌. 
			// 파라미터가 없을 때는 전체 데이터의 마임타입을 적어서 보내줘야 한다.
			contentType: 'application/json',
			/* 응답 */
			dataType: 'json',  // 보낼 때도, 받을 때도 json 데이터
			success: function(resData) {
		         var ul = '<ul>';
		         ul +='<li>' + resData.id + '</li>';
		         ul +='<li>' + resData.pw + '</li>';
		         ul +='</ul>';
				 $('#result').html(ul);    // 태그 들어갔으니 .html()
			}
		})  // ajax
	}
	
	
	function fn_ajax2(){
		
		$('#result').empty();
		$.ajax({
			/* 요청 */
			url: '${contextPath}/contact/detail2',
			// JSON 데이터를 서버로 보낼 때는 반드시 post방식을 사용해야 함.
			type: 'post',
			// data에 파라미터가 없을 주의! 문자열 json이라 쳐도 보내는 파라미터 이름이 있어야 하는데 없는 상태.
			// 파라미터가 없다? -> 파라미터로 전달되지 않기 때문에 주소창을 이용한 get방식이 불가능
			data: JSON.stringify({   // JSON.stringify : 자바스크립트 객체를 JSON문자열로 변환해서 데이터를 만들어줌
				'id': $('#id').val(),
				'pw': $('#pw').val(),
			}),
			// 서버로 보내는 JSON 데이터의 MIME-TYPE을 작성해줌. 
			// 파라미터가 없을 때는 전체 데이터의 마임타입을 적어서 보내줘야 한다.
			contentType: 'application/json',
			/* 응답 */
			dataType: 'json',  // 보낼 때도, 받을 때도 json 데이터
			success: function(resData) {
		         var ul = '<ul>';
		         ul +='<li>' + resData.id + '</li>';
		         ul +='<li>' + resData.pw + '</li>';
		         ul +='</ul>';
				 $('#result').html(ul);    // 태그 들어갔으니 .html()
			}
		})  // ajax
	}
	
	
</script>
</head>
<body>

	<form id="frm_member">
		<div>
			<label for="id">아이디</label>
			<input type="text" name="id" id="id">
		</div>
		<div>
			<label for="pw">패스워드</label>
			<input type="password" name="pw" id="pw">
		</div>
		<div>
			<input type="button" value="전송1" id="btn1">
			<input type="button" value="전송2" id="btn2">
		</div>
	</form>
	
	<hr>
	
	<div id="result"></div>



</body>
</html>