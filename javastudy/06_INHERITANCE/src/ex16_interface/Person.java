package ex16_interface;

public class Person {


	public void foodFeed(Pet pet, String food) {   //업캐스팅. 전달하는게 dog cat snake니까 Pet으로 받음
		System.out.println(pet.getPetName() + "에게 " + food + "주기");
	}
	
	
	public void walk(Walkable pet) {  // 걸을 수 있는 pet을 받자.
									  // Dog, Cat 클래스에 Walkable을 implements 입력
									  // Snake는 산책이 안되기 때문에 implements 주지 않음!
									  // 뱀은 워커블 타입이 아니기 때문에 이쪽으로 전달되지 않는다.
		System.out.println(((Pet) pet).getPetName() + "와 산책" );
						// Walkable에는 pet이 없기 때문에 Pet 캐스팅을 해야 petName을 불러올 수 있다.
	}
	
	
	
	// ~able : 인터페이스에서 많이 볼 수 있는 이름.
	
}
