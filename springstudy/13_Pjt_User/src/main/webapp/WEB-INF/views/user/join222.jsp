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
	
	$(function() {
		fn_idCheck();
		fn_pwCheck();
		fn_pwCheckAgain();
		fn_mobileCheck();
		fn_birthyear();
		fn_birthmonth();
		fn_birthdate();
		fn_emailCheck();
		fn_join();
	});
	
	// 1. 아이디 중복체크 & 정규식
	function fn_idCheck(){
		$('#id').keyup(function(){
			
			// 입력한 아이디
			let idValue = $(this).val();
			
			// 정규식
			let regId = /^[0-9a-z][0-9a-z-_]{3,19}$/;
			
			// 정규식 검사
			if(regId.test(idValue) == false) {
				$('#msg_id').text('아이디는 4~20자의 소문자, 숫자, 특수문자(-,_)를 조합해야 합니다.')
				return; // 코드 진행 방지(이후에 나오는 ajax 실행을 막는다.)
			} else {
				$('#msg_id').text('사용 가능한 아이디 입니다.')
			}
			
			// 중복체크(중복체크 하려면 DB를 갔다와야 함(요청과 응답까지 모두 가능한 ajax!))
			$.ajax({
				/*요청*/
				type: 'get',
				url: '${contextPath}/user/checkReduceId',
				data: 'id=' + $(this).val(),   // 입력된 것 그대로 보내주면 되니까 $(this).val()
				/*응답*/
				dataType: 'json',
				success: function(resData){  // resData = {"isUser" : true, "isRetireUser" : false}
					if(resData.isUser || resData.isRetireUser) {  // 현재 회원이거나 탈퇴한 회원이다
						$('#msg_id').text('이미 사용중이거나 탈퇴한 아이디입니다.');
					} else {
						$('#msg_id').text('사용 가능한 아이디입니다.');
					}
					
				}
			})
		})
		
	}
	
	// 2. 패스워드
    function fn_pwCheck(){
      
		$('#pw').keyup(function(){
			
			// 입력한 패스워드
			let pwValue = $(this).val();
			
			// 정규식(8~20자, 소문자+대문자+숫자+특수문자8종(!@#$%^&*) 3개 이상의 조합)
			let regPw = /^[0-9a-zA-Z!@#$%^&*]{8,20}$/;
		})
		
    }
   
    // 3. 패스워드 확인
    function fn_pwCheckAgain(){
      
    }
   
    // 4. 휴대전화
    function fn_mobileCheck(){
      
    }
   
    // 5. 생년월일(년도)
    function fn_birthyear(){
       
    }
   
    // 6. 생년월일(월)
    function fn_birthmonth(){
      
    }
   
    // 7. 생년월일(일)
    function fn_birthdate(){
      
    }
   
    // 8. 이메일 - ajax 2개 필요
    //	  1) 입력된 이메일이 회원 정보에 있는지 체크하는 ajax
    // 	  2) 입력된 이메일로 인증번호를 보내는 ajax    ( ajax가 2개 이상 동시에 동작할 때는 순차적으로 보내준다는 보장이 없음 -> promise )
    function fn_emailCheck(){
    	$('#btn_getAuthCode').click(function() {
    		
	    	// resolve : 성공했을 때 수행하는 function 
	    	// reject : 실패했을 때 수행하는 function
	    	new Promise(function(resolve, reject) {  
	    		$.ajax({
	    			/*요청*/
	    			type: 'get',
	    			url: '${contextPath}/user/checkReduceEmail',  
	    			data: 'email=' + $('#eamil').val(),     // 보내줄 데이터 : 입력받은 email
	    			/*응답*/
	    			dataType: 'json',
	    			success: function(resData) {
	    				// 요 안에서 resolve/reject 펑션이 수행된다
	    				if(resData.isUser) {  // 실패!(reject) 사용할 수 없는 이메일. (이미 있단 이야기)
	    					reject();  // Promise 객체의 catch 메소드에 바인딩되는 함수
	    				} else {
	    					resolve(); // Promise 객체의 then 메소드에 바인딩되는 함수
	    				}
	    			}
	    		}) // ajax
			}).then(function(){  // 성공!
				// 인증번호 보내는 ajax
				$.ajax({
					/*요청*/
					type: 'get',
					url: '${contextPath}/user/sendAuthCode',
					data: 'email=' + $('#email').val(), // 사용자가 입력한 email을 자바측으로 보내줘야 인증코드를 해당 이메일로 보내줄 수 있다
					/*응답 - 받아오기*/ 
					dataType: 'json',
					success: function(resData) {  // resData에 authCode 인증코드 들어있음
						alert('인증코드를 발송했습니다. 이메일을 확인하세요.');
						// impl에서 발송한 인증코드(resData.authCode)와 사용자가 입력한 인증코드가 같은지 비교
						$('#btn_verifyAuthCode').click(function(){
							if(resData.authCode == $('#authCode').val()) {
								alert('인증되었습니다.');
							} else {
								alert('인증에 실패했습니다.')
							}
						})
					},
					error: function(jqXHR){
						alert('인증번호 발송이 실패했습니다.');
					}
				})
				
			}).catch(function(){
				// 사용할 수 없는 이메일이 입력된 경우(다른 회원이 등록한 이메일을 입력한 경우)
				$('#msg_email').text('이미 사용중인 이메일입니다.')  // 사용자가 인증번호를 입력하면 안됨
				$('#authCode').prop('readonly', true); // -> autCode를 입력하지 못하게 막음 readonly
			});  // new promise
	    	
    	}); // click
    }
   
    // 9. 서브밋 (회원가입)
    function fn_join(){
      
    }
	
</script>
</head>
<body>

	<div>
		<h1>회원 가입</h1>
		<div>* 표시는 필수 입력 사항입니다</div>
	</div>
	<hr>
	
	<form id="frm_join" action="${contextPath}/user/join" method="post">
		<!-- 약관동의여부 -->
		<input type="hidden" name="location" value="${location}">
		<input type="hidden" name="promotion" value="${promotion}">
		
		<!-- 아이디 -->
		<div>
			<label for="id">아이디*</label>
			<input type="text" name="id" id="id" >
			<span id="msg_id"></span>
		</div>
		
		<!-- 패스워드 -->
		<div>
			<label for="pw">패스워드*</label>
			<input type="text" name="pw" id="pw" >
			<span id="msg_pw"></span>
		</div>
		<!-- 패스워드 재확인 -->
		<div>
			<label for="re_pw">패스워드 확인*</label>
			<input type="text" id="re_pw" >
			<span id="msg_re_pw"></span>
		</div>
		
		<!-- 이름 -->
		<div>
			<label for="name">이름*</label>
			<input type="text" name="name" id="name" >
		</div>
		
		<!-- 성별 -->
		<div>
			<label for="none">선택안함</label>
			<input type="radio" name="gender" id="none" value="N" checked="checked">
			<label for="female">여자</label>
			<input type="radio" name="gender" id="female" value="F">
			<label for="male">남자</label>
			<input type="radio" name="gender" id="male" value="M">
		</div>
		
		<!-- 모바일 -->
		<div>
			<label for="mobile">휴대전화*</label>
			<input type="text" name="mobile" id="mobile" >
			<span id="msg_mobile"></span>
		</div>
		
		<!-- 생년월일 -->
		<div>
			<label for="birthyear">생년월일*</label>
			<select name="birthyear" id="birthyear"></select>
			<select name="birthmonth" id="birthmonth"></select>
			<select name="birthdate" id="birthdate"></select>
		</div>
		
		<!-- 주소 -->
         <div>
            <input type="text" name="postcode" id="postcode" placeholder="우편번호">
            <input type="button" onclick="fn_execDaumPostcode()" value="우편번호 찾기"><br>
            <input type="text" name="roadAddress" id="roadAddress" placeholder="도로명주소">
            <input type="text" name="jibunAddress" id="jibunAddress" placeholder="지번주소"><br>
            <span id="guide" style="color:#999;display:none"></span>
            <input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소">
            <input type="text" name="extraAddress" id="extraAddress" placeholder="참고항목">
            
            <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
            <script>
                //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
                function fn_execDaumPostcode() {
                    new daum.Postcode({
                        oncomplete: function(data) {
                            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            
                            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                            var roadAddr = data.roadAddress; // 도로명 주소 변수
                            var extraRoadAddr = ''; // 참고 항목 변수
            
                            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                                extraRoadAddr += data.bname;
                            }
                            // 건물명이 있고, 공동주택일 경우 추가한다.
                            if(data.buildingName !== '' && data.apartment === 'Y'){
                               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                            }
                            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                            if(extraRoadAddr !== ''){
                                extraRoadAddr = ' (' + extraRoadAddr + ')';
                            }
            
                            // 우편번호와 주소 정보를 해당 필드에 넣는다.
                            document.getElementById('postcode').value = data.zonecode;
                            document.getElementById("roadAddress").value = roadAddr;
                            document.getElementById("jibunAddress").value = data.jibunAddress;
                            
                            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                            if(roadAddr !== ''){
                                document.getElementById("extraAddress").value = extraRoadAddr;
                            } else {
                                document.getElementById("extraAddress").value = '';
                            }
            
                            var guideTextBox = document.getElementById("guide");
                            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                            if(data.autoRoadAddress) {
                                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                                guideTextBox.style.display = 'block';
            
                            } else if(data.autoJibunAddress) {
                                var expJibunAddr = data.autoJibunAddress;
                                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                                guideTextBox.style.display = 'block';
                            } else {
                                guideTextBox.innerHTML = '';
                                guideTextBox.style.display = 'none';
                            }
                        }
                    }).open();
                }
            </script>
         </div>
		
		<div>
			<label>이메일*</label>
			<input type="text" name="email" id="email">
			<input type="button" value="인증번호 받기" id="btn_getAuthCode">
			<span id="msg_email"></span><br>
			<input type="text" name="authCode" id="authCode" placeholder="인증코드 입력">
			<input type="button" value="인증하기" id="btn_verifyAuthCode">
		</div>
		
		<hr>
		
		<!-- 버튼 -->
		<div>
			<button>가입하기</button>
			<input type="button" value="취소" onclick="location.href='/'">  <!-- index로 가기 => / or ${contextPath} -->
		</div>
		
	</form>

</body>
</html>