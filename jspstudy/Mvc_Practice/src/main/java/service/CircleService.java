package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class CircleService implements AreaService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	     
	      int radius = Integer.parseInt(request.getParameter("radius"));
	      
	      double circle = Math.pow(radius, 2);
	      
	      request.setAttribute("area", circle);
	      request.setAttribute("radius", radius);
	      
	      ActionForward actionForward = new ActionForward();
	      actionForward.setView("views/output.jsp");
	      actionForward.setRedirect(false);
	      
	      return actionForward;

	}

}
