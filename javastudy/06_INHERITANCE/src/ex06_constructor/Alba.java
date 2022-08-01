package ex06_constructor;

public class Alba extends Student {

	private String company;
	


	public Alba(String name, String school, String company) {		// Alba 생성자
		super(name, school);										// 알바의 부모는 스튜던트(super)
		this.company = company;										// 무조건 부모 먼저, 그다음이 자식. 순서 바뀌는 것도 안됨
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	// 왜 이런 코드 조합이 나오는지 이해만 하고 있으면 된다.
	
	
	
}
