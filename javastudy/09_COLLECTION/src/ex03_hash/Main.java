package ex03_hash;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		/*
		Book book1 = new Book();
		System.out.println(book1.hashCode());		// 653305407 - book1객체의 참조값

		Book book2 = new Book();
		System.out.println(book2.hashCode());		// 1227229563 - book2객체의 참조값
		
		System.out.println(book1.equals(book2));	// 참조값 비교함. 다르니까 false
		
		// Hashcode
		// 자바의 객체 참조값 기반으로 해서 해시코드 값을 만든다.
		*/
		
		
		Book book1 = new Book(1, "어린왕자");
		Book book2 = new Book(2, "홍길동전");
		Book book3 = new Book(3, "소나기");
		Book book4 = new Book(3, "소나기");		
		// book3, 4는 같은 책. 자바의 해시에 의하면 둘의 참조값은 다름. 그래서 똑같을 리가 없어서 해시비교도 안 했을 것
		
		System.out.println(book3.hashCode());
		System.out.println(book4.hashCode());	// 같은 값 나옴. 같은 객체로 인식하게끔 hashCode와 equals 오버라이드
		
		Set<Book> books = new HashSet<Book>(); 	// 따지자면 책장!
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);		// 중복 저장 시도(정상 동작하려면 Book 클래스에 hashCode(), equals() 메소드를 오버라이드 해야 함)
														// ㄴ중복으로 들어가지 않게 하려면..!
		
		for(Book book : books) {
			System.out.println(book);
		}
		
		// hash라는 이름을 가진 애들은 hashCode와 equals를 오버라이드 해야 정상 동작이 가능하다.
		
	}

}
