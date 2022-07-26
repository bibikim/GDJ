package ex02_loop;

public class Ex03_break {

	public static void main(String[] args) {
		
		// break문
		// switch문을 종료할 때 사용한다.
		// 반복문(for, while)을 종료할 때 사용한다.

		// 모금 목표 : 100000원
		// 한 번에 30원씩 모금
		
		// 1회(serial) 모금액 30원(money)   현재 30원(total)
		// 2회         모금액 30원          현재 60원
		// ...                             	목표액 넘으면 break
		
		int total = 0;
		int money = 30;
		int serial = 0;            // 모금 시작 안 한 것부터 변수잡기. 밑에서 ++ 연산을 하니깐여
		int goal = 100000;
		while(true) {              // 무한 루프. 언제나 만족한다는 얘기(끝나지 않는다)
			
			if(total >= goal) {
				break;             // 어떠한 조건문을 만족하면 그만하겠다고 거는 break.
			}
			
			
			total += money;
			serial++;
			System.out.println(serial + "회 모금액 " + money + "\t현재 " + total + "원");      // \t=8칸
		}
		
		
		
	}

}
