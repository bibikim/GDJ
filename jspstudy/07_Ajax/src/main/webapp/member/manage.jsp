<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String contextPath = request.getContextPath(); 
        // => ${contextPath} EL로 부를 수 없음. jsp 네개의 저장 장소 중 어느 곳에도 저장하지 않았기 때문에
	pageContext.setAttribute("contextPath", contextPath);
        // pageContext에 저장 완. 따라서 ${contextPath} 가능    <-- <c:set var="contextPath" value="<%= requ..."..
        // jstl을 쓰는 이유 -> 자바코드를 태그로 바꾸려고 쓰는게 jstl
        // 여기선 jstl 자르파일 안 해줬기 때문에 자바코드로 한거!
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	/*  onload = function(){}     		-> 자바스크립트에서는 이걸로 사용*/

	$(document).ready(function(){    /*  -> jquery에서는 .ready로 사용해야 함*/
		fn_getAllMembers();      // 호출
		fn_getMember();
		fn_registration();
	});
	function fn_getAllMembers(){
		$.ajax({
			/* 요청 */    // 파마리터가 없음
			type: 'get',    // 주소로 요청했으니까 get,,?
			url: '${contextPath}/member/list.do',
			/* 응답 */
			dataType: 'json',
			success: function(resData){  // 성공한 데이터는 ()안으로 넘어옴.
				// resData : {"count: 3, "members": [{}, {}, {}] resData가 객체. 객체의 프로퍼티 꺼내보는 방법 = . or []
				
				// 1.(resData.count, resData['count']
				$('#count').text(resData.count);  // <span> 태그에 resData의 count를 텍스트로 추가하고 싶다~
				
				// 2. member_list 영역의 초기화.    기존에 있던 list를 지우고 새로 가져와서 화면에 뿌리게끔 초기화 작업
				$('#member_list').empty();
				// 3. resData.members : [{}, {}, {}]
				// 		$.each(배열, function(인덱스, 배열요소){})    {}하나하나가 각배열의 요소
				$.each(resData.members, function(i, member){
					var tr='<tr>';
					tr += '<td>' + member.memberNo + '</td>';
					tr += '<td>' + member.id + '</td>';
					tr += '<td>' + member.name + '</td>';
					tr += '<td>' + (member.gender == 'M' ? '남자' : '여자') + '</td>';
					tr += '<td>' + member.grade + '</td>';
					tr += '<td>' + member.address + '</td>';
					tr += '<td><input type="button" value="조회" class="btn_detail"><input type="hidden" value="' + member.memberNo + '"></td>';  // value안에는 변수로 들어와야함
					// <input type="button" value="조회" class="btn_detail">   == this
					// <input type="hidden" value="' + member.memberNo + '">   == this.next (형제관계니까 next. next()는 jquery문법. $()로 this 감싸줘야함) -> $(this).next()
					// 만약 └> 코드가 조회버튼 앞에 있으면 this.prev -> $(this).prev()
					// 눈에 안 보이게 hidden을 주기!
					
					// 반복문이라 동일한 id 3개 나옴. 그러면 안되는데! id를 굳이 사용할거면 btn_detail[i]. 근데 비추.. 
					// 동일한 class는 생겨도 상관 없음. 따라서 class를 주는 것이 깔끔!
					tr += '</tr>';
					$('#member_list').append(tr); 
					// html은 기존의 데이터를 덮어쓰기 하는 애. each문 -> 3번의 덮어쓰기가 이루어짐 -> user3의 정보만 보임. 따라서 누적시키는 메소드 append()
					
					// 추가해서 4명이 되면, 4명 가지고 오는게 append 돼서 3명/ 4명... 이런식으로 처음 append한 3명을 계속해서 보여줌. 
					// └> 따라서 반복문 이전에 초기화 작업 필요  -> empty()
					// 게시글 댓글 목록을 가지고 와서 list를 뿌리는데에 이 기술이 깔끔하고 좋음.
					});
				}
		});
	}
		
	function fn_getMember() {
		// "조회" 버튼은 동적 요소이기 때문에 다음의 이벤트 방식을 사용해야 한다.
		// $(부모요소).on(이벤트타입, 이벤트대상, 이벤트리스너)     -> 부모요소는 조상이어도 상관 없음. body도 가능
		$('body').on('click', '.btn_detail', function(){  // 버튼이 원래 있었던 버튼(정적버튼)이 아니기때문에 '조회'버튼은 jquery가 만든 버튼(동적버튼)임. 정상적인 클릭이벤트를 먹지 않음
														  // 동적으로 생성된 요소는 동적요소를 품고있는 정적요소를 부모로 지정해줘야 함
			$.ajax({
				/* 요청 */
				type: 'get',
				url: '${contextPath}/member/detail.do',
				data: 'memberNo=' + $(this).next().val(), // 파라미터 적어주는 곳
				/* 응답 */
				dataType: 'json',
				success: function(resData) {   // resData : {"exists" : true, "member": {}}
					if(resData.exists) {
						alert('회원 정보가 조회되었습니다.');
						$('#id').val(resData.member.id).prop('readonly', true);  // 입력상자에 들어갈 value 속성. value 집어넣을 때 속성 => val(입력상자에 들어갈 값). id수정 불가->readonly속성(true, false) 주기. 조회했을 때만 readonly하기 위해
						$('#name').val(resData.member.name);   // resData의 member의 name을 입력상자에 채우겠다~
						$(':radio[name=gender][value=' + resData.member.gender + ']').prop('checked', true);  // or $(':radio')  /
						//$(':radio[name=gender]').val(M)  -> name이 gender인 value를 모두 M으로 바꾸겠다는 완전 딴판의 이야기가 되므로 절대 안 됨
						$('#grade').val(resData.member.grade);  // select는 input태그와 똑같이 작업해주면 된다.
						$('#address').val(resData.member.address);
					} else {
						alert('조회된 회원 정보가 없습니다.');
					} 
				}
			});
		});           // 이벤트 대상 => input type="조회" == this / this.parent() => <td> 
	}				  // 회원번호 가져오기 this.parent().parent().find(td:first-type).text() = 3  ---> 개복잡.. 이벤트 대상의 형제관계로 input 요소를 hidden으로 주자! line49~53 참고!

	
	function fn_registration(){
		
		$('#btn_add').click(function() {

			$.ajax({
				/* 요청 */
				type: 'post',    // insert랑 update는 post로 진행
				url: '${contextPath}/member/add.do',
			 	data: $('#frm_member').serialize(),   // 요청파라미터들을 data로. serialize() : 폼 내부의 모든 입력 요소를 파라미터로 변환해주는 메소드(다섯개의 파라미터 한번에 주는 방법)
				/* 응답 */
				dataType: 'json',
				// 정상 응답 success가 처리
				success: function(resData){    // 넘어오는 resData : {"isSuccess" : true}
					if(resData.isSuccess) {
						alert('신규 회원이 등록되었습니다.'); 
						fn_getAllMembers();     // 목록 갱신. 목록 새로 가져와서 화면에 뿌려주는 함수. 이거 때문에 각각 다 function을 준겅미
					// 함수 호출 없이 한다면, ajax는 순서가 없는 통신 방식. 내부에선 삽입하고 삽인한거 보고 목록 가져와 -> ajax안애 ajax -> promise 사용해야함
					} else {
						alert('신규 회원 등록이 실패했습니다.'); 
					}
				},
				// 예외 응답 error가 처리
				error: function(jqXHR){  // 예외 처리 응답 데이터(일반 텍스트)는 jqXHR 객체의 responseText 속성에 저장됨
					alert(jqXHR.responseText);   // addService 예외 처리 응답이 여기 담김
				}
			}); // ajax
		
		}); // click
	
	} // function 
	
		
		
</script>
</head>
<body>
	<div class="wrap"></div>
		<h1 class="title">회원관리</h1>
		<form id="frm_member">
			<div>
				<label for="id">아이디</label>
				<input type="text" id="id" name="id"> 
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</div>
			<div>
				<label for="male">남자</label>
				<input type="radio" id="male" name="gender" value="M">   
				<label for="female">여자</label>
				<input type="radio" id="female" name="gender" value="F">   
									<!-- value는 db에서 gender에 준 타입 따라 주기 -->
			</div>
			<div>
				<label for="grade">회원등급</label>
				<select id="grade" name="grade">
					<option value="">등급선택</option>
					<option value="gold">골드</option>
					<option value="silver">실버</option>
					<option value="bronze">브론즈</option>
				</select>
			</div>
			<div>
				<label for="address">주소</label>
				<input type="text" id="address" name="address">
			</div>
			<div>
				<input type="button" value="초기화" id="btn_init">
				<input type="button" value="신규등록" id="btn_add">
				<input type="button" value="변경내용저장" id="btn_modify">
				<input type="button" value="회원삭제" id="btn_remove">
			<!-- form에 action 속성 안 달아줌. ajax 처리는 제이쿼리로 만들어서 화면처리할 것이기 때문에 submit이 아닌 button으로 -->
				<!-- 네이밍규칙 : db에서 쓰는 이름을 보통 사용하지 않음..  -->
				<!--
					select    -> find
					insert    -> add/resist
					update    -> save
					delete    -> remove
				-->
			</div>
		</form>
		<hr>
		<table class="member_table">
			<caption>전체 회원수 : <span id="count"></span>명</caption>
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>성명</td>
					<td>성별</td>
					<td>등급</td>
					<td>주소</td>
					<td></td>
				</tr>
			</thead>
			<tbody id="member_list">
			<!-- db에 들어가있는 멤버 3명 가져와서 화면에 뿌려주는 작업 -->
				
			</tbody>
		</table>
</body>
</html>