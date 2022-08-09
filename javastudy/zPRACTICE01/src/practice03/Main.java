package practice03;

public class Main {

	public static void main(String[] args) {
		
		Bakery paris = new Bakery(500, 100, 10000);		// 빵1개500원, 100개, 자본금10000원
		Bakery tour = new Bakery(1000, 50, 10000);		// 빵1개1000원, 50개, 자본금10000원
		
		Customer customer = new Customer(20000);	// 20000원 가진 고객
		
		// sell -> buy에서 넘어온 예외를 여기서 try-catch해도, 다시 던져서 JVM에 맡겨도 된다.
		
		try {
			//customer.buy(paris, 30000);		// 구매불가 예외 -> 밑에 buy메소드도 작동x
			//customer.buy(tour, 500);			// 판매불가 예외 발생
			
			customer.buy(paris, 10000); 	//구매한빵 20개, 남은돈10000원
			customer.buy(tour, 5000);		//구매한빵 25개, 남은돈5000원
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		
			paris.info();	// 빵80개, 자본금20000원
			tour.info();	// 빵45개, 자본금15000원
	
	}	

	
}
