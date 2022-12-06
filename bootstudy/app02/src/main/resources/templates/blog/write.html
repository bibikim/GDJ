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
		//console.log(location);
		
		var begin = location.href.indexOf(location.origin) + location.origin.length;   // location.href에서 index를 찾아락  // "/"를 찾는겅
		var end = location.href.indexOf("/", begin + 1);   // 어디서부터 찾으라는지 지정 -> /부터 
		return location.href.substring(begin, end);
		
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
			 ],
			 callbacks: { 
				 // summernote 편집기에 이미지를 로드할 때, 이미지는 
				 onImageUpload: function(files){   // 편집기의 이미지를 로드할 때, 이 펑션을 동작시켜라
					// files에는 우릭 ㅏ첨부해야되는 파일정보가 있는데 그걸 하드에 저장하고 싶어 -> 자바를 짜야돼 -> 이미지를 ajax처리 해서 hdd로 보내고 경로를 가져와라~ 
				 	// 이미지를 ajax를 이용해서 서버로 보낼 때 가상 form 데이터 사용. form
					var formData = new FormData();
					formData.append('file', files[0]);  // 파라미터 file, summernote 편집기에 추가된 이미지가 files[0]임. (실어줄 파라미터, 실어줄 이미지)
 					// 이미지를 HDD에 저장하고 경로를 받아오는 ajax
					$.ajax({
						type: 'post',
						url: getContextPath() + '/blog/uploadImage',
						data: formData,
						contentType: false,    // ajax 이미지 첨부용
						processData: false,    // ajax 이미지 첨부용
						dataType: 'json',      // HDD에 저장된 이미지의 경로를 json으로 받아옴(저정시키고 경로를 받아올궹)
						success: function(resData) {
							$('#content').summernote('insertImage', resData.src);   // map에 담은 src
							/*
								src=${contextPath}/load/image/aaa.jpg 값이 넘어온 경우
								summernote는
								<img src=" ${contextPath}/load/image/aaa.jpg"> 태그를 만든다.
									insertImage속성을 써서 경로를 넣어주면 해당 경로를 src 속성에 그대로 넣어준다
							
								mapping=${contextPath}/load/image/aaa.jpg인 파일의 실제 위치는
								location=C:\\upload\\aaa.jpg
							
								스프링에서 정적 자원 표시하는 방법은 servlet-context.xml에 있다. (정적자원의 관리 방법. 어떤 파일에 기록되어 있음... )
								이미지(정적 자원)의 mapping과 location을 servlet-context.xml에 작성해야 한다.
								
								
								서블릿컨텍스트.xml을 자바파일로 바꾸는 것도 있음... 관심있으면 공부하든지~
							
							*/
						}
				 	});   // ajax
				 }  // onImageUpload
			 }   // callbacks
		});
		
		// 목록
		$('#btn_list').click(function() {
			location.href = getContextPath() + '/blog/list';     // == getContextPath() + '/blog/list';
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