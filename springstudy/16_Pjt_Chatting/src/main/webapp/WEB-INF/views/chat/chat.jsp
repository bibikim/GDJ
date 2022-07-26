<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	* {
		box-sizing: border-box;
	}
	.wrap {
		width: 640px;
		margin: 20px auto;
	}
	.btn {
		width: 60px;
	}
	.talking_content {
		width: 640px;
		height: 360px;
		overflow-y: scroll;
		border : 1px solid gray;
		background-color: skyblue;
	}
	.message {
		width: 574px;
		height: 100px;
		display: inline-block;
	}
	.message_area > * {
		vertical-align: top;
	}
	#btn_send {
		height: 100px;
	}
	.talking_content {
		position: relative;
		padding: 5px;
	}
	.small_text {
		font-size: 12px;
	}
	.my_message, .other_message {
		margin-bottom: 5px;
	}
	.my_message {
		text-align: right;
	}
	.other_message {
		text-align: left;
	}
	.my_message_content, .other_message_content {
		display: inline-block;
		max-width: 48%;
		border-radius: 3px;
	}
	.my_message_content {
		text-align: right;
		background-color: yellow;
	}
	.other_message_content {
		text-align: left;
		background-color: white;
	}
</style>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>

	<div class="wrap">
	
		<h1>채팅 서비스</h1>
		
		<div id="before_login">
			<input type="text" id="id" placeholder="아이디" >
			<input type="button" value="입장" class="btn" id="btn_start">
		</div>
		<div id="after_login">
			<strong id="login_id"></strong>님이 입장했습니다.
		</div>
		
		<hr>
		
		<div>대화창</div>
		<div id="talking_content" class="talking_content">
		</div>
		
		<div class="message_area">
			<textarea class="message" id="message" wrap="hard"></textarea>
			<input type="button" value="전송" class="btn" id="btn_send">
		</div>
		
	</div>
	
	<script>

		var ws;  // ChatServer
		var sendData = {};  // CharServer로 전송할 데이터는 JSON 형식

		// 컨텍스트 패스 알아내기
		function getContextPath() {
			var begin = location.href.indexOf(location.origin) + location.origin.length;
			var end = location.href.indexOf("/", begin + 1);
			return location.href.substring(begin, end);
		}

		// ChatServer로 메시지 보내는 함수
		function fn_send() {
			if($('#message').val().trim() != '') {
				sendData.id = $('#id').val();  // 작성자
				sendData.message = $('#message').val();  // 메시지
				sendData.date = new Date().toLocaleString();  // 날짜
				ws.send(JSON.stringify(sendData));  // CharServer로 전송
			}
			$('#message').val('');
		}
	
		$('#after_login').hide();
		
		$('#btn_start').click(function(){
			
			if($('#id').val() != '') {
				$('#login_id').text($('#id').val());
				$('#before_login').hide();
				$('#after_login').show();
			}

			// ChatServer와 연결
			var url = 'ws://' + location.host + getContextPath() + '/chatserver';  // ws://localhost:9090/app16/chatserver 요청하면 ChatServer.java가 동작함
			ws = new WebSocket(url);
			
			// ChatServer에서 메시지가 도착하면 동작
			ws.onmessage = function(message) {
				
				var data = JSON.parse(message.data);
				
				if(data.id == $('#id').val()) {
					text = '<div class="my_message"><div class="my_message_content">';
				} else {
					text = '<div class="other_message"><div class="other_message_content">';
				}
				text += '<div class="small_text">'
				text += '<strong>' + data.id + '</strong>';
				text += '(' + data.date + ')';
				text += '</div>';
				text += '<pre style="white-space: pre-wrap;">' + data.message + '</pre>';
				text += '</div>';
				text += '</div>';
				
				$('#talking_content').append(text);  // 대화내용 추가하기
				$('#talking_content').scrollTop($('#talking_content').prop('scrollHeight'));  // 대화가 쌓이면 스크롤 내려주기
				
			};
			
		});
		
		$('#message').keyup(function(event){
			if(event.keyCode == 13 && !event.shiftKey) {  // keyCode가 13인 키는 Enter이므로 Enter를 눌렀다는 의미, Shift+Enter는 textarea에서 줄 바꿀 때 사용하므로 제외해야 함
				fn_send();
			}
		});
		
		$('#btn_send').click(function(){
			fn_send();
		});

	</script>
</body>
</html>