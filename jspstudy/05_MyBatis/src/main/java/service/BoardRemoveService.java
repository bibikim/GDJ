package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDao;

public class BoardRemoveService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo = Integer.parseInt(opt.orElse("0"));
		
		// DB로 boardNo로 보내기(삭제)
		int result = BoardDao.getInstance().deleteBoard(boardNo);
		
		// 삽입 성공 / 실패 응답 만들기
		PrintWriter out = response.getWriter();
		if(result > 0) {   // insert 성공
			out.println("<script>");
			out.println("alert('게시글이 삭제되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/board/list.do'");  // 글을 성공적으로 삭제한 뒤 목록보기로 이동
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 삭제가 실패했습니다.')");
			out.println("history.back()");  // == history.go(-1)    실패하면 이전 페이지로 돌아감.
			out.println("</script>");
		}
		out.close();
		
		return null;   // java 문법때문에 남겨둔 코드(실행은 되지 않음)

	}

}
