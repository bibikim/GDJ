package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardDetailService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
									// request에 원하는 게시글 번호가 요청으로 들어옴. 따라서 리퀘스트에서 번호 뽑아오는거
		// 요청 파라미터
		String str = request.getParameter("board_no");		// 파라미터 board_no가 온다.
		Optional<String> opt = Optional.ofNullable(str);	// 파라미터가 null일 수 있다.
		int board_no = Integer.parseInt(opt.orElse("0"));	// 파라미터가 null이면 "0"을 쓰라.   -> 요청이 옴. 그럼 옵셔널로 파라미터를 한번 싸서 있으면 주고 null이면 0을 반환할게!
		// 파라미터 전달이 없으면 0을 쓰니까 0번 게시글을 주세요 하면 null 반환이 되는 것.. 잘못된 파라미터면 정상적으로 null떨어지도로 만든 상황
		
		// DB에서 게시글 정보 가져오기  // 선택한 게시글 하나가 board
		Board board = BoardDao.getInstance().selectBoardByNo(board_no);   // 넘어온 번호(board_no) 전달
		
		// 게시글 정보를 Jsp로 보내기 위해서 request에 저장
		request.setAttribute("board", board);
		
		// 어디로? 어떻게?!
		ActionForward af = new ActionForward();
		af.setView("/board/detail.jsp");          // webapp/board/detail.jsp를 의미
		af.setRedirect(false);
		
		return af;    // af 꼮 넘겨줘야한다!!
	}

}
