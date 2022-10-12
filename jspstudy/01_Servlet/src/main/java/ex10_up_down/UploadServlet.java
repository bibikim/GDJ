package ex10_up_down;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
			1. FileUpload를 위한 COS 라이브러리    // 파일업로드를 하려면 라이브러리 추가
				1) http://servlets.com/
				2) [COS File Upload Library] - [Download] - [cos.22.05.zip]
				3) 압축 해제 후 lib 디렉터리의 cos.jar
		*/
		
		/*
			2. MultipartRequest 클래스
				1) COS 라이브러리가 지원하는 클래스
				2) HttpServletRequest 클래스는 파일 업로드 처리가 불가능함
				3) 파일 업로드를 위해서 MultipartRequest 클래스를 사용해야 함
		*/
		
		// 1. 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2. 업로드 할 경로
		//		1) 실제 서버 경로 사용(물론 일반 드라이브 경로로 바꿀 수 있음)
		// 		2) ServletContext의 realPath() 메소드를 이용
		//      3) 실무에서는 한 곳에 모이지 않도록 첨부 파일의 경로를 매번 바꿔줄 필요가 있음
		String realPath = getServletContext().getRealPath("upload");   // src/main/webapp(web root) 아래 upload.  모든 업로드는 storage에 저장
		
		// 3. 업로드 할 디렉터리 생성
		File dir = new File(realPath);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// 4. 업로드
		//    COS 라이브러리가 지원하는 MultipartRequest 객체를 생성하면 업로드가 진행됨
		MultipartRequest multipartRequest = new MultipartRequest(
				/* HttpServletRequest */  request,    // 이 리퀘스트로 새로운 request 만든거!
				/* 업로드 할 경로 */ 	  realPath,
				/* 최대 크기 */			  1024 * 1024 * 10,
				/* 인코딩 */			  "UTF-8",
				/* 파일명 중복 처리 */    new DefaultFileRenamePolicy()
										// a와 b가 같은 이름의 파일을 업로드할 경우, 파일명을 바꿔주는 정책(파일명 뒤에 번호붙이기)을 쓰겠다
										// 나중에는 스프링에서 파일명을 랜덤으로 바꿔서 올리는 처리를 하기도 함
				);
		
		// 5. 업로드 결과
		//    일반 리퀘스트인 HttpServletRequest는 사용할 수 없음.     일반 request가 아닌 multi~request 사용
		String uploader = multipartRequest.getParameter("uploader");   			 // 
		String originalName = multipartRequest.getOriginalFileName("filename");  // 원래 이름 // client.html의 name속성값인 파라미터 이름인 것!
		String filesystemname = multipartRequest.getFilesystemName("filename");  // 저장된 이름 (저장할 때 동일한 이름의 파일일 경우 이름 바꿔 저장될 수도 있음
		File file = multipartRequest.getFile("filename"); // file 객체를 알아낸 것.
		long size = file.length();   // 파일 크기(바이트)
		String strSize = new DecimalFormat("#,##0").format(size / 1024);
		long lastModified = file.lastModified();   // 파일의 최종 수정일(타임스탬프)
		String strLastModified = new SimpleDateFormat("yyyy-MM-dd a h:mm").format(new Date(lastModified));  // java.sql.date
		
		
		
		// 6. 업로드 결과 확인
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>작성자 : " + uploader + "</h3>");
		out.println("<h3>원래 파일명 : " + originalName + "</h3>");
		out.println("<h3>저장 파일명 : " + filesystemname + "</h3>");
		out.println("<h3>파일 크기 : " + strSize + "KB</h3>");
		out.println("<h3>최종 수정일 : " + strLastModified + "</h3>");
		out.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
