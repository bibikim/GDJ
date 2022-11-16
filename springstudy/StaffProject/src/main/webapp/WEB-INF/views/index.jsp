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

	// 주소 : http://localhost:9090/staff/list.json
	// resData = [{}, {}, {}] 받아오는 데이터는 배열, 요소 하나하나가 사원(staff)
	$(function(){
		fn_staffList();
		fn_addStaff();
		fn_lookUpStaff();
	});
	
	function fn_staffList() {
		$.ajax({
			type: 'get',
			url: '${contextPath}/list.json',
			dataType: 'json',
			success: function(resData) {	// resData = [{설}, {전}, {정}]
				$('#staff_list').empty();   // ajax로 화면에 뿌릴때 기본 : 기존에 뿌려둔 데이터를 초기화하는 것 .empty()!
				$.each(resData, function(i, staff) { // 배열의 인덱스와 요소를 받아오는 function(i, 요소)
					
			
					var tr = '<tr>';
					tr += '<td>' + staff.sno + '</td>';
					tr += '<td>' + staff.name + '</td>';
					tr += '<td>' + staff.dept + '</td>';
					tr += '<td>' + staff.salary + '</td>';
					tr += '</tr>';
					$('#staff_list').append(tr);
				
				
				/* jQuery 방식
				$('<tr>')
					.append($('<td>').text(staff.sno))
					.append($('<td>').text(staff.name))
					.append($('<td>').text(staff.dept))
					.append($('<td>').text(staff.salary))
					.appendTo('#staff_list');
				 */
				});
			}
		});
	}
	
	function fn_addStaff() {
		$('#btn_add').click(function(){
			if(/^[0-9]{5}$/.test($('#sno').val()) == false ) {       // 숫자로 시작하고 숫자로 끝나게 5자리, 정규식 검사 메소드 .test()
				alert('사원번호는 5자리 숫자입니다.');
				return;     // 리턴은 리턴 바로 밑으론 코드 진행하는걸 막는것. 즉 ajax 실행 막기..
			} 
			if(/^[가-힣]{3,5}$/.test($('#dept').val()) == false) {
				alert('부서는 3~5자리 한글입니다.');
				return;
			}
			$.ajax({
				type: 'post',
				url: '${contextPath}/add',			
				data: 'sno=' + $('#sno').val() + '&name=' + $('#name').val() + '&dept=' + $('#dept').val(),      // data 속성 = 요청 명세서의 보내줄 파라미터들 의미.  $('#frm_add').serialize();  폼에 있는거 싹다 보내줄게! name 속성 기반으로 동작하는 것이 serialize()
																											   //  'sno=' $('#sno').val() + '&name=' + $('#name').val() + '&dept=' + $('#dept').val()
				dataType: 'text',  		// 받아오는 데이터는 text. 삽입부분 ->  Exception의 가능성이 있다(ex.이미 있는 사원번호로 삽입을 할 경우) -> 따라서 error도 있어야 함. error가 exception을 먹는다 
				success: function(resData){   // resData는 '사원등록을 성공했습니다'				 
					alert(resData);
					fn_staffList();  	  // 목록 가지고 오는 함수를 실행시키면 가져와서 '기존의 목록 지우고empty, 새로운 목록으로 뿌려줌'
					$('#sno').val('');	  // 입력창에 입력된 것 지우기
					$('#name').val('');
					$('#dept').val('');    // 자바스크립트 코드 :  document.getElementById('sno').value = '';
				},
				error: function(jqXHR) {
					alert(jqXHR.responseText);  // 에러가 났을 때, 응답 메시지로 '사원 등록이 실패했습니다.'가 들어가 있음
				}
			})
		});
	}
	
	function fn_lookUpStaff() {
		$('#btn_lookone').click(function(){
			$.ajax({
				type: 'get',
				url: '${contextPath}/query',
				data: 'sno=' + $('#query').val(),
				dataType: 'json',
				success: function(resData){  // resData == {sno, name, dept, salary}
					$('#staff_list').empty();
					var tr = '<tr>';
					tr += '<td>' + resData.sno + '</td>';
					tr += '<td>' + resData.name + '</td>';
					tr += '<td>' + resData.dept + '</td>';
					tr += '<td>' + resData.salary + '</td>';
					tr += '</tr>';
					$('#staff_list').append(tr);
				},
				error: function(jqXHR){
					alert('조회된 사원 정보가 없습니다.')
				}
			})
			
		})
	}
	

</script>
</head>
<body>
	<h3>사원등록</h3>
	<form id="frm_add">
		<input type="text" id="sno" name="sno" placeholder="사원번호">
		<input type="text" id="name" name="name" placeholder="사원명">
		<input type="text" id="dept" name="dept" placeholder="부서명">
		<input type="button" id="btn_add" value="등록">
	</form>
	
	<hr>
	
	<h3>사원조회</h3>
	<form id="frm_lookup">
		<input type="text" id="query" placeholder="사원번호">
		<input type="button" value="조회" id="btn_lookone">
		<input type="button" value="전체" onclick="fn_staffList();">
	</form>

	<hr>

 	<h3>사원목록</h3>
 	<table border="1">
 		<thead>
 			<tr>
 				<td>사원번호</td>
 				<td>사원명</td>
 				<td>부서명</td>
 				<td>연봉</td>
 			</tr>
 		</thead>
 		<tbody id="staff_list">
 			
 		</tbody>
 	</table>
 
</body>
</html>