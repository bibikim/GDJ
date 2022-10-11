package ex01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
	Servlet
	
	1. 웹 화면(html)을 만드는 자바 클래스
	2. JSP/Servlet Container(Tomcat)에 의해서 실행
	3. 서블릿을 실행하면 웹 브라우저가 실행됨
	4. URL
		프로토콜://호스트:포트번호/ContextPath        		-- 여기까지가 이 프로젝트를 실행했다, ContextPath가 같으면 동일한 프로젝트란 얘기
		프로토콜://호스트:포트번호/ContextPath/URLMapping 	-- URLMapping이 서블릿이라 보면 됨. 주소에 프로젝트 내에 어떤 서블릿 파일이 실행됐는지 나옴. 
		                                                    -- 서블릿이 다르면 서블릿마다 뒤에 주소가 다름! 동일한 URLMapping 값을 가질 수 없음
		http://localhost:9090/01_Servlet/HelloServlet
 */
@WebServlet("/HelloServlet")  // URL Mapping 값     // 똑같은 URLMapping 값이 2개 이상이면 Tomcat이 돌지 않음..
public class HelloServlet extends HttpServlet {  // Servlet은 HttpServlet을 상속 받으면 된다.

	/*
		1. 생성자
			1) 가장 먼저 호출
			2) 생성자 호출 뒤 init() 메소드가 호출
	*/
    public HelloServlet() {
        super();
        System.out.println("생성자");
    }

    /*
    	2. 초기화
    		1) 각종 초기화 정보 실행
    		2) 서블릿 환경 설정 처리
    		3) init() 메소드 호출 뒤 service() 메소드가 호출
    */
	public void init(ServletConfig config) throws ServletException {
		// 원하면 여기서 config를 열어서 작업 수행 가능 (config = 환경설정)
		System.out.println("init");
	}

	/*
		3. 서비스
			1) 클라이언트의 요청마다 매번 호출
			2) GET 방식의 요청은 doGet() 메소드 호출, POST 방식의 요청은 doPost() 메소드 호출(로 연결함)
			
		- 클라이언트의 요청을 받아서 해당 클라이언트의 요청을 1)직접 처리하거나 2)doGet/doPost에 넘겨주는 역할을 함
		- 2)의 경우, 어떤 요청인지 요청메소드를 파악해서 요청메소드(get이면 doGet을, post면 doPost)를 열어주는 역할을 하는게 서비스
		- service가 가지고 있는 변수 request(요청), response(응답) -> 서블릿 실행 역할을 하는게 service
		- get이든 post든 무조건 service로 넘어오는데, 서블릿이 요청할 때마다 알아서 doGet()이나 doPost()가 호출된다.
	*/
	// 서비스가 없으면 서비스가 백그라운드에서 2)를 수행함
	// 그러나 개발자가 서비스를 만들어버리면 기본 베이스 동작을 하지 않게 됨
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service");
		
		// service 메소드가 존재하는 경우 doGet()/doPost()가 자동으로 호출되지 않기 때문에 코드를 아래와 같이 직접 작성
		
		// 요청 메소드 확인 : request.getMethod()    -> GET인지 POST인지 확인 가능 (요청에 관련된 정보는 request에 들어있음)
		switch(request.getMethod()) {
		case "GET":
			doGet(request, response);   // doGet() 메소드 호출
			break;
		case "POST":
			doPost(request, response);  // doPost() 메소드 호출
			break;
		}
		// 사용자가 요청한 정보request 돌아갈 정보response 모두 service의 변수로 넘어옴
		// doGet, doPost로 단순 전달 됨
		// 서비스가 갖고 이쓴 request, response를 doGet 메소드로 넘겨주는 것만 함
		
		// 결론은, 개발자가 만들 필요 없음! 안 하면 알아서 해줌ㅇㅇ service는 공부 안 해도 되는 부분
	}

	/*
		4. doGet
			1) GET 방식의 요청을 처리
			2) 요청 메소드가 GET인 경우
				(1) $.ajax({
						'type': 'GET'
						'url': 'http://localhost:9090/01_Servlet/HelloServlet'        -> 이렇게 요청하면 바로 밑에 doGet으로 떨어짐!
					});
				(2) <a href="http://localhost:9090/01_Servlet/HelloServlet">링크</a>
				(3) <form method="GET" action="http://localhost:9090/01_Servlet/HelloServlet"></form> 또는 <form></form>   
				                         -> action으로 서브밋(==form)할 때 GET이라 하면 doGet으로 넘어옴. method는 생략 가능
				(4) location.href='http://localhost:9090/01_Servlet/HelloServlet'     -> JS
					open('http://localhost:9090/01_Servlet/HelloServlet')			  -> JS
					--> 주소창을 이용한 방식은 전부 GET방식
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// response : 사용자에게 전잘할 때 사용
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 객체 response가 사용되고 있음. 응답이 나오면 서버가 사용자에게 보내주겠다란 뜻!
		// request는 사용자가 서버로 보내주는 것
		// rseponse.getWriter()는 PrintWriter를 반환해 줄 것임(Writer : 문자 기반의 출력 스트림)
		// ContextPath = /01_Servlet
	
		// 실행 결과 : Served at: /01_Servlet
	}

	/*
		5. doPost
			1) POST 방식의 요청을 처리
			2) 요청 메소드가 POST인 경우
				(1) $.ajax({
						'type': 'POST'
						'url': 'http://localhost:9090/01_Servlet/HelloServlet'
					});
				(2) <form method="POST" action="http://localhost:9090/01_Servlet/HelloServlet"></form>
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST:나한테 요청을 해? 해~ 근데 나는 일 안 할거야 니(GET)가 해줘~
		// 즉, POST가 GET한테 넘겨버림. post방식으로 넘겨줘도 get이 일을 함.
		// doHandle()  -> get도 post도 서로 떠밀면(요청을 받는 역할만 수행하면), doHandle()이라는 별도의 메소드를 만들어 response를 작업 수행
		// 어쨌든, get으로 요청하면 바로 응답 넘겨주고, post로 요청하면 get으로 넘겨서 처리. doGet이 다~ 한다.
		
		// 요청과 응답 정보를 doGet() 메소드로 넘겨버림
		doGet(request, response);   // doGet() 메소드 호출
		
		
		// Servlet 실행은 톰캣이 한다.
		// 톰캣이 켜져 있으면 직접 주소url치고 들어가면 실행이 되는데
		// 톰캣이 꺼져 있으면 직접 주소url치고 들어가는거 불가능
		
	}

}
