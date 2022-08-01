package ex08_override;

public class Circle extends Shape {

	private double radius;

	public Circle(String type, double radius) {
		super(type);
		this.radius = radius;
	}
	
	// circle의 넓이를 구하는 건 다시 만들어야됨.
	// shape 슈퍼클래스 걸로 가져오면 안돼. return 0값이기 땜에
	@Override
	public double getArea() {
		return Math.PI * Math.pow(radius, 2);	// radius의 2제곱
	}
	
	// 반지름, 넓이 ^info()^를 다시 불러오기
	@Override
	public void info() {
		// TODO Auto-generated method stub
		super.info();		// super의 info() 호출. 슈퍼의 메소드를 가져다 쓸 경우. 도형의 종류를 출력하기 위해 살려둔다.
		System.out.println("반지름 : " + radius);
		System.out.println("넓이 : " + getArea());		// getArea호출
	}
	
}
