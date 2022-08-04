package quiz01_updown;

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
	public int input() {								// 예외 발생할 수 있는 부분이자 예외 처리해야 하는 부분.

		try {
			count++; // 사용자가 한번씩 ++ 시도했다
		   System.out.println("입력 >>> ");
		   int n = sc.nextInt();
		   if(n < 1 || n > 100) {
			   throw new RuntimeException("1~100 사이 정수만 입력할 수 있습니다.");
		   }
		   return n;  // n을 반환	// return이 try안에 있기때문에 input();이랑 겹치니까 public int input에 오류가 남
		   							// 그래서 try-catch문 밖에 return 0;을 넣어준다
		} catch (InputMismatchException e) {
			System.out.println("정수만 입력할 수 있습니다.");
			sc.next();  // 잘못 입력된 문자열을 먹어 치우는 역할
			input();	// 다시 해라. input호출. public int input()으로 돌아가서 다시 해라. 잘못 입력한 것까지 카운팅 됨.
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			input();	// 실패하면 다시 처음부터 할 수 있게끔 짜놓은 것.
		}
		return 0;  // 이클립스 안심시키는 용도. 실제로 돌아가진 X
	}
														
	
										// ▼ 입력받은걸 실행으로 넘겨주기
	
	
	// 게임 실행
	public void play() {
		// 맞힐 때까지 무한루프
		while(true) {

			int n = input(); 	// ??? 넌 머니
			if( n < rand) {
				System.out.println("Up!");
			} else if(n > rand) {
				System.out.println("Down");
			} else {
				System.out.println(rand + " 정답입니다. " + count + "번만에 성공~!");
				break;
			}
			// System.out.println(n < rand ? "Up!" : "Down!");  
			
		}
		
		
	
		
	}
	
	
	
	
	
	
}
