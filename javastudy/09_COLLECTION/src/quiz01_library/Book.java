package quiz01_library;

public class Book {

		private int bookNo;	
		private String tilte;	
		private String author;	
		
	
	public Book() {			// setter
		
	}


	public Book(int bookNo, String tilte, String author) {
		super();
		this.bookNo = bookNo;
		this.tilte = tilte;
		this.author = author;
	}
	
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getTilte() {
		return tilte;
	}
	public void setTilte(String tilte) {
		this.tilte = tilte;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", tilte=" + tilte + ", author=" + author + "]";
	}

	// 책이 3권이 저장되어 있다.
	// 저장 안 된 한 권의 책과 배열 안에 동일한 책이 있는지 확인하고 싶다. Object equals()
	// 참조값을 비교하기 때문에 4권 모두 모두 참조값이 달라서 false가 떨어질거다. 
	// 내용이 동일한지를 판단하는게 아니라 다른 객체니까 다르다고 판단.(6장 상속의 object class부분 참고)
	// 같은 내용을 가진 책은 동일한 객체임을 인식시키기 위해 equals를 오버라이드 해준다
	// source - generate hashcode and equals   이클립스가 만들어준당~ 땡큐
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookNo != other.bookNo)
			return false;
		if (tilte == null) {
			if (other.tilte != null)
				return false;
		} else if (!tilte.equals(other.tilte))			// 하나라도 다르면 false
			return false;
		return true;		// 저자, 타이틀, 북넘버 모두 같으면 true
	}
	
	// 객체간의 내용을 비교할 땐 equals의 오버라이드가 필요하다!
	
	
	
	
	
}
