package ex09_cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CookieServlet2")
public class CookieServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 쿠키 가져오기  - 요청을 통해서 쿠키값을 가지고 올 수 있음(골라서 X, 저장되어있는 쿠키 모두 가져옴)
		Cookie[] cookies = request.getCookies();
		
		
		// 전체 쿠키 확인
		for(int i = 0; i < cookies.length; i++) {
			out.println("<h2>쿠키이름: " + cookies[i].getName() + "</h2>");
			out.println("<h3>쿠키값 : " + URLDecoder.decode(cookies[i].getValue(), "UTF-8") + "</h3>");  // 인코딩했기 때문에 꺼낼 때는 반대의 과정인 디코딩을 해줘야 한다
		}
		
		
		// 원하는 쿠키만 확인 (쿠키이름이 name인 쿠키 찾기)
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("name")) {
			out.println("<h2>쿠키이름: " + cookies[i].getName() + "</h2>");
			out.println("<h3>쿠키값 : " + URLDecoder.decode(cookies[i].getValue(), "UTF-8") + "</h3>"); 
			}
		}
		
		out.println("<a href=\"/01_Servlet/CookieServlet3\">이동</a>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
