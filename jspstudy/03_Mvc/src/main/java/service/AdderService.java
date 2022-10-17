package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class AdderService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 요청 파라미터
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));
		
		// 응답을 만드는 작업
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('" + a + "+" + b + "=" + (a + b) + "')");
		out.println("history.back()");		// 직접 응답을 만들 때에는 history.back() or location.href를 한다
		out.println("</script>");
		
		// 직접 응답한 경우에는 ActionForward를 반환하지 않는다. 이미 응답처리 되었기 때문.(요청-응답이 끝났으니 return 할 필요가 없음)
		return null;
	}
	
	
	// ==> 모델이 직접 응답을 만들어주는 경우에는 actionForward(널이아닐때만실행) 반환이 아닌 null 반환
	// AdderService는 myService로 등록이 됨 -> execute가 실행됨 -> 받아온건 null -> forward는 진행되지 않음 -> 이동하는 코드는 동작하지 않음
	// -> 이동은 adderService 안에 있음.  history.back()이 이동
	

}
