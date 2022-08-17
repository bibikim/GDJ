package ex03_runnable;

// 스레드 생성 방법
// 1. Runnable 인터페이스 구현
// 2. public void run() 오버라이드

public class WashRobot extends Robot implements Runnable {  // 다중 상속이 안되니까 인터페이스 구현을 통해 thread 생성!
	
	 private String name;
	
	public WashRobot(String name) {
	super();
	this.name = name;
}

	@Override
	public void run() {
		System.out.println(name + " 빨래중");
	}
	
	
}
