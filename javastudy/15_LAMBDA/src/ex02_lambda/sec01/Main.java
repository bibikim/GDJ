package ex02_lambda.sec01;


/*
	람다식
	
	1. 익명 구현 객체를 생성하기 위한 표현식
	2. 이름이 없는 익명 함수를 사용함
	3. 실행(런타임)할 때 익명 구현 객체가 만들어지면서 동작함.
 	4. 형식                           => @Override 부분의 형식. 내부가 실행 되면 (new Runnable)이 익명 구현개체가 알아서 만들어지며 동작
 		(매개변수) -> { 메소드 본문 }
 	5. 예시
 		1) (int a) -> { System.out.println(a); }    // 정수 a를 출력하겠다.
 		2) (a) -> { System.out.println(a); }  	 매개변수의 타입은 작성하지 않는다. 타입은 관심 없다(무엇이든 전달할 수 있다. 모든지 저장할 수 있는 형태).
 		3) a -> System.out.println(a); 			 매개변수가 하나이거나, 메소드 본문이 하나이면 중괄호를 작성하지 않는다(생략). 고로 2)와 3)은 같은 코드
 		4) () -> System.out.println("Hello");    매개변수가 없으면 빈 괄호를 작성한다.  ex) @Override  public void run() { System.out.println("Hello"); }
 		5) (a, b) -> {
 			System.out.println( a + b );
 			return a + b;						 반환타입의 명시는 없다(하지 않는다). -> 매개변수도 모든지 전달할 수 있기 대문에. 정수가 오면 정수 반환, 실수가 오면 실수 반환
		  }
		6) (a, b) -> a + b ;         			 실행문이 return만 있는 경우 return을 작성하지 않는다.(return a+b; X) 실행문 하나이므로 중괄호 생략, return도 사라짐.
*/



public class Main {

	// ex01.sec01의 Main 과 같은 결과가 나옴!!!
	public static void main(String[] args) {
		
		// ex01.sec01의 Main의 메소드 람다식으로 바꿔보기 => 우리가 구현해야 하는 메소드만(여기선 run()) 람다로 바꾸기
		// run() 메소드만 람다식으로 바꾸면, Runnable 익명 구현 객체(new Runnable)는 실행할 때 만들어진다.
		Thread thread = new Thread(() -> System.out.println("hello world"));   // 매개변수 없으니까 (), 실행문 하나니까 { } 생략!
		thread.start();
	}
}
