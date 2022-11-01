<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/jquery-ui.min.css">
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.min.js"></script> 
<!-- jquery-ui를 쓰려면 jquery-3.6.1.min.js가 위에 있어야 한다. 순서 중요!  -->
<script>
	
	$(document).ready(function(){
		
		$('#targetDt').datepicker({
			dateFormat: 'yymmdd'    // 실제로는 yyyymmdd로 적용
		});
		
		$('#btn').click(function(){
			$.ajax({
				type: 'get',
				url: '${contextPath}/movie/boxOfficeList', // 요청주소
				data: 'targetDt=' + $('#targetDt').val(),  // 보내줘야하는 data 값 -> 날짜(targetDt)를 파라미터로 받아오기
				dataType: 'json', // String 데이터가 json 덩어리기 때문에 json으로 받아오는 거라 생각하면 됨!
				success: function(resData) {
					// 기존 목록 초기화
					$('#boxOfficeList').empty();
					// 가져온 목록 나타내기
					$.each(resData.boxOfficeResult.dailyBoxOfficeList, function(i, movie){   // resData로 넘어온 데이터에서 boxOfficeResult 안에 dailyBoxOfficeList가 필요한 정보 => 배열로 되어 있음
						$('<tr>')
						.append($('<td>').text(movie.rank))
						.append($('<td>').text(movie.movieNm))
						.append($('<td>').text(movie.openDt))
						.append($('<td>').text(movie.audiCnt))
						.append($('<td>').text(movie.audiAcc))
						.appendTo('#boxOfficeList');
					});
				}
			})
		})
		
	});
	
</script>
</head>
<body>

	<div>
		<label for="targetDt">조회 날짜</label>
		<input type="text" id="targetDt">  
		<%-- form이 없음! 서브밋 안할거임! serialize() 안할거임! 따라서 name 필요 없음! --%>
		<input type="button" value="조회" id="btn">
	</div>
	
	<hr>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순위</td>
					<td>영화제목</td>
					<td>개봉일</td>
					<td>일일 관객수</td>
					<td>누적 관객수</td>
				</tr>
			</thead>
			<tbody id="boxOfficeList"></tbody>
			<!-- ajax는 가져와서 화면에 뿌려라 식이라 바디는 이게 끝..! -->
		</table>
	</div>
</body>
</html>