package practice03;

public class Bakery {

	// 필드
	private int price;	// 빵 가격
	private int cnt;	// 갯수
	private int money;	// 자본금
	
	// 생성자
	public Bakery(int price, int cnt, int money) {
		super();
		this.price = price;
		this.cnt = cnt;
		this.money = money;
	}
	

	
	// 판매.		들어오는 매개변수; 손님돈 , 나가는 반환타입 잔돈/빵
	public BreadAndChange sell(int custMoney) throws RuntimeException {	
										   // ----------------------- 생략 가능
		// 판매불가
		if(custMoney < price) {
			throw new RuntimeException("판매불가");	 // 던져진 예외는 구매메소드로 던져짐
		}
		
		
		// 판매할 수 있는 빵은 몇 개?
		int sellCnt = custMoney / price;	// 몫이 판매할 수 있는 빵, 나머지는 잔돈
		// 잔돈은 얼마?
		int change = custMoney % price;
		
		// 매장 내부의 변화 처리
		cnt -= sellCnt;		// 빵 갯수는 판매하는 만큼 줄어듦
		this.money += (custMoney - change);		// 들어오는 돈 - 줘야되는 잔돈 = (this.)money 매장의 현금통
		
		
		// 고객에게 되돌려 줄 빵과 잔돈
		return new BreadAndChange(sellCnt, change);
		
	}

	// 정보 확인
	public void info() {
		System.out.println("빵 " + cnt + "개, " + money + "원");
	}
	

}
