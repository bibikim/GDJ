package ex13_abstract;

public class Circle extends Shape {

	private double radius;    // 필드 생성

	public Circle(String type, double radius) {			// 생성자  source - generate constructor using field
		super(type);
		this.radius = radius;
	}
	
	
	// Shape 클래스는 추상 클래스이므로, 반드시 double getArea() 메소드를 오버라이드 해야 함.
	//                                                    ㄴ 추상 메소드만 Override
	// 4. 추상 클래스를 상속 받는 클래스는 "반드시" "모든" 추상 메소드를 오버라이드 해야 함. --> 추상 클래스의 특성 중 하나
	@Override
	public double getArea() {		// Shape클래스의 getArea는 값이 0이니까 데려올 수 없어서 오버라이드 해서 새로 만들기
		return Math.PI * Math.pow(radius, 2);
	}
	
	
	
	
	
}
