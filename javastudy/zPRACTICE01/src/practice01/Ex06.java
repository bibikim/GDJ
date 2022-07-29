package practice01;

public class Ex06 {

	public static void main(String[] args) {
		
		
		// 6. 윷놀이 랜덤 구현. 윷이나 모가 나오면 계속 생성
		// 지우고 다시 풀어보기 이게 왜 while문으로 풀리는지도 생각해보기
		
		String[] yut = { "", "도", "개", "걸", "윷", "모"}; 	
		
		
		int sum = 0;
		
		while(true) {
			int play = (int)(Math.random()*5) + 1;		// 움직이는게 play
			sum += play;
			if(play <= 3) {
				System.out.println(yut[play] + ", " + sum + "칸 이동한다.");
				break;
			} else {	
				System.out.println(yut[play] + ", " + sum + "칸 이동한다.");
			}
		}
	
		
		

	}

}
