package ex05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.XML;


@WebServlet("/XMLServlet")
public class XMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    // JSON 라이브러리는 JSON을 만들어주고 JSON을 XML로 바꿔주는 역할도 해줌
    public XMLServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		// 응답할 JSON 객체 만들기
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("age", age);
		JSONObject person = new JSONObject();
		person.put("person", obj);  // "프로퍼티이름", 집어넣을 값
		
		// 응답할 JSON 객체를 XML로 변환하기
		String responseXML = XML.toString(person);
		
		// 응답 (이 부분이 중요행! ★application/xml★ text 반환했을 때와 다르지 않다!) 
		response.setContentType("application/xml; charset=UTF-8");  // JSON 데이터의 MIME-TYPE
		
		/*
		  XML 데이터 결과(resData) ▼
		    <person>
		  		<name>김연아</name>
		  		<age>33</age>
		  	</person>
		*/
		
		PrintWriter out = response.getWriter();
		out.println(responseXML);	// 응답 데이터는 XML
		out.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
