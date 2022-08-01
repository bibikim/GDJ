package ex09_upcasting;

public class Main {

	public static void main(String[] args) {
		
		// Alba alba = new alba();
		// alba.eat();
		// alba.study();
		// alba.work();					원랜 이렇게 했음
		
		// Upcasting
		// 슈퍼클래스 객체 = new 서브클래스();
		
		Person alba = new Alba();	// Person 타입으로 study를 부를려면 Person에 study를 만들어두어라.
		alba.eat();		
		alba.study();			// 호출은 Person의 study, 실제 실행은 new Alba()를 보고 (override해서 다시 만들어진) Student타입의 study??????? 
		alba.work();			// 호출은 Person의 work, 실제 실행은 new Alba()를 보고 (override해서 다시 만들어진) Alba타입의 work
		// 펄슨이 알바자나.. 
		
		
		// new Student()와 new Alba()는 모두
		// Person타입으로 처리할 수 있다. (객체가 다른데 동일한 타입을 쓸 수 있다.)
		
		// 한 교실에 Student와 Alba가 섞여 있다.
		// 어떻게 처리할 것인가?
		// Person타입의 배열을 이용해서 모두 처리할 수 있다.
		
		Person[] people = new Person[10]; 	// 펄슨에 학생, 알바를 저장
		people[0] = new Alba();
		people[1] = new Alba();
		people[2] = new Student();   // 알바가 몇명이든 학생이 몇명이든 Person[]에 10 다 저장할 수 있다
		//. . .
		
		
		for(int i = 0; i < people.length; i++) {
			if(people[i] != null) {
			people[i].eat();
			people[i].study();
			people[i].work();		// 결과값에 student는 일 안하니까 일한다 안나옴. 먹고 공부하는데서 끝남
			}
		}
		
		// =  동일한 코드
		
		for(Person person : people) {
			if(person != null) {
				person.eat();
				person.study();
				person.work();
			}
		}
		
		// ★★자식은 언제나 부모타입으로 저장 가능하다.★★
		
		
	}

}
