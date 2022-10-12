

document.getElementById('id').onkeyup = function(event) {
    var id = document.getElementById('id');
    var id_msg = document.getElementById('id_msg');
    if(id.value.length == 0) { 
        id_msg.textContent = ''; 
    } else if(id.value.length < 4) {   
        id_msg.textContent = '아이디는 4자 이상입니다.';
    } else if(id.value.length >= 4) { 
        id_msg.textContent = '정상적인 아이디입니다.';
    } 
}

document.getElementById('btn_signin').onclick = function(event) {
    var id = document.getElementById('id');
    var pw = document.getElementById('pw');
    if(pw.value == '') { 
        alert('비밀번호를 입력하세요.');
        event.preventDefault();  
        return;  
       
    }
    // 비밀번호 글자수 설정
    if(pw.value.length < 8) {
        alert('비밀번호는 8자 이상 입력해야 합니다.');
        event.preventDefault();
        return;
    }
    // 아이디 글자수&비밀번호 글자수 맞춰 경고창 설정
    if(id.value.length < 4 && pw.value.length >= 8) {
        alert('아이디를 양식에 적합하게 입력해주세요.')
        event.preventDefault();
        return;
    }
}


