package ex02_lambda.sec04;

public class Soil {

	private int totalOil = 1000;
	private int payPerLiter = 2000;
	private int earning;
	
	// 3. 메소드의 매개변수로 익명 객체 저장하기
	public void sellOil(Car car) { // 어떤 자동차(Car car)에 기름을 파는지 받아와라. 
		car.addOil();    // 받아오면 받아온 자동차(car)에 기름 넣어줄겡~
	}
	public int getTotalOil() {
		return totalOil;
	}

	public void setTotalOil(int totalOil) {
		this.totalOil = totalOil;
	}

	public int getPayPerLiter() {
		return payPerLiter;
	}

	public void setPayPerLiter(int payPerLiter) {
		this.payPerLiter = payPerLiter;
	}

	public int getEarning() {
		return earning;
	}

	public void setEarning(int earning) {
		this.earning = earning;
	}

	
	
	
}
