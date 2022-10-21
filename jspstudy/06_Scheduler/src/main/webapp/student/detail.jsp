<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생조회</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function(){
		
		$('#frm_detail').submit(function(event){    // 서브밋 날릴 때 정수입력 맞는지 확인
			var kor = $('#kor');
			var eng = $('#eng');
			var math = $('#math');
			if(kor.val() == '' || isNaN(kor.val()) || kor.val() < 0 || kor.val() > 100) {   // isNaN =  숫자가 아니다
				alert('국어 점수를 확인하세요')
				kor.focus();
				event.preventDefault();
				return;
			}
			else if(eng.val() == '' || isNaN(eng.val()) || eng.val() < 0 || eng.val() > 100) {   // isNaN =  숫자가 아니다
					alert('영어 점수를 확인하세요')
					eng.focus();
					event.preventDefault();
					return;
			}
			else if(math.val() == '' || isNaN(math.val()) || math.val() < 0 || math.val() > 100) {   // isNaN =  숫자가 아니다
					alert('수학 점수를 확인하세요')
					math.focus();
					event.preventDefault();
					return;
			}
		});
		
		$('#btn_list').click(function(event){
			location.href = '${contextPath}/student/list.do';
		});
		
	});

</script>
</head>
<body>

	<h1>학생 상세 조회</h1>
	<div>
		<form id="frm_detail" method="POST" action="${contextPath}/student/modify.do">   
									<!-- 상세보기 + 수정까지 한꺼번에 가능하게 구현. write.do 페이지 복사해서 속성 값들만 바꿔줌 -->
			<div>
				<label for="stuNo">학번</label>
				<input type="text" id="stuNo" name="stuNo" value="${student.stuNo}" readonly>  <!-- 시퀀스는 수정불가. 따라서 학번 수정 불가 = readonly -->
			</div>
			<div>
				<label for="name">이름</label> <!-- readonly가 안 붙어 있으면 언제든 수정 가능 -->
				<input type="text" id="name" name="name" value="${student.name}">
			</div>
			<div>
				<label for="kor">국어</label>
				<input type="text" id="kor" name="kor" value="${student.kor}">
			</div>
			<div>
				<label for="eng">영어</label>
				<input type="text" id="eng" name="eng" value="${student.eng}">
			</div>
			<div>
				<label for="math">수학</label>
				<input type="text" id="math" name="math" value="${student.math}">
			</div>
			<div>
				<label for="ave">평균</label>
				<input type="text" id="ave" name="ave" value="${student.ave}" readonly>  <!-- 국영수에 의해 생기는 파생값이기 때문에 수정 불가하게 해야함 -->
			</div>
			<div>
				<label for="grade">학점</label>
				<input type="text" id="grade" name="grade" value="${student.grade}" readonly>
			</div>
			<!-- 사이트의 '마이페이지'를 이런 식으로 구현 가능! -->
			<hr>
			<div>
				<input type="submit" value="수정완료">
				<input type="reset" value="입력 초기화">
				<input type="button" value="목록" id="btn_list">
			</div>
		</form>
	</div>

</body>
</html>
