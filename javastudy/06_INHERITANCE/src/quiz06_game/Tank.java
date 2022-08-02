package quiz06_game;

public class Tank extends GameUnit {

	// 생성자
	public Tank(String name) {		// GameUnit unit1 = new Tank("탱크"); 탱크라는 이름만 받아오면 됨
		super(name, 100, 10);		// 슈퍼클래스인 GameUnit을 불러오려고 super, 이름만 받아오고 에너지와 공격력을 넣어준다.
	}

	@Override
	public void attack(GameUnit unit) {
		
			// 내 에너지 : getEnergy(), this.getEnergy()
			// 내 공격력 : getPower(), this.getPower()  / this 현재 객체를 말하는 참조값
			// 내 이름   : getName(), this.getName()
		
			// 상대 에너지 : unit.getEnergy()
			// 상대 공격력 : unit.getPower()
			// 상대 이름   : unit.getName()
		
		
			// 10% 확률로 KO시킬 수 있다.
		if(Math.random() < 0.1) {
			unit.setEnergy(0);
			unit.setAlive(false);    // K.O
			System.out.println(unit.getName() + "를 한 방에 죽였다.");
		} else {
			int unitEnergy = unit.getEnergy() - getPower() < 0 ? 0 : unit.getEnergy() - getPower();
			// 상대 때리고 났더니 상대 에너지가 -값이 되면 0, 아니면 걍 상대에너지 - 내파워
			unit.setEnergy(unitEnergy);   // 공격받은 후의 상대에너지
			unit.setAlive(unitEnergy > 0);	
			System.out.println(unit.getName() + "의 남은 에너지 " + unit.getEnergy());
		}
		
		
		
		
		
	}
	
	
	
}
