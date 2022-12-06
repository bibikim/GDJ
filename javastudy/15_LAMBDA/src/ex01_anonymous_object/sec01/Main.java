package ex01_anonymous_object.sec01;

public class Main {

	public static void main(String[] args) {
	
		// 인터페이스는 new를 할 수 없다. : 이름이 있는 객체를 만드는 것
		// Runnable runnable : new Runnable();   -> runnable같이 이름을 줘서 저장할 수 없음
		// Thread thread = new Thread(runnable);
		
		// 인터페이스는 new를 할 수 있다. : 이름이 없는 객체를 만드는 것  -> 이름이 없기 때문에 1회용. 익명객체로 만들어 관리
		Thread thread = new Thread(new Runnable() {   // anonymous inner type : 이름없는 객체로는 생성 가능.
									// 이름은 주지 못하고 1회용으로 갖다 쓸 수 있음
			@Override
			public void run() {     
				System.out.println("hello world");
			}
		});
		
		// 스레드 진행   -> start()를 하면 내부에서 run()를 찾아서 코드를 돌린다. 
		thread.start();
		
	}

}
