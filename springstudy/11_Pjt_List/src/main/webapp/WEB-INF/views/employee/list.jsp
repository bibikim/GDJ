<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function(){
		// area1, area2 표시
		// 초기 상태 : area1, area2 둘 다 숨김
		$('#area1, #area2').css('display', 'none');
		// column 선택에 따른 area1, area2 표시
		$('#column').change(function() {     // #column == this
			let combo = $(this);
			if(combo.val() == '') {
				$('#area1, #area2').css('display', 'hidden');
			} else if(combo.val() == 'HIRE_DATE' || combo.val() == 'SALARY') {
				$('#area1').css('display', 'none');
				$('#area2').css('display', 'inline');
			} else {
				$('#area1').css('display', 'inline');
				$('#area2').css('display', 'none');
			}
		})
		
		$('#btn_all').click(function() {
			location.href = '${contextPath}/emp/list';
		})
	})
</script>
</head>
<body>

	<div>
	<!-- 
		<selcet name="column">
		<input name="query">     -> 이 두개의 데이터가 DB로 전달 될 것
		
		 /emp/search로 보내는 파라미터 4개 = column, query, begin, end
	-->
		<form id="frm_search" action="${contextPath}/emp/search">
			<select id="column" name="column">  <!-- submit할 때 넘어가는 값은 name값! -->
				<option value="">:::선택:::</option>   <!-- 칼럼이름을 value값으로 -->
				<!-- area1 : 완벽 일치해야 검색 -->
				<option value="EMPLOYEE_ID">사원번호</option>     
				<option value="E.DEPARTMENT_ID">부서번호</option> 
				 <!-- area1 : 일부만 일치해도 조회 가능 -->
				<option value="LAST_NAME">성</option>   	 
				<option value="FIRST_NAME">이름</option>
				<option value="PHONE_NUMBER">연락처</option> 
				 <!-- area2 : 날짜 및 숫자 검색 -->
				<option value="HIRE_DATE">입사일</option>
				<option value="SALARY">연봉</option>
			</select>
			<span id="area1">
				<input type="text" id="query" name="query">
			</span>
			<span id="area2">
				<input type="text" id="start" name="start">
				~
				<input type="text" id="stop" name="stop">
			</span>
			<span id="">
				<input type="submit" value="검색">
				<input type="button" value="전체사원 조회" id="btn_all">
			</span>
		</form>
		
		<!-- /emp/search로 보내는 파라미터 4개 = column, query, start, stop -->
	</div>

	<div>
		<table border="1">
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
				<c:forEach items="${employees}" var="emp" varStatus="vs">  <!-- varStatus  0부터 시작하는 index 갖고 오는 방법! 변수vs에서 index 가져오기 -->
					<tr>
						<td>${beginNo - vs.index}</td>  <!-- 107, 106, 105 ... 순번 표시하기 -->
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
					${paging}
					<!-- java에서 처리를 하면, model에 실어서 보낸 속성과 값을 EL로 감싸서 써주면 간단하게 처리 가능(밑에처럼 구구절절 안 써도 가넝한..) --> 
					
				<%-- 						
						<!-- 이전블록 : 1block이 아니면 이전블록이 있다. 즉 beginPage가 1이 아니면~ -->
						<c:if test="${pageUtil.beginPage != 1}">   <!-- jsp쪽으로 보내준 이름 pageUtil, getter 해놨으니 꺼내쓰는데 문제 없당 -->
							<a href="${contextPath}/emp/list?page=${pageUtil.beginPage - 1}">◀</a>
							<!-- ◀ 누르면 바로 이전페이지로 가기 -->
						</c:if>
						<!-- 페이지번호 : 현재 페이지는 링크가 없다! -->
						<c:forEach var="p" begin="${pageUtil.beginPage}" end="${pageUtil.endPage}" step="1"> <!-- for문 : var=변수, begin=시작값, end=종료값, step=증감값 -->
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
			   	--%>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
</body>
</html>