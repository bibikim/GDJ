package practice01;

import java.util.Scanner;

public class Ex07 {

	public static void main(String[] args) {
		
		
		// 7. 경과시간을 맞추는 게임. 첫 번째 엔터 누르면 해당 시점의 초시간을,
		// 두 번째 엔터를 누르면 해당 시점의 초 시간을 다시 보여준다. 10초에 근접하도록 엔터를 누른 사람이 이김.
	
		
		Scanner sc = new Scanner(System.in); 
		
		
		
		long start = System.currentTimeMillis();
		System.out.println("강아지님 시작하려면 <Enter>를 누르세요.");
		System.out.println("==== 시작 시간(초) : " + sc.nextLine() + start);
		System.out.println("10초가 된 것 같으면 <Enter>를 누르세요.");
		long end = System.currentTimeMillis();
		System.out.println("==== 종료 시간(초) : " + sc.nextLine() + end);
	
		long start2 = System.currentTimeMillis();
		System.out.println("고양이님 시작하려면 <Enter>를 누르세요.");
		System.out.println("==== 시작 시간(초) : " + sc.nextLine() + start2);
		System.out.println("10초가 된 것 같으면 <Enter>를 누르세요.");
		long end2 = System.currentTimeMillis();
		System.out.println("==== 종료 시간(초) : " + sc.nextLine() + end2);
		
		
		
		
		double diff1 = (end - start) * 0.001;
		double diff2 = (end2 - start2) * 0.001;
		if(diff1 > diff2) {
			System.out.println( "강아지님 결과 " + diff1 + "초," +  "고양이님 결과 " + diff2 + "초, 승자는 강아지님입니다.");
		} else {
			System.out.println( "강아지님 결과 " + diff1 + "초," +  "고양이님 결과 " + diff2 + "초, 승자는 고양이님입니다.");
			
		}
		
		
		
		

	}

}
