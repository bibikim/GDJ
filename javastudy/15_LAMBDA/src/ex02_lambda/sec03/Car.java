package ex02_lambda.sec03;

public interface Car {
	public void addOil();   // 추상메소드가 들어가야 하니까 본문 들어가는 게 아니라 이러케 들어가야 한다.
	
	// Car car = new Car() { public void addOill() { }  }  -> 이렇게 만드는건 가능!
	
}
