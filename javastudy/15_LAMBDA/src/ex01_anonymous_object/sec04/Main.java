package ex01_anonymous_object.sec04;

public class Main {

	// 자동차가 올 때마다 임시객체를 만들어서 사용하고 빠이하고, 또 만들어서 사용하고 빠이하고. 메모리 관리에 좋음
	public static void main(String[] args) {
		
		Soil soil = new Soil();
		
		// 기름을 팔고 싶다
		// soil에 자동차 하나가 들어왔다
		soil.sellOil(new Car() {
			@Override
			public void addOil() {
				int oil = 30;
				soil.setTotalOil(soil.getTotalOil() - oil);   // 주유소에 남은 오일 : totalOlil -= oil;
				soil.setEarning(soil.getEarning() + oil * soil.getPayPerLiter());  // 얼마 벌었냐 -> 
				System.out.println("감사합니다 카이옌~");
			}
		});
		
		
		// 기름을 팔고 싶다
		// soil에 자동차 하나가 들어왔다
		soil.sellOil(new Car() {
			@Override
			public void addOil() {
				int oil = 50;
				soil.setTotalOil(soil.getTotalOil() - oil);   // totalOlil -= oil;
				soil.setEarning(soil.getEarning() + oil * soil.getPayPerLiter());  // 얼마 벌었냐 -> 원래 있던 돈에 + 판 oil리터 * 리터당가격
				System.out.println("감사합니다 제네시스g90~");
			}
		});
		
		System.out.println(soil.getTotalOil());
		System.out.println(soil.getEarning());

	}

}
