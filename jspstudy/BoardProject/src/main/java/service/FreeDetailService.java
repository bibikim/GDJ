package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeDetailService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("freeNo"));
		int freeNo = Integer.parseInt(opt.orElse("0"));
		String ip = request.getRemoteAddr();
			
		
		Free free = FreeDAO.getInstance().selectByNoFree(freeNo);
		
		request.setAttribute("free", free);		
		
		return new ActionForward("/free/detail.jsp", false);
	}

}
