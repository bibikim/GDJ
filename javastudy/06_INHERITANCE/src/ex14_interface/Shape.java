package ex14_interface;

public interface Shape {             // interface 색은 grey 
	
	
	//private String type;  -> 못 씀
	
	
	// final 상수
	public final static double PI = 3.141592;
	
	// 추상 메소드
	public double getArea();      // abstract 생략 가능해서 생략함
	
	// default 메소드(본문이 있는 메소드)
	public default void message() {
		System.out.println("나는 도형이지롱.");
	}
	
	
	
}
