package ex08_override;

public class Shape {

	private String type; 	// 도형의 종류

	public Shape(String type) {			
		// super();	 신경 안 써도 됨
		this.type = type;
	}
	
	public double getArea() {	// circle에서 getArea를 오버라이드 시킨거 -> Shape shape = new Circle(); 부모타입으로 자식 저장
								//             ㄴ호출용도로 사용된 것(실제 사용X) 	 Shape.getArea();
		return 0;				// 도형의 넓이 현재x = 0
	}
	public void info() {
		System.out.println("도형의 종류 : " + type);
	}
	
	
	
}


// shape[] s = new Shape[10];
// s[0] = new Circle();
// s[1] = new Rectangle();  자식을 부모에 저장 가능
// 업캐스팅이 아니면 저장 불가능