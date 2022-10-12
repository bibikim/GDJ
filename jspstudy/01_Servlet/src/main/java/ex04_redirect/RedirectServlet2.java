package ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RedirectServlet2")
public class RedirectServlet2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터 a 확인
		String a = request.getParameter("a");
		// 최종목적지까지 도달하는게 아니라 1차 요청에서 1차 응답받고 사라짐. redirect에 의해 생긴 2차 요청에서는 사라진다
		// 1요청 1응답이기 때문에 a가 사라지는 것.
		// 내가 요청하고, 서버 내부에서 요청하고 해서 총 2번의 요청이 들어가는게 redirect
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<h1>Hello World</h1>");
		out.println("<h1>파라미터 a = " + a + "</h1>");
		out.close();
		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
