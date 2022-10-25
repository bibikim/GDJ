package controller;

import java.io.IOException;
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
			// map에 있는 dirname, filename을 꺼내서 넘겨주기
			request.setAttribute("dirname", map.get("dirname"));
			request.setAttribute("filename", map.get("filename"));
			// ActionForward 생성
			af = new ActionForward("/member/login.jsp", false);	// mvc패턴에서 데이터 넘기는 방법은 forward
			break;
		case "/member/refreshCaptcha.do" :
			service.refreshCaptcha(request, response);   // ajax이동이므로 페이지 이동 없이 이동
	        break;
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
