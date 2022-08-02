package ex11_downcasting;

public class Main {

	public static void main(String[] args) {
		
		
		Car[] cars = new Car[10];

		for(int i = 0; i < cars.length; i++) {
			if(Math.random() < 0.33) {
				cars[i] = new Car();
			} else if(Math.random() < 0.66) {
				cars[i] = new Ev();
			} else {
				cars[i] = new Hybrid();
			}
		}
		
		//car[0] = new Car();
		//car[0] = new Ev();
		//car[0] = new Hybrid();
		// ....						 위에가	랜덤 10개 만들기 위한 코드 
		
		
		/*
		 Car이면 drive() 호출
		 Ev이면 charge() 호출
		 Hybrid이면 addOil() 호출
		 */
		
		for(int i = 0; i < cars.length; i++) {
			if(cars[i] instanceof Hybrid) {			// 후손부터 먼저 체크
				((Hybrid) cars[i]).addOil();		// cars[i]. <-하고 addOil 클릭
			} else if(cars[i] instanceof Ev) {
				((Ev) cars[i]).charge();
			} else if(cars[i] instanceof Car) {
				cars[i].drive();
			} 					
			
			// 모든 자식들은 부모타입이기도 함. car ev hybrid 순으로 체크하면 drive만 주구장창 나온다.
			// 모든 자식들이 부모타입이기도 하기 때문에 if drive 에서 내려오질 않음.
			// 이럴 땐 자식들부터 체크. 따라서 역순으로 코드를 쓴다
			// 상속 관계의 결과는 후손부터 먼저 체크한다.
		}
		
		}

	}


