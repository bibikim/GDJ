package ex10_downcasting;

public class Main {

	public static void main(String[] args) {
		
		// 클래스타입 : Person
		// 객체(인스턴스) : p
		Person p = new Student(); 	// 업캐스팅으로 학생 만들기				Student s = new Alba(); 이런 업캐스팅도 가능(알바 만들기)
		
		// instanceof 연산자
		// 특정 인스턴스가 어떤 클래스타입인지 점검하는 연산자
		// 해당 클래스타입이면 true, 아니면 false 반환
		
		System.out.println(p instanceof Person);
		System.out.println(p instanceof Student);
		System.out.println(p instanceof Alba);				// 전부 true. p는 실제로는 Alba이지만 상속관계이기 때문에 person이기도, student이기도 하다
															// Person p = new Student(); 의 경우에는 instanceof Alba 하면 false 출력

		//p.         if 밖에서 점찍어보면 study() 안 보임.
		
		
		// p가 Student타입의 인스턴스이면 study() 메소드를 호출할 수 있다.
		if(p instanceof Student) {	
			((Student) p).study(); 			// *다운캐스팅.  p를 스튜던트 타입으로 바꾸고 study를 호출해라.	 	
											// 원래 p는 Person타입 p. 찍으면 eat()도 study()도 자동완성으로 보인다. if ~ Student 조건때문에!!
		}
		
		// p가 Alba타입의 인스턴스이면 work() 메소드를 호출할 수 있다.
		if(p instanceof Alba) {
			((Alba) p).work();				// Person p = new Student(); 일 땐 공부한다가 호출 X, new Alba(); 이면 일한다까지 호출 O
		}
		
		
		
		
		
		
		
	}

}
