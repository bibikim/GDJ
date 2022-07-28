package ex11_static;

public class MyMathMain {

	public static void main(String[] args) {
		
		/*
		MyMath math1 = new MyMath();
		System.out.println(math1.abs(-5));
		
		MyMath math2 = new MyMath();
		System.out.println(math1.abs(-9));
		
		MyMath math3 = new MyMath();
		System.out.println(math1.abs(21));
		*/

		//------------------------------------------- math1,2,3 각각 불렀을 때 차이가 없다
		//------------------------------------------- math마다 만들지 말고 클래스가 사용하는 메모리영역에 abs를 하나만 만들고 사용하겠다.
		// 객체(math123)를 만들어 사용하는게 아니라 클래스가지고 사용. 누구를 만나도 똑같은 일을 하니까
		// MyMath 클래스에 저장된 abs를 찾아서 호출을 하자 => MyMath.abs();
		// Math를 이용한 객체를 사용하지 않는다. 객체를 만들어 봐야 차이가 없이 똑같아
		// 예를 들어 Math math = new Math();   --> 이걸 쓰지 않는다는 이야기
		
		System.out.println(MyMath.PI);
		System.out.println(MyMath.abs(-5));
		
		// Math. >> Math에 있는건 전부 static 메소드
		
		System.out.println(MyMath.pow(2, 5)); 	// 2의 5제곱
		
		
		
	}

}
