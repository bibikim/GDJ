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
	$(function(){    // 익명함수를 jqueryWrapper로 묶음 => $(document).ready(function(){})이랑 같은거!
		
		if('${recordPerPage}' != '') {
			$('#recordPerPage').val(${recordPerPage});   // $('#recordPerPage')의 value를 recordPerPage로 바꿔주겠다
														 // ${recordPerPage} = BbsServiceImpl에서의 recordPerPage 변수
		} else {
			$('#recordPerPage').val(10);
		}
	
		$('#recordPerPage').change(function(){
			location.href = '${contextPath}/bbs/list?recordPerPage=' + $(this).val();   // 목록을 새로 요청할거임
		})
		
	});
</script>
<style>
	.lnk_remove {
		cursor: pointer;
	}
	.blind {
		display: none;      
	}
</style>
</head>
<body>

	<div>
		<a href="${contextPath}/bbs/write">작성하러 가기</a>
	</div>

	<div>

		<select id="recordPerPage">
			<option value="10">10</option>
			<option value="20">20</option>
			<option value="30">30</option>
		</select>

	</div>


	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>작성자</td>
					<td>제목</td>
					<td>IP</td>
					<td>작성일</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bbs" items="${bbsList}" varStatus="vs">   <%-- items는 EL-${}로 작성 --%>
					<c:if test="${bbs.state == 1}">
						<tr>
							<td>${beginNo - vs.index}</td>  <!-- vs.index => 0, 1, 2, 3, ... -->
							<td>${bbs.writer}</td>
							<td>
								<!-- 
									depth에 따른 들여쓰기 : 반복문으로! 
								 	depth가 0이면 원글이고 시작은 1부터니까 반복x  >> 들여쓰기 x
								 		depth가 1이면 답글이고 반복문 실행o >> &nbsp;&nbsp; 한번 진행
								 		depth가 2이면 다답글이고  〃        >> &nbsp;&nbsp; 두번 진행
								-->
								<c:forEach begin="1" end="${bbs.depth}" step="1">
									&nbsp;&nbsp;
								</c:forEach>
								<!-- 답글은 [RE] 표시. 답글인지 아닌지는 depth로 알 수 있음. depth가 0보다 크면 답글! -->
								<c:if test="${bbs.depth > 0}">
									[RE]
								</c:if>
								<!-- 제목 -->
								${bbs.title}
								
								<%-- 
										대댓에 대댓을 못 주게 하려면! 답글달기에 c:if를 달아서 depth=0일때만 [답글]버튼 보이게!
										▶  <c:if test="${bbs.depth == 0}"><input 답글></c:if>
								--%>
								<!-- 답글달기 버튼 : 입력할 수 있는 양식을 만들어 두기 -->
								<input type="button" value="답글" class="btn_reply_write">
								<script>   
									$('.btn_reply_write').click(function() {
										// 답글 버튼 누르면 모든 tr의 blind 주기!                       .addClass(blind)
										// 내가 클릭한 버튼에 속한 것만 보여주고 나머지는 안보여줌      .removeClass(blind)
										// 즉, 답글버튼 누를 때마다 다른 글의 답글도 열리게 하는게 아니라, 하나만 열리게
										$('.reply_write_tr').addClass('blind');
										// this(btn_reply_write)의 부모<td>의 부모<tr>의 다음인접형제next()가 <tr class="blind">
										$(this).parent().parent().next().removeClass('blind');
										
									})
								</script>
							</td>
							<td>${bbs.ip}</td>
							<td>${bbs.createDate}</td>
							<td>
								<form method="post" action="${contextPath}/bbs/remove"> 
									<input type="hidden" name="bbsNo" value="${bbs.bbsNo}">
									<a id="lnk_remove${bbs.bbsNo}">X</a>
								</form>
								<script>
									$('#lnk_remove${bbs.bbsNo}').click(function() {
										if(confirm('삭제할까요?')) {
											// $('.frm_remove').submit();  -> 이렇게 form을 부르면 아예 싹 삭제..?
										    // alert( $(this).parent().data('aaa') );  
										   <%-- 번호를 누른 <a>의 부모 <form>의 data-aaa속성을 클릭하게 한 것. /// data- 속성값을 가지고 오는 함수 :  .data('aaa') --%>
											// alert($(this).prev().val());  // 같은레벨의 이전형제, 즉 input=hidden의 value값=bbs.bbsNo 을 창을 띄워준 것
											$(this).parent().submit();
										} 
									})
								</script>
							</td>
						</tr>
						<tr class="reply_write_tr blind">  <!-- display: none; 처리해서 있지만 안 보이게, [답글]버튼을 누르면, <tr>의 class를 제거하는 걸로 => jquery에서 .remove -->
						    <!-- BbsServiceImpl의 addReply()메소드에서 보내는 파라미터 5개 -->
							<td colspan="6">
								<form method="post" action="${contextPath}/bbs/reply/add">
									<div><input type="text" name="writer" placeholder="작성자" required></div>
									<div><input type="text" name="title" placeholder="제목" required></div>
									<div><button>답글달기</button></div>
									<input type="hidden" name="depth" value="${bbs.depth}">
									<input type="hidden" name="groupNo" value="${bbs.groupNo}">
									<input type="hidden" name="groupOrder" value="${bbs.groupOrder}">
								</form>
							</td>
						</tr>
					</c:if>
					<c:if test="${bbs.state == 0}">
						<tr>
							<td>${beginNo - vs.index}</td>
							<td colspan="5">삭제된 게시글입니다</td>  <!-- 삭제한 게시물은 안보이게 만드는 것으로! -->
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
							<%-- forEach문으로 class가 여러개 만들어졌을 때, 내가 삭제하려는(원하는) frm_remove가 어떤 frm_remove이냐 어떻게 알거냐? form이 여러개 있음 --%>
							<%-- <form data-aaa="${bbs.bbsNo}" class="frm_remove" method="post" action="${contextPath}/bbs/remove">  --%>
							<%-- data-aaa="${bbs.bbsNo}" -> data-속성의 값을 ${bbs.bbsNo}로 주면, form마다 다르게 부여됨 --%>
			<tfoot>
				<tr>
					<td colspan="6">${paging}</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
	
</body>
</html>