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
<script src="${contextPath}/resources/js/agree.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/agree.css"/>
</head>
<body>

	<div>
	
		<h1>약관 동의하기</h1>
		<form id="frm_agree" action="${contextPath}/user/join/write">   <!-- 어떤 약관을 체크해서 넘겨줘야되는지 알아야 되니까 form 만들어 주기 -->
			
			<div>
				<input type="checkbox" id="check_all" class="blind" name="check_all">
				<label for="check_all" class="lbl_all">모두 동의</label>  
				<!-- 체크된 라벨이라는 의미로 lbl_checked를 클릭할 때마다 줬다 뺐다 (toggle클래스 이용해서)-->
			</div>
			
			<hr>
			
			<div>
				<input type="checkbox" id="service" class="check_one blind">
				<label for="service" class="lbl_one">이용약관 동의(필수)</label>
				<div>
					<textarea>본 약관은 ...</textarea>
				</div>
			</div>
			<div>
				<!-- 필수요소에는 name이 필요 없다  -->
				<input type="checkbox" id="privacy" class="check_one blind">
				<label for="privacy" class="lbl_one">개인정보수집 동의(필수)</label>
				<div>
					<textarea>개인정보보호법에 따라 ...</textarea>
				</div>
			</div>
			<div>
				<!-- checkbox submit의 기준은 name과 value. 두가지가 있어야 함 -->
				<!-- 선택요소는 value를 안 넣으면 체크시 value가 on으로 처리 됨. 넘어왔다/안넘어왔다 -->
				<!-- 체크박스는 체크를 안 하면 넘어가질 않음,, -->
				<!-- name이 없으면 파라미터를 모르고, value의 값을 db에 넣는것 -->
				<input type="checkbox" id="location" class="check_one blind" name="location">
				<label for="location" class="lbl_one">위치정보수집 동의(선택)</label>
				<div>
					<textarea>위치정보 ...</textarea>
				</div>
			</div>
			<div>
				<input type="checkbox" id="promotion" class="check_one blind" name="promotion">
				<label for="promotion" class="lbl_one">마케팅 동의(선택)</label>
				<div>
					<textarea>이벤트 ...</textarea>
				</div>
			</div>
			
			<hr>
			
			<div>
				<input type="button" value="취소" onclick="history.back();">
				<button>다음</button>
			</div>
		
		</form>
	
	</div>

</body>
</html>