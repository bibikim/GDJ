package ex13_abstract;

// 추상 메소드
// 1. 본문이 없는 메소드
// 2. 호출용으로 사용되는 메소드 (실행되지 않고 오직 호출만)
// 3. 중괄호{} 자체를 없애고 세미콜론; 을 추가함
// 4. public abstract 또는 abstract public

// 추상 클래스
// 1. 추상 메소드가 1개 이상 존재하는 클래스. 하나라도 있으면 추상 클래스가 됨
// 2. public abstract class로 처리
// 3. 본문이 없는 메소드를 포함하기 때문에 객체 생성이 불허됨(만들 수 없게 막아둠)
// 4. 추상 클래스를 상속 받는 클래스는 "반드시" "모든" 추상 메소드를 오버라이드 해야 함.

public abstract class Shape {			// 맨 밑에 추상 메소드가 하나 있기 때문에 class 앞에 abstract 붙여준다.

	private String type;

	public Shape(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	//public double getArea() {	// Shape을 상속 받는 객체들이 호출할 때 사용하는 메소드
								// 사용되진 않는다. 오직 호출만. -> 추상 메소드로 바꿔준다.(본문이 없는 메소드)
	//	return 0;				// 결정된 도형이 없으니까 넓이값 0
	//}							// 호출때문에 굳이 본문인 return 0; 을 넣어준 것
	
	
	public abstract double getArea(); // == 호출할 때 쓰일 메소드라는 의미로 '추상메소드' 로 바꿔준 것!
	
	
	
}
