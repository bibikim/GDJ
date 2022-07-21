package ex02_loop;

public class Ex05_nested_for {

	public static void main(String[] args) {
		
		// 1일차 1교시
		// 1일차 2교시
		// ...
		// 1일차 8교시
		// 2일차 1교시
		// ...
		// 3일차 8교시
		// 1~3 for문, 1~8 for문 2중으로 중첩
		
		for(int day = 1; day <=3; day++) {
			for(int hour = 1; hour <= 8; hour++) {                    // 1일차로 고정시키고 8번출력, 2일차로 고정하고 8번출력
				System.out.println(day + "일차 " + hour + "교시");
			}
		}

		// 연습.
		// 전체 구구단.
		// 2x1=2
		// ...
		// 9x9=81
		for(int n = 2; n <= 9; n++) {
			for(int m = 1; m <= 9; m++) {
				System.out.println(n + "x" + m + "=" + (n*m));
			}
		}
		
		// 연습.
		// 2x1=2
		// ...
		// 5x5=25
		for(int n = 2; n <= 5; n++) {
			for(int m = 1; m <= 9; m++) {
				System.out.println(n + "x" + m + "=" + (n*m));
				if(n == 5 && m == 5) {
					break;                        // 안에 들어있는 for문에서 종료.
				}
			}
		}
		
		
		
		// 라벨(label)을 이용한 풀이 (아무 이름 붙이고 :)
		outer: for(int n = 2; n <= 9; n++) {
			for(int m = 1; m <= 9; m++) {
				System.out.println(n + "x" + m + "=" + (n*m));
				if(n == 5 && m == 5) {
					break outer;
					}
				}
		}
		
		
		// 2x1=2  3x1=3  ... 9x1=9           곱하기 1을 먼저 출력하라
		// 2x2=4  3x2=6  ... 9x2=18          위에 연습문제와 반대로. 단을 밑으로 안으로, m을 바깥쪽 for문으로.
		// ...
		for(int m = 1; m <= 9 ; m++) {
			for(int dan = 2; dan <= 9; dan++) {
				System.out.print(dan + "x" + m + "=" + (dan*m) + "\t");		// 줄이 바뀌면 안되니까 print
			}                                                               // x1 조건이 안쪽 for문에서 끝나고 줄 바뀌어야 하니까 안쪽 for문 닫고 줄바꿈
				System.out.println();

			}
		
		// 9x1이 끝나면 줄바꿈... System.out.println();
		

		
		
	}

}
