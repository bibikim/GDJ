package ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/my")  // @WebSevlet({"/my", "/me"}) 처럼 2개 이상의 URL Mapping을 지정할 수 있음. <- 두 개의 URLMapping값 주는 방법. 주소가 달라도 같은 창을 보여줌.
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 요청
		//	  1) 클라이언트 -> 서버로 보내는 요청 또는 데이터
		//	  2) HttpServletRequest request 객체가 처리 (Tomcat이 있어야 사용 가능)
		
		// 1) 요청에 포함된 한글 처리(문자셋 : UTF-8)
		request.setCharacterEncoding("UTF-8");  // 맨날 하게 될 일..!
		
		// 2) 요청 파라미터(Parameter)
		//	  (1) URL?파라미터=값&파라미터=값
		// 	  (2) 모든 파라미터는 String 타입! (숫자를 보내도 문자로 인식)
		String name = request.getParameter("name");  // 파라미터는 request에서 꺼낸다.
		String strAge = request.getParameter("age"); // ("") 안에가 주소창에 적어주는 parameter 이름이 된다!
		/*
		 int age = Integer.parseInt(strAge); 
		System.out.println(name + ", " + age);    - > null처리 하기 전에는 500 오류
		*/
		
		// 문자열을 정수로 바꾸는 처리
		int age = 0;
		if(strAge != null) {			
			age = Integer.parseInt(strAge);
		}
		/*
		    ? 뒷부분을 완성하지 않았기 때문에 네임에이지 다 null값, 그렇다고 널을 가진다고 오류가 나진 않는데
		    int age에 parseInt 한 strAge에 null이 들어갔기 때문에 500 오류가 남
		    null 처리를 위해 if~~
		    어떤 파라미터도 처리하지 않았으니 null과 0이 뜸
		    null 처리가 필요한 이유: 500 오류 피하기 위해
		
	    	 주소창 입력할 때마다 doGet이 계속 실행.
     		 서블릿 뒤에 ?파라미터=값 적어주면 콘솔창에 파라미터의 값이 넘어옴
		*/
		
		
		// 2. 응답
		//	  1) 서버 -> 클라이언트로 보내는 응답
		//    2) HttpServletResponse response 객체가 처리 (Tomcat이 있어야 사용 가능)
		
		// 1) 사용자(클라이언트)에게 전달할 데이터의 형식을 HTML 문서로 결정한다.
		//    MIME-TYPE을 활용
		//    (1) HTML : text/html
		//    (2) CSS  : text/css
		//    (3) JS   : text/javascript
		//    (4) XML  : application/xml
		//    (5) JSON : application/json
		response.setContentType("text/html");    // 사용자한테 html 문서 보내겠다!    ContentType = MIME-TYPE 
		
		// 2) 응답에 포함되는 한글 처리
		response.setCharacterEncoding("UTF-8");
		
		// ★ 1) MIME-TYPE + 2) 문자셋   -> 동시에 처리하는 방법
		response.setContentType("text/html; charset=UTF-8");
		
		// 3) 응답 스트림 생성
		//    (1) 문자 출력 스트림(*Writer)을 생성            (서버측 입장에선 출력)
		//    (2) response 객체로부터 PrintWriter 객체를 얻을 수 있음
		//        - IOException 예외 처리가 필요한데 이미 처리되어 있음
		//	      - write() 메소드보다는 print()/println() 메소드를 사용하는 것을 권장(PrintWriter는 print(), println()도 지원)
		//           ㄴ>write메소드는 줄바꿈을 보내주지 않아서..
		PrintWriter out = response.getWriter();    // try-catch가 없는데 오류가 나지 않음. -> doGet() 메소드에서 처리중임 throws IOException
		
		// 4) 응답 만들기 (HTML 문서 만들기)
		out.println("<html lang=\"ko\">");     // 자바에서는 \가 이스케이프니까 "" 넣고싶으면 \ 쓰기
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>");
		out.println("나의 첫 번째 응답");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>안녕하세요. " + name + "님 반갑습니당~</h1>");
		if(age >= 20) { 
			out.println("<h1>귀하는 " + age + "살이므로 입장이 가능합니다.</h1>");
		} else {
			out.println("<h1>" + age + "살? 애들은 다음에 오렴</h1>");
		}
		out.println("</body>");
		out.println("</html>");
		
		out.flush();  // 출력 스트림에 남아 있는 모든 데이터 내보내기(만약을 위한 작업)
		out.close();
		// 우클릭 페이지소스보기 하면 한 줄씩 들어가 있는거 확인 가능 -> println() 써서 그래~~
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
