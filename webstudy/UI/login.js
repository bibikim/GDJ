
// 테스트
//var cont = document.getElementsByClassName('container');
//console.log(cont);    // 배열,,

// 비밀번호 입력 없이 sign in 버튼 누르면 경고창 띄우기 (비밀번호를 입력하세요)

document.getElementById('btn_signin').onclick = function(event) {
    var pw = document.getElementById('pw');
    if(pw.value == '') { // pw에 입력한 값 value가 비어있으면
        alert('비밀번호를 입력하세요.');
        event.preventDefault();  // 버튼의 기본 동작(submit)을 막는다
        return;    // 더이상 아무 코드도 진행하지 않도록 return까지 넣어야 함.
        // or  return;
    }
}
// 서브밋 이벤트 1) button의 click 이벤트 2) form의 submit 이벤트를 만드는 방법

document.getElementById('id').onkeyup = function(event) {
    var id = document.getElementById('id');
    var id_msg = document.getElementById('id_msg');
    if(id.value.length == 0) { // id.value 아이디 값의 string 길이, 즉 글자수   => 한 글자도 안 썼을 때!
        id_msg.textContent = ''; 
    } else if(id.value.length < 4) {    // 아이디의 글자 입력마다 처리
        id_msg.textContent = '아이디는 4자 이상입니다.';
    } else if(id.value.length >= 4) { 
        id_msg.textContent = '정상적인 아이디입니다.';
    } 
}
// 아이디 글자랑 비번 3글자 입력후에 sign in 하면 로그인 됨. 안되게 서브밋 막아보기..!!!!!!!!!! 4점짤
document.getElementById('btn_signin').onclick = function(event) {
    var pw =document.getElementById('pw');
    if(id.value.length >= 4 && pw.value.length < 4) {
        alert('로그인을 할 수 없습니다');
        event.preventDefault();
        return;
    }
}



//jquery로 바꿔보기
//$('#btn_signin').on('click', function)
