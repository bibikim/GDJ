package practice02;

import java.util.Arrays;
import java.util.Scanner;

public class Hall {

	private String hallName;			// 공연장 이름
	private SeatGroup[] seatGroups;	
	private Scanner sc;
	private String[] seatTypes = {"S", "R", "A"};
	private int[] seatCount = {10, 10, 10};  // 각 그룹별 좌석 갯수
	
	public Hall(String hallName) {
		this.hallName = hallName;
		seatGroups = new SeatGroup[seatTypes.length];		// S석 R석 A석이 있음을 의미
		for(int i = 0; i < seatGroups.length; i++) {
			seatGroups[i] = new SeatGroup(seatTypes[i], seatCount[i]);
		}
		// seatGroups[0] = new SeatGroup("S", 10);
		//            i          "seatTypes", seatCount
		sc = new Scanner(System.in);
	}

	// 예약
	public void reserve() {
		System.out.print("예약할 좌석 타입 입력" + Arrays.toString(seatTypes) + " >> ");   // S석은 idx 0번 그룹
		String seatType = sc.next();					// ㄴ 배열 출력해줌
		/*
		 S 입력 -> seatGroups[0].reserve()
		 R 입력 -> seatGroups[1].reserve()
		 A 입력 -> seatGroups[2].reserve()
		*/
		for(int i = 0; i < seatTypes.length; i++) {
			if(seatType.equals(seatTypes[i])) {  // S입력하면 seatTypes[0]이랑 같은지 봐주는거
				seatGroups[i].reserve();	// boolean 반환값은 사용하지 않음
				return;
			}
		}
		System.out.println(seatType + "은 없는 타입입니다.");
	}
	
	// 예약 취소
	public void cancel() {
		System.out.print("취소할 좌석 타입 입력" + Arrays.toString(seatTypes) + " >> ");   // S석은 idx 0번 그룹
		String seatType = sc.next();
		for(int i = 0; i < seatTypes.length; i++) {
			if(seatType.equals(seatTypes[i])) {  // S입력하면 seatTypes[0]이랑 같은지 봐주는거
				seatGroups[i].cancel();	// boolean 반환값은 사용하지 않음
				return;
			}
		}
		System.out.println(seatType + "은 없는 타입입니다.");	
	}
	
	
	// 전체 예약 확인
	public void hallInfo() {
		System.out.println("[" + hallName + "]");
		for(int i = 0; i < seatGroups.length; i++) {
			seatGroups[i].reserveInfo();// 시트그룹 하나.예약정보();
		}
		System.out.println();
	}
	
	// 실행
	public void manage() {
		while(true) {
			System.out.println("1.예약 2.취소 3.예약현황 0.종료 >> ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1: reserve(); break;
			case 2: cancel(); break;
			case 3: hallInfo(); break;
			case 0: return;
			default : System.out.println("Bad Request");
			}
			
			
			
		}
	}
	
	
	
	
}
