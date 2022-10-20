package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardAddService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// DB로 보내기 위해 Board board 생성
		Board board = new Board(); // 받는 파라미터가 2개(title, content)니까 하나로 board에 모으는 것
		board.setTitle(title);
		board.setContent(content);
		
		// DB로 Board board 보내기(삽입)
		int result = BoardDao.getInstance().insertBoard(board);  // result에 board 전달
		
		// 삽입 성공 / 실패 응답 만들기
		PrintWriter out = response.getWriter();
		if(result > 0) {   // insert 성공
			out.println("<script>");
			out.println("alert('게시글이 등록되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/board/list.do'");  // 글을 성공적으로 등록한 뒤 목록보기로 이동
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글이 등록이 실패했습니다.')");
			out.println("history.back()");  // == history.go(-1)    실패하면 이전 페이지로 돌아감.
			out.println("</script>");
		}
		out.close();
		
		return null;   // java 문법때문에 남겨둔 코드(실행은 되지 않음)
					   // location.href or history.back()으로 요청이 날아가기 때문에 굳이 해야할 return할게 없음    ->선생님깃 확인
	}

}
