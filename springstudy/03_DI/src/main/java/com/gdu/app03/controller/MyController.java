package com.gdu.app03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gdu.app03.domain.Board;
import com.gdu.app03.domain.Notice;


// 3장 DI == Dependency Injection


@Controller        // 자바 클래스로 컨트롤러를 만들고 @controller 애너테이션 작업해주기
public class MyController {

	
	/*
	 	@RequestMapping(value="/", method=RequestMethod.GET)
	 	@RequestMapping(value="/", method=RequestMethod.POST)
	 	
	 	Spring4부터 새로운 애너테이션으로 바꿀 수 있다.
	 	@GetMapping("/")    GET방식인 경우 사용
		@PostMapping("/")	POST방식인 경우 사용
	*/
	
	
	// 웰컴파일로 주소 요청 -> " contextPath = / " 컨텍스트패스로 요청되면,  return "default"; default.jsp로 이동해주라!
	@GetMapping("/")    // http://localhost:9090/app03으로 접속하면 처리되는 메소드
	public String welcome() {         // 메소드 하나가 요청 하나를 받는다. 반환타입은 string / void
		return "default";
		// ViewResolver에 의해서
		// return "/WEB-INF/views/default.jsp"로 해석된다.
		// ViewResolver는 servlet-context.xml에서 확인 가능
	}
	
	
	/*
	 	Container에 등록된 Bean을 가져오는 방법
	 	
	 	1. @Inject
	 		1) 타입(class)이 일치하는 Bean을 가져오는 애너테이션
	 		2) 동일 타입이 여러 개 있는 경우 @Qualifier를 이용해서 Bean을 식별
	 	2. @Resource
	 		1) 이름(bean의 id)이 일치하는 Bean을 가져오는 애너테이션
		3. @Autowired ★
			1) 타입(class)이 일치하는 Bean을 가져오는 애너테이션
			2) 동일 타입이 여러 개 있는 경우 자동으로 @Qualifier를 등록해서 Bean을 식별
			3) 실무에서 주로 사용
	*/
	
	/*
	 	@Autowired 사용 방법
	 	
	 	1. 필드로 생성된 Bean 가져오기(setter)
	 		1) 필드마다 @Autowired를 추가한다. (필드가 10개면 @Autowired도 10번 작성)
	 		2) 필드가 많으면 사용하지 않는다
	 	2. 생성자를 이용해 Bean 가져오기
	 		1) 생성자의 매개변수로 Bean을 가져온다.
	 		2) @Autowired를 작성할 필요가 없다.
	 	3. 메소드를 이용해 Bean 가져오기
	 		1) 메소드의 매개변수로 Bean을 가져온다.
	 		2) @Aotowired를 작성해야 한다.
	 		3) 일반적으로 setter를 사용하지만 setter가 아니어도 상관 없다.
	*/
	
	
	
	/* root-context.xml에 board2 주석 처리 하고 확인하기 */
	
	
	// 1. 필드로 생성된 Bean 가져오기
	// @Autowired
	// private Board board; 	// 가지고 오는 기준 :  @Autowired는 타입을 기준으로 움직인다. 타입이 일치하는 bean을 컨테이너에서 가지고 온다.
	
	/*
		@Autowired는 타입(class)이 일치하는 Bean을 Container에서 가져온다.
		
		@Autowired
		private Board board;   // ★★★ 타입(class)이 Board인 Bean을 Container에서 가져오거라.
		
		---------- Container -----------
		<bean id="board1" class="Board">
		<!-- <bean id="board2" class="Board"> -->   board2는 주석처리하고 보기.
		---------------------------------
		
		★★★★★★★★★★★★★★★★★★
		====> private Board board; 에서  ^Board^를 보고 가지고 온 것이다.
		====> @Autowired에서 필드이름(board)은 아무 역할도 안한다. @Autowired는 오로지 타입만 봄!
	*/
	
	// -> Autowired가 필드에 컨테이너의 bean을 가지고 왔기 때문에
	// 밑에 작업과 확인이 가능한 것.
	
	
	
	
	
	// 2. 생성자를 이용해서 Bean 가져오기            // 필드를 적고, 생성자를 만든다. @Autowired는 작성하지 않아도 된다.
	// private Board board;
	
	//@Autowired 		// 생성자에는 @Autowired를 생략할 수 있다.
	// public MyController(Board board) {		// 매개변수 Board board로 타입이 Board인 Bean을 Container에서 가져오거라.
												// 생성자가 있으면, 생성자의 매개변수를 보고 해당 타입의 Bean을 가져오는 것임. 특별한 언급이 없어도 그 일을 한다!
	// 	super();
	//	this.board = board;  // -> 컨테이너에서 가져온 bean을 필드로 넘겨주는 코드. 그러니까 필드가 container에서 가져온 빈으로 주입된 상태.
	//}
	/*
		@Autowired로 가져오란 얘기도 안 했는데 어떻게 가져왔냐? 
		└> 생성자의 매개변수로 Bean을 가져옴! 어디로 가져온거냐? -> 필드가 아닌 생성자의 매개변수로 가져옴.
		여기선 public MyController(Board board) 에서 ^Board^를 보고 가져온 것. 생성자에 있는 Board타입(생성자의 매개변수)을 보고 가져온 것임!!
		
		메소드에 붙어있을 땐 백프로 매개변수에 붙는다..?
		// 생성자도 (특별한)메소드. 모든 메소드는 자동으로 주입된다.
	*/

	
	
	
	
	/*
	// 3. 메소드를 이용해 Bean 가져오기
	private Board board;
	

	@Autowired		// 생성자가 아닌 일반 메소드로 Bean을 가지고 올땐 @Autowired를 반드시 작성해야 한다.
	public void setBoard(Board board) {		// 매개변수 Board board로 Board타입의 Bean을 Container에서 가져오거라. 
		this.board = board;
	}		
	// 이쪽으로 Board타입의 Bean을 가져와서 집어 넣는 것.
	// public void setBoard(Board board)에서 ^Board^를 보고 가져온 것. setBoard() 메소드의 매개변수 Board타입을 보고 가져온 거당
	// 메소드 이름은 어떻게 주든 상관 없지만 세터로 만들면 매개변수가 같이 만들어지기 때문에 기본적으로 setter를 사용한다.
	*/
	
	
	
	
	
	/* board2 주석 풀고 보기 */
	
	// 4. 동일한 타입의 Bean이 여러 개 등록된 경우
	//	   1) 변수명을 자동으로 식별자(@Qualifier)로 인식한다.
	// 	   2) 식별자(@Qualifier)는 Bean의 이름(id)이 일치하는 Bean을 가져온다.   -> Board타입이 여러개라 하나로 특정할 수 없어서 가져오는 걸 실패했으니 이름으로 가져오겠다!
	// 			따라서, 변수이름을 Bean의 id와 맞춰주면 가지고 오는데 문제 없음!
	
	
	// 4-1. 필드로 생성된 Bean 가져오기
	/*
	@Autowired
	private Board b1;
	
	@Autowired
	private Board b2;
	*/
	
	// board1, board2 라고 이름을 잡아주면 정상적으로 돌아가는데 b1, b2로 바꾸면 오류가 난다. -> 타입도 똑같은게 n개지, b1/b2라는 이름도 없지~ 그러니 오류 ㅇㅇ
	// @Autowired의 동작원리를 알 수 있다.
	// class="Board" 인걸 찾아라 -> 컨테이너에 가보니 Board가 2개임. 
	// -> 타입으로 Board를 찾는건 실패라고 생각함
	// -> 플랜비로 이름(id)이 일치하는 것을 찾아 나선다. 각각 board1, board2이라는 id를 가진 애들은 하나니까 가지고 옴.
	// 타입으로 가지고 오지 못할 때 @Autowired 안에 내장된 기능으로 @Qualifier를 통해 변수명으로 찾아옴. 변수명의 역할이 그때서야 부여되는 것(그전엔 아무일도 안햐) 
	
	
	
	
	// 4-2. 생성자를 이용해 Bean 가져오기
	/*
	private Board b1;
	private Board b2;
	
    // myController에서 만든 생성자이니까 이름은 myController인거다
	public MyController(Board board1, Board board2) {  	// 매개변수명이 Bean의 이름(id)과 일치하므로 자동으로 주입된다.
		b1 = board1;   // 필드로 주입되는 게 아닌 이쪽에 주입되는 것.. 그러니 정상적인 실행이 되는 것. ㄴ즉 매개변수 이름과 빈 이름이 같아야 한다
		b2 = board2;
	}
	*/
	

	// 4-3. 메소드를 이용해 Bean 가져오기
	private Board b1;
	private Board b2;

	// 생성자가 아니니 @Autowired는 필수로 붙여야 된다!
	@Autowired
	public void setB1(Board board1, Board board2) {      // 매개변수만 Bean의 id와 맞춰주면 된다!! b1이 아닌 board1으로 수정
		this.b1 = board1;
		this.b2 = board2;
	}
	// @Autowired가 붙어있는 메소드의 매개변수에 자동으로 주입되는 거로 이해하기
	// setter를 활용하지만 세터를 사용하는게 아니라 메소드의 매개변수로 주입되는 거!!

	/*
	public void setB1(Board board1) {
		this.b1 = board1;				▲
	}                          		────┘ 	두개의 setter 하나로 만들기~~~  생성자 생김새처럼~~ 메소드 이름은 상관없으니까 삽가넝
	public void setB2(Board board2) {
		this.b2 = board2;
	}
	*/



	@GetMapping("board/detail")   // @GetMappin("/board/detail")  슬래시를 붙이든 안 붙이든 상관 없다.
	public void boardDetail() {        // 수업동안 mapping가지고 이름을 만들거양
		// root-context에 등록한 내용대로 나오는지 확인! 
		System.out.println(b1.getBoardNo());
		System.out.println(b1.getTitle());
		System.out.println(b1.getCreateDate());
		System.out.println(b2.getBoardNo());
		System.out.println(b2.getTitle());
		System.out.println(b2.getCreateDate());
	}



	
	// @Autowired를 사용하는 이유  :  @Inject + @Qualifier  (간단하게 작업 가능)
	/*
	@Inject
	@Qualifier(value="board1")
	private Board b1;     // 쿼없으면 실패, 쿼로 인해 board1이란걸 찾아서 가져옴
	
	@Inject
	@Qualifier(value="board2")
	private Board b2; 
	*/
	
	
	
	/// Notice //////////////////////////////////////////////////////////////////////////////////////////////
	
	// 4-1. 필드로 생성된 Bean 가져오기
	// 여기서 필드 이름과 SpringBeanConfig의 메소드 이름을 맞춰야 내부적으로 @Qualifier가 작동해서 둘을 연결시켜준다!!
	/*
	@Autowired
	private Notice notice1;

	@Autowired
	private Notice notice2;
	*/

	
	// 4-2. 생성자를 이용해 Bean 가져오기
	
	private Notice notice1;
	private Notice notice2;
	
	public MyController(Notice notice1, Notice notice2) {
		this.notice1 = notice1;    // 생성자의 매개변수를 필드로 보내주는 코드
		this.notice2 = notice2;
	}
	
	
	// 4-3. 메소드를 이용해 Bean 가져오기
	/*
	private Notice notice1;
	private Notice notice2;
	
	@Autowired
	public void setNotice1(Notice notice1, Notice notice2) {
		this.notice1 = notice1;
		this.notice2 = notice2;
	}
	*/

	@GetMapping("notice/detail")
	public void noticeDetail() {
		System.out.println(notice1.getNoticeNo());
		System.out.println(notice1.getTitle());
		System.out.println(notice2.getNoticeNo());
		System.out.println(notice2.getTitle());
	}
	
	
	
	
}
