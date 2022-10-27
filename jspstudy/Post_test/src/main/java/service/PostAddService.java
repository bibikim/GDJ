package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Post;
import repository.PostDAO;

public class PostAddService implements PostService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Post post = Post.builder()
				.writer(writer)
				.title(title)
				.content(content)
				.build();
		
		int result = PostDAO.getInstance().insertPost(post);
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('삽입 성공')");
			out.println("location.href='" + request.getContextPath() + "/post/list.po'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('삽입 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		out.close();
		
		return null;
	}

}
