package ex04_method;

public class VendingMachine {

	
	String getCoffee(int money, int button) {   //★★ 이게중요
		
		String[] menu = {"아메리카노", "카페라떼"};
		return menu[button - 1]	+ " " + (money / 1000) + "잔";	
			//  ㄴ 커피의 종류				ㄴ 잔 수
			// [0] = 아메리카노
			// [1] = 카페라떼
		
		//String[] menu = {"", "아메리카노", "카페라떼"};
		//return menu[button]	+ " " + (money / 1000) + "잔";
		
		
		
		// 벤딩머신의 메인을 꼭 열어놓고 실행해야 함
	}
	
	
	
	
	
	
	
	
}
