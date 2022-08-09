package practice04;

public class Main {

	public static void main(String[] args) {
		
		Tour tour1 = new KoreaTour();
		TourGuide guide1 = new TourGuide(tour1);	// 투어가이드에 tour1,2 라는 필드를 가지게 된다
		
		guide1.sightseeing();	// 광화문 여행			반환타입, 매개변수 둘다 없음 -> void 타입으로 메소드 만들기
		guide1.leisure();		// 한강유람선
		
		Tour tour2 = new GuamTour();
		TourGuide guide2 = new TourGuide(tour2);
		
		guide2.sightseeing();	// 사랑의절벽
		guide2.leisure();		// 패들보트
		
		// 출력문은 Tour에서 가지고 있음
		//
	}

}
