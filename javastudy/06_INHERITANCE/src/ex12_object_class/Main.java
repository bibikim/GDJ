package ex12_object_class;

public class Main {

	public static void main(String[] args) {
		
		// Object 클래스는 모든 객체나 변수를 저장할 수 있다.
		// Object는 모든 클래스의 슈퍼클래스
		Object p = new Person(); 	
		// 먹는다 출력 안됨. why? Object 클래스를 호출(Object p)했지만 거기엔 eat이 없으니까..!
		// 때문에 다운캐스팅해서 다시 자식클래스로 내려가서 호출한다.
		
		// Object 클래스타입의 객체는
		// 항상 다운캐스팅해서 사용해야 한다. 그래야 정상적인 호출 가능
		if(p instanceof Person) {
			((Person) p).eat();		// 객체 쓰고 . 점 찍고 eat 호출
		}
		
		// 새로운 Person(toString() 확인용)
		Person person = new Person();
		person.setName("james");
		
		System.out.println(person);			// 이름 : james로 나오는지?!
		// System.out.println(person.toString());   
		// 둘이 같은거! person만 출력해도 실제론 내부적으론 toString이 작동하고 있는 것. Object가 가지고 있는 toString메소드를 가져다가 출력
	
	
		// 새로운 Person(equals() 확인용)
		// name이 같으면 동일한 객체로 인식하기
		Person p1 = new Person();
		Person p2 = new Person();
		p1.setName("kim");
		p2.setName("kim");		// 이 둘은 다른 객체. equals로 비교하면 두개 객체의 참조값을 비교하기 대문에
								// equals를 Object용으로 그냥 쓰면 객체가 저장된 메모리의 참조값으로 비교하기 때문에 false라고 나온다
								// 때문에 오버라이드 필요
		System.out.println(p1.equals(p2)); 	// 동일한 name은 true. equals 오버라이드 통해서 나옴
	
	}
	
}
		// p.equals(p)    동일한 객체인지 체크
		// p.getClass 	  p가 어떤 클래스냐 체크
		// p.toString()   객체가 가진 정보를 문자열 형태로 반환
	


