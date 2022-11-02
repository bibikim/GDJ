package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.MemberService;
import service.MemberServiceImpl;
import service.NoticeService;
import service.NoticeServiceImpl;


@WebServlet("*.me")   // member니까 me로 이름지어준당. 정해진게 아님@!!
// 컨트롤러는 만들면 쌓여서 여러개가 만들어지는데, 그럼 구분하는 방법이 필요함. 근데 둘다 *.do라고 해버리면 어디로 가는지 컨트롤러가 못 찾음
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 서비스 하는 애들은 다 첫단계가 정보(파라미터) 받음 이런 애들은 다 공통모듈로 뭐가 들어가면 좋느냐 -> 파라미터를 확인하는 코드!
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 / 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// MemberServiceImpl 객체 생성             스프링에서는 getBean()으로 new @@을 만듦 --> 만들어져 있는 서비스 임플을 가지고 온다.
		NoticeService service = new NoticeServiceImpl();
		
		// ActionForward
		ActionForward af = null;
		
		// 요청에 따른 메소드 선택 및 실행 (MemberService 객체 하나고, 그 객체에 서비스를 하나로~ MemberService에 메소드 4개 만든거 그걸로!)
		switch(urlMapping) {
		case "/notice/list.no" :
			af = service.findAllNotices(request);
		// 매핑 잘못 작성한 경우
		default :
			System.out.println("매핑(case 뒤)을 확인하세요");
			
		}
		
		
		// 서비스 실행 없앤 이유 - 위에서 같이 하려고!
		
		// 어디로 어떻게?
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);   // history.back()으로 응답 했
			}
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
