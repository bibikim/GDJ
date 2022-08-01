package quiz01_coffee;

public class Americano extends Espresso {

	private String type;	// hot or ice

	public Americano(String origin, int water, String type) {
		super(origin, water);
		this.type = type;
	}
	
	@Override
	public void info() {
		super.info();	// 원두 정보, 물의 양은 여기서 정보가 나옴
		System.out.println(type + " 아메리카노");  // 여기선 타입 정보만 뽑으면 됨
	}

	
	
	
	
	
}
