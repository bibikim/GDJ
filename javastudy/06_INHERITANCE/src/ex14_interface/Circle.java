package ex14_interface;

public class Circle implements Shape {			// extends가 아니라 implements
												// 상속받는다가 아니라 구현한다. 같은 이야기임
												// 인터페이스 구현하라 = 상속받아라

	private double radius;
	
	// source메뉴에서 생성자 
	public Circle(double radius) {
		super();
		this.radius = radius;
	}


	@Override								// ctrl space 로 오버라이드 자동완성
	public double getArea() {
		// TODO Auto-generated method stub
		return PI * Math.pow(radius, 2); 				// Shape의 PI값으로 선언
	}
	
	
}
