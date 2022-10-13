package ex10_up_down;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 파라미터
		request.setCharacterEncoding("UTF-8");
		String filename = request.getParameter("filename");
		
		// 다운로드할 파일 경로
		String realPath = getServletContext().getRealPath("upload");
		
		// 다운로드할 파일 객체
		File file = new File(realPath, filename);    
		
		// 다운로드할 파일을 읽어 들일 바이트 기반 입력 스트림(file 객체를 읽어들이는 스트림 생성 => byte 기반의 입력 스트림)
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));  // 파일과 연결하는 파일인풋스트림
		
		// 다운로드 응답 헤더
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));  // 파일명 한글이면 인코딩 필수 따로 바깥에서 해도 됨ㅋ
		// 헤더 이름: Content=Disposition // 다운로드 받을 파일 이름을 filename으로 지정(그 이름으로 다운로드 완)
		response.setHeader("Content-Length", file.length() + "");  // 파일의 크기
		// 헤더는 모두 string 타입이어야 하기 때문에 long타입인 file.length를 string으로 만들어주기 위해 빈문자열 ""을 더해줌

		// 응답으로 내보낼 바이트 기반 출력 스트림 
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());    // 응답을 문자로 출력할 땐 printwriter의 getWriter() // 파일을 출력할 땐 getOutputStream()
		
		// 파일 복사 ( 10_IO/prac2 참고 )
		byte[] b = new byte[1024];  // 1kb씩 읽어 오기
		int readByte = 0;  // 실제로 읽어 들인 바이트 readByte
		while((readByte = in.read(b)) != -1) {   // 1024씩 읽어 들인(in.read(b)) 파일을 다 읽었으면 
			out.write(b, 0, readByte);   // 실제로 읽어들인 바이트 수만 반환받기 (ex. 2050바이트짜리 파일-> 1024만큼씩 읽고나서 남은 2만 읽어오게 하기)
		}
		
		out.close();
		in.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
