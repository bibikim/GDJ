<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- Core Library --%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		속성(Attribute) 만들기 태그
		
		1. <c:set var="속성이름" value="값" scope="영역">     scope에는 속성을 적어줄 수 있는 4개의 영역 중 하나를 적어주면 된다
		2. 영역 : page(디폴트. 생략하면 page사용), request, session, application
	--%>
	
	<%--
		pageContext.setAttribute("name", "한비");	
		pageContext.setAttribute("age", 31);	
	 --%>
	<!-- 위와 아래는 같은 코드..!! 위 자바 코드를 아래 코드처럼 사용 -->
	
	<c:set var="name" value="한비" scope="page"/>
	<c:set var="age" value="31" scope="page"/>
	<c:set var="isAdult" value="${age >= 20}" scope="page"/>  <!-- value자체를 el로 작성 -->
	<c:set var="height" value="168.6" scope="page"/>
	<c:set var="weight" value="49.2" scope="page"/>
	<c:set var="bmi" value="${weight div (height*height*0.0001)}" scope="page"/>
	<c:set var="health" value="${bmi ge 25 ? '비만' : '정상'}" scope="page"/>
	
	<h2>이름: ${name}</h2>
	<h2>나이: ${age}</h2>    
	<h2>${isAdult ? '성인' : '미성년자'}</h2>
	<!-- 이즈어덜트 역시 속성으로 만들어놨기 때문에 el 쓰기 가능. 밸류로 el 쓰는데에 문제 없음 -->
	<h2>bmi : ${bmi}</h2>
	<h2>건강상태 : ${health}</h2>

</body>
</html>