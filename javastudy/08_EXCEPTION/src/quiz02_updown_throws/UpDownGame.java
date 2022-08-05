package quiz02_updown_throws;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpDownGame {

	// 필드
	private int rand; 	// 1~100 사이 난수 저장
	private int count;  // 시도 횟수
	private Scanner sc; 
	
	// 생성자
	public UpDownGame() {			// 생성자이름=클래스이름
		// rand, sc 만들기
		sc = new Scanner(System.in);
		rand = (int)(Math.random()*100 + 1);  // 1~100
	}


	// 입력
	public int input() {    // public int input() throws RuntimeException, InputMismatchException { 		
							//---> unchecked exception이기 때문에 throws~ 생략 가능하다!! 안 써줘도 잘 돌아가요~ 
		
			count++; // 사용자가 한번씩 ++ 시도했다
		   System.out.println("입력 >>> ");
		   int n = sc.nextInt();
		   if(n < 1 || n > 100) {
			   throw new RuntimeException("1~100 사이 정수만 입력할 수 있습니다.");
		   }
		   return n;  // n을 반환	// return이 try안에 있기때문에 input();이랑 겹치니까 public int input에 오류가 남
		   							// 그래서 try-catch문 밖에 return 0;을 넣어준다
	}
	
											// ▼ 입력받은걸 실행으로 넘겨주기
	
	// 게임 실행
	public void play() {
		// 맞힐 때까지 무한루프
		while(true) {

			try {
				int n = input(); 	// return값도 받고 sc.nextint 도 받고 런타임익세션어쩌구도 받는 애 => 여기에 try블록
				if( n < rand) {
					System.out.println("Up!");
				} else if(n > rand) {
					System.out.println("Down");
				} else {
					System.out.println(rand + " 정답입니다. " + count + "번만에 성공~!");
					break;
				}
				// System.out.println(n < rand ? "Up!" : "Down!");  
			
			} catch (InputMismatchException e) {
				sc.next();   // 정수가 아닌거 먹어치우기
			System.out.println("정수만 입력 가능합니다.");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		
		}
	}
	
	
	
}
