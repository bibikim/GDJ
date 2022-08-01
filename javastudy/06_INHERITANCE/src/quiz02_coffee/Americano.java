package quiz02_coffee;

public class Americano {		// 에프를 상속받지 않고 포함한 상태로 보고 코드를 짤 때.

	private Espresso espresso;
	private int shot;
	private String type;	// hot or ice
	
	public Americano(Espresso espresso, int shot, String type) {
		super();
		this.espresso = espresso;
		this.shot = shot;
		this.type = type;
	}

	public void info() {
		espresso.info();
		System.out.println(shot + "샷");
		System.out.println(type + "아메리카노");
	}
	
}
