package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardAddService;
import service.BoardDetailService;
import service.BoardEditService;
import service.BoardListService;
import service.BoardModifyService;
import service.BoardRemoveService;
import service.BoardService;

/**
 	요청을 받아주는 건 서블릿
 */
@WebServlet("*.do")   // * 들어가면 / 없어야 됨
public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 및 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청 확인
		String requestURI = request.getRequestURI();    // 전체 요청 확인         전체: contextPath/urlMapping
		String contextPath = request.getContextPath();  // contextPath 값         컨패: contextPath
		String urlMapping = requestURI.substring(contextPath.length() + 1);       // urlMapping값 가져오기. urlMapping값에 따라 switch로 실행
		
		// BoardService  선언
		BoardService service = null;
		
		// ActionForward 선언
		ActionForward af = null;
		
		// 요청(urlMapping)에 따른 Service 생성
		switch(urlMapping) {
		// 비즈니스 로직이 있는 경우 -> 해당 비즈니스 로직을 사용하기 위한 service가 필요
		case "board/list.do" :
			service = new BoardListService();    // list.do 요청을 처리하는건 BoradListService다!
			break;
		case "board/detail.do" : 					// 요청은 detail.do로 하고
			service = new BoardDetailService();     // BoardDetailService를 줄게!
			break;
		case "board/add.do" :
			service = new BoardAddService();
			break;
		case "board/edit.do" :
			service = new BoardEditService();
			break;
		case "board/modify.do" :
			service = new BoardModifyService();
			break;
		case "board/remove.do" :
			service = new BoardRemoveService();
		// 비즈니스 로직이 없는 경우(단순이동)  -> ActionForward 이용해서 어디로 어떻게 가겠다라는 것만 알려주면 된다.
		case "board/write.do" :
			// 작성할 수 있는 페이지로 넘어가기 => 단순 이동. 따라서 af만 여기서 만들어서 넘기면 됨
			af = new ActionForward();
			af.setView("/board/write.jsp");
			af.setRedirect(false); // 단순이동은 forward. 굳이 redirect로 할 필요 없음
			break;
		}
		
		// Service 실행
		try {
			if(service != null) {   // 단순이동 시 
				af = service.execute(request, response);   // 여기서 BoardListService로부터 받은 Exception을 받아야 하므로 try-catch
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 어디로 어떻게?    갈 것인지는 af 가 가지고 있음. 서비스실행이 안되면 null을 계쏙 가지고 잇을거기때문에 널체크 해줘야함
		if(af != null) {
			if(af.isRedirect()) {           // 리다이렉트면 밑으로 응답해라
				response.sendRedirect(af.getView());   								 // getView() 이동할 경로
			} else {						// 포워드면 밑으로 가라
				request.getRequestDispatcher(af.getView()).forward(request, response);   
			}
		}
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
