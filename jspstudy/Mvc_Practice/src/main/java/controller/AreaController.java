package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.AreaService;
import service.RectangleService;
import service.TriAngleService;


@WebServlet("*.do")
public class AreaController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		AreaService areaService = null;;
		ActionForward actionForward = null;
		
		switch(command) {
		case "input.do" :
			actionForward = new ActionForward();
			actionForward.setView("views/input.jsp");
			
			break;
		case "triangle.do" :
			areaService = new TriAngleService();
			break;
		case "rectangle.do" :
			areaService = new RectangleService();
			break;
		}
		
		// 선택한 모델의 실행. 무슨 모델이든 여기서 실행됨
		if(areaService != null) {
			actionForward = areaService.execute(request, response);
		}
		if(actionForward != null) {
			if(actionForward.isRedirect()) {
				response.sendRedirect(actionForward.getView());
			} else {
				request.getRequestDispatcher(actionForward.getView()).forward(request, response);	
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
