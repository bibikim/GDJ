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
	
	
	// 관리자 페이지 구현은 이런식으로 짜주면 된다는데,,,,,,,,,,,, 모르겟서여 어려워여
	

	$(document).ready(function(){    /*  -> jquery에서는 .ready로 사용해야 함*/
		fn_init();				// 초기화함수
		fn_getAllMembers();     // 호출
		fn_getMember();
		fn_registration();
		fn_modify();
		fn_remove();
	});
	
	function fn_init(){			// 초기화 함수. 
		$('#id').val('').prop('readonly', false);    // readonly 속성 날리기~
		$('#name').val('');
		$(':radio[name=gender]').prop('checked', false);
		$('#grade').val('');
		$('#address').val('');
		// 조회할 땐 아이디는 수정가능한 대상이 아니라서 못 고치게 해놨지만(readonly) 초기화하면 입력 가넝하게 해둠
	}
	
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
				// 		$.each(배열, function(인덱스, 배열요소){})    -> {}하나하나가 각배열의 요소
				$.each(resData.members, function(i, member){
					var tr='<tr>';
					tr += '<td>' + member.memberNo + '</td>';
					tr += '<td>' + member.id + '</td>';
					tr += '<td>' + member.name + '</td>';
					tr += '<td>' + (member.gender == 'M' ? '남자' : '여자') + '</td>';
					tr += '<td>' + member.grade + '</td>';
					tr += '<td>' + member.address + '</td>';
					tr += '<td><input type="button" value="조회" class="btn_detail"><input type="hidden" value="' + member.memberNo + '"></td>';  // value안에는 변수로 들어와야함
					tr += '<td><input type="hidden" value="' + member.memberNo + '"><input type="button" value="삭제" class="btn_remove"></td>';  // fn_remove()에서 prev()로 했으니 똑같이 prev()로 해준당. btn_remove의 prev값으로~
					/* 
						<input type="button" value="조회" class="btn_detail">   == this
					    <input type="hidden" value="' + member.memberNo + '">   == this.next (형제관계니까 next. next()는 jquery문법. $()로 this 감싸줘야함) -> $(this).next()
					    만약 └> 이 코드가 조회버튼 앞에 있으면 this.prev -> $(this).prev()
						---> 눈에 안 보이게 hidden을 주기!
					 */
					
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
						$('#address').val(resData.member.address);  // resData 응답으로 받아온 데이터를 id=address인 입력창에 member.adderss를 넣어준다
						$('#memberNo').val(resData.member.memberNo);
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
						fn_init();   // 작업이 완료된 후(신규등록) 입력된 데이터가 초기화 되도록 작업
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
	
	function fn_modify(){
		
		$('#btn_modify').click(function(){
	
			$.ajax({
				/* 요청 */
				type: 'post',     // insert와 update는 post로.
				url: '${contextPath}/member/modify.do',
				data: $('#frm_member').serialize(),   // form의 모든 입력 요소(파라미터)들을 줄세워 다 보내겠다~ 
				/* 응답 */
				dataType: 'json',
				success: function(resData) {   // resData에는 {"isSuccess": true} 가 넘어온다
					if(resData.isSuccess){
						alert('회원 정보가 수정되었습니다.');
						fn_getAllMembers();		// 수정된 내용이 반영되도록 회원목록을 새로 고침. 다시 가져와서 다시 화면에 뿌려라
					} else {
						alert('회원 정보 수정이 실패했습니다.');
					}
				},
				error: function(jqXHR) {
					alert(jqXHR.responseText);
					}
			}) // ajax
		
		}); // click
	
	} // function
	
	function fn_remove() {
		// memberNo값으로 삭제할 예정. but 폼에 memberNo가 없어서 전달 불가. 따라서 폼에 memberNo값을 만들어줘야함
		// fn_getAllMembers() 에서 썼던 방법 그대로 활용해보자. 삭제버튼 주변에 만들기.. 난 삭제버튼 위에 만듦!
		$('body').on('click', '.btn_remove', function(){   // class=btn_remove가 됨. 위에서 class를 줬음!! 아래 form에서도 작업을 해주면, 대상이 똑같아짐!. (id는 안 쓰니까 없애도 됨)
											// 만든 버튼은 동적요소가 되니까 일반 이벤트는 동작X -> fn_getMember 에서 사용했던 방법과 동일
			if(confirm('삭제할까요?') == false) {
			return;  // 코드 진행 막기
			}
			
			$.ajax({
				/* 요청 */	
				type: 'get',   // delete는 get방식으로.
				url: '${contextPath}/member/remove.do',
				data: 'memberNo=' + $(this).prev().val(), // 현재 클릭한 버튼(this)의 이전 형제(.prev)가 갖고 있던 값(val)을 삭제할 회원번호로 넘겨준다
				/* 응답 */
				dataType: 'json',
				success: function(resData) {  // resData : {"isSuccess": true}
					if(resData.isSuccess) {
						alert('회원 정보가 삭제되었습니다.');
						fn_getAllMembers();
						fn_init();
					} else {
						alert('회원 정보 삭제가 실패했습니다.');   // 없는 번호 전달
					}
				},
				error: function(jqXHR){
					// 잘못된 회원번호(숫자가 아닌 값)를 전달
					alert(jqXHR.responseText);
				}
			});  // ajax

		});  // click
	}  // function
		
</script>
</head>
<body>
	<div class="wrap"></div>
		<h1 class="title">회원관리</h1>
		<form id="frm_member">
			<div>
				<label for="id">아이디</label>
				<input type="text" id="id" name="id">   <!-- value 제거할 때 val()에 빈 문자열'' 주면 됨. 읽기 전용(readonly) 속성 준 상태 .prop('readonly', false) 초기화-->
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</div>
			<div>
				<label for="male">남자</label>
				<input type="radio" id="male" name="gender" value="M">  <!--  .prop('checked', false)  -> checked 속성 초기화 -->
				<label for="female">여자</label>
				<input type="radio" id="female" name="gender" value="F">   
									<!-- value는 db에서 gender에 준 타입 따라 주기 -->
			</div>
			<div>
				<label for="grade">회원등급</label>
				<select id="grade" name="grade">
					<option value="">등급선택</option>  
					<!--  셀렉트작업할 때 선택 안 했을 때의 선택지도 줘라. 빈문자열로 value값 주ㅓ~  이렇게 해놨기 때문에 초기화 함수에서 빈문자열 초기화 설정 가능한거임 -->
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
				<input type="button" value="초기화" onclick="fn_init()">   <!-- 클릭하면 fn_init() 함수 호출~ -->
				<input type="button" value="신규등록" id="btn_add">
				<input type="button" value="변경내용저장" id="btn_modify">
				<input type="hidden" id="memberNo" >	
				<!-- value는 조회버튼 눌렀을 때 value=memberNo가 들어가 있게끔 조회함수에 memberNo값을 전달하는 코드 추가(line.104) -->
				<input type="button" value="회원삭제" id="btn_remove" class="btn_remove">
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