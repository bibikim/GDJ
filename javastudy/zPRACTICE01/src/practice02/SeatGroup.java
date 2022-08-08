package practice02;

import java.util.Scanner;

public class SeatGroup {

	private String seatType; 	//  S, R, A
	private Seat[] seats;
	private Scanner sc;			// idx 안 잡는 이유 : 순서대로 예약하는게 아니라 원하는 자리 픽해서 들어가는거니까
								// 순차적증가가 아니기 때문에
	
	public SeatGroup(String seatType, int cnt) { // 등급별로 좌석수(cnt) 받아오기 위함
		this.seatType = seatType;
		seats = new Seat[cnt];			// 배열 선언만 하면 null값만 있는 상태. 
		for(int i = 0; i < cnt; i++) {	// 배열의 길이만큼 생성해야됨
			seats[i] = new Seat();		// 빈 의자 가져다두기. null주기 싫어서!
		}
		sc = new Scanner(System.in);
	}											 // new SeatGroup("s", 10)
	
	// 예약
	public boolean reserve() {
		reserveInfo();   // 예약 현황 보여주기
		// 시트번호는 1번부터 시작
		System.out.print("예약할 시트 번호 >> ");
		int seatNo = sc.nextInt();
		if(seatNo < 1 || seatNo > seats.length) {
			System.out.println(seatNo + "번 좌석은 없는 좌석입니다.");
			return false;	// void가 아닌 데서는 return;으로만 끝낼 수 없다. 반환타입에 맞게 return 해줘야 오류X
		}
		// 예약된 시트인지 확인
		// 시트번호 1 = idx 0
		if(seats[seatNo - 1].isOccupied()) {
			System.out.println(seatNo + "번 좌석은 이미 예약된 좌석입니다.");
			return false;	// 예약 실패
		}
		//예약 진행
		System.out.println("예약자 이름 >> ");
		String name = sc.next();
		seats[seatNo - 1].reserve(name);
		System.out.println(seatNo + "번 좌석 예약 완료");
		return true;   // 예약 성공
	}
	
	
	
	// 예약 취소
	public boolean cancel() {
		reserveInfo();   // 예약 현황 보여주기
		System.out.println("취소자 이름 >>> ");
		String name = sc.next();
		for(int i = 0; i < seats.length; i++) {
			//if(seats[i] != null) {	이미 null은 안 나옴. 빈 좌석을 두었기 때문에 아무것도 없는 null은 아님
			if(seats[i].isOccupied()) {// 예약된 좌석만 비교
				if(seats[i].isMatched(name)) { 	// 예약자 이름과 취소자 이름이 같으면 true 반환
					seats[i].cancel();			// 같으면 취소 완.
					System.out.println("예약자 " + name + "의 예약이 취소되었습니다.");
					return true;				// 취소 성공
				}
			}
				
		} // for문 끝
		System.out.println(name + "으로 예약된 좌석이 없습니다.");
		return false;
	}
	
	
	
	// 예약 상황 출력                            [S석]    김* X X X 이* X X 박* X X X
	public void reserveInfo() {
		System.out.println("[" + seatType + "]");
		for(int i = 0; i < seats.length; i++) {
			if(seats[i].isOccupied()) {
				System.out.print(seats[i].getName().substring(0, 1) + "* ");	// 가로 한줄로 출력하기 위해 print! ln(XXXX)
			} else {
				System.out.print((i + 1) + "   ");		// 좌석을 1부터 출력하기 위해 (i + 1)
			}
	//		if((i + 1) % 10 == 0) { 		좌석이 20개 이상씩일 때 2차원 배열처럼 보여주는
	//			System.out.println();		출력 옵션!
	//		}
		}
		System.out.println();
	}
	
	
	
	
}
