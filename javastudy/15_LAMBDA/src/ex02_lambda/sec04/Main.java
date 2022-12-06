package ex02_lambda.sec04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	// 자동차가 올 때마다 임시객체를 만들어서 사용하고 빠이하고, 또 만들어서 사용하고 빠이하고. 메모리 관리에 좋음
	public static void main(String[] args) {
		
		Soil soil = new Soil();
		
		// 기름을 팔고 싶다
		// soil에 자동차 하나가 들어왔다
		soil.sellOil(() -> {
				int oil = 30;
				soil.setTotalOil(soil.getTotalOil() - oil);   // 주유소에 남은 오일 : totalOlil -= oil;
				soil.setEarning(soil.getEarning() + oil * soil.getPayPerLiter());  // 얼마 벌었냐 -> 
				System.out.println("감사합니다 카이옌~");
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
	
	
	// 자동차 interface 만들때 람다는 interface Car 하나짜리로 하는 것.. addOil();, drive(); 둘다는 못 함.
	// 이런걸 Functional Interface라 말함. 4가지가 만들어져 있음..
	// 인터페이스에 추상메소드 1개 있을 때! 그때 갸는 펑셔널인터테이스라고 부를 수 있음.  -> 펑셔널인퍼테이스들은 람다로 만들 수 있다.
	// 펑셔널인터페이스당 추상메소드는 하나. 그 하나만 람다로 바꿔주면 됨.
	// 주기만 하고 받아오는 건 없는 인터페이스를 Consumer라고 함. 컨슈머가 보이면 람다로 해줘야되는구나~하고 알아야됨.

	List<String> list = Arrays.asList("a, b, c");
	//Stream<String> stream = list.forEach((a) -> System.out.println());
	
}
