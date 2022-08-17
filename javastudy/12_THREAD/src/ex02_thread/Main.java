package ex02_thread;

public class Main {

	public static void main(String[] args) {
		
		// 스레드 우선순위
		System.out.println("가장 높은 우선순위: " + Thread.MAX_PRIORITY);
		System.out.println("가장 낮은 우선순위: " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위: " + Thread.NORM_PRIORITY);
	
		
		// 스레드 2개(s1, s2)
		Soldier s1 = new Soldier("서대위", new Gun(6));
		Soldier s2 = new Soldier("한상병", new Gun(9));
		
		// 각 스레드의 우선순위
		System.out.println("s1 우선순위: " + s1.getPriority());
		System.out.println("s2 우선순위: " + s2.getPriority());
		
		// 우선순위가 높은 스레드를 (최대한) 먼저 실행 - 무조건 먼저 실행하진 않음... 때에 따라 다름
		// 우선순위 조정
		s1.setPriority(Thread.MIN_PRIORITY);	 // 가장 낮은 우선순위
		s2.setPriority(Thread.MAX_PRIORITY);	// 가장 높은 우선순위.  s2가 먼저 총을 쏠 것으로 예상할 수 있다.(100%는 아님)
		
		// 스레드 실행
		s1.start();
		s2.start();    // run()이 아닌 start()로 동작~
		
		
	}

}
