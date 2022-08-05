package ex05_throws;

public class ParkingLot_throw {
	
	private Car[] cars;
	private int idx;
	
	public ParkingLot_throw() {
		cars = new Car[10];
	}
	

	public void addCar() {
		try {
			if(idx==cars.length) {
				throw new RuntimeException("자리가 없으니까 그냥 돌아가");
				}
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void deleteCar() {
		try {
			if(idx==0) {
				throw new RuntimeException("EMPTY");
			} 
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void findCar() {
		try {
			if(idx==0) {
				throw new RuntimeException("EMPTY");
			} 
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void printAllCars() {
		try {
			if(idx==0) {
				throw new RuntimeException("EMPTY");
			}
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public void manage() {
		
		try {
			
			while(true) {
				int choice = 1;
				switch(choice) {
				case 1: addCar(); break;
				case 2: deleteCar(); break;
				case 3: findCar(); break;
				case 4: printAllCars(); break;
				case 0: return;
				default : throw new RuntimeException("Bad Request");
				}
			}
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	public static void main(String[] args) {
	
		new ParkingLot_throw().manage();
	}
	
	
	
	
	
	
	
	
	
}
