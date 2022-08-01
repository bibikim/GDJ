package quiz03_bus;

public class Person {

	private String name;   		// 공통되는 것이 이름이기 때문에 person에 string name 필드 선언

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {		// getName 통해서 이름 알아낸다.
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
