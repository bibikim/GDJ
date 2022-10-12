package ex03_parameter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AnchorServlet")

public class AnchorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AnchorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청
		request.setCharacterEncoding("UTF-8");
		
	
		// 연습2 요청 파라미터 a, b
		String strA = request.getParameter("a");
		String strB = request.getParameter("b");
		
		/*
		int a = 0;
		if(strA != null) {
			a = Integer.parseInt(strA);
		}
		int b = 0;
		if(strB != null) {
			b = Integer.parseInt(strB);
		}
		*/
		
		
		int a = 0;
		int b = 0;
		if(strA != null && strB != null) {
			a = Integer.parseInt(strA);
			b = Integer.parseInt(strB);
		}
		
		
		
		// PrintWriter plus = response.getWriter();

		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();  // response.getWriter()가 반환하는건 PrintWriter뿐이라서 얘만 사용하는 것!
		out.println("<h1>Hello World</h1>");
		out.println("<h3>" + a + " + " + b + " = " + (a+b) + "</h3>");
		out.flush();
		out.close();
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
