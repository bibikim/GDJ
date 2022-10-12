package ex03_parameter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청
		request.setCharacterEncoding("UTF-8");
		
		// 변수(파라미터)
		String id = request.getParameter("id");  // 파라미터의 이름
		// if(id.equals(""))   // 자바에서 문자열 비교 .equals  : id가 빈 문자열이면,
		if(id.isEmpty()) { // isEmpty가 자바 6버전 이상부터 지원하는 것. isBlank는 11부터 지원하는거라 잘 안 쓴다!
			id="빈 아이디"; 
		}
		// 파라미터가 오기로 했는데 안왔음 : null처리 <-> 파라미터가 오긴 왔는데 비어있음 : isEmpty()
		// if(id.isEmpty() || id == null){}  -> 어떻게 처리해야될지 모르겠으면 둘다 처리
	     
		// if(id == null || id.isEmpty()){}  -> 이렇게 짜면 안돼!!!!!!!!!! 
		// null이 왔다고 치면 null.isEmpty => null포인트가 메소드를 부르고 있음을 알리는 nullpointException 예외 떨어짐
		//        id가 널이라면 true니까 뒤에 코드 진행 X (isEmpty를 안한다는것) -> shortcircuit
		// ||(OR) 처리할 때, 앞에서부터 순차적으로 온다. null이 뒤에 오면 하나마나
		String pwd = request.getParameter("pwd");  // 파라미터의 이름
		if(pwd.isEmpty()) { // isEmpty가 자바 6버전 이상부터 
			pwd="빈 비밀번호"; 
		}
		String gender = request.getParameter("gender");
		if(gender==null) {
			gender="빈 성별";
		}
		// 체크박스와 라디오는 체크를 하나도 안 해서 보내면 서버로 아예 가질 않음 -> null
		String city = request.getParameter("city");
		if(city.isEmpty()) {
			city ="빈 도시"; 
		}
		
		// null체크가 안되는 이유, 파리미터=빈문자열로 서버로 보내긴 보냈기 때문!
		
		
		// 배열(동일한 파라미터가 여러 개인 경우)
		String[] phone = request.getParameterValues("phone");   // ParameterValues!!!
		// 입력하지 않고 보내면 "" "" "",  입력을 하든 안하든 3개의 데이터를 가지고 있음. 비어 있을 뿐 3개의 input은 넘어옴
		if(phone[0].isEmpty()) {
			phone[0] = "빈 전화1";
		}
		if(phone[1].isEmpty()) {
			phone[1] = "빈 전화2";
		}
		if(phone[2].isEmpty()) {
			phone[2] = "빈 전화3";
		}
		String strPhone = phone[0] + "-" + phone[1] + "-" + phone[2];
		
		
		String[] agreement = request.getParameterValues("agreement");
		if(agreement == null) {
			agreement = new String[1];     // 내용이 없는 배열..?
			agreement[0] = "모두 비동의";
		}
		// 데이터가 전송되지 않았을 때 각종 예외같은 것들은 백, 프론트 모두 체크해주는 것이 안전하고 좋다!
		
		
		// 연습(이메일)
		String emailId = request.getParameter("email_id");
		String domain = request.getParameter("domain");


		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>아이디 : " + id + "</h3>");  // printwriter를 쓴다는건 println을 쓰겠다는 목적이라 볼 수도 있음 ㅇㅇ
		out.println("<h3>비밀번호 : " + pwd + "</h3>");
		out.println("<h4>성별 : " + gender + "</h4>");
		out.println("<h4>도시 : " + city + "</h4>");
		out.println("<h4>연락처 : " + strPhone + "</h4>");
		out.println("<h4>동의여부 : " + Arrays.toString(agreement) + "</h4>");  // 배열에 있는걸 string으로 꺼내서 출력
		out.println("<h4>이메일 : " + emailId + "@" + domain);
		// -- 마케팅 동의 체크 여부 --
		List<String> list = Arrays.asList(agreement); // 문자열 배열을 List로 바꾸는 방법 
		// list에 저장할 데이터는 모두 String
		// list로 변환시키면 for문 돌리지 않고도 어떤 데이터가 있는지 없는지 if로 검사 가능. 
		if(list.contains("marketing")) {   // 메소드는 List가 가지고 있는 contains() 사용!
			out.println("<h4>마케팅 동의한 회원입니다.</h4>");
		} 
		
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
