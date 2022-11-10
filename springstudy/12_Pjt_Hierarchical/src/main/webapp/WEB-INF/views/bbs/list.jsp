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
					<tr>
						<td>${beginNo - vs.index}</td>  <!-- vs.index => 0, 1, 2, 3, ... -->
						<td>${bbs.writer}</td>
						<td>
							<c:if test="${bbs.state == 0}">
								삭제된 게시글입니다.          <!-- 삭제한 게시물은 안보이게 만드는 것으로! -->
							</c:if>
							<c:if test="${bbs.state == 1}">
								${bbs.title}
							</c:if>
						</td>
						<td>${bbs.ip}</td>
						<td>${bbs.createDate}</td>
						<td>
							<%-- forEach문으로 class가 여러개 만들어졌을 때, 내가 삭제하려는(원하는) frm_remove가 어떤 frm_remove이냐 어떻게 알거냐? form이 여러개 있음 --%>
						<%-- <form data-aaa="${bbs.bbsNo}" class="frm_remove" method="post" action="${contextPath}/bbs/remove">  --%>
							<form method="post" action="${contextPath}/bbs/remove"> 
							<%-- data-aaa="${bbs.bbsNo}" -> data-속성의 값을 ${bbs.bbsNo}로 주면, form마다 다르게 부여됨 --%>
								<input type="hidden" name="bbsNo" value="${bbs.bbsNo}">
								<a id="Ink_remove${bbs.bbsNo}">X</a>
							</form>
							<script>
								$('#Ink_class').click(function() {
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
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">${paging}</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
	
</body>
</html>