package library;

import java.util.Scanner;

public class Library {

	private Scanner sc;			// 객체의 선언. 생성은 X     Scanner ctrl + space = java.util
	private Book[] books;		// 마찬가지 (배열)
	private int idx;			// 추가,삭제,조회 다 쓸 수 있게 필드로 index 선언
	
	public Library() {			// 생성자. 인수 및 매개변수 X = 빈칸~
		sc = new Scanner(System.in);
		books = new Book[100];
	}
		
	// 4개의 메소드 생성~
	private void addBook() {
		if(idx == books.length) {		// 체크 사항은 제일 첫 줄에!
			System.out.println("더 이상 등록할 수 없습니다.");
			return;
		}
		System.out.println("===책 등록===");
		System.out.print("제목 입력 >>> ");
		String title = sc.next();	// 문자열입력이니까 next()
		System.out.println("저자 입력 >>> ");
		String author = sc.next();
		Book book = new Book(idx + 1, title, author);		// new Book쓰고 ctrl sp - 두번째거 선택
		// ㄴ 책 한권 만든거!   ㄴ책 번호 1부터 100까지니까 +1
		books[idx++] = book;	// books[99++] =100, idx: 0~99 =100, books.length =100
		
	}
	
	private void removeBook() {			// 책 번호로 삭제
		if(idx == 0) {					// idx에 저장된 만큼이 책의 갯수
			System.out.println("등록된 책이 한 권도 없습니다.");
			return;
		}
		System.out.println("===책 삭제===");
		System.out.println("삭제할 책의 번호(1~" + idx + ") >>> ");
		int bookNo = sc.nextInt();
		if(bookNo < 0 || bookNo >= idx) {
			System.out.println("책 번호가 " + (bookNo + 1) + "인 책은 없습니다.");
			return;		// 더 이상 진행 않겠다.
		}
		System.arraycopy(books, bookNo + 1, books, bookNo, idx - bookNo - 1);   // 통째로 덮어쓰기
		books[--idx] = null;  
		System.out.println("책 번호가 " + (bookNo + 1) + "인 책을 삭제했습니다.");
	}
	
	// idx가 10인 상황 가정.  idx=10
	// books[0] >> 실제 bookNo =1 책번호는 1~100까지 부여받지만 실제로는 idx로 들어가기 땜에 0~99
	// books[2]를 삭제한다고 하자.
	// books[2] <- books[3] 2 삭제하면 3으로 덮어쓰기        배열은 null값을 가지면 초기화됨
	// books[3] <- books[4]
	// ...
	// books[8] <- books[9]
	// books[9]를 [8로] 덮어쓰고도 books[9]는 그대로 남아 있으니까 null값을 줘서 초기화 시켜야함. 
	// 									ㄴ books[9] = null;   idx--;
	// copy(books, bookNo + 1, books, bookNo, 7)
	//      ------------원본-, ------복사되는곳-
	
	
	private void findBook() {
		if(idx == 0) {					// idx에 저장된 만큼이 책의 갯수
			System.out.println("등록된 책이 한 권도 없습니다.");
			return;
		}
		System.out.println("===책 조회===");
		System.out.println("조회할 책 제목 입력 >>> ");
		String title = sc.next();						// 동일한 책은 하나만 있다.
		for(int i = 0; i < idx; i++) {
			// 저장된 책 제목 : getTitle  ->  그냥 title은 Library(외부)이기 때문에 내부정보인 title을 호출할 수 없다. 
			//                            ->  프라이빗(내부) 정보를 호출하기 위해 쓰는 것이 getter니까 getTitle을 호출하면 완.
			// 조회할 책 제목 : title
			// String의 동등비교 : equals() 메소드
			if(books[i].getTilte().equals(title)) {  // = if(title.equals(books[i].getTitle())
				System.out.println(books[i]);		 // 같은 책 제목이 있으면 보여준다.
				return;		// 안 넣으면 계속 비교하기 때문에 한번만 비교하고 그만두게 걸어놔야 함.
							// findBook() 메소드 종료.
							// break 넣으면 for문이 끝남. 그럼 메소드도 끝남. ~책이 없읍니다도 출력이 돼버리기 때문에 불가.
							// 찾는 책이 없다 = return 찾았으면 잔말말고 끝내게 해야 함.
			}
		}
		System.out.println("제목이 " + title + "인 책은 없습니다.");
		// ----> 니가 찾는 책이 없다. for문이 끝난 상황은 찾는 책이 없는 상황. 다 비교해보고 마지막에 없습니다 출력하기 위한 위치는 for문이 끝나고!
	}
	private void printAllBooks() {
		if(idx == 0) {					// idx에 저장된 만큼이 책의 갯수
			System.out.println("등록된 책이 한 권도 없습니다.");
			return;
		}
		System.out.println("===전체 조회===");
		for(int i = 0; i < idx; i++) {		// 5권이면 0~4까지 5개
			System.out.println(books[i]);   // 배열에 저장된 책 한권. arr[i] = 배열에 저장된 하나라는 듯
											// Book클래스에 toString 넣어줘야 제대로 된 정보 출력
		}
	}
	
	// ㄴ 내부에서 사용하기로했는데 아직 한번도 사용하지 않았기 때문에 경고 메세지 뜨는 것
	
	// 그동안은 필드를 private으로 했는데, 메소드도 private으로 안 보이게 할 수 있음. 
	// 메소드들을 외부에서 접근해서 볼 수 있게 밑줄에 메소드 만들기
	public void manage() {
	
		while(true) {		// 무한루프. 안 끝나는 프로그램
			System.out.print("1.추가 2.삭제 3.조회 4.전체목록 0.프로그램 종료 >>> ");
			int choice = sc.nextInt();	// 정수값.
			sc.nextLine();	// 불필요한 엔터 제거할 때.
			switch(choice) {
			case 1: addBook(); break;	// choice가 1이다, 그러면 addBook메소드를 호출하고 break
			case 2: removeBook(); break;
			case 3: findBook(); break;
			case 4: printAllBooks(); break;		// break = switch를 끝내려고
			case 0: System.out.println("Library 프로그램을 종료합니다. 감사합니다.");
					return;      // manage() 메소드 종료. (반환타입이 void일 때 return은 프로그램 종료시킨다.)
			default : System.out.println("알 수 없는 명령입니다. 다시 시도하세요.");
			}
			
		}
		
		
		
		
	}
	
	
	
	
		
}
