<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  <!-- JSP 구성요소 -->
<!-- JSP파일 인코딩 UTF-8로 안 되어있으면!!  윈도우 - preference - web - jspFile - encoding - UTF-8로 변경 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- HTML 주석 : 소스보기(페이지 우클릭 소스보기)에서 확인 가능 -->   
	<%-- JSP 주석  : 소스보기에서 확인 불가능--%>  <%-- 자바영역 주석 --%>
	
	<%!
		// 선언부(Declaration) : 전역 변수 선언, 자바 메소드 정의                 느낌표 O
		public int getSum(int begin, int end) {  // 합계 구하는 메소드 선언
			int sum = 0;
			for(int n = begin; n<= end; n++){
				sum += n;
			}
			return sum;
		}
	%>
	
	<%
		// 스크립트릿(Scriptlet) : 일반 자바 코드(선언부가 아닌 모든 부분)        느낌표 X
		int sum = getSum(1, 100);   // 메소드 호출
	%>
	
	<!-- 표현식(Expression) : 값(변수, 메소드 호출 결과)을 나타낼 때 사용 -->
	<div><%=sum%></div>  			   <!-- sum이라는 자바 변수 => sum값을 보여달라 -->
	<div><%=getSum(1, 1000)%></div>	   <!-- getSum이라는 자바 메소드 => getSum() 메소드 호출 결과를 보여달라 -->


	<!-- 연습. 화면에 1~10 출력하기 -->
	<%for(int m = 1; m <= 10; m++) {%>
		<div><%=m%></div>
	<% } %>
	
	<!-- 
		연습. select 태그 만들기 
		 <select name="month">
		 	<option value="">월 선택</option>
		 	<option value="1">1월</option>
		 	...
		 	<option value="12">12월</option>
		 </select>
	-->
	
	<div>
		<select name="month">
			<option value="">월 선택</option>
			<%for(int month = 1; month <= 12; month++) {%>
			<option value="<%=month%>"><%=month + "월"%></option>
			<% } %>
		</select>
	</div>
		
		
	<!--
		연습. table 만들기
		순번	이름	나이
		1		정숙	25
		2		영희	26
		3		영숙	27
	-->
	<%
		String[] names = {"정숙", "영희", "영숙"};
		int[] ages = {25, 26, 27};
	%>
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>이름</td>
					<td>나이</td>
				</tr>
			</thead>
			<tbody>
				<% for(int i = 0; i < names.length; i++) {%>
					<tr>
						<td><%=i+1%></td>
						<td><%=names[i]%></td>
						<td><%=ages[i]%></td>
					</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	

</body>
</html>