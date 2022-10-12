package ex07_naver_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

@WebServlet("/PapagoServlet")
public class PapagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클라이언트 아이디, 시크릿
		String clientId = "DJxqiwv2wqFur2KBtCxx";
		String clientSecret = "nq6dLkY86Z";
		
		// 요청 파라미터(원본언어, 목적언어, 번역할텍스트)
		String source =  request.getParameter("source");
		String target =  request.getParameter("target");
		String text =  request.getParameter("text");
		
		// 번역할텍스트 UTF-8 인코딩
		try {
			text = URLEncoder.encode(text, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("번역할 텍스트 인코딩 실패");
			out.close();
		}
		
		// API 접속
		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
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
			out.println("API 접속에 실패했습니다.");
			out.close();
		}
		
		// API 요청
		try {
			// 요청 헤더
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
			// 요청 메소드
			con.setRequestMethod("POST");
			con.setDoOutput(true);  // post방식에서 보낼(전송할) 데이터(파라미터)가 있을 때 같이 써줘야 하는 코드. 데이터가 있냐? true 응 있어
			// 파라미터를 ? 붙여서 뒤에 붙여주면 get방식
			// post방식은 파라미터(보내려는 데이터)가 붙지 않음 -> 본분(body)에 붙여서 가야됨(내 입장에서는 출력스트림. 출력스트림을 통해서 보내주는 코드를 짜줘야 함)
			
			// 요청 파라미터 보내기
			String params = "source=" + source + "&target=" + target + "&text=" + text;
			OutputStream outputStream = con.getOutputStream(); // byte배열(outputStream은 byte기반 스트림)로 전송할 데이터. string으로는 못 보내기 때문에 byte로 바꿔주는 작업
			outputStream.write(params.getBytes()); // string을 byte[]로 바꿔주는 메소드 getBytes();
			outputStream.close(); // stream을 이용해 직접적으로 보내줄 데이터 붙여주기
			
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 요청이 실패했습니다.");
			out.close();
		}
		// 받을때는 문자 기반(Reader)으로 받는다 (json은 char타입이니까!)
		//
		BufferedReader reader = null;
		try {
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream())); // 바이트스트림을 가져온담에->리더로 문자로 
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("응답 스트림 생성이 실패했습니다.");
			out.close();
		}
		
		// API 응답
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while((line = reader.readLine()) != null) {
					sb.append(line);
			}
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 응답이 실패했습니다.");
			out.close();
		}
		
		// client.html로 API 응답 결과(StringBuilder) 보내기
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
