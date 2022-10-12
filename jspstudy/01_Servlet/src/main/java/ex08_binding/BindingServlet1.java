package ex08_binding;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BindingServlet
 */
@WebServlet("/BindingServlet1")
public class BindingServlet1 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// stateless
		// 1. 상태 없음
		// 2. 웹 페이지 간 이동은 stateless
		// 3. 현재 페이지는 이전 페이지의 정보가 전혀 없음
		// 따라서 어딘가에 저장해서 값을 넘겨주는 작업이 필요 ↓↓↓

		
		// Binding
		
		// 1. 속성(Attribute)에 정보를 저장하고 사용하는 것   - 서블릿에서 속성 사용 가능
		// 2. 3개 영역을 사용
		//    1) ServletContext 	: 애플리케이션(프로젝트) 내부 어디서든 접근해서 사용 가능    				 Ex) 방문자수
		// 						 	  서블릿에 저장하면 애플리케이션 종료(=>서비스 종료)할 때까지 지워지지 않음
		//	  2) HttpSession    	: 브라우저 종료 전까지 접근해서 사용 가능 (창 닫기 전까지)    				 Ex) 로그인
		//    3) HttpServletRequset : 하나의 요청/응답 사이클 내에서 접근해서 사용 가능 (새로운 요청 전까지. 저장해서 요청하는 용도로.. 가장 많이 씀)
		// 		 ▶ 어디에 저장하느냐(컨텍스트패스, 세션, 리퀘스트)에 따라서 사용 가능한 범위가 달라진다
		// 3. 사용 메소드
		// 	  1) getAttribute('속성')     : 해당 속성 값 가져오기 (캐스팅이 필요할 수 있음)
		//	  2) setAttribute('속성', 값) : 속성에 값 저장하기    (Object 타입으로 저장)      // 값: object 뭐든지 저장 가능(byte, int, char, double, string ...)
		//    3) removeAttribute('속성')  : 해당 속성 삭제하기
		
		
		// 정보를 속성에 저장해두기    setAttribute()
		
		// ServletContext     ( HttpServlet -> GenericSevlet : 모든 서블릿의 부모. 부모가 가지고 있는 메소드 getServletContext(); )
		ServletContext ctx = getServletContext(); // 또는 request.getServletContext(); 리퀘스트를 통해서 알아낼 수도 있음
		ctx.setAttribute("a", 1);
		
		
		// ★★★ HttpSession ★★★
		HttpSession session = request.getSession();  // session은 request를 이용해서 알아냄
		session.setAttribute("b", 2);
		
		
		// HttpServletRequest     -> 위에 매개변수로 선언이 되어 있어서 그냥 쓰면 됨
		request.setAttribute("c", 3); 
		
		
		// 응답
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<a href=\"/01_Servlet/BindingServlet2\">이동</a>"); 	
		out.close();
		// "<a href=\"/01_Servlet/BindingServlet2\">  => 첫번째 요청.   [_이동_]을 누르면 BindingServlet2로 넘어가는 또다른 요청(=> 두번째 요청). 거기엔 c라는 정보는 없음. 
		// 이동을 누르기 전에는 c라는 속성 사용 가능하지만 이동을 누르는 건 새로운 주소로 이동하겠다는 새로운 요청 사이클의 시작이기 때문에 c가 유효하지 않아 접근 불가능한 것임
		// BindingServlet1이 요청(주소)-응답([_이동_]) 하나의 사이클
		// BindingServlet2가 [_이동_]을 누르고서부터의 새로운 요청 사이클 시작
		// a와 b는 정상 작동
		
		
		// ★★★ 포워드 ★★★
		
		// 1. request를 전달함
		// 2. 컨텍스트(서버) 내부 이동이므로 경로 작성 시 컨텍스트 패스는 작성하지 않음(이미 해당 컨텍스트에 도착해 있다)
		//     -> 포워드는 첫 요청을 그대로 살려서 사용할 수 있게 보내줌
		//request.getRequestDispatcher("/BindingServlet2").forward(request, response);  // 저장해놓은 것이 잘 넘어감. c도 넘어감
		// 리퀘스트에 저장해놓고 포워드로 보내주면 해당 정보를 다음 페이지에서도 사용할 수 있다. 값을 전달하는 대표적인 방법
		// 값의 전달이 필요한 경우 대표적으로 흔히 쓰는 패턴!!!
		
		
		// ★★★ 리다이렉트 ★★★
		// 1. request를 전달하지 않음
		// 2. 클라이언트 -> 서버로 이동하므로 컨텍스트 패스를 작성해야 함
		//response.sendRedirect("/01_Servlet/BindingServlet2");  // c = null
		// <a> 링크, locaiton 이동은 redirect로 이동  ==> 따라서 응답의 <a>때와 같은 결과값이 나옴.
		
		
		
		// 리퀘스트에 데이터 저장시키고 포워딩/리다이렉팅 하는 것의 차이!!!!!!!!!!! 매우 중요. 공부!~!~!~!
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
