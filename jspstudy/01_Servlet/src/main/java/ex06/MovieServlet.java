package ex06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클라이언트 아이디, 시크릿
		String clientId = "DJxqiwv2wqFur2KBtCxx";
		String clientSecret = "nq6dLkY86Z";
		
		// 요청 파라미터(검색어, 검색 결과수)
		request.setCharacterEncoding("UTF-8");
		String query = request.getParameter("query");
		String display = request.getParameter("display");
		
		
		// 검색어 UTF-8 인코딩
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			response.setContentType("text/plain; charste=UTF-8");   // 에러용 응답(예외 떨어지는 경우의 응답)
			PrintWriter out = response.getWriter();
			out.println("검색어 인코딩 실패");   // error의 responseText로 넘어감
			out.close();
			// catch 블럭 자체는 error로 넘어가고 println()은 responseText로 가서 출력되는 것
		}
		
		// API 접속    -> javastudy 12_network 참고
		String apiURL = "https://openapi.naver.com/v1/search/movie.xml?query=" + query + "&display=" + display;  // 물음표 뒤에 파라미터를 바로 붙여주는 게 GET방식
		URL url = null;																				// ㄴ숫자가 넘어오는거니까 UTF-8 처리 필요 없음
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
		} catch(MalformedURLException e) {	// 주소 형식이 잘못된 경우의 예외
			response.setContentType("text/plain; charste=UTF-8"); 
			PrintWriter out = response.getWriter();
			out.println("API URL이 잘못되었습니다. ");
			out.close();
		} catch(IOException e) {   // url connection 접속 실패
			response.setContentType("text/plain; charste=UTF-8");  
			PrintWriter out = response.getWriter();
			out.println("API 연결이 실패했습니다.");
			out.close();
		}
		
		// API 요청
		try {
			// 요청 메소드
			con.setRequestMethod("GET");
			// 요청 헤더
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret); 
		} catch(IOException e) {
			response.setContentType("text/plain; charste=UTF-8");  
			PrintWriter out = response.getWriter();
			out.println("API 요청이 실패했습니다.");
			out.close();
		}
		
		// API 응답 스트림 생성(정상 스트림, 에러 스트림) -> 네이버로부터 xml을 읽어드리는 스트림
		BufferedReader reader = null;
		try {
			int responseCode = con.getResponseCode();	 // 응답코드(status)를 의미함
			if(responseCode == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));   // 정상 스트림
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));   // 에러 스트림
			}
		} catch(IOException e) {
			response.setContentType("text/plain; charste=UTF-8");  
			PrintWriter out = response.getWriter();
			out.println("API 응답 스트림 생성이 실패했습니다.");
			out.close();
		}
		// con.getInputStream() (->byte 스트림) ------> reader (->char 문자스트림)
		// 커넥션으로부터 입력스트림(InputStream)을 얻어낸다(한글 포함이면 한글이 깨지기 때문에 이걸 InputStreamReader로 바꿔줘야 한글 멀쩡)
	
		// API 응답 데이터 저장하기
		StringBuilder sb = new StringBuilder();
		String line = null;  // 한줄씩 읽어드리기 위함
		try {
			while((line = reader.readLine()) != null) {
				sb.append(line);    // 응답 데이터 sb에 저장
			}
		} catch(IOException e) {
			response.setContentType("text/plain; charste=UTF-8");  
			PrintWriter out = response.getWriter();
			out.println("응답이 실패했습니다.");
			out.close();
		}
		// System.out.println(sb.toString());   // <item> 태그 기준으로 영화 한 편의 정보가 들어있음
		
		// client.html로 API 응답 결과 보내기
		response.setContentType("application/xml; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
