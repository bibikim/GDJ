package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardEditService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 어떤 게시글을 수정할 건지 번호가 넘어온다
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));    // 어떤 게시글을 수정할 건지 번호가 넘어온다 내가 한번 감싸는게 null일수도 있어!
		int board_no = Integer.parseInt(opt.orElse("0")); // null이 들어있으면 0 주라  <- null처리를 위한 optional
		
		// DB에서 board_no에 해당하는 Board 가져오기
		Board board = BoardDao.getInstance().selectBoardByNo(board_no);  // board_no 전달
		
		// 게시글 정보를 Jsp로 보내기 위해서 request에 저장
		request.setAttribute("board", board);
		
		// 어디러 / 어떻게
		ActionForward af = new ActionForward();
		af.setView("/board/edit.jsp");   // webapp/board/edit.jsp 의미
		af.setRedirect(false);   // 포워드
		
		return af;
	}

}
