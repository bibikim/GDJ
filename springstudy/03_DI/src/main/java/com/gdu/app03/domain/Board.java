package com.gdu.app03.domain;

public class Board {
	// container애 bean을 만들거임
	// 1. xml로 만들기 <bean>
	// 2. java로 만들기 @Bean
	// 만든 빈을 가지고 오는 방법 - 1) genericApplication~   2) annotationConfig~  -> getBean()
	// 지금부터는 가지고 오는 방법을 새로 배울거야~ 
	// @Inject 만들어져 있는 빈을 변수에다 집어넣기
	// @Autowired  변수랑 관련된 빈을 묶어주기
	// MVC패턴에서 xml(<bean>) 만드는 곳이 달라짐.(원래는 main/resources인데 거기다 아닌 다른 곳에 만들거임)
	// --> 1장에서는 resources 폴더 하위에 xml 폴더를 두고 appCtx라는 파일을 뒀지만,
	// 이제부터는 web-inf/spring/root-context.xml에 만들거야~
	
	
	// field
	private int boardNo;
	private String title;
	private String createDate;
	
	// constructor
	public Board() {      // 디폴트 생성자
		
	}

	public Board(int boardNo, String title, String createDate) {    // 매개변수가 있는 생성자
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.createDate = createDate;
	}

	// getter/setter
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
	
	
}
