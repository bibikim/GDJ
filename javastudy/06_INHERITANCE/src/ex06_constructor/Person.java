package ex06_constructor;

public class Person {

	private String name;
	
	// 디폴트 형식이 아닌 경우
	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
