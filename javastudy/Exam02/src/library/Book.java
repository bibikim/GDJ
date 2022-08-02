package library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor		// new Book()
@AllArgsConstructor 	// new Book(1, "어린왕자", "생텍쥐베리")
@Getter		// getBookNo(), getTitle(), getAuthor()
@Setter		// setBookNo(1), setTitle("어린왕자"), setAuthor("생텍쥐베리")
@ToString	// String toString() {. . .}  System.out.println(books[i]);에서 쓰기 위해 만든 것! 
			// = source - generate toString()   -> 이건데 lombok이 쉽게 만들어쥼
public class Book {
	private int bookNo;		// 1~100 자동부여
	private String tilte;	// 사용자 입력
	private String author;	// 사용자 입력
	
}
