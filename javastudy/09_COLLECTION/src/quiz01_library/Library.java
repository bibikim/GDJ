package quiz01_library;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Library {

	private Scanner sc;
	private List<Book> books;
	// private int idx;   배열이 없어지면서 idx도 같이 없어짐! 노필요

	
	
	public Library() {
		sc = new Scanner(System.in);
		books = new ArrayList<Book>();	// 길이 조정 필요 없음
	}
	
	private void addBook() {
		System.out.println("===책 등록===");
		System.out.println("책 번호 입력 >>> ");
		int bookNo = sc.nextInt();
		System.out.print("제목 입력 >>> ");
		String title = sc.next();	
		System.out.println("저자 입력 >>> ");
		String author = sc.next();
		Book book = new Book(bookNo, title, author);
		books.add(book);
	}
	
	private void removeBookByIndex() {		// 책번호로 삭제
		// Book remove(int index) 메소드 이용
		if(books.isEmpty()) {
			throw new RuntimeException("등록된 책이 한 권도 없습니다.");	// throws 생략
		}
		System.out.println("===책 삭제===");
		System.out.println("삭제할 책의 번호 >>> ");
		int bookNo = sc.nextInt(); 
		for(int i = 0, length = books.size(); i < length; i++) {
			if(books.get(i).getBookNo() == bookNo) {
				Book removeBook = books.remove(i);
				System.out.println(removeBook + " 책을 삭제했습니다.");
				return;
			}
		}
		throw new RuntimeException("책 번호가 " + bookNo + "인 책을 삭제했습니다.");
	}

		
	private void removeByObject() {			// 책이름으로 삭제
		// boolean remove(Object ojb) 책을 전달하면 바로 지워줌
		if(books.isEmpty()) {
			throw new RuntimeException("등록된 책이 한 권도 없습니다.");	// throws 생략
		}
		System.out.println("책 번호 입력 >>> ");
		int bookNo = sc.nextInt();
		System.out.print("제목 입력 >>> ");
		String title = sc.next();	// 문자열입력이니까 next()
		System.out.println("저자 입력 >>> ");
		String author = sc.next();
		Book book = new Book(bookNo, title, author);
		if(books.remove(book)) {
			System.out.println(book + " 책을 삭제했습니다.");
			return;
		}
		throw new RuntimeException(book + " 책을 삭제했습니다.");
	}
	
		
	private void findBook() {
		if(books.isEmpty()) {	
			throw new RuntimeException("등록된 책이 한 권도 없습니다.");
		}
		System.out.println("===책 조회===");
		System.out.println("조회할 책 제목 입력 >>> ");
		String title = sc.next();						// 동일한 책은 하나만 있다.
		
		for(int i = 0, length = books.size(); i < length; i++) {
			//books.get(i) = 저장된 책.
			if(books.get(i).getTilte().equals(title)) {  // = if(title.equals(books[i].getTitle())
				System.out.println(books);		 // 같은 책 제목이 있으면 보여준다.
				return;	
			}
		}
	System.out.println("제목이 " + title + "인 책은 없습니다.");
	}
	
	
	private void printAllBooks() {
		if(books.isEmpty()) {					// idx에 저장된 만큼이 책의 갯수
			throw new RuntimeException("등록된 책이 한 권도 없습니다.");
		}
		System.out.println("===전체 조회===");
		for(int i = 0, length = books.size(); i < length; i++) {		
			System.out.println(books.get(i)); 
		}
	}
	
	
	public void manage() {
		
		while(true) {
			try {
			
				System.out.print("1.추가 2.삭제 3.조회 4.전체목록 0.프로그램 종료 >>> ");
				int choice = sc.nextInt();	// 정수값.
				sc.nextLine();	// 불필요한 엔터 제거할 때.
				switch(choice) {
				case 1: addBook(); break;	// choice가 1이다, 그러면 addBook메소드를 호출하고 break
				case 2: removeBookByIndex(); break;
				case 3: findBook(); break;
				case 4: printAllBooks(); break;		// break = switch를 끝내려고
				case 0: System.out.println("Library 프로그램을 종료합니다. 감사합니다.");
						return;      // manage() 메소드 종료. (반환타입이 void일 때 return은 프로그램 종료시킨다.)
				default :System.out.println("알 수 없는 명령입니다. 다시 시도하세요.");
				}
				
			} catch(InputMismatchException e) {
				sc.next();
				System.out.println("명령은 정수입니다.");
			} catch(RuntimeException e) {
				System.out.println(e.getMessage());
				
			}
			
		}
	
	
	}

		
		
		
		
}
	
	
	

