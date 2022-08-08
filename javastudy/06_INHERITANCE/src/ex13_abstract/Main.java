package ex13_abstract;

public class Main {

	public static void main(String[] args) {
		
		// Shape 클래스타입의 객체는 존재할 수 없는 객체이다.
		// abstract 처리해서 객체의 생성을 막을 수 있다.
		
		// Shape s1 = new Shape("도형"); - 실체X	// 추상 클래스 객체는 못 만듦
		// System.out.println(s1.getType());		// Shape 클래스 목적은 그냥 모든 서브클래스를 저장할 수 있는 슈퍼클래스 타입으로만 사용
		// System.out.println(s1.getArea()); 
		
		Shape s1 = new Circle("원", 1);		// 상속관계기 때문에 자식을 부모타입으로 불러도 가능
		System.out.println(s1.getType());
		System.out.println(s1.getArea()); 	

	}

}
