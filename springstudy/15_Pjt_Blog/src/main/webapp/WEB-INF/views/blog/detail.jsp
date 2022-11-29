<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="../layout/header.jsp">
	<jsp:param value="${blog.blogNo}번 블로그" name="title"/>
</jsp:include>

<!-- 댓글 목록 처음엔 안 보여주는 detail -->

<style>
	.blind {
		display: none;
	}
</style>

<div>
	<h1>${blog.title}</h1>
	
	<div>
		<span>▷작성일 <fmt:formatDate value="${blog.createDate}" pattern="yyyy. MM. dd HH:mm"/></span>
		&nbsp;&nbsp;&nbsp;
		<span>▷수정일 <fmt:formatDate value="${blog.modifyDate}" pattern="yyyy. MM. dd HH:mm"/></span>
	</div>

	<div>
		<span>조회수 <fmt:formatNumber value="${blog.hit}" pattern="#,##0"></fmt:formatNumber> </span>
	</div>
	
	<hr>
	
	<div>
		${blog.content}
	</div>
	
	<div>
		<!-- 주소로 바로 이동하는 방식을 막기 위해 post방식으로 넘기는 것.-->
		<form id="frm_btn" method="post">
		<!-- 블로그넘버를 넣어줘야 서브밋할 때 넘어감. hidden으로 해서 숨겨놓고 수정할때도 삭제할때도 써먹자 -->
			<input type="hidden" name="blogNo" value="${blog.blogNo}">
		<!-- 수정/삭제버튼은 작성자만 볼 수 있어야 함. -->
			<input type="button" value="수정" id="btn_edit_blog">
			<input type="button" value="삭제" id="btn_remove_blog">
		</form>
		<script>
			// 수정화면으로 넘어가기(action 넣어주고 서브밋~)
			$('#btn_edit_blog').click(function() {
				$('#frm_btn').attr('action', '${contextPath}/blog/edit'); // 속성추가 attr(), form의 action 속성 추가
				$('#frm_btn').submit();   // 서브밋을 해야 action에 적어준 곳으로 이동~
			})
		
			$('#btn_remove_blog').click(function(){
				if(confirm('블로그를 삭제하면 블로그에 달린 댓글을 더 이상 확인할 수 없습니다. 삭제하시겠습니까?')) {
					// SET NULL 설정 -> null 값 들어감
				$('#frm_btn').attr('action', '${contextPath}/blog/edit'); // 속성추가 attr(), form의 action 속성 추가
				$('#frm_btn').submit();   // 서브밋을 해야 action에 적어준 곳으로 이동~   삭제 매핑으로 변신 완.
				}
			})
		</script>
	</div>
	
	
	
	
	<hr>
	

	<span id="btn_comment_list">
		댓글
		<span id="comment_count"></span>개
	</span>
	
	<hr>
	
	<div id="comment_area" class="blind">
		<div id="comment_list"></div>
		<div id="paging"></div>
	</div>
	
	<hr>
	
	<!-- 댓글 작성창은 정적으로 만들어둠. -->
	<div>
		<form id="frm_add_comment">
			<div class="add_comment_input">
				<input type="text" name="content" id="content" placeholder="댓글을 작성하려면 로그인을 해 주세요">
			</div>
			<div class="add_comment_btn">
				<input type="button" value="작성완료" id="btn_add_comment">
			</div>
			<input type="hidden" name="blogNo" value="${blog.blogNo}">
			<!-- form의 serialize()를 쓰면 폼안에 들어간 입력요소의 name들을 한번에 보낸다. + 누가 작성했는지 사용자정보도 함께 보내줘야 함 -->
		</form>
	</div>
	
	<!-- 현제 페이지 번호를 저장하고 있는 hidden -->
	<input type="hidden" id="page" value="1">
	
	<script>
	
		// 함수 호출
		fn_commentCount();
		fn_switchCommentList();
		fn_addComment();
		fn_commentList();
		fn_changePage();
		fn_removeComment();
		fn_switchReplyArea();
		fn_addReply();
		
		// 호이스팅...
		
		// 함수 정의
		function fn_commentCount() {
			$.ajax({
				type: 'get',
				url: '${contextPath}/comment/getCount',
				data: 'blogNo=${blog.blogNo}', // 어떤 글에 달린 댓글인지 알 수 있게 글번호를 넘겨준다
				// 받아오기~
				dataType: 'json',
				success: function(resData) {  // resData = {"commentCount" : 개수}
					$('#comment_count').text(resData.commentCount); 
				}
			});
		}
		
		function fn_switchCommentList() {
			$('#btn_comment_list').click(function() {
				$('#comment_area').toggleClass('blind');
			})
		}
		
		
		function fn_addComment() {
			$('#btn_add_comment').click(function(){
				if($('#comment').val() == '') {
					alert('댓글 내용을 입력하세요');
					return;	 // return이 하는 일 : 아래 ajax가 실행되지 않도록 막는것
				}
		
				$.ajax({
					type: 'post',
					url: '${contextPath}/comment/add',
					data: $('#frm_add_comment').serialize(),
					dataType: 'json',
					success: function(resData) {   // resData = {"isAdd", true} 성공실패여부 가져오기
						if(resData.isAdd) {
							alert('댓글이 등록되었습니다.');
							$('#content').val(''); // 입력되어있는 댓글을 초기화
							fn_commentList();   // 댓글 목록 가져와서 뿌리는 함수
							fn_commentCount();  // 댓글 목록 개수 갱신하는 함수
						}
					}
				})
			})
		}
		
		function fn_commentList(){
			$.ajax({
				type: 'get',
				url: '${contextPath}/comment/list',
				data: 'blogNo=${blog.blogNo}&page=' + $('#page').val(),  // 현재페이지 page 도 넘겨줘야 함
				dataType: 'json',
				success: function(resData){
					/*
						resData = {
							"commentList" : [     // 배열로 들어있을 것
								{댓글하나},
								{댓글하나},
								...
							],
							"pageUtil" : {
								page: n,
								...
							}
						}
					*/
					// 화면에 댓글 목록 뿌리기
					$('#comment_list').empty();  // 목록 초기화 필수~ 안그럼 쌓임
					$.each(resData.commentList, function(i, comment){   // 배열 이름 resData.commentList
						// 원댓 depth : 0, 대댓 depth: 1  구분하기 (-> 1단은 groupOrder 없음)
						var div = '';
						if(comment.depth == 0) {
							div += '<div>';
						} else {
							div += '<div style="margin-left: 40px;">';
						}
						// 경우에 따라 여기서 아이디나 닉네임 표시위해 가져오면 됨.
						// 내용   state=1 정상 state=-1 삭제(따라서 보여주면 안됑)
						if(comment.state == 1) {      // 정상이면 내용을 보여주고
							div += '<div>'
							div += comment.content;
							// 작성자만 삭제할 수 있도록 if 처리 필요!
							div += '<input type="button" value="삭제" class="btn_comment_remove" data-comment_no="'+ comment.commentNo + '">';  // 삭제하려면 삭제할 번호를 알아야되니까 data-속성으로 번호저장
							// 댓글에 대댓글을 달 수 있도록 if 처리 필요 (depth == 0 이면 댓글, depth == 1 이면 대댓. 따라서 depth==0일때만 답글버튼 보이도록)
							if(comment.depth == 0) {
							// insertComment는 사용 불가/ depth를 1로 바꿔야함. (groupNo로) 댓글의 코멘트 번호를 변수로 그대로 받아와야 함
							// groupNo를 알아야 대댓 닫 수 있음. 그룹넘버를 넘겨줘야함 
								div += '<input type="button" value="답글" class="btn_reply_area">'; // groupNo는 commnetNo
							}
							div += '</div>';	
						} else {
							if(comment.depth == 0) {   // 삭제되면 삭제되었단 글귀를 보여주고
								div += '<div>삭제된 댓글입니다.</div>';
							} else {
								div += '<div>삭제된 대댓글입니다.</div>';
							}
						}
						// 자바스크립트에서 날짜시간 형식 지정하는 라이브러리 moment-with-locale
						div += '<div>';
						moment.locale('ko-KR');     // moment-with-locale 라이브러리 사용하기
						div += '<span style="font-size: 12px; color: silver;">' + moment(comment.createDate).format('YYYY. MM. DD hh:mm') + '</span>';
						div += '</div>';
						div += '<div style="margin-left: 40px;" class="reply_area blind">';   // blind라는 클래스값이 toggleClass마냥 나왓다드러갓다 이지룰
						div += '<form class="frm_reply">'  // 보내줄게 3개가 넘으니 form으로 보내자. 여기는 반복문 안이니까 id주면 안됑
					    div += '<input type="hidden" name="blogNo" value="' + comment.blogNo + '">';	// serialize()로 보낼때는 name속성 사용
					    div += '<input type="hidden" name="groupNo" value="' + comment.commentNo + '">';	 // groupNo는 commnetNo
					    div += '<input type="text" name="content" placeholder="답글을 작성하려면 로그인을 해주세용">';
					    // 로그인한 사용자만 볼 수 있도록 if처리
					    div += '<input type="button" value="답글작성완료" class="btn_reply_add">'; // 서브밋할거아니고 ajax로 하는거니까 서브밋 하쥐뫙
					    div += '</form>';
						div += '</div>';
						div += '</div>';   // comment.depth에서 열어준 div 닫기
						$('#comment_list').append(div);
						$('#comment_list').append('<div style="border-bottom: 1px dotted gray;"></div>');
					});
					// 페이징
					$('#paging').empty();
					var pageUtil = resData.pageUtil;
					var paging = '';
					// 이전 블록
					if(pageUtil.beginPage != 1) {
						paging += '<span class="enable_link" data-page="'+ (pageUtil.beginPage - 1) +'">◀</span>';    // 에작은 페이지를 파라미터로 넘길 수 없음. 링크를 클릭하면 fn_commnetList를 재실행, 그때 페이지파라미터를ㄷ ㅏ시 보내줘야함
						// 태그에 클릭하면 몇 페이지로 가는 링크인지 넣을것
					}
					// 페이지번호
					for(let p = pageUtil.beginPage; p <= pageUtil.endPage; p++) {
						if(p == $('#page').val()) {
							paging += '<strong>' + p + '</strong>';
						} else {
							paging += '<span class="enable_link" data-page="'+ p +'">' + p + '</span>';
						}     // enable_link 클릭하면 data-page값을 가져와서 목록을 갱신
					}
					// 다음 블록
					if(pageUtil.endPage != pageUtil.totalPage) {
						paging += '<span class="enable_link" data-page="' + (pageUtil.endPage + 1) + '">▶</span>';
					}
					$('#paging').append(paging);
				}
			})
			
		} // fn_commentList();
		
		
		
		function fn_changePage() {
			// $('.enable_link').click(function())  -> 안 됨. enable_link 처럼 만든 애(동적 요소)는 이렇게 일반 클릭 event를 걸 수 없음
			$(document).on('click', '.enable_link', function(){   // $(만들어져있었던부모).on('click', '.enable_link', 이런식으로 해야함. 부모는 아무거나 ㅏㄱ능
				$('#page').val( $(this).data('page') );  // this 현제페이지.. 페이지밸류값이 commentList)할때마다 넘어감 -> $('#page').val값을 바꿔서 다시 요청
				fn_commentList();
			});
		}
		
		
		function fn_removeComment(){   // 마찬가지로 자스로 만든 동적 이벤트니까 위와 같이 만들깅
			$(document).on('click', '.btn_comment_remove', function() {
				if(confirm('삭제된 댓글은 복구할 수 없습니다. 댓글을 삭제할까요?')) {
					$.ajax({
						type: 'post',
						url: '${contextPath}/comment/remove',
						data: 'commentNo=' + $(this).data('comment_no'),// 코멘트번호 버튼에 넣어뒀음
						dataType: 'json',
						success: function(resData) {   // resData = {"isRemove" : true}
							if(resData.isRemove){
								alert('댓글이 삭제되었습니다.');
								fn_commentList();   // 목록 갱신
								fn_commentCount();	// 댓글 개수 갱신
							}
						}
					})
				}
			})
		}
		
		function fn_switchReplyArea() {
			$(document).on('click', '.btn_reply_area', function(){
				$(this).parent().next().next().toggleClass('blind');  // 토글로 blind 클래스를 줫다뺏다 난리부르스
				// 답글의 부모(<div>)의 다음, 다음에만 blind
			})
		}
		
		function fn_addReply() {
			$(document).on('click', '.btn_reply_add', function() {
				if($(this).prev().val() == ''){  // 입력은 버튼 이전 형제 input의 name="contnet"에 입력됨
					alert('답글 내용을 입력하세요.');
					return;
				}
				$.ajax({
					type: 'post',
					url: '${contextPath}/comment/reply/add',
					data: $(this).closest('.frm_reply').serialize(), // $('.frm_reply').serialize(), <-이건 안돼! 이렇게 생긴 폼이 여러개 있기 때문에 버튼 기준으로 기준잡아야함.
							// 혹은 parent..도 가넝.. 여러이벤트가 나올 때는 클릭버튼 기준으로 this같은걸 쓰는게 좋댕...
					dataType: 'json',
					success: function(resData) {   // resData = {"isAdd", true}
						if(resData.isAdd) {
							alert('답글이 등록되었습니다.');
							fn_commentList();   // 목록 갱신
							fn_commentCount();	// 갯수 갱신  -> 정상게시글의 댓글수만 세어보겠다 -> state==1인 것만 쓴다.. 쿼리문 수정하면 삭제된 댓글카운트는 안함
						}
					}
				})
				
			})
		}
		/* 댓글에서 코멘트넘버 == 그룹넘버. 따라서 commentNo == gropuNo 바까써도 무방 */
		
	</script>
	
	
	
	<%-- 
		상세보기하면 상세보기에 있는 이미지와 DB와 하드의 내용을 보고 비교해본 담에 지워버리게 하는 로직이 BLOG서비스에 들어가 있음..(getBlogByNo) 
	
		써머노트가 작성화면에서 이미지 2개를 올렸다가 하나는 지워서 작성완료를 하는 경우에도 db와 경로에 이미지파일을 저장시키기 때문에
		이 작업이 필요하댄다.
	--%>
	
	
	
</div>


	
</body>
</html>