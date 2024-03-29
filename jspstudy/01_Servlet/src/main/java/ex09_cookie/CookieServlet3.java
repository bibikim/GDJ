package ex09_cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CookieServlet3")
public class CookieServlet3 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 쿠키 수정하기
		// 같은 이름의 쿠키를 만들어서 덮어쓰기
		Cookie cookie1 = new Cookie("address", URLEncoder.encode("서울시 금천구 가산동", "UTF-8"));
		cookie1.setMaxAge(60);
		response.addCookie(cookie1);
		
		// 쿠키 삭제하기
		// 같은 이름의 쿠키를 만들어서 유효시간을 0으로 설정하고 덮어쓰기
		Cookie cookie2 = new Cookie("name", "");
		cookie2.setMaxAge(0);
		response.addCookie(cookie2);
		
		// location 이동은 리다이렉트와 같음!
		out.println("<script>");
		out.println("location.href='/01_Servlet/CookieServlet2'");
		out.println("</script>");
		out.close();
		
		
		// 이동 누르면 name은 없어지고 address는 60초 유지했다가 지워짐
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
