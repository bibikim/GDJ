package quiz04_employee;

public class Regular extends Employee{

	private int salary;

	public Regular(int empNo, String name, int salary) {
		super(empNo, name);
		this.salary = salary;
	}

	@Override
	public String toString() {
		return super.toString() + ", salary=" + salary + "]";
	}
	
	//Object의 toString() 은 참조를 출력
	
	//Employee의 toString()은 empNo, name
	//   ↑
	//Regular의 toString()은 salary -> 계속 오버라이드 했기 때문에 얘만 출력됨
	//									super.toString() 해야 empNo, name까지 다 출력됨 => super.toString()+salary
	// 부모의 투스트링을 호출해서 사번이름을 땡겨오고 salary를 뒤에 합쳐주는 식으로 코드를 짜면 된다.
	// super.toString() = Employee [empNo=" + empNo + ", name=" + name]
	// 상속관계의 toString 쓰는 법...
	
	
}
