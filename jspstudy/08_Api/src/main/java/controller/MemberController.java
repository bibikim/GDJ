package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.NaverCaptchaServiceImpl;


@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 / 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		// 요청 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// NaverCaptchaServiceImpl 객체 생성
		NaverCaptchaServiceImpl service = new NaverCaptchaServiceImpl();
		
		// ActionForward 객체
		ActionForward af = null;
		
		// 요청에 따른 Service 선택 및 실행
		switch(urlMapping) { 
		case "/member/loginPage.do" :
			// 캡차키 발급 요청
			String key = service.getCaptchaKey();    // key값으로 아무것도 넘어오지 않았다면 요청이 안 들어간걸 수도 있음. jsp 페이지에 경로확인해보자!
			// 캡차이미지 발급 요청
			Map<String, String> map = service.getCaptchaImage(request, key); // kye 발급 받은거 전달
			// map에 있는 dirname, filename을 꺼내서 넘겨주기   - request에 담아서 포워딩 준비
			request.setAttribute("dirname", map.get("dirname"));
			request.setAttribute("filename", map.get("filename"));
			request.setAttribute("key", map.get("key"));   // map에 있는 key를 key라는 이름의속성으로 request에 저장!
			   // ㄴ> login.jsp에 포워딩 된다
			
			// ActionForward 생성
			af = new ActionForward("/member/login.jsp", false);	// mvc패턴에서 데이터 넘기는 방법은 forward
			break;
		case "/member/refreshCaptcha.do" :
			service.refreshCaptcha(request, response);   // ajax이동이므로 페이지 이동 없이 이동
	        break;
		case "/member/validateCaptcha.do" :
			boolean result = service.validateUserInput(request);   // result 값을 받아오고 요청 전달~
			if(result) {
				af = new ActionForward("/member/success.jsp", false);  // rsult가 true이면(성공하면) success.jsp로 가는데 포워드로 간당
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('자동입력 방지문자를 확인하세요');");
				out.println("location.href='" + request.getContextPath() + "/member/loginPage.do'");  // 입력 실패 alert창 뜨고 난 뒤에 로그인페이지 다시 돌아가기(새 이미지 뜸)
				out.println("</script>");
				out.close();
			}
		}

		// 어디로 어떻게?
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
