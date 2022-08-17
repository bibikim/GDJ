package ex04_join;

public class Main {

	public static void main(String[] args) {

		// Calculator를 2개 준비
		// 작업을 반으로 나눠서 진행 (계산하는 사람은 1명, 계산기가 2개 하나 쓰고 끝나면 하나 씀)
		// 스레드 작업 (계산하는 사람도 2명, 계산기도 2명 동시에 진행)
		
		// Calculator가 동시에 연산을 수행하려면 Calculator를 스레드로 처리해야 함.
		
		
		Calculator calc1 = new Calculator(1, 5000);
		Thread thread1 = new Thread(calc1);
		thread1.start(); // 1번째 계산기 동작 시작
		
		Calculator calc2 = new Calculator(5001, 10000);
		Thread thread2 = new Thread(calc2);
		thread2.start(); // 2번째 계산기 동작 시작
		
		// 모든 계산기의 동작이 끝날 때까지 기다린다.
		// join() : 스레드가 종료(die)될 때까지 기다림
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();				// join()이 있기 때문에 isAlive는 둘다 false가 뜬다
		}
		
		System.out.println(thread1.isAlive());
		System.out.println(thread2.isAlive());
		
		System.out.println(calc1.getTotal() + calc2.getTotal());
		// 스레드는 단독으로 동작하니까  thread1,2의 (계산)동작이 끝나든 말든 main은 진행하기 때문에 합계가 0이 나올 수 있음. 기다리지 않으니까
		// ㄱㅖ산은 시켰지만 결과는 받아볼 생각이 없는 코드!
		
		// 스타트를 동작아면 런이 실행, 런 안에 에드가 호출되는 코드를 짜라
		
	}

}
