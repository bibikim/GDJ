<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%--
		EL의 파라미터 처리
		1. EL 내장 객체 param을 사용(param, paramValues)   // 똑같은 입력 요소가 여러 개면 배열로 넘어온다. -> getParmeterValue(핸드폰번호 파라미터 받아오는 예제 참고)
		2. 모든 파라미터는 String이므로 크기 비교를 수행할 수 없음
			예시)
			"10", "5" 중 누가 큰가?   ->   "5"가 크다(문자열의 크기는 사전 편찬 순)  앞에 숫자 하나만 판단...
		3. 파라미터의 크기 비교
			두 파라미터의 차이를 구한 뒤 0보다 큰지 여부를 판단
			두 파라미터의 차이 결과는 숫자가 반환됨
			"10" - "5" == 5
	--%>


	<div>${param.a + param.b}</div>
	<div>${param.a - param.b}</div>
	<div>${param.a * param.b}</div>
	<div>${param.a / param.b}, ${param.a div param.b}</div>
	<div>${param.a % param.b}, ${param.a mod param.b}</div>
	
	<div>${param.a - param.b <  0}, ${param.a - param.b lt 0}</div>
	<div>${param.a - param.b <= 0}, ${param.a - param.b le 0}</div>
	<div>${param.a >  param.b}, ${param.a gt param.b}</div>
	<div>${param.a >= param.b}, ${param.a ge param.b}</div>
	<div>${param.a == param.b}, ${param.a eq param.b}</div>
	<div>${param.a != param.b}, ${param.a ne param.b}</div>

	<div>${param.a == 7 && param.b == 2}, ${param.a eq 7 and param.b eq 3}</div>
	<div>${param.a == 7 || param.b == 2}, ${param.a eq 7 or  param.b eq 3}</div>
	<div>${!(param.a == 7)}, ${not(param.a eq 7)}</div>
	
	<div>${param.a == 7 ? "a는 7이다" : 'a는 7이 아니다'}</div>  <!--  "", '' 둘다 사용 가능 -->
	
	<%-- 
		param : EL 전용 내장 객체    -> 속성은 그냥 부르고 파라미터는 param을 붙여줘야 한다. 
	
		name="a"  ==  ${param.a}
	
	--%>

	
	
</body>
</html>