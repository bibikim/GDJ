package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Post;
import repository.PostDAO;

public class PostDetailService implements PostService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("postNo"));
		int postNo = Integer.parseInt(opt.orElse("0"));
		
		Post post = PostDAO.getInstance().selectPostByNo(postNo);
		
		if(post != null) {
			request.setAttribute("post", post);
			
			ActionForward af = new ActionForward();
			af.setView("/post/detail.jsp");
			af.setRedirect(false);
			return af;
		}
	
		
		
		return null;
	}

}
