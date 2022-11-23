<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<jsp:include page="../layout/header.jsp">
	<jsp:param value="블로그작성" name="title"/>
</jsp:include>

<script>

	// contextPath를 반환하는 자바스크립트 함수   <- 자바스크립트에서 자바부르지 않고 자바스크립트로만 사용해보기~
	function getContextPath() {
		// console.log(location);
		var begin = location.href.indexOf(location.origin) + location.origin.length;   // location.href에서 index를 찾아락  // "/"를 찾는겅
		var end = location.href.indexOf("/", begin+1);   // 어디서부터 찾으라는지 지정
		return location.href.subString(begin, end);
	}

	$(document).ready(function(){
		
		//console.log(getContextPath());
		
		$('#content').summernote({
			width: 800,   // 객체 형태로 여러가지 넣어줄 수 있음
			height: 400,
			lang: 'ko-KR',
			 toolbar: [        // 사용한 태그까지 데이터베이스에 저장된당
			    // [groupName, [list of button]]
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert', ['link', 'picture', 'video']]
			  ]
		});
		
		// 목록
		$('#btn_list').click(function() {
			location.href='${contextPath}/blog/list';     // == getContextPath() + '/blog/list';
		})
		
		// 서브밋
		$('#frm_write').submit(function(event){
			if($('#title').val() == '') {  // 제목의 입력값이 없을 때('' 비었을 때)
				alert('제목은 필수입니다.');
				event.preventDefault();  // 서브밋 취소
				return;  // 더 이상 코드 실행할 필요 없음
			}
		});
		
	})
	

</script>
</head>
<body>


	<div>
		<h1>작성 화면</h1>
		<form id="frm_write" action="${contextPath}/blog/add" method="post">
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title">
			</div>

			<div>
				<label for="content">내용</label>
				<textarea id="content" name="content"></textarea>
			</div>
			<div>
				<button>작성완료</button>
				<input type="reset" value="입력초기화">
				<input type="button" value="목록" id="btn_list">
			</div>
		</form>
	</div>

</body>
</html>