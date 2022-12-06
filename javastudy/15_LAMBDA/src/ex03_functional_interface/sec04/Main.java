package ex03_functional_interface.sec04;

public class Main {

	public static void main(String[] args) {
		
		MyIneterface4 my = (a, b) -> {
			System.out.println(a + b);   // 생략가능
			return a + b;    // 생략가능
		};
		my.add(1, 8);

		MyIneterface4 your = (a, b) -> a + b;   // 매개변수로 온 3과 2가 a + b 를 반환해서 5를 출력
		System.out.println(your.add(3, 2));
	}

}
