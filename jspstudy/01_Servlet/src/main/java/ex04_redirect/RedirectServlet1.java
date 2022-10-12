package ex04_redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RedirectServlet1")
public class RedirectServlet1 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ★ redirect 하는 방법! 외우기       (응답response를 이용)
		response.sendRedirect("/01_Servlet/RedirectServlet2"); // RedirectServlet2로 가라고 이동경로가 새로 잡힌 것
		// response를 이용해서 request를 함
		// redirect할 때, 파라미터에 저장되어있던 a(1차요청)는 2차요청에서 더이상 존재하지 않음
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
