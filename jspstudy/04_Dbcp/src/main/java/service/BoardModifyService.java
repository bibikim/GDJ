package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardModifyService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		// form에 담겨서 넘어오기 때문에, title을 하나도 안적어서 보냈다? 그저 입력요소 input은	"" 빈문자열로 넘어온다. (null로 넘어오는 경우는 checkbox, radio)
		// <input type="text">, <textarea> 태그 요소는 입력 값이 없을 때 빈 문자열("")로 전달되므로
		// Optional은 사용할 수 없다. (optional은 null처리용)
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		// DB로 보낼 Board 작성
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setBoard_no(board_no);
		
		// DB로 Board 보내서 수정
		int result = BoardDao.getInstance().updateBoard(board);  // 수정 결과는 정수로 받아옴
		
		
		// 수정 결과는 콘솔에서 확인 (<-- 그저 수업을 위한 확인용)
		System.out.println("수정 결과 : " + result);
		
		// 어디로 / 어떻게    --> 수정완료 후 상세보기로 넘어가기 (or 목록보기) 
		ActionForward af = new ActionForward();
		af.setView(request.getContextPath() + "/board/detail.do?board_no=" + board_no);  // redirect 할 때는 새로운 요청을 위해 매핑으로 이동!
											// 상세보기 할 거면 내가 상세보기 할 게시글 번호를 파라미터로 줘야됨!!!!!!
											// board_no가 변수로 위에 잡혀 있으니 그냥 board_no 써주면 됨
		
		af.setRedirect(true);		// UPDATE 이후에는 Redirect
		return af;
	}

}
