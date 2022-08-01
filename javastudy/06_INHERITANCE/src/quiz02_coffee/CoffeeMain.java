package quiz02_coffee;

public class CoffeeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Espresso espresso = new Espresso("케냐", 50);
		espresso.info(); 	// 케냐 원두, 물 50
		
		Americano americano = new Americano(espresso, 2, "아이스");
		americano.info(); 	// 케냐 원두, 2샷, 아아
		
		
		
	}

}
