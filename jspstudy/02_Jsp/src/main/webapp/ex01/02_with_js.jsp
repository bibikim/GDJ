<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 
		1. 가능한 작업
			Java 변수를 JavaScript에서 표현식으로 사용할 수 있다.
	-->
	
	<%
		String name = "김한비";
		int age = 31;
	%>
	<script>
		var name = '<%=name%>';
		var age = <%=age%>;   		// 나이는 정수니까 '' 불필요
		alert(name + ', ' + age);
	</script>
	
	
	<!-- 
		2. 불가능한 작업 
			JavaScript 변수는 Java 영역에서 사용할 수 없다.
	-->
	<script>
		var address = '부천시';   // 자바스크립트 변수
	</script>
	<%-- <div><%=address%></div>  // 위 예제와 반대로 js 변수는 java 영역에서 인식 불가 --%>
	
	
</body>
</html>