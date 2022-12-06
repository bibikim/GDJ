package ex03_functional_interface.sec03;

public class Main {
	
	public static void main(String[] args) {
	
		MyInterface3 my = () -> 10;
		System.out.println(my.method());
	
	
		
		MyInterface3 your = () -> {
			int a = 20;
			return a;
		};
		System.out.println(your.method());
		
		
		
		MyInterface3 our = () -> 30;   // 이 메소드를 호출하면 20을 return 
		System.out.println(our.method());
	}
	
	
	
	
}
