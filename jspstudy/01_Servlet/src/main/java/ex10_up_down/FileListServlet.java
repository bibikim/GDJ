package ex10_up_down;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileListServlet
 */
@WebServlet("/FileListServlet")
public class FileListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		// upload 디렉터리의 경로
		String realPath = getServletContext().getRealPath("upload");   // httpServlet의 부모인 GenericSevlet이 가지고 있는 거라 가져다 쓰기 가능
	
		
		// upload 디렉터리에 저장된 파일 목록 가져오기
		File dir = new File(realPath);
		File[] files = dir.listFiles(); // files에 있는 것들을 배열로 가져옴

		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		for(int i=0; i < files.length; i++) {
			out.println("<div><a href=\"/01_Servlet/DownloadServlet?filename=" + files[i].getName() + "\">" + files[i].getName() + "</a></div>"); // 파일의 목록(파일이름)을 클릭하면 다운로드가 동작하도록 링크 만들기
		}
		// 파일정보가 DownloadServlet으로 넘어가야 함. 그래야 뭘 다운로드 됐는지 알 수 있음. 파일명을 파라미터로 보내주기 
		// a 링크에 -> "/01_Servlet/DownloadServlet?filename=파일명" 추가 작업
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
