package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.PostAddService;
import service.PostDetailService;
import service.PostListService;
import service.PostService;


@WebServlet("*.po")
public class PostController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		PostService service = null;
		
		ActionForward af = null;
		
		switch(urlMapping) {
		case "/post/list.po" :
			service = new PostListService();
			break;
		case "/post/add.po" :
			service = new PostAddService();
			break;
		case "/post/detail.po" :
			service = new PostDetailService();
			break;
		case "/post/write.po" :
			af = new ActionForward("/post/write.jsp", false);
			break;
			
		}
		
		
		try {
			if(service != null) {
				af = service.execute(request, response);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
