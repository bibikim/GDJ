package ex01_branch;

public class Ex01_if {

	public static void main(String[] args) {
		
		// if문 (대표적인 분기문)
		// 조건을 만족하는 경우에만 실행. 조건을 만족할 때 {} 내부를 실행하는 것.
		// if(조건){
		//		실행문
		// }
		
		// 실행문이 하나밖에 없을 때는 {} 생략 가능. 권장하진 않음.
		
		int score = 70;
		
		if(score >= 60) {
			System.out.println("합격");
			System.out.println("축하합니다");
	}
		
		if(score < 60) {
			System.out.println("불합격");
	}
		
		
		
	}

}
