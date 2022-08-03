package quiz08_schedule;

import java.util.Scanner;

public class WeekScheduler {

	private int nthWeek;    // 1 ~ n주차
	private Day[] week;		// 배열 선언. 요일 배열 현재 null값임
	private String[] dayNames = {"일", "월", "화", "수", "목", "금", "토"};  // 요일을 찾을 때 쓸 배열
	private Scanner sc;		// 선언만, 생성은 밑에 생성자에서~
	
	public WeekScheduler(int nthWeek) {
		this.nthWeek = nthWeek;
		week = new Day[7];	// 7개짜리 길이를 가진 Day배열 생성
		sc = new Scanner(System.in);
	}
	
	private void makeSchedule() { 
		System.out.println("♬ 등록 ♬");
		System.out.print("요일 입력 >>> ");
		String dayName = sc.next().substring(0, 1);  // 0번부터 1번 전까지. 0번(첫번째) 글자 하나만 입력받음. 월요일을 입력해도 월로 인정해서 입력받아줌
		sc.nextLine();
		// 수요일을 입력하면 index 3에 연결해주는 작업
		for(int i = 0; i < week.length; i++) {    // week.length = Day[] week 받은거
			if(dayName.equals(dayNames[i])) {     //dayName 수, dayNames(수)[i](i=3)  --> Day[] week[i] 여기에 3이 전달(week[3]), 거기에 스케줄을 넣자
				if(week[i] == null) {   // 등록된 스케줄이 없으면
					System.out.print("스케줄 입력 >>> ");
					String schedule = sc.nextLine();  // 스케줄 입력. 스케줄에 공백 입력 가능하게 함
					Day day = new Day();	// 하루를 만들어주고
					day.setSchedule(schedule);
					week[i] = day; 	// 그 하루를 배열에 넣는다
					System.out.println(dayName + "요일에 새 스케줄이 등록되었습니다.");
				} else {
					System.out.println(dayName + "요일은 이미 스케줄이 있습니다.");
				}
				return;		// 스케줄을 등록해도, 등록하지 못해도 메소드 종료시키게끔.
			}
		}
		System.out.println(dayName + "요일은 없는 요일입니다.");
		// for문이 동일한 요일과 등록된 스케줄이 없으면 그만 돌고 나오는 지점.
	}
	
	private void changeSchedule() {
		System.out.println("♬ 수정 ♬");
		System.out.println("변경할 요일 입력 >>> ");
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {
					System.out.println(dayName + "요일은 스케줄이 없습니다.");
					System.out.println("새 스케줄을 등록할까요?(y/n) >>> ");
					String yesNo = sc.next().substring(0, 1);
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")) {
						System.out.println("새 스케줄 입력 >>> ");
						String schedule = sc.nextLine();
						Day day = new Day();			// 새로 등록하기 때문에 day를 넣어줘야 됨!
						day.setSchedule(schedule);
						week[i] = day;
						System.out.println(dayName + "요일에 새 스케줄이 등록되었습니다.");
					} else {
						System.out.println("스케줄 변경이 취소되었습니다.");
					}
						
				} else {
					System.out.println(dayName + "요일의 스케줄은 " + week[i].getSchedule() + "입니다.");
					System.out.println("변경할까요?(y/n) >>> ");
					String yesNo = sc.next().substring(0, 1);
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")) {
						week[i] = null;
						System.out.println("변경할 스케줄 입력 >>> ");
						String schedule = sc.nextLine();
						Day day = new Day();
						day.setSchedule(schedule);
						week[i] = day;
						System.out.println(dayName + "요일의 스케줄이 변경되었습니다.");
				}
			}
				return;
			}
			
		}
		System.out.println(dayName + "은 없는 요일입니다.");
	}
	
	private void deleteSchedule() {
		System.out.println("♬ 삭제 ♬");
		System.out.println("삭제할 요일 입력 >>> ");
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) { 		// 어떤 요일을 찾는건지
				if(week[i] == null) {		// 스케줄이 null값이면(없으면)
					System.out.println(dayName + "요일은 스케줄이 없습니다.");
				} else {
					System.out.println(dayName + "요일의 스케줄은 " + week[i].getSchedule() + "입니다.");
					System.out.print("삭제할까요?(y/n) >>> ");
					String yesNo = sc.next().substring(0, 1);	// y/n 값  string으로 입력받겠단 코드
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")) {	// y를 대소문자 구분 무시하겠다
						week[i] = null;		// y면 스케줄 취소니까 삭제해서 null값
						System.out.println(dayName + "요일의 스케줄이 취소되었습니다.");
					} else {
						System.out.println("스케줄 삭제가 취소되었습니다.");
					}
					
				}
				return; // 스케줄을 삭제 하든, 삭제할게 없어서 삭제를 취소하든 메소드 종료 if문 끝나고
			}
		}
		System.out.println(dayName + "요일은 없는 요일입니다."); 	
	}
	
	private void printWeekSchedule() {
		System.out.println("♬ 전체조회 ♬");
		System.out.println(nthWeek + "주차 스케줄 안내");
		for(int i = 0; i < week.length; i++) {
			System.out.print(dayNames[i] + "요일 - ");
			System.out.println(week[i] == null ? "X" : week[i].getSchedule());   // week[i].getSchedule() 그날 스케줄 알려줭
			                                                                     // week[i] = day
		}
		
	}
	
	public void manage() {
		
		while(true) {
			
			System.out.print("1.등록 2.수정 3.삭제 4.전체조회 0.종료 >>> ");
			int choice = sc.nextInt();  // 숫자만 받아가고
			sc.nextLine();				// 엔터 받아가는 용도
			
			switch(choice) {
			case 1 : makeSchedule(); break;
			case 2 : changeSchedule(); break;
			case 3 : deleteSchedule(); break;
			case 4 : printWeekSchedule(); break;
			case 0 : System.out.println("스케줄러를 종료합니다."); return;
			default : System.out.println("인식할 수 없는 명령입니다.");
			}
			
			
			
		}
		
		
		
	} // 외부에선 manage로 부를거니까 위에 메소드들 private으로 변경 가넝
	
	
	
	
}
