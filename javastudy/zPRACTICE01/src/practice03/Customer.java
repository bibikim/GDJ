package practice03;

public class Customer {

	// 필드
	private int money; 	// 가진 돈
	private int cnt;	// 구매한 빵의 갯수
	
	// 생성자// customer는 main에 보면 돈만 있으니까 생성도 money만 해주면 됨
	public Customer(int money) {
		this.money = money;
	}
	
	// 구매 (구매 후 출력)						 buyMoney 구매하려고 내놓은 돈
	// Bakery에서 판매한 빵을 가질 수 있다. (Bakery의 sell()메소드 사용)                    판매는 구매에 속해있게끔
	public void buy(Bakery bakery, int buyMoney) throws RuntimeException {	 
		// 어떤 베이커리를 가겠다, 얼마의 돈을 내겠다
		
		// sell에서 던져진 예외를 1.try-catch 할건지, 2. 다시 throw 할건지 두 가지 선택 가능 
		// 2번을 선택한 경우, main에 customer.buy로 예외가 또 던져진다.
		
		// 구매 불가
		if(money - buyMoney < 0) {
			throw new RuntimeException("구매 불가");
		}
		
		BreadAndChange bnc = bakery.sell(buyMoney);		// sell의 호출 결과는 빵과 잔돈
		// 빵과 잔돈이 넘어오는 것 = 내가 돈을 주고 사니까
			
		cnt += bnc.getBread();
		money += bnc.getChange();
		
		money -= buyMoney;
		
		System.out.println("구매한빵 " + cnt + "개, 남은돈 " + money + "원");
		
	
		
		
	}

	
	
	
	
	
}
