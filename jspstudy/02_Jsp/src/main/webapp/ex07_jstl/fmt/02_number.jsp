<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="n" value="12345.6789"></c:set>   <!-- 따로 저장 안하면 pageContext에 저장되는 것:) -->
	
	<%--
		<fmt:formatNumber> 태그
		
		1. DecimalFormat 클래스를 대체하는 태그
		2. value 속성과 pattern 속성과 type 속성 이용
		3. pattern 속성에서 사용하는 패턴은 2와 동일한 패턴임
		4. 자릿수는 반올림 처리함
	--%>

	<%-- 천 단위 구분 기호(,) 사용 --%>
	<h1><fmt:formatNumber value="${n}" pattern="#,##0"/></h1>         <!-- 12,346 (반올림 반영) -->
	<h1><fmt:formatNumber value="${n}" pattern="#,##0.0"/></h1> 
	<h1><fmt:formatNumber value="${n}" pattern="#,##0.00"/></h1>      <!-- 12,345.68 -->
											<!-- 대문자 H는 24시간제 -->
	
	<%-- 천 단위 구분 기호(,) 없음 --%>										
	<h1><fmt:formatNumber value="${n}" pattern="0"/></h1>
	<h1><fmt:formatNumber value="${n}" pattern="0.0"/></h1> 
	<h1><fmt:formatNumber value="${n}" pattern="0.00"/></h1>
	
	<%-- 백분율(%) : 값에 100을 곱한 뒤 %를 붙임 --%>
	<h1><fmt:formatNumber value="${n}"  type="percent"></fmt:formatNumber></h1>
	
	<%-- 통화 기호 : $ ￦ € 기호 붙임 --%>
	<h1><fmt:formatNumber value="${n}" type="currency" currencySymbol="$"/></h1>
	<h1><fmt:formatNumber value="${n}" type="currency" currencySymbol="￦"/></h1>
	<h1><fmt:formatNumber value="${n}" type="currency" currencySymbol="￥"></fmt:formatNumber></h1>
	
	
</body>
</html>