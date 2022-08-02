package quiz06_game;

public class Marine extends GameUnit{

	public Marine(String name) {		
		super(name, 50, 5);
	}

	@Override
	public void attack(GameUnit unit) {
		
		// 40% 확률로 KO시킬 수 있다.
		if(Math.random() < 0.4) {
			unit.setEnergy(0);
			unit.setAlive(false);    // K.O
			System.out.println(unit.getName() + "를 한 방에 죽였다.");
		} else {
			int unitEnergy = unit.getEnergy() - getPower() < 0 ? 0 : unit.getEnergy() - getPower();
			// 상대 때리고 났더니 상대 에너지가 -값이 되면 0, 아니면 걍 상대에너지 - 내파워
			unit.setEnergy(unitEnergy);   // 공격받은 후의 상대에너지(상대 에너지 - 내 공격력)
			unit.setAlive(unitEnergy > 0);	
			System.out.println(unit.getName() + "의 남은 에너지 " + unit.getEnergy());
		}
		
		
		
		
	}
	
	
	
}
