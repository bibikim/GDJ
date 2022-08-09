package practice04;

public class TourGuide {

	// 필드
	private Tour tour;

	// 생성자
	public TourGuide(Tour tour) {	// 투어가 코리아일 수도, 괌일 수도 있음
		super();
		this.tour = tour;
	}
	
	// 메소드
	public void sightseeing() {
		tour.sightseeing();		// 본인이 진행하는 관광
		
	}
	
	public void leisure() {
		tour.leisure();			// 본인이 진행하는 레저
		
		
	}
	
	
	
}
