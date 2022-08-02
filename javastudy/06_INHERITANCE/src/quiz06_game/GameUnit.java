package quiz06_game;

public abstract class GameUnit {

	private String name;
	private int energy;
	private int power;
	private boolean isAlive;	// boolean alive;로 해도 동일한 Getter, Setter메소드가 생성 된다

	public GameUnit(String name, int energy, int power) {
		super();
		this.name = name;
		this.energy = energy > 0 ? energy : 0;  // 잘못된 에너지(-10)가 들어왔을 때 0으로 초기화하는 과정
		this.power = power;
		this.isAlive = energy > 0;  // true, < 0 false
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public boolean isAlive() {			// boolean타입의 getter는 is라서 isAlive 
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public void info() {
		System.out.println(name + " 에너지 " + energy + " 공격력 " + power);
	}
	
	
	public abstract void attack(GameUnit unit); 	// 추상 메소드
	// 다른 게임유닛을 받아와서 공격!

	
	
	
	
}
