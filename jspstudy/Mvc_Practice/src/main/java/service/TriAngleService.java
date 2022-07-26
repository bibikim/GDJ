package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class TriAngleService implements AreaService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int width = Integer.parseInt(request.getParameter("width"));
		int height = Integer.parseInt(request.getParameter("height"));
		
		int triangle = (width * height) / 2;
		
		request.setAttribute("area", triangle);
		request.setAttribute("height", height);
		request.setAttribute("width", width);
		
		ActionForward actionForward = new ActionForward();
		actionForward.setView("views/output.jsp");
		actionForward.setRedirect(false);
		
	
		return actionForward;
		
	}

}
