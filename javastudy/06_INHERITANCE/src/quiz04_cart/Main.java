package quiz04_cart;

public class Main {

	public static void main(String[] args) {
		
		// Snack, Meat, Milk 모두 Product
		// 모든 Product는 이름(name)과 가격(price)만 가진다.
		// 고객은 모든 product를 10개 담을 수 있는 cart를 가진다.
		// 고객은 돈(money)와 보너스포인트(bonusPoint, 구매액의 10%)를 가진다.
			
		
		Customer customer = new Customer();			// 아직 돈이 없는 고객
		customer.setMoney(10000);					// 10000원이 생긴 고객
		customer.buy(new Snack("홈런볼", 1500)); 	// 1500원짜리 홈런볼 구매(카트에 담는다.)
		customer.buy(new Meat("한우", 5000));		// 5000원짜리 한우 구매(카트에 담는다.)
		customer.buy(new Milk("서울우유", 2500));	// 2500원짜리 우유 구매
		customer.buy(new Meat("불고기", 5000));		// 구매 불가 ( 돈 부족)
		customer.receipt();							// 영수증을 본다.
		
		// 상품 위치 위아래 바꿔가면서 보면 부족한 금액이 또 다르게 나온다!
		
		
		
		/*
		 홈런볼    1500원
		 한우	    500원
		 서울우유  2500원
		 -----------------
		 구매총액  9000원
		 보너스 	900원
		 남은돈	   1000원	
		 */
		
	}

}
