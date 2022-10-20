package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardModifyService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		// input입력요소로 만들어주면 name이 고정돼서 전달되기 때문에 null값이 전달되지 않음. 혹시라도 비어있으면 '' 빈문자열로 전달.
		// 따라서 Optional 할 필요 없음
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// DB로 보낼 Board board 생성
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setBoardNo(boardNo);
		
		// DB로 Board board 보내기(수정본)
		int result = BoardDao.getInstance().updateBoard(board);
		
		// 수정 성공 / 실패
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('게시글이 수정되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/board/detail.do?boardNo=" + boardNo + "'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 수정이 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		out.close();
		
		return null;  // 응답은 하고 자바는 자바대로 retun null을 동작. 
					  // Controller로 null을 반환하면 컨트롤러는 리다이렉트&포워드 모두 수행하지 않는다.
	}

}
