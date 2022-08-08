package ex12_object_class;

public class Person {

	private String name;		// 게터세터 만든다. 그게 있어야 이름 저장, 이름 확인 가능!
	
	
	public String getName() {
		return name;
	}		
	public void setName(String name) {
		this.name = name;
	}

	public void eat() {
		System.out.println("먹는다.");
	}
	
	@Override						 // 오라 안 하면 Object가 지원하는 정보를 출력. 쓸만한 정보로 나오지 X 그래서 오버라이드
	public String toString() {
		return "이름 : " + name;    // 출력할 형태를 만들어서 반환. System.out.println(person);에서 사용됨
	}
									 // Source - Generate toString - field만 체크해서 적용하면 자동으로 만들어줌
	@Override		// r Person의 equals
	public boolean equals(Object anObject) {    // p1.equals(p2)에서 사용됨    -  p2를 anObject로 보내서 그걸 Person p로 전달.
		Person p = (Person) anObject;			// Person 타입으로 다운캐스팅
		return name.equals(p.name);				// name은 (equals를 호출한 객체인)p1, p.name은 p2  둘을 비교
					// ㄴString이 갖고 있는 equals
}
	// Person 객체의 비교를 이름으로 하기 위해 오버라이드. equals가 object의 메소드니까.
	// 이름이 같으면 같은 사람임을 인식하게 하기 위해
}
