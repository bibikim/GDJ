package ex04_has_a_inherit;

public class SoldierMain {

	public static void main(String[] args) {
		
		
		Soldier soldier = new Soldier();
		soldier.reload(10);				// 총알 10개 넣고 1발 쏜거
		soldier.shoot();
		
		System.out.println(soldier.getBullet());	// 결과 9
	}

		// 총은 솔져에 만들어져 있고 (총에 상속되었으니) 실행이 되는 것



}
