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
				<c:forEach items="${employees}" var="emp">
					<tr>
						<td>순번자리</td>
						<td>${emp.employeeId}</td>
						<td>${emp.firstName} ${emp.lastName}</td>
						<td>${emp.email}</td>
						<td>${emp.phoneNumber}</td>
						<td>${emp.hireDate}</td>
						<td>${emp.salary}</td>
						<td>${emp.commissionPct}</td>
						<td>${emp.deptDTO.departmentId}</td>
						<td>${emp.deptDTO.departmentName}</td>
						<!-- emp의 deptDTO에 들어있는 departmentId, departmentName -->
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="10">
						<!-- 이전블록 : 1block이 아니면 이전블록이 있다. 즉 beginPage가 1이 아니면 -->
						<c:if test="${pageUtil.beginPage != 1}">   <!-- jsp쪽으로 보내준 이름 pageUtil, getter 해놨으니 꺼내쓰는데 문제 없당 -->
							<a href="${contextPath}/emp/list?page=${pageUtil.beginPage - 1}">◀</a>
							<!-- ◀ 누르면 바로 이전페이지로 가기 -->
						</c:if>
						<!-- 페이지번호 : 현재 페이지는 링크가 없다 -->
						<c:forEach var="p" begin="${pageUtil.beginPage}" end="${pageUtil.endPage}" step="1"> <!-- for문. begin=시작값, end=종료값, step=증감값 -->
							<c:if test="${p == pageUtil.page}">
								${p}  <!-- 현재페이지는 페이지번호 표시하고 끝! -->
							</c:if>
							<c:if test="${p != pageUtil.page}">						   <!-- 현재페이지가 p가 아니면,  -->
								<a href="${contextPath}/emp/list?page=${p}">${p}</a>   <!-- p로 갔을 때 페이지파라미터 p로 이동, 화면에도 p표시 -->
							</c:if>
						</c:forEach>
						
						<!-- 다음블록: 마지막 블록이 아니면 다음블록이 없다.  endPage != totalPage인 경우 마지막블록이 아님! -->
						<c:if test="${pageUtil.endPage != pageUtil.totalPage}">					  <!-- endPage와 totalPage가 같지 않으면 -->
							<a href="${contextPath}/emp/list?page=${pageUtil.endPage + 1}">▶</a>  <!-- ▶ 눌렀을 때 다음블록의 beginPage로 가겠다. 즉 endPage + 1  -->
						</c:if>
						
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
</body>
</html>