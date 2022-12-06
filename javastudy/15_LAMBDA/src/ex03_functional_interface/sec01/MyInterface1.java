package ex03_functional_interface.sec01;

@FunctionalInterface   // 추상 메소드를 1개 가지고 있는 메소드(= 람다식으로 생성할 수 있는 인터페이스)
					   // 공부할 때는 만들어져 있는 4가지의 functional interface 생성해서 만들면 됨!
public interface MyInterface1 {
	public void method();   // 람다는 이름없는 메소드니까 메소드 이름 노관심
							// 매개변수 없고 반환타입 없음!
}
