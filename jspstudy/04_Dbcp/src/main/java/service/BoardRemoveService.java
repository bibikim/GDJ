package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDao;

public class BoardRemoveService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("board_no")); // q보드넘버라는 파라미터가 넘어오는데 혹시 null이 오면 오류가 남. 
		// parseInt로 해서 파라미터 받으면 null처리 때문에 numberFormatE예외가 생길 수 있기 때문에 위험함
		// 그래서 optional을 사용하는 것. 한번 옵셔널로 감싸주고 꺼내주는거
		int board_no = Integer.parseInt(opt.orElse("0")); // 널값이 아니면 request에 저장했던거 그대로 꺼내주고 혹시 널(데이터가 없으면)이면 0으로 꺼내주라
		// 0을 만든 이유 : 0을 DAO측으로 전달하기 위해서. 영을 다오로 전달하면 실행 결과가 0 -> 보드넘버가 0인 게시글은 없기때문에 삭제가 안됨. 따라서 반환값이 0. 삭제를 막기 위해 0을 써준거.	
		
		// 
		int result = BoardDao.getInstance().deleteBoard(board_no);
		
		// 삭제 성공/실패 여부 콘솔에 출력
		System.out.println("삭제 여부 : " + result);
		
		// 어디로 / 어떻게
		ActionForward af = new ActionForward();
		af.setView(request.getContextPath() + "/board/list.do");  // Redirect 할 때는 맵핑으로 이동하는 것이 기본!! (jsp로 가는게 아님. 요청을 두번하는 것이기 때문에 또다른 요청을 해주는 것이 맞다.
		                                                          // 삭제 후에 db에 다시 가서 가져와야함. 따라서 목록을 가져다주는 맵핑인 list.do를 적어줘야 함!!
		af.setRedirect(true);		// DELETE 이후에는 Redirect
		
				// 첫번째 맵핑 = remove.do  딜리트가 끝난 후,  두번째 맵핑 = list.do
		return  af;
	}

}
