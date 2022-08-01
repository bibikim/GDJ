package quiz04_cart;

public class Customer {

	// 필드
	private int money;
	private int bonusPoint;
	private int total;	// 현재 0
	private Product[] cart = new Product[10];	// 고객이 제품을 10개 담을 수 있는 카트를 가진다
	private int idx;	// 현재 0(초기화하면 0이닝깡) 카트에 담은 물건의 갯수. cart배열의 인덱스이자 카트에 담을 수 있는 물건의 갯수와 일치
	
	// 생성자 생략
	// 따라서 new Customer() 가능
	
	// 메소드
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;					// 실제로 사용할 건 setMoney만~
	}

	public int getBonusPoint() {
		return bonusPoint;
	}

	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}
	
	// buy()메소드
	public void buy(Product product) {			// 업캐스팅 사용
		int price = product.getPrice();			// product.getPrice() = 제품가격
		// 가진 돈보다 비싼 물건은 못 산다.
		if(money < price) {
			System.out.println("돈이 " + (price - money) + "원 부족합니다.");
			return;			// 더 이상 진행 못하게 막아준다
		} 
		// 카트가 가득 차면 물건을 더 이상 못 산다.
		if(idx == cart.length) {
			System.out.println("카트가 가득 찼습니다.");
			return;
		}
		// 구매
		cart[idx++] = product; // 카트 0번에 넣어주고 인덱스는 1씩 는다. 카트에 product이 들어간다.
							   // buy할 때마다 카트[0]에 상품 넣으면 =1(상품 하나 구매 완), 카트[1]에 상품 넣으면 =2(상품 두개 구매 완)
		money -= price; 	// 제품의 가격만큼 줄어드는 고객의 주머니
		total += price;
		bonusPoint += price * 0.1;
	}
	
		//receipt() 메소드
		public void receipt() {
			System.out.println();
			System.out.println("=========영수증========");
			// 물건을 안 샀다.
			if(idx == 0) {
				System.out.println("구매한 물건이 없습니다.");
				return;
			}
			// 구매 총액 구하기 및 출력
			for(int i = 0; i < idx; i++) {		// idx만큼 구매한 상태. i
				Product product = cart[i]; 		// 카트에 있는 물건 하나 꺼내면 그게 product
				System.out.println(product.getName() + "  " + product.getPrice() + "원");
			}
			System.out.println("-----------------------");
			System.out.println("구매총액 " + total + "원");
			System.out.println("보너스 " + bonusPoint + "원");
			System.out.println("남은돈 " + money + "원"); 	// 구매할 때마다 값을 감소시켰기 때문에 남은돈은 그냥 money로 하면 됨
		}
		
		
		
		
		
	}
	
	
