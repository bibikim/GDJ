package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardListService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
																							// 이 예외를 controller의 49행으로 던짐
		// DB에서 가져온 게시글 목록
		List<Board> boards = BoardDao.getInstance().selectAllBoards(); // 싱글턴패턴이기 때문에 호출 이렇게!
		// boards를 jsp로 보내는 게 목적
		
		// 게시글 목록을 Jsp로 보내기 위해서 request에 저장하기이이
		request.setAttribute("boards", boards);
		
		// 어디로 어떻게?
		ActionForward af = new ActionForward();
		af.setView("/board/list.jsp");   // webapp/board/list.jsp로 보낸다   // setVeiw 어디로 jsp결과 보낸다
		af.setRedirect(false);			 // 어떻게 갈거냐 redirect로 하면 요청이 전달 안되니까 forward로
		
		return af;
	}

}
