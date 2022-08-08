package ex03_hash;

public class Book {

	private int bookNo;
	private String title;
	
	
	public Book(int bookNo, String title) {
		super();
		this.bookNo = bookNo;
		this.title = title;
	}


	@Override
	public String toString() {	// 정보 출력을 위한 toString
		return "Book [bookNo=" + bookNo + ", title=" + title + "]";
	}


	@Override						// hash알고리즘
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookNo;										// 알고리즘에서 책 넘버와 제목을 비교
		result = prime * result + ((title == null) ? 0 : title.hashCode());		// 책 넘버와 제목의 내용을 비교하게끔 짜져있는 알고리즘
		return result;															// 이 둘이 같으면 같은 해시값을 갖게 한다.
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookNo != other.bookNo)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
	
	
	
}
