package ex02_thread;

public class Soldier extends Thread {

	private String name;
	private Gun gun;
	
	public Soldier(String name, Gun gun) {
		super();
		this.name = name;
		this.gun = gun;
	}
	
	public void shoot() {
		System.out.print(name + " : ");
		gun.shoot(); // 총을 쏨
	}
	
	@Override
	public void run() {
		
		try {
			// 1초에 한 발씩 쏘기 
			while(gun.getBullet() != 0) { // 총알이 몇 발 있는지 알기 위해 bullet의 게터세터를 만들어줘라.
				shoot(); // 위에 public void shoot() 메소드 호출~
				Thread.sleep(1000);
			}
		} catch(InterruptedException e) { // 작업하다가 태클이 걸려서 
			e.printStackTrace();
		}
		
		
	}
	
	// thread는 public void run() 만들어줘야하고
	// 동작은 start()로 해줘라! 그것이,,, 약속,,!
	
}
