package ex05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TextServlet")
public class TextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 새로운 예외를 처리하기 위한 try-catch 블럭
		try {
		
			// 요청
			request.setCharacterEncoding("UTF-8");
			
			String name = request.getParameter("name");
			int age= Integer.parseInt(request.getParameter("age"));   // age가 null 또는 int가 아닐 때 예외 발생 소지 있는 부분 (NumberFormatException)
			// age 파라미터를 전달하지 않았을 경우(data에서 name파라미터만 보낸 경우) : NumberFormatException 발생 (500코드)
			// 파라미터가 정수가 아닌 null값 혹은 소숫점이 들어오면 NumberFormatException 예외 발생
			
			if(age < 0 || age > 100) { 		// 자바가 모르는 예외 상황은 직접 만들어줘야 함. 예외별로 예외 응답코드를 다르게(1000, 2000) 줘야 실무 가능
				throw new RuntimeException(age + "세는 불가능한 나이입니다."); 
			}
				
			
			// 응답    - client.html의 /*응답*/과 맞춰주기  res
			response.setContentType("text/plain; charset=UTF-8");  // 단순 text의 MIME-TYPE(종류: text/html, text/plain, application/xml, application/json)
								  // text/plain이기 때문에 이 서블릿을 실행하면 단순 텍스트가 나온당
			PrintWriter out = response.getWriter();
			out.println("이름은 " + name +"이고, 나이는 " + age + "세입니다.");  // ()안이 resData로 넘어가는 데이터
			out.close();
			
		} catch(NumberFormatException e) {
			// 예외가 발생하면 응답은 실행되지 않고(응답처리X) catch 블럭으로 넘어옴
			// 따라서 예외 처리를 위한 응답을 따로 만들어준다.
			response.setContentType("text/plain; charset=UTF-8");
			
			response.setStatus(1000);	// 개발자가 임의로 작성한 status(응답 코드)
			
			PrintWriter out = response.getWriter();
			out.println("예외 발생 : 파라미터 age는 정수입니다.");   // 개발자가 임의로 작성한 responsText
			out.close();
			
		} catch(RuntimeException e) {
			
				response.setContentType("text/plain;charset=UTF-8");
				
				response.setStatus(2000);
				
				PrintWriter out = response.getWriter();
				out.println(e.getMessage());   // RuntimeException 예외 객체 e에 저장된 예외 메시지를 responseText로 처리~
				out.close();
				
		}
		
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
