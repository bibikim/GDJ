package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardVO;
import repository.BoardDAO;

public class BoardAddService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVO board = BoardVO.builder()
						.writer(writer)
						.title(title)
						.content(content)
						.build();
		
		// 1차 요청. DB로 BoadVo를 보냄 -> 삽입. 
		BoardDAO.getInstance().insertBoard(board);
		
		// 어디로 어떻게
		ActionForward af = new ActionForward();
		// redirect로 보내주어야 한다. 1차 요청: 삽입, 2차 요청: 목록 반환 
		af.setView(request.getContextPath() + "/board/list.do");
		af.setRedirect(true);
		
		return af;
		
	}

}
