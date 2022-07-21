package ex02_loop;

public class Ex04_continue {

	public static void main(String[] args) {
		
		// continue문
		// 반복문의 시작 지점으로 이동한다.
		// 실행에서 제외할 코드가 있는 경우에 사용한다.
		
		// while() {
		//		a;
		//		b;
		//		c;
		//		continue;	// a,b,c만 실행하고 다시 while()문으로 이동한다. d~e까지는 실행 안 함.
		//		d;
		//		e;
		// }

		// 1 ~ 100 중에서 3의배수를 제외하고 모두 더하기

		int total = 0;
		int n = 0;
		while(n < 100) {
			
			n++;
			
			if(n % 3 == 0) {
				continue;       //3의 배수는 while로 올려버리는 것. 3을 올리면 n++돼서 4가 되고, 4가 내려오면 3의 배수가 아니니까 continue에 걸리지 않고 밑으로 내려가서 연산.
			}
			
			total += n;        // 3의 배수는 이 과정을 하지 않음.
			
		}
		System.out.println("합계 " + total);
		
		
		
		// 위, 아래 같은 코드. continue 없이도 코드를 만드는 건 언제나 가능하다.
		
		total = 0;
		n = 0;
		while(n < 100) {
			n++;
			if(n % 3 != 0) {        // != (다르다는 비교 연산). 0과 다르면 += n 을 해라.
				total += n;
			}
		}
		System.out.println("합계 " + total);
		
		
		
		
		
		
	}

}
