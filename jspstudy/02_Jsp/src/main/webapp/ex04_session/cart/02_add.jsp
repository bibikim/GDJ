<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 요청 파라미터
	request.setCharacterEncoding("UTF-8");
	String item = request.getParameter("item");
	int amount = Integer.parseInt(request.getParameter("amount")); 
	
	// 제품 + 구매수량 -> Map (장바구니에 담을 Map)     (=> 두개의 정보 하나로 모으기)
	Map<String, Object> product = new HashMap<>();   // key, value   value에 string과 int를 모두 저장하기 위해 object로 잡음
	product.put("item", item);
	product.put("amount", amount); // product이란 이름으로 item과 amount가 묶인 상태!
	// 장바구니는 session에 보관
	
	// session에 장바구니를 cart 속성으로 저장한 상황이다.
	// 1. session에 cart 속성이 있는지 확인한다.
	// 2. cart 속성이 없으면 새로 만들어서 저장한다.	
	// 배열 or 리스트가 cart의 타입이 되어야 함! map하나가 하나의 제품(product), 제품이 여러개! -> Map을 여러개 저장하는 List ==> List<Map>
	List<Map<String, Object>> cart = (List<Map<String, Object>>)session.getAttribute("cart");  //키와 밸류가 string, object인 맵을 리스트에 저장
	// session에서 꺼낸 "cart"는 List의 Map에 담아놓은 카트다
	if(cart == null) {
		cart = new ArrayList<>();  // 카트 새로 만들기
		session.setAttribute("cart", cart);  // 카트를 카트라는 이름으로 session에 올리기
	}
	
	// session의 cart에 product 저장하기
	cart.add(product);  
	// cart가 기존에 존재하던 카트 아니면 새 카트 -> 물건 처음 담는 상황이면 arraylist의 cart, 두번째부터는 세션에서 꺼내서 집어넣는 cart
	
	
%>

<script>
	// product에 들어가있는 아이템 product.get("item")
	alert('<%=product.get("item")%> 제품을 장바구니에 추가했습니다.');
	if(confirm('장바구니를 확인하려면 "확인", 계속 쇼핑하려면 "취소"를 누르세요.')) {
		location.href = '03_cart_list.jsp';
	} else {
		location.href = '01_form.jsp';
	}
</script>


