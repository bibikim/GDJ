package ex04_method;

public class VendingMachineMain {

	public static void main(String[] args) {

		
		VendingMachine machine = new VendingMachine();
		
		// 커피 뽑기 시나리오
		// 한 잔 1000원
		// 종류 : 1(아메리카노), 2(카페라떼)  -> 배열의 인덱스와 결부시켜보기
		
		String coffee1 = machine.getCoffee(1000, 1); 		// 아메리카노 1잔
		String coffee2 = machine.getCoffee(2000, 2);			// 라떼 2잔
		
		System.out.println(coffee1);
		System.out.println(coffee2);
		
		
	
		
		// ㄴ메소드 이름 getCoffee. 겟커피를 호출하면 coffee 1,2가 나오는거. 반환 타입은 string
		// getCoffee로 전달되는 인수는 1000, 1
		// getCoffee(int money, int button)      >> 변수 선언. 이름은 맘대로
	
		
		
		

	}

}
