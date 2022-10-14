<%@page import="java.util.ArrayList"%>
<%@page import="domain.Board"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 1. 1 ~ 5 --%>
	<c:forEach var="n" begin="1" end="5" step="1">    
		${n}&nbsp;  <%-- 속성을 만들면 EL로 접근할 수 있다. --%>
	</c:forEach>
	
	<hr>
	
	<%-- 2. 5 ~ 1 --%>
	<c:forEach var="n" begin="1" end="5" step="1">
		${6-n}&nbsp;
	</c:forEach>

	<hr>
	
	<%-- 3. 1월 ~ 12월 --%>
	<select name="month">
		<option>월 선택</option>
		<c:forEach var="m" begin="1" end="12" step="1">
			<option value="${m}">${m}월</option>
		</c:forEach>
	</select>
	
	<hr>
	
	<%-- 4. 배열 --%>
	<%
		String[] menus = {"튀김", "떡볶이", "순대"};     	// 자바변수이기 때문에 EL 사용 불가
		pageContext.setAttribute("menus", menus);   		// 대개는 어딘가에서부터 request로 넘어옴!
	%>
	
	<c:forEach var="menu" items="${menus}" varStatus="vs">    <%-- varStatus 배열의 인덱스를 꺼내서 쓰고 싶으면 선언해야 하는 키워드. 이름은 아무렇게나 짓기 --%> 
		인덱스 : ${vs.index}, 순번 : ${vs.count}, 배열요소 : ${menu}<br>    <%-- .index/.count는 정해진 속성 --%>
	</c:forEach>
	
	<hr>
	
	<%-- 5. 리스트 --%>
	<% 
		List<String> seasons = Arrays.asList("봄", "여름", "가을", "겨울");  // list 만들때 add없이 배열 호다닥 만드는 방법 
		pageContext.setAttribute("seasons", seasons);
	%>
	
	<c:forEach var="season" items="${seasons}" varStatus="ss">
		인덱스 : ${ss.index}, 순번 : ${ss.count}, 배열요소 : ${season}<br>
	</c:forEach>
	
	<%-- 6. Map (반복이 필요한 건 아님) --%>
	<% 
		Map<String, Integer> map = new HashMap<>();                  // <> generic은 참조타입만 가능
		map.put("begin", 1);   // 1번부터 10번가지 목록을 가져오시오
		map.put("end", 10);
		pageContext.setAttribute("map", map);
	%>
	${map.begin} ~ ${map.end}<br>      <%-- map을 EL에서 사용하는 방식 --%>
	
	<%-- 7. 객체 (반복이 필요한 건 아님) --%>
	<%
		Board board = new Board();
		board.setBoardNo(1);
		board.setTitle("도대체 언제까지 수업을");
		board.setHit(100);
		pageContext.setAttribute("board", board);
	%>
	
	${board.boardNo}, ${board.title}, ${board.hit}<br>   <!-- 이렇게만 적어도 얘가 -->
	${board.getBoardNo()}, ${board.getTitle()}, ${board.getHit()}<br>  <!-- 겟을 알아서 부른다. -->
	
	<%-- 
			${board.title}은 ${board.getTitle()}을 자동으로 호출한다.
			 
			(따라서 bean을 만들 때에는 반드시 getter/setter 생성이 필수!)
	--%>
	
	<%-- 문제. 임의의 Board 객체를 3개 저장한 리스트 만들기 --%>
	<% 
		//Board board2 = new Board();
		List<Board> boards = new ArrayList<>();
		boards.add(new Board(100, "질문입니다", 2));
		boards.add(new Board(200, "  [Re]저도 궁금합니다", 2));
		boards.add(new Board(300, "답변입니다", 12));	
		pageContext.setAttribute("boards", boards);
		
	%>
	<table border="1">
		<thead>
			<tr>
				<td>순번</td>
				<td>게시글 번호</td>
				<td>제목</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boards}" varStatus="vs">  <%-- 객체 하나가 board인 것 --%>
				<tr>
					<td>${vs.count}</td>
					<td>${board.boardNo}</td>  <%-- 필드이름 불러주면 자동으로 getter가 호출 --%>
					<td>${board.title}</td>
					<td>${board.hit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>