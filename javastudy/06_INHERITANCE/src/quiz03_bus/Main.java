package quiz03_bus;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Bus bus = new Bus(25);
		bus.ride(1, new Person("kim"));
		bus.ride(5, new Student("choi"));
		bus.ride(10, new Alba("koo"));
		bus.info();
		
	}

}

