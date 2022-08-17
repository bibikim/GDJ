package ex05_synchronized;

// Cleaner는 1개 (공유 자원)

// Cleaner를 사용할 Robot 2개 (스레드)

// Robot이 Cleaner를 자지하기 위한 쟁탈전이 벌어짐.(하나의 자원을 공유하는 과정을 보려는 것)

// synchronized
// 1. 스레드의 충돌을 방지하기 위해서 한 번에 한 스레드만 접근할 수 있도록 허용
// 2. 싱크 처리를 하면 공유 자원의 일관성을 보장할 수 있다
// 3. 한 번에 한 스레드만 접근할 수 있는 영역을 임계 영역(Critical Section)이라고 함

// Object 클래스의 wait() 메소드
// 1. 스레드가 동작하지 않고 대기 상태가 됨
// 2. 다른 스레드가 깨울 때까지 대기함

// Object 클래스의 notify() 메소드
// 1. 다른 스레드에를 깨움
// 2. 스레드가 여러 개인 경우에는 notifyAll() 메소드로 모든 스레드를 깨움


public class Cleaner {

	public synchronized void toiletCleaning() {		
		System.out.println("화장실 청소");
		try {
			notify(); // 나 화장실 청소 끝났다고 알리고
			wait();  // 잠깐 쉼. 이 두 메소드를 스레드끼리 왔다갔다 번갈아가면서 진행
		} catch (InterruptedException e) {  // wait() 메소드는 예외처리exception 필요
			e.printStackTrace();
		}
	}
	
	public synchronized void roomCleaning() {
		System.out.println("방 청소");
		try {
			notify();	// 나 방 청소 끝났다고 알리고(다른 스레드를 깨우는거징)
			wait();		// 본인은 대기(쉼).
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
