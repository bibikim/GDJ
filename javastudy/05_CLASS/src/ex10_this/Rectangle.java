package ex10_this;

public class Rectangle {
	
	// 필드
	private int width;
	private int height;
	
	// 생성자
	public Rectangle(int width, int height) {
		this.width = width;							// this를 이용해서 너비와 높이를 필드값으로 보내준다. 그러면 필드값과 매개변수와의 구분이 가능
		this.height = height;
	}
				
	public Rectangle(int n) {
		this(n, n);									// 여기서 this는 다른 생성자. 위에 생성자인 public Rectangle(int width, int height) <- 얘를 호출한거임.
		//this.width = n;							// 인수 2개인 다른 생성자를 호출한다.
		//this.height = n;
	}
		
	// 메소드
	public int getArea() {
		return width * height;
	}
	
	public int getCircumference() {
		return 2 * (width + height);
	}
	
	
	
	
	
	
}
