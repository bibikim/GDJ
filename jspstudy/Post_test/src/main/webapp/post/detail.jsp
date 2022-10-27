<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	
	$(document).ready(function(){
	
		
		
	})
	
</script>
</head>
<body>

	<div>
		<form id="frm_write" action="${contextPath}/post/add.po" method="post">
			<table border="1">
					<tbody>
						<tr>
							<td>작성자</td>
							<td><input type="text" name="writer"></td>
							<!-- name 속성 필수 지정해줘야 됨. name이 db를 오갈 파라미터임 당연함 ㅠ -->
						</tr>
						<tr>
							<td>제목</td>
							<td><input type="text" name="title"></td>
						</tr>
							
					<tr>
						<td>내용</td>
						<td><textarea rows="5" cols="40" name="content"></textarea></td>
					</tr>
					
				</tbody>	
				<tfoot>	
						<tr>
							<td>
								<input type="submit" id="btn_edit" value="수정" onclick="location.href='${contextPath}/post/detail.po?postNo=${post.postNo}';">
								<input type="submit" id="btn_ok" value="등록">
								<input type="button" id="btn_list" value="목록" onclick="location.href='${contextPath}/post/list.po';">
							</td>
						</tr>
				</tfoot>
			</table>
		</form>
	</div>




</body>
</html>