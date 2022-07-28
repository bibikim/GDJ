package ex05_method;

public class Car {

	// 필드             를 먼저 작성하고
	int oil;
	int speed;
	
	
	// 메소드           를 필드 밑에 쪽에 작성
	
	// 1. 기름 넣기
	// 반환타입 : void (반환값이 없다는 뜻)
	// 메소드명 : addOil
	// 매개변수 : int o
	void addOil(int o) {
		oil += o;
		
		if(oil > 60) {				// 기름 만땅이 60이라고 가정할 때
			oil = 60;				// o를 그 이상을 넣어도 반환값이 60이 나오게 하기 위한 추가 식
		}
	}
	
	
	
	// 2. 달리기
	// 반환타입 : void
	// 메소드명 : pushAccel
	// 매개변수 : X
	void pushAccel() {				// 매개변수 없으므로 선언할 것도 X. 괄호만 적어주면 된다
		if(oil == 0) {
			return;	 // 반환타입이 void일 때만 사용 가능
					 // return; = 강제 중지. 아무것도 안 붙여야 달리기가 멈추는 것.
		}
		if(speed == 120) {
			oil--;			// 기름이 있는 상태에서 최대 속도만 120이다
			return;			// 그럼 기름을 쓰지 않으니까 return; 
		}
		// 속도는 악셀 한 번에 25씩 증가, 최대 속도 120
		// 기름은 1씩 사용
		speed += 25;
		if(speed > 120) {
			speed = 120;  // 속도가 120으로 넘어가면 120으로 떨어뜨려라.
		}
		oil--;				// 줄어드니까 -- or -=
	}							
	
	// 3. 멈추기
	// 반환타입 : void
	// 메소드명 : pushBrake
	// 매개변수 : X
	// 속도는 25씩 감소, 기름은 안 먹음
	void pushBrake() {
		if(speed == 0) {
			return;			// 스피드가 0이면 그만. return
		}
		speed -= 25;
		if(speed < 0) {		// 스피드는 -가 없으니 0보다 작으면 스피드는 그냥 0
			speed = 0;
		}
	}
		
	// 4. 계기판(기름, 속도) 확인
	// 반환타입 : void
	// 메소드명 : panel
	// 매개변수 : X
	void panel() {
		System.out.println("기름 " + oil);
		System.out.println("속도 " + speed);
	}
		
		
		
		
	}
	
	//------------------위에 모든 식이 다 필드값!
	// 기능 하나가 하나의 method
	
	
	
	
