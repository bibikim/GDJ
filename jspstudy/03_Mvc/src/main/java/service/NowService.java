package service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class NowService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// NowService가 하는 일
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("a h:mm:ss");
		String now = sdf.format(date);
		
		// 뷰(jsp)로 전달할 데이터. 그 데이터를 request에 속성(여기서는 result라는 이름의 속성)으로 저장한다.
		request.setAttribute("result", now);
		
		// 어디로 갈 것인가?(응답 Jsp 명시)
		// 어떻게 갈 것인가?(리다이렉트 또든 포워드)
		ActionForward actionForward = new ActionForward();
		actionForward.setView("views/result.jsp");
		actionForward.setRedirect(false); 	// 포워드 하겠다.
		
		// ActionForward로 반환
		return actionForward;
		
	}
	
}
