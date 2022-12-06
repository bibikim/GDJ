package ex03_functional_interface.sec02;

public class Main {

	public static void main(String[] args) {
		
		MyInterface2 my = (a) -> System.out.println(a);   // 매개변수의 타입은 작성하지 않는다.
		my.method(10);
	
	}
}
