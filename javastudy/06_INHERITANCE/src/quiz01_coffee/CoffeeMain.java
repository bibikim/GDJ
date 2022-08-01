package quiz01_coffee;

public class CoffeeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Espresso espresso = new Espresso("케냐", 50);
		espresso.info(); 	// 케냐 원두, 물 50
		
		Americano americano = new Americano("케냐", 300, "아이스");
		americano.info(); 	// 케냐 원두, 물 300, 아아
		
		
		
	}

}
