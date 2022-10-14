package domain;

public class Board {
	
	// 이 클래스 모두가 요런걸 bean이라고 한다. 객체! 네트워크로 빈 주고받는게 웹 개발인거고!
	

	// 필드값 -> 데이터를 직접 접근하는게 막혀 있으므로(private) 게터세터로~ 
	private int boardNo;
	private String title;
	private int hit;
	
	// 생성자
	public Board() {
		
	}

	public Board(int boardNo, String title, int hit) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.hit = hit;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
	
}
