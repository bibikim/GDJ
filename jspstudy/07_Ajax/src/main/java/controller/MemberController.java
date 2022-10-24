package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.MemberAddService;
import service.MemberDetailService;
import service.MemberListService;
import service.MemberService;


@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 / 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");    // 스크립트 태그 응답할때 쓴 contentType = text/html. ajax 처리할 때는 사용안함
		
		// 요청 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// MemberService 객체
		MemberService service = null;
		
		// ActionForward
		ActionForward af = null;
		
		// 요청에 따른 Service 선택
		switch(urlMapping) {  // /member/manage.do
		case "/member/manage.do" :  // 멤버폴더의 manag.jsp로 이동하기. 이동할 때 사용할 객체 af. ajax는 이동이 없으므로 여기서 처음이자 마지막으로 사용함
			af = new ActionForward("/member/manage.jsp", false);  //  단순 이동이니까 서버 아래서 forward 처리. 
			break;
		case "/member/list.do" :
			service = new MemberListService();
			break;
		case "/member/detail.do" :
			service = new MemberDetailService();
			break;
		case "/member/add.do" :
			service = new MemberAddService();
			break;
		}
		
		// 선택된 Service 실행
		try {		// 보드 서비스에서 Exception을 던지고 있기 때문에 try-catch 필요
			if(service != null) {
				 service.execute(request, response);   // 서비스는 void 타입의 반환. af 반환이 아니므로 af= 삭제
			}
		} catch(Exception e) {
			e.printStackTrace();
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
