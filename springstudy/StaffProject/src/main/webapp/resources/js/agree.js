/**
 * 
 */

$(function() {
	fn_checkAll();
	fn_checkOne();
	fn_toggleCheck();
	fn_submit();
});



// 모두 동의 (모두 동의의 체크 상태 = 개별 선택들의 체크 상태)
function fn_checkAll() {
	$('#check_all').click(function(){
		// 체크 상태 변경   checked 값은 true/false 체크되면 true=1, 체크 안하면 false=0
		$('.check_one').prop('checked', $('#check_all').prop('checked'));  // 모두 동의의 프로퍼티 checked를 개별 선택들의 프로퍼티 checked 값으로 넣어줄 것이다()  
		// 모두 동의를 클릭 했을 때, 모두동의의 체크 여부를 개별 선택의 체크 여부로
		
		// 체크 이미지 변경
		if($(this).is(':checked')) {   // 모두 동의가 체크되었다면.  is()메소드로 상태 확인 가능
			$('.lbl_one').addClass('lbl_checked');
		} else {
			$('.lbl_one').removeClass('lbl_checked');
		}
	});
}                                     

// 개별 선택(항상 개별 선택 4개를 모두 순회하면서 어떤 상태인지 체크해야 함)
function fn_checkOne() {
	// 네 개중 어떤 것이든 클릭을 하면, 어떤걸 체크햇는지 체크박스 4개를 다 뒤져봐야 함
	$('.check_one').click(function(){
		// 체크 상태 변경
		let checkCount = 0;
		for(let i = 0; i < $('.check_one').length; i++) { // $(this)는 클릭 당한 애 1개만 해당, 따라서 그냥 클래스째로 length 달아주기
			// 체크 몇 개 되어 있는건지 갯수 확인
			checkCount += $($('.check_one')[i]).prop('checked');    // 인덱스를 붙이면 자바스크립트 일반변수이기 때문에 $()한번 더 붙여야 prop 사용 가능. 
			// checked 값은 true/false 체크되면 true=1, false=0. checkCount가 2이면 두개 체크, 4개면 네개 체크(=> 모두동의 체크박스 체크되게끔)
			// 체크박스 갯수 vs 체크된 박스갯수 두개를 비교 해보고 같으면 true, 다르면 false 전체박스 check_one의 길이 + 1로 처리..?
			$('#check_all').prop('checked', $('.check_one').length == checkCount);   // 체크원의 길이와 체크카운트가 같으면 check_all에 checked 속성을 주겠다
			
			// 체크 이미지 변경
			if($('#check_all').is(':checked')) {
				$('.lbl_all').addClass('lbl_checked');
			} else {
				$('.lbl_all').removeClass('lbl_checked');
			}
		}
	});
}

// 체크할 때마다 lbl_checked 클래스를 줬다 뺐었따 하기
function fn_toggleCheck() {
	$('.lbl_all, .lbl_one').click(function(){
		$(this).toggleClass('lbl_checked') // checked된 대상()에 toggle클래스
	})
}

// 서브밋 (필수 체크 여부 확인)
function fn_submit() {
	$('#frm_agree').submit(function(event){
		if($('#service').is(':checked') == false || $('#privacy').is(':checked') == false) {
			alert('필수 약관에 동의해주세요');
			event.preventDefault();
			return;
		}
	})
}