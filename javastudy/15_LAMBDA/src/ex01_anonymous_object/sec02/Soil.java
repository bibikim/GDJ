package ex01_anonymous_object.sec02;

public class Soil {

	private int totalOil = 1000;
	private int payPerLiter = 2000;
	private int earning;
	
	// 1. 익명 객체를 필드에 저장하기   <- 주유소에 자동차가 하나 들어가 있음!
	private Car car = new Car() {      // 익명 inner type
		@Override
		public void addOil() {
			int oil = 10;
			totalOil -= oil;     // 주유소 입장에서는 oil 만큼 total에서 빠짐
			earning += oil * payPerLiter;  // 동시에 돈은 벎
			System.out.println("감사합니다. 지바겐~");
		}
	};

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

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	
	
	
}
