package ex08_override;

public class Square extends Rectangle{

	// public Square(String type, double width, double height) {	정사각형의 경우 오류의 위험성 있음. 3, 4를 줘도 유라가 안 뜸
	//	super(type, width, height);									글서 밑에 식처럼.
	//}

	// 이미 Rectangle(부모클래스)이 너비높이 필드 갖고 있기 때문에 필드 필요 없음
	// rectangle 생성자를 square가 호출 해야 함.
	// 필드가 없을 때 생성자를 만들어주는 키
	// source - generate constructor from supuerclass
	
	// new Square -> new Square("정사각형", n); 하나만 값을 주고 나눠 써라
	
	public Square(String type, double width) { // 너비만 가져와서 그 너비를 슈퍼클래스의 너비와 높이에 사용해라
		super(type, width, width);
	
	
	}
}
