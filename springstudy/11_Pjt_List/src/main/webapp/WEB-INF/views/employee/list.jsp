<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<table>
			<thead>
				<tr>
					<td>순번</td>
					<td>사원번호</td>
					<td>사원명</td>
					<td>이메일</td>
					<td>전화번호</td>
					<td>입사일자</td>
					<td>연봉</td>
					<td>커미션</td>
					<td>부서번호</td>
					<td>부서명</td>
					<!-- 부서명(departmentName) 때문에 조인이 필요함. 조인: pk와 조인 대상의 fk인 departmentId로 한다~ -->
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="10">
						
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>