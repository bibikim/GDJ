package practice02;

public class Seat {

	private String name; 	// 시트 예약한 사람 이름

	// 생성자 의미없을 것 같아서 안 만듦
	
	
	// 예약한 사람 확인
	public String getName() {
		return name;
	}

	// 예약
	public void reserve(String name) {		// setName자리인데 예약O이랑 매칭 안되니까 바꿔줌!
		this.name = name;
	}
	
	// 예약 취소
	public void cancel() {
		name = null;	// 취소하면 예약한 사람이 없어지는 거니까 null값
	}
	
	// 예약 여부 확인
	public boolean isOccupied() {
		return name != null;	// null이 아니면(예약되어있으면) true 반환
	}
	
	// 예약자 확인
	public boolean isMatched(String name) {  // 받아온 이름(name)과 저장된 이름(예약: this.name)이 일치하는지
		return name.equals(this.name);		
	}
	
	
	
	
}
