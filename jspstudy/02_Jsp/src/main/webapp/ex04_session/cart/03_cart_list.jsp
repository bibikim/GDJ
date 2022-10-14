<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		// session에 저장된 cart 가져와서 화면 출력
		List<Map<String, Object>> cart = (List<Map<String, Object>>)session.getAttribute("cart");
		StringBuilder sb = new StringBuilder();
		if(cart == null) {
			sb.append("<div>장바구니가 비었습니다.</div>");
		} else {
			for(int i=0; i < cart.size(); i++) {  // List는 size로 불러옴. (배열이 length)
				String item = cart.get(i).get("item") + "-----" + cart.get(i).get("amount");
			    // 배열이 아니면 [] 안에 인덱스 i가 들어가지 않음 // 배열 exmaple[i] = List의 example.get(i).  arraylist에선 get()이라는 메소드 사용!
				sb.append("<div>" + item + "</div>");
			}
		}
	%>
	
	<%-- cart 정보 출력하기 --%>
	<% if(cart != null){ %> 
		<div>담은 제품 : <%=cart.size()%>개</div>
		<div><%=sb.toString()%></div>
	<% } %>
	
	<hr>
	
	<input type="button" value="계속쇼핑하기" onclick="fn_shopping()">
	<input type="button" value="장바구니 비우기" onclick="fn_empty()">
	<script>
		function fn_shopping(){
			location.href = '01_form.jsp';
		}
		function fn_empty(){
			if(confirm('장바구니를 비울까요?')) {
				location.href = '04_delete_cart.jsp';
			} else {
				alert('취소되었습니다.');
			}
		}
	</script>
</body>
</html>