package ex05_forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ForwardServlet1")
public class ForwardServlet1 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Forward
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ForwardServlet2");  // 어디로 보낼지 다른 경로 줌
		requestDispatcher.forward(request, response);   // requestDispatcher가 forward를 통해 요청응답을 넘겨줌
	}

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
