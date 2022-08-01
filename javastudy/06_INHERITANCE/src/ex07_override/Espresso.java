package ex07_override;

public class Espresso extends Coffee {

	@Override
	public void taste() {						// 똑같이 만들면(부모와 자식이 같은 메소드(이름)을 가지면 override에 문제 없다
		System.out.println("쓰다");				// = override 하려면 이름이 같아야 한다
	}
	
	
	
}
