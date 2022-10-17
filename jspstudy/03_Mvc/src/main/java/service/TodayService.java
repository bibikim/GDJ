package service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class TodayService implements MyService {

	@Override    // 오버라이드 생략하지마!!
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// TodayService가 할 일
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		
		request.setAttribute("result", today); // 데이터 저장 가능한 영역중 하나인 request.  today이란 이름으로 today의 값을 실었당
												// 어떤 서비스를 실행하든  result 속성이 생김
	
		// 어디로 갈 것인가?(응답 Jsp 명시)
		// 어떻게 갈 것인가?(리다이렉트 또든 포워드)
		ActionForward actionForward = new ActionForward();
		actionForward.setView("views/result.jsp");  // 뷰라는 필드값으로
		actionForward.setRedirect(false); 	// 포워드 하겠다.
		
		// ActionForward로 반환
		return actionForward;
		
		
 	}
	
}
