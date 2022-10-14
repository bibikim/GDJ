<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <!-- a b는 request에 저장되어 전달하기 때문에 el 사용 가능 -->
	<div>
		<form method="GET" action="02_parameter2.jsp">
			<div>
				<input type="text" name="a">  
			</div>
			<div>
				<input type="text" name="b">  		   <!-- input에 들어가는 name은 파라미터가 됨 -->
			</div>
			<div>
				<input type="submit" value="전송">     <!-- <button>과 다를거 없다! -->
			</div>
		</form>
	</div>


<!-- 	 request에 저장되는건 속성/파라미터. 둘은 다른 것! attr은 attr이고 param은 param 다름. 부르는 방법 다름 -->
</body>
</html>