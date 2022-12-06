package ex01_anonymous_object.sec03;

public class Main {

	public static void main(String[] args) {
		
		Soil soil = new Soil();
		soil.sellOil();  // 기름 팔겠다 안에서 선언한 지역변수의 addOil메소드를 통해서 기름을 팔 수 있음.
		
		soil.sellOil();  // 똑같은 차에 두 번 판거임. 두 번 호출했으니까 
		
		System.out.println(soil.getTotalOil());
		System.out.println(soil.getEarning());

	}

}
