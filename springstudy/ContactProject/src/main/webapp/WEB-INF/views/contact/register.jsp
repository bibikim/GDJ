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
		
		$('#btn_list').click(function(){
			location.href='${contextPath}/cntc/list';
		})
		
		
		$('#frm_regi').submit(function(event){
			if($('#name').val() == '') {
				alert('이름을 입력해주세요.');
				evnet.preventDefault;
			} else if($('#tel').val() == '') {
					alert('전화번호를 입력해주세요.');
					evnet.preventDefault;
					return;
			} else if($('#addr').val() == '') {
					alert('주소를 입력해주세요.');
					evnet.preventDefault;
					return;
			} else if ($('#email').val() == '') {
					alert('메일을 입력해주세요.');
					evnet.preventDefault;
					return;
			}
		})
		
	})
	
	
	
</script>
</head>
<body>

	<h1>연락처 등록</h1>
	<form action="${contextPath}/cntc/list" method="post">
		<div>
			<label for="name">이름*</label>
			<input type="text" name="name" id="name">
		</div>
		<div>
			<label for="tel">전화*</label>
			<input type="text" name="tel" id="tel">
		</div>
		<div>
			<label for="addr">주소*</label>
			<input type="text" name="addr" id="addr">
		</div>
		<div>
			<label for="email">이메일*</label>
			<input type="text" name="email" id="email">
		</div>
		<div>
			<label for="note">비고</label>
			<input type="text" name="note" id="note">
		</div>
		<div>
			<input type="button" id="btn_regi" value="연락처 저장하기">
			<input type="button" id="btn_list" value="전체연락처">
		</div>
	</form>

</body>
</html>