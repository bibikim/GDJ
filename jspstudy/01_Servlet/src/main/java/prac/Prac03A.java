package prac;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Prac03A")
public class Prac03A extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청
		request.setCharacterEncoding("UTF-8");
		
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String content = request.getParameter("content");
		
		// 파일명
		String filename = new Date(System.currentTimeMillis()) + "-" + from + ".txt";  // long타입의 날짜,시간은 timeStamp값
		
		// 디렉터리 생성
		File dir = new File(request.getServletContext().getRealPath("storage"));  // 이 서블릿의 context 가져와라 -> /01_Servlet을 말함
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		// 톰캣(==>jsp 서블릿보관장소)에 만들어지는 webapp의 실제 경로
		// C:\GDJ\jspstudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\01_Servlet\
		// request.getServletContext().getRealPath() == webapp
		
		// 가짜경로 C:\GDJ\jspstudy\01_Servlet --> 그냥 눈에 바로 보이는 경로..
		
		
		
		// 파일 객체
		File file = new File(dir, filename);
		
		// 문자 출력 스트림 생성
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		// 출력
		bw.write("To. " + to + "\n");
		bw.write(content + "\n");
		bw.write("From. " + from + "\n");
		bw.close();
			// 이 파일은 서버에 등록된 것! 따라서
			// 서버에 등록된 Context(01_Servlet)가 지워지면 txt 내용도 다 지워진다.
		
		// 이동
		response.sendRedirect("/01_Servlet/Prac03B?filename=" + URLEncoder.encode(filename,	"UTF-8"));
		           												// ㄴ 파일이름이 한글일 수 있으므로 encoding 필수
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
