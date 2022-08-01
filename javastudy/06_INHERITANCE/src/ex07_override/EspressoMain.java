package ex07_override;

public class EspressoMain {

	public static void main(String[] args) {
	
		Espresso espresso = new Espresso();
		espresso.taste();						//커피의 taste가 아니라 에프의 taste가 자동완성
		
		Americano americano = new Americano();
		americano.taste();
		
		CafeLatte cafelatte = new CafeLatte();
		cafelatte.taste();

	}

}
