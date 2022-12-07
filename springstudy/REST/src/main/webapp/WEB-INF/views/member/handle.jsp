<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	$(function() {
		fn_add();
	});
	
	function fn_add() {
		$('#btn_add').click(function() {
			// 추가할 회원 정보를 JSON으로 만든다.  -> 이름이 member인 JSON으로 만들어주깅!!
			let member = JSON.stringify({			// JSON = json만들 때 쓰는 자바스크립트 내장 객체, 자바스크립트의 {} = 객체를 의미
				// '' 안에 이름은 DTO의 field명과 맞춰야 한다. 그래야 매핑이 된다요! 보내는 프로퍼티와 필드명을 맞춰야 한다!
				'id': $('#id').val(),
				'name': $('#name').val(),      // '' 프로퍼티 : id의 value값으로
				'gender': $(':radio[name=gender]:checked').val(),  // 선택한 radio  = :radio,  name속성이 gender인 radio 중에서 check된! (radio가 여러개 있으니까 name=@@로 특정해주기)
				'address': $('#address').val()   // 선택한 주소. select는 input과 똑같이 해주면 된다.
			});
			// 추가할 회원 정보를 DB로 보낸다.
			$.ajax({
				/*요청*/
				type: 'post',
				url: '${contextPath}/members',
				data: member,   // 보내는 데이터는 let member의 member, 파라미터 이름 없음(post방식으로 보내는 거는 본문(Body)에 member를 포함시켜서 전송) <-> (get방식은 헤더(Header)에 포함시켜 보낸는 것)
				contentType: 'application/json',   // 요청 데이터의 MIME-TYPE.  보내는 memeber의 mime-type을 적는 것
				/*응답*/
				dataType: 'json', // 받아오는 데이터타입은 map  <- 컨트롤러에서 produces="application/json" 이라고 해놨음!
				success: function(resData) {
					
				},
				error: function(jqXHR){
					
				}
				
				
			}); // ajax
		});
	}
	
</script>
</head>
<body>

	<h1>회원관리</h1>
	<div>
		<div>
			<label for="id">아이디
				<input type="text" id="id">
			</label>
		</div>
		<div>
			<label for="name">이름
				<input type="text" id="name">
			</label>
		</div>
		<div>
			<span>성별</span>
			<!-- radio는 db로 보내는게 value. 넘어가는 값..~ -->
			<label for="male">
				<input type="radio" name="gender" id="male" value="M">남자
			</label>
			<label for="female">
				<input type="radio" name="gender" id="female" value="F">여자
			</label>
		</div>
		<div>
			<label for="address">주소
				<select id="address">
					<option value="서울">서울</option>
					<option value="경기">경기</option>
					<option value="인천">인천</option>
				</select>
			</label>
		</div>
		<div>
			<input type="button" value="초기화" id="btn_init">
			<input type="button" value="등록하기" id="btn_add">
			<input type="button" value="수정하기" id="btn_modify">
		</div>
	</div>
	
	<hr>
	
	<div>
		<input type="button" value="선택삭제" id="btn_remove">
		<table border="1">
			<thead>
				<tr>
					<td><input type="checkbox" id="check_all"></td>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>주소</td>
					<td></td>
				</tr>
			</thead>
			<tbody>

			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						<div id="paging"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>