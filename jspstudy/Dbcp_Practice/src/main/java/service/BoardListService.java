package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardVO;
import repository.BoardDAO;

public class BoardListService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) {
		
		List<BoardVO> boards = BoardDAO.getInstance().selectAllBoards();
		
		request.setAttribute("boards", boards);
		
		ActionForward af = new ActionForward();
		af.setView("/board/list.jsp");  // webapp/board/list.jsp  -> setView(= 어디로 jsp 결과 보낸다)
		af.setRedirect(false);
		
		return af;
	}

}
