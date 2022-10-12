package ex07_naver_api;

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


@WebServlet("/MovieJSONServlet")
public class MovieJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clientId = "DJxqiwv2wqFur2KBtCxx";
		String clientSecret = "nq6dLkY86Z";
		
		// 요청
		request.setCharacterEncoding("UTF-8");
		
		String query = request.getParameter("query");
		String display = request.getParameter("display");
		

		
		// 검색어 인코딩
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("검색어 인코딩 실패");
			out.close();
		} 
		
		// API 접속
		String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + query + "&display=" + display;
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
		} catch(MalformedURLException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API URL이 잘못되었습니다.");
			out.close();
		} catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 연결이 실패했습니다.");
			out.close();
		}
		
		// API 요청
		try {
			con.setRequestMethod("GET");
			
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		} catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 요청이 실패했습니다.");
			out.close();
		}
		
		// API 응답 스트림
		BufferedReader br = null;
		try {
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
		} catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("스트림 생성에 실패했습니다.");
			out.close();
		}
		
		// 응답 데이터 저장하기
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("응답에 실패했습니다.");
			out.close();
		}
		
		
		// client.html로 응답 결과 보내기
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
