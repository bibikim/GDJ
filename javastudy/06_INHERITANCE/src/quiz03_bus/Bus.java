package quiz03_bus;

// Bus Seat Person Student Alba 클래스 각각 만들긩

// Bus bus = new Bus(25);   좌석이 25개인 버스
// bus ride(1, new Person("kim"));
// bus ride(5, new Student("choi"));
// bus ride(10, new Alba("koo"));
// bus.info();
// 1 kim 	2 choi	 10 koo
// 단, 55번 좌석은 없는 거니까 앉을 수 없는 좌석엔 불가능하게 설정하라


public class Bus {

	private Seat[] seats;   	// 배열 선언
								// ㄴ 시트가 여러개인 버스
	private int limit;			// 버스 정원
	
	// Bus 생성자에서 배열 생성을 진행함
	public Bus(int cnt) {
		seats = new Seat[cnt];	// 배열 생성, new Bus(25)인 경우 Seat가 25개 생성됨	
		limit = cnt;
		for(int i = 0; i < cnt; i++) {	// 25개 하나하나 넣어줘야 됨
			seats[i] = new Seat();
		}
	}
	
	
	// ride() 메소드
	public void ride(int seatNo, Person person) { 	// P,Stu,Al 다 저장할 수 있는 Person타입	// 업캐스팅
		// 존재하지 않는 시트 번호
		if(seatNo <= 0 || seatNo > limit) {
			return;   // ride()메소드 종료
		}
		// 시트에 사람이 없으면, 시트번호에 Person 저장하기(탑승)
		Seat seat = seats[seatNo - 1];
		Person p = seat.getPerson();	// 시트에 앉아있는 사람을 빼고
		if(p == null) {					// getPerson=의자에 앉아있는 사람. null = 자리가 비어있으면
			seat.setPerson(person);     // 배열의 index는 0 ~ 24로 바꿔야되기 땜에 -1 (좌석번호는 1~25)
		}
	}
	
	
	// info()메소드
	public void info() {
		for(int i = 0; i < limit; i++) {		// limit은 seats 배열의 length와 같음
			Seat seat = seats[i]; 	// 좌석이 하나
			Person person = seat.getPerson(); 	// Person person = seats[i].getPerson();
			if(person != null) {	// if(seat.getPerson[i] != null), if(seats[i].getPerson() 
				System.out.println((i+1) + ", " + person.getName());
				// System.out.println((i+i) + "," + seat.getPerson().getName());
				// System.out.println((i+1) + "," + seats[i].getPerson().getName()); 하면 됨
			} else {
				System.out.println((i+1) + ", 비어 있음");  // 1~25번 좌석까지 전부 출력하기 위한 추가 코드
			}
		}
	}
	
}


// Seat[] seats = new Seat[25];  그냥 이 상태면 null만 25개
// seats[0] - new Sat();
// seats[1] = new Seat(); .....x25번 해줘야됨



