package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardAddService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// DB로 보낼 Board 생성
		// addservice가 보드를 만들어서 전달
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		
		// DB로 Board 보냄(삽입)  -> 삽입 결과는 정수로 나옴
		int result = BoardDao.getInstance().insertBoard(board);
		
		// 삽입 성공/실패 처리는 하지 않음  (<--- 다음에 해주신댕)
		System.out.println("삽입 결과 :" + result);
		
		// 어디로 / 어떻게
		// forward(request전달)를 하게 되면, 삽입이 2번 실행 됨..
		// 1차 요청(INSERT 해달라) --> 삽입 완.  2차 요청(새로 삽입된 목록 보여달라) --> 변경된 후의 삽입목록 보여줘
		ActionForward af = new ActionForward();
		af.setView(request.getContextPath() + "/board/list.do");	// Redirect 할 때에는 대부분 맵핑으로 요청한다. (.do)
										// 삽입하고 그냥 가면(list.jsp라고 적은게 그냥가는거) 보여줌 ? 안 보여줌.. 
										// 다시 DB에 가서 목록을 와야하므로 do로!
		af.setRedirect(true);			// INSERT, UPDATE, DELETE와 같은 DB 변경 작업에는 Redirect (true = redirect 하겠다)
		// redirect : 1번째 요청 -> 삽입 요청(add.do)  2번째 요청 -> 새로 목록을 집어넣고 난 후의 목록을 가져와라(list.do)
		return af;
	}

}
