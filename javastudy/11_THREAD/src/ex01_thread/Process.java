package ex01_thread;

// 스레드(Thread)
// 1. 세부 실행 단위
// 2. 자바 실행의 기본 단위(main 스레드: 메인만 실행되는 스레드)
// 3. main 스레드 이외의 스레드 추가 가능(메인만 실행되는 게 아니라 다른 것도 실행 되게 할 수 있다)

// 스레드 생성
// 1. Thread 클래스를 상속받는 방법
// 2. Runnable 인터페이스를 구현하는 방법

// Thread 클래스 상속
// 1. extends Thread
// 2. Thread 클래스의 public void run() 메소드를 @Override해서 (내부에) 수행할 작업 작성

// 스레드 실행
// 1. start() 메소드를 호출
// 2. start() 메소드를 호출하면 run() 메소드에 오버라이드 한 내용이 실행됨
// ㄴ 런을 오버라이드 했는데 런을 호출하진 않음ㅇㅇ start를 돌리면 런을 알아서 호출하는 방식

// thread를 만들었다는 것은 메인 사람 말고 새로운 사람이 생긴거라 생각하면 됨. 1명이서 하던걸 2명이서
// main은 시작해라 start() 알려주고 종료하고 끝. (main시작-> main: start해~ 난 종료할거임 -> main종료 -> 연산 작업 실행(thread))
// 메인이 끝나든 말든 상관 없이 쭉 실행함. 메인과는 상관 없는 실행 체계를 가지기 때문에.
// 프로세스 내부처리는 다른 애가!
// 원래는 main이 혼자 다~ 하던거..


public class Process extends Thread { // 메인과 별개의 실행 체계를 가짐

	private String name;

	public Process(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(5000); 	// 5초 .sleep() 일시중지. long millis(1/1000)초동안 실행하지 말아라. 
			System.out.println(name + " 작업 실행");
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

}
