package quiz06_game;

public class Main {

	public static void main(String[] args) {
		
		GameUnit unit1 = new Tank("탱크");
		GameUnit unit2 = new Marine("마린");
		
		unit1.info(); 	// 탱크 에너지 100, 공격력 10		-> 둘 다 GameUnit에 넣는다
		unit2.info(); 	// 마린 에너리 50 공격력 5
		
		System.out.println("===전투 시작===");
		
		// 공격 차례 정하기
		boolean myTurn = Math.random() < 0.5;  // 절반의 확률로 차례 정하기
	
		// 한 대씩 주고 받기
		// 두 유닛이 모두 살아있으면 계속 싸움
		while(unit1.isAlive() && unit2.isAlive()) {  // 에너지가 0보다 크면 생존
			if(myTurn) {	// while(unit1.getEnergy() > 0 && unit2.getEnergy() > 0
				System.out.println(unit1.getName() + "의 공격!");
				unit1.attack(unit2); 	// unit1이 unit2를 공격한다. (탱크는 10% 확률로 상대를 한번에 죽임)
				//myTurn = false;		// 이제 unit2 니차례다(턴 변경)
			} else {
				System.out.println(unit2.getName() + "의 공격!");
				unit2.attack(unit1); 	// unit1이 unit2를 공격한다. (마린은 40% 확률로 상대를 한번에 죽임)
				//myTurn = true;
			}
				myTurn = !myTurn;		// 공격 차례를 바꿔준다
		}
		// attack 두개 만드는데 확률만 조금 다름
	
		System.out.println("===전투 종료===");
		
		// 승자 확인
		if(unit1.isAlive()) {
			System.out.println(unit1.getName() + "의 승리! 남은 에너지 " + unit1.getEnergy());
		} else {
			System.out.println(unit2.getName() + "의 승리! 남은 에너지 " + unit2.getEnergy());
		}
		
		
		
	}

}
