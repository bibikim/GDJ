package ex03_has_a;

public class Gun {

	// 필드
	private String model;
	private int bullet;
	private final int MAX_BULLET = 15;		// 최대 15발이 들어간다

	
	// 메소드
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getBullet() {
		return bullet;
	}
	public void setBullet(int bullet) {
		this.bullet = bullet;
	}
	
	
	// 장전
	public void reload(int bullet) {
		if(this.bullet == MAX_BULLET) {			// 현재 불렛이 꽉 차면
			return;								// 못 합니당. 리턴~
		}
		this.bullet += bullet;					// 총알 받은 갯수만큼 누적시킨당
		this.bullet = (this.bullet > MAX_BULLET) ? MAX_BULLET : this.bullet;
	}											// 최대 갯수보다 많으면 15개로 출력, 아니면 현재 갯수로 출력
	
	
	// 총쏘기
	public void shoot() {
		if(bullet == 0) {
			return;
		}
		bullet--;
	}
	
	
	
	
	
	
	
}
