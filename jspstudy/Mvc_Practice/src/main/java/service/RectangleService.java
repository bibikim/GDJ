package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class RectangleService implements AreaService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		// RectangleService가 할 일
		int width = Integer.parseInt(request.getParameter("width"));
		int height = Integer.parseInt(request.getParameter("height"));
		
		int rectangle = width * height;
		
		request.setAttribute("width", width);    // output.jsp에서 자바변수 갖다 쓰기 위해 request에 속성으로 저장
		request.setAttribute("height", height);
		request.setAttribute("area", rectangle);
		
		ActionForward actionForward = new ActionForward();
		actionForward.setView("views/output.jsp");
		actionForward.setRedirect(false);
		
		return actionForward;
		
	}
	
	
}
