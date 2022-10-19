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
import service.BoardListService;
import service.BoardRemoveService;
import service.BoardService;


@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 / 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// BoardService 객체
		BoardService service = null;
		
		// ActionForward
		ActionForward af = null;
		
		// 요청에 따른 Service 선택
		switch(urlMapping) {
		// 비즈니스 로직
		case "/board/list.do" :     // contextPath의 length에 +1이 없기 때문에 매핑이 /부터 시작!
			service = new BoardListService();
			break;
		case "/board/detail.do" :
			service = new BoardDetailService();
			break;
		case "/board/add.do" :
			service = new BoardAddService();
			break;
		case "/board/remove.do" :
			service = new BoardRemoveService();
			break;
		// 단순이동(포워딩)
		case "/board/write.do" :
			af = new ActionForward();
			af.setView("/board/write.jsp");  // service를 실행시키는게 아니라 af 통해서 write.jsp로 이동시키기
			af.setRedirect(false);  // boolean 데이터는 기본값이 false. 따라서 이 코드는 생략 가능하다. 생략하면 false로 알아서 작성함.
			break;
		}
		
		// 선택된 Service 실행
		try {		// 보드 서비스에서 Exception을 던지고 있기 때문에 try-catch 필요
			if(service != null) {
				af = service.execute(request, response);   // 실행결과 af에 받아주고
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
