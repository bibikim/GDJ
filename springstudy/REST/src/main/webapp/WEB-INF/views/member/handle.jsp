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
		fn_list();
		fn_init(); 
		fn_detail();
		fn_modify();
		fn_remove();
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
					if(resData.insertResult > 0) {
						alert('회원이 등록되었습니다.');
						fn_list();  // 목록 갱신
						fn_init();  // 초기화
					} else {
						alert('회원이 등록되지 않았스니다.');
					}
				},
				error: function(jqXHR){
					alert('에러코드(' + jqXHR.status + ') ' + jqXHR.responseText);   // db까지 갔다가 중복체크하고 돌아옴
				}
			}); // ajax
		});
	}
	
	function fn_init() {
		$('#id').val('').prop('readonly', false);  // 초기화 시에, 읽기전용readonly 해제!
		$('#name').val('');
		$(':radio[name=gender]').prop('checked', false);  // 이 라디오의 프로퍼티에서 선택된것을 false(해제) 해줘라
		$('#address').val('');	// value가 빈문자열이면 선택x
	}
	
	// 전역변수
	var page = 1;    // 1페이지로 초기화 
	function fn_list() {
		$.ajax({
			type: 'get',
			url: '${contextPath}/members/page/' + page,      // rest방식은 '${contextPath}/members?page=' + page 와 같은 모양새로 요청 안 함
			// 받아오는 데이터						// => 주소 이렇게 됨 :  9090/rest/members/page/1
			dataType: 'json',
			success: function(resData) {
				$('#member_list').empty();  // 결과 나올 장소 : member_list
				$.each(resData.memberList, function(i, member) {
					var tr = '<tr>';
					tr += '<td><input type="checkbox" class="check_one" value="' + member.memberNo + '"></td>';
					tr += '<td>' + member.id + '</td>';
					tr += '<td>' + member.name + '</td>';
					tr += '<td>' + (member.gender == 'M' ? '남자' : '여자') + '</td>';
					tr += '<td>' + member.address + '</td>';
					tr += '<td><input type="button" value="조회" class="btn_detail" data-member_no="' + member.memberNo + '"></td>' // 반복문이니까 class로 이름주기, 누구를 조회하는건지 멤넘을 알아야하니까 data 속성 주기
					tr += '</tr>';
					$('#member_list').append(tr);
				})
			}
		})
	}
	
	// 조회버튼은 위에서 동적으로 만듦 -> 조회버튼 클릭이벤트는 아래처럼 만들어야함
	function fn_detail(){
		$(document).on('click', '.btn_detail', function() {
			$.ajax({
				type: 'get',
				url: '${contextPath}/members/' + $(this).data('member_no'),
				dataType: 'json',
				success: function(resData) {
					let member = resData.member;
					if(member == null) {
						alert('해당 회원을 찾을 수 없습니다.');
					} else {
						$('#memberNo').val(member.memberNo);
						$('#id').val(member.id).prop('readonly', true); // 상세조회해서 정보를 가져왔을 때, 프로퍼티 중에 readonly라는 프로퍼티를 true로 해라!
						$('#name').val(member.name);
						$(':radio[name=gender][value=' + member.gender + ']').prop('checked', true); // name=gender인 radio에서 value가 m/f인 프로퍼티를 check 해준다
						$('#address').val(member.address);
					}
				}
			})
		});
	}
	
	function fn_modify() {
		$('#btn_modify').click(function() {
			// 수정할 회원정보를 json으로 만들기
			let member = JSON.stringify({
				memberNo: $('#memberNo').val(),   // 수정할 회원의 번호.   이거 이렇게 써먹으려고 <input type="hidden" id="memberNo"> 한거임. 상세보기 하면 이 데이터가 들어가니까 memberNo의 value를 빼는거
				name: $('#name').val(),
				gender: $(':radio[name=gender]:checked').val(),   // 모든 radio들 = :radio,  name=gender 네임이 젠더인애들(m/f 두개) 중에서 체크 된 애들의 value를 가져온다
				address: $('#address').val()	// select는 input과 다를게 없다잉
			}) // stringify
			// alert(member);  -> 알럿창으로 확인해보면 member가 json데이터로 잘 넘어오는 것을 확인할 수 있다.
			// 수정
			$.ajax({
				type: 'put',
				url: '${contextPath}/members',
				data: member,  // 보내는 데이터에 member 넣기
				contentType: 'application/json',  // 내가 보내는 데이터가 json이다! 라고 알려주기
				dataType: 'json',  // 받아오는 데이터 json
				success: function(resData){
					if(resData.updateResult > 0) {
						alert('회원 정보가 수정되었습니다.');
						fn_list(); // 목록까지 새로 뿌려야! 상세보기도 바뀌고 목록까지 갱신
					} else {
						alert('회원 정보가 수정되지 않았습니다.');
					}
				},
				error: function(jqXHR) {
					alert('에러코드('+ jqXHR.status +') ' + jqXHR.responseText);// null값이 갔다던가, 30byte이상의 글자가 갔다던가 할 때 위와 같은 예외상황 발생~
				}
			})
		});
	}
	
	
	function fn_remove() {
		$('#btn_remove').click(function() {  // 76행에 check_one -> member.memberNo을 줬음.  다중선택 -> 1,3,4.... 컴마 단위로 파싱해서 List(arrayList)에 담아서 어쩌구
			if(confirm('선택한 회원을 모두 삭제할까요?')) {
				// 삭제할 회원번호
				let memberNoList = ''; 
				for(let i=0; i < $('.check_one').length; i++) {  // class 체크된 애들
					if( $($('.check_one')[i]).is(':checked') ) {       // $('.check_one') 배열임.(jquery임) -> $('.check_one')[i] : 요소 하나[i]를 빼면 변수가 되기 때문에 자바스크립트 요소. 따라서 $()로 한번 감싸줘야 함
						memberNoList += $($('.check_one')[i]).val()+ ',';  // 3, 1,   => ,가 마지막 memberNo 뒤에도 붙어있음 주의
						}
					}
					memberNoList = memberNoList.substr(0, memberNoList.length - 1);  // 3 , 1
					$.ajax({
						type: 'delete',
						url: '${contextPath}/members/' + memberNoList,
						dataType: 'json',
						success: function(resData) {
							if(resData.deleteResult > 0) {
								alert('선택된 회원 정보가 삭제되었습니다.');
								fn_list();
							} else {
								alert('선택된 회원 정보가 삭제되지 않았습니다.');
							}
						}
					});
				}
			})
		}
	
</script>
</head>
<body>

	<h1>회원관리</h1>
	<div>
		<input type="hidden" id="memberNo">

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
			<!-- 초기화 버튼 누르면 fn_init()함수 호출  -->
			<input type="button" value="초기화" onclick="fn_init()"> 
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
			<tbody id="member_list">
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