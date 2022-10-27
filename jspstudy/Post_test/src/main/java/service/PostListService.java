package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.PostDAO;

public class PostListService implements PostService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("posts", PostDAO.getInstance().selectAllPosts());
		request.setAttribute("count", PostDAO.getInstance().selectAllCount());
		
		/*
		ActionForward af = new ActionForward();
		af.setView("/post/list.jsp");
		af.setRedirect(false);
		
		return af;
		*/
		
		return new ActionForward("/post/list.jsp", false);
	}

}
