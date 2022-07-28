package ex03_has_a;

 	// 이 패키지 예제는 '상속' 아님. 클래스의 특성. 찐상속은 04패키지에~

public class Soldier {

	// 필드
	private Gun gun;		// 가지고 있다..

	// 메소드
	public Gun getGun() {
		return gun;
	}

	public void setGun(Gun gun) {		// setGun 호출해서 가질 수 있음
		this.gun = gun;
	}
	
	
	// 캡슐화: 어떤 기능을 어떤 클래스에 넣어줄 것이냐
	public void reload(int bullet) {
		gun.reload(bullet);  			// 군인이 총알을 받으면 자기 총에 넣은 상태.
	}
	
	public void shoot() {
		gun.shoot(); 					// 총을 쏜다
	}
	
	
}
