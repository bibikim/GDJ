package ex16_interface;

// extends Pet : 애완동물이다.
// implements Walkable : 산책이 된다.


public class Dog extends Pet implements Walkable{

	public Dog(String petName) {			// 별도의 필드값 없이 생성자 생성
		super(petName);						// 강아지 만들 때 이름만 주면 됨
		
	}


	
}
