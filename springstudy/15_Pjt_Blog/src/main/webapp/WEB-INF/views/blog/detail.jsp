<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="../layout/header.jsp">
	<jsp:param value="${blog.blogNo}번 블로그" name="title"/>
</jsp:include>

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
	
	
</div>


	
</body>
</html>