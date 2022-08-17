package ex01_thread;

public class Main {

	public static void main(String[] args) {

		System.out.println("main 시작");
		// main은 시작하라고 알려주기만 함.
		
		Process process1 = new Process("연산");
		process1.start(); // Process 클래스의 오버라이드된 run() 메소드가 호출된다.
		// Thread가 가지고 있는 start() 메소드를 호출 -> run() 실행. 직접 호출 X (걍.. 약속임!)
		
		Process process2 = new Process("제어");
		process2.start();
		// 1과 2의 동작 순서는 바뀔 수 있음.
		// 2개고 3개고 다 다른 줄기에서 새롭게 시작하는 것. 나뭇가지처럼 메인줄기에서 요쪽 저쪽 잔가지처럼 process는 별개로 실행 
		
		System.out.println("main 종료");
		

	}

}
