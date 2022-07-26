package ex03_number;

public class Ex03 {

	public static void main(String[] args) {
		
		// 대입 연산
		int score = 100;  // 등호(=)가 대입 연산자이다. score <- 100;
		System.out.println(score);
		
		// 연습
		// x에 10이 있고, y에 20이 있다.
		// x와 y에 저장된 값을 서로 교환하시오.
		int x = 10;
		int y = 20;
		int temp;  // 힌트.
		
		temp = x;
		x = y;
		y = temp;   // 등호(=) 오른쪽의 값을 왼쪽으로 대입한다. 
					// x를 temp에 옮기고 비어있는 x에 y를 대입한 후, y에 temp(x)를 대입
		
		System.out.println(x);
		System.out.println(y);
		
		// 복합 대입 연산자
		// +=, -=, *=, /=, %= 등
		int wallet = 0;
		wallet = wallet + 5000;  // wallet은 여기선 5000
		wallet += 5000;  // wallet = wallet + 5000; wallet에 5000 더해라   ->   여기선 10000이 됨
		wallet -= 3000;  // wallet = wallet - 3000; wallet에서 3000을 빼라 -> 10000 - 3000이니까 7000 
		System.out.println(wallet);
		
		// 연습
		// 통장 잔액(balance)에서 이자 5%를 받았음을 나타내자.
		long balance = 10000;
		balance *= 1.05;  // long타입(balance)과 double타입(1.05) promotion 진행됨. b를 d로 promotion해서 처리한다. 복합 연산은 불필요한 casting을 잡아줌.
		System.out.println(balance);
		
		//balance = balance * 1.05;  실패. balance * 1.05 결과는 double이기 때문에 long인 balance에 저장할 수 없다. (밑에처럼 강제 변환으로 해야됨)
		//balance = (long)(balance * 1.05);  성공. balance * 1.05 결과를 먼저 수행하고 long으로 casting해서 저장할 수 있다. 
		
		//balance = balance + balance * 0.05;  실패. balance + balance * 0.05 결과는 double이기 때문에 long balance에 저장할 수 없다.
		//balance = (long)(balance * 0.05);  성공.
		//balance = (long)(balance + balance * 0.05);  성공. balance + balance * 0.05 결과를 long으로 casting해서 저장할 수 있다.
		
		
		
		

	}

}
