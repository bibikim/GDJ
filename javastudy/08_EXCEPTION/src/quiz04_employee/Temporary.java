package quiz04_employee;

public class Temporary extends Employee {
	// 임플로이의 상속을 받았기때문에
	// 슈퍼클래스의 생성자 호출해서 를 만들어야함
	
	private int pay;
	private int workTimes;
	
	public Temporary(int empNo, String name) {
		super(empNo, name);
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public int getWorkTimes() {
		return workTimes;
	}

	public void setWorkTimes(int workTimes) {
		this.workTimes = workTimes;
	}

	@Override
	public String toString() {
		return super.toString() + ", pay=" + pay + ", workTimes=" + workTimes + "]";
	}
	
	
	
	
	// new Temporary(1, name)
	// setPay()
	// setWorkTimes()
	
}
