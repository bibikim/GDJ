package ex03_functional_interface.sec01;

public class Main {

	public static void main(String[] args) {
		
		// 람다식으로 바꾸기 전
		MyInterface1 my = new MyInterface1() {
			@Override
			public void method() {
				System.out.println("집에 가고 싶다.");
			}
		};

		my.method();   // 실행시키기
		
		
		// 람다식으로 변경    -> 임시 객체들이기 때문에 얼마든지 만들 수 있다.
		MyInterface1 your = () -> System.out.println("집에 가고 싶다궁");
		your.method();
		
		MyInterface1 our = () -> System.out.println("집에 가서 자고 싶다.");
		our.method();
	
		
	}

}
