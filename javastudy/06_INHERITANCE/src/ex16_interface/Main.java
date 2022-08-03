package ex16_interface;

public class Main {

	public static void main(String[] args) {
		
		Dog dog = new Dog("백구");
		Cat cat = new Cat("냥냥이");
		Snake snake = new Snake("낼름이");

		Person person = new Person();
		person.foodFeed(dog, "개껌"); 		// 백구에게 개껌주기
		person.foodFeed(cat, "츄르"); 		// 냥냥이에게 츄르주기
		person.foodFeed(snake, "거미"); 	// 낼름이에게 거미주기
					   // ㄴ Pet타입으로 받아서 이름 전달 -> Pet pet 
	
		person.walk(dog);		// 백구와 산책
		person.walk(cat);		// 냥냥이와 산책
		//person.walk(snake);		// 실행을 못하게 막고 싶다 -> 없어야 하는 코드.
				//   ㄴ public void walk(Pet pet) { } -> 불가
				// public void walk(Cat cat) { }
				// public void walk(Dog dog) { }  
				// -> 일일히 만들면 가능은 하나, 동물이 100마리면 어떡할거냐? 인터페이스를 이용하자
				// Walkable 인터페이스 만들기
	
	}

	
	
}
