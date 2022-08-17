package ex05_synchronized;

public class ToiletRobot extends Thread {

	private Cleaner cleaner;
	
	
	
	public ToiletRobot(Cleaner cleaner) {
		super();
		this.cleaner = cleaner;
	}



	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
		cleaner.toiletCleaning();  // 클리너의 화장실 청소기능을 사용하겠다.
		}
	}
}
