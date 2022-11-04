package com.gdu.app08.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app08.domain.BoardDTO;
import com.gdu.app08.repository.BoardDAO;

        
@Service  // 컴포넌트로 등록
public class BoardServiceImpl implements BoardService {

	
	// ★ Service는 DAO를 사용합니다. 언제나!!!
	@Autowired
	private BoardDAO dao;   // ("가져올 빈의 이름 = 메소드의 이름", 타입)


	@Override
	public List<BoardDTO> findAllBoards() {   // 목록보기
		
		return dao.selectAllBoards();
	}

	@Override
	public BoardDTO findBoardByNo(int board_no) {
		
		return dao.selectBoardByNo(board_no);
	}

	@Override
	public int saveBoard(BoardDTO board) {  // save할 내용은 BoardDTO board에 들어있다아. 
		// TODO Auto-generated method stub
		return dao.insertBoard(board);  // dao에 주입되어 있음.. 다오에 insert가 있으니까 그거 불러주면 된다.
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return dao.updateBoard(board);
	}

	@Override
	public int removeBoard(int board_no) {
		// TODO Auto-generated method stub
		return dao.deleteBoard(board_no);
	}
	
	@Override
	public void testTransaction()  {   // transaction
		
		// ★ 트랜잭션은 언제 필요한가요?
		// 하나의 서비스에서 2개 이상의 Insert/Update/Delete가 호출되는 경우에 필요합니다.!
		
		// 성공(하는 데이터)
		dao.insertBoard(new BoardDTO(0, "트랜잭션제목", "트랜잭션내용", "트랜잭션작성자", null, null));
		
		// 실패(하는 데이터)
		dao.insertBoard(new BoardDTO());  // nullpointerException이 발생하는 실패상황 연출. 데이터가 안 들어온 것(null)
		
		
		// 트랜잭션이 정상적으로 동작한다면,           (인/딜/업가 하나의 서비스 안에 두 번 이상 호출될 때 필요한게 트랜잭션)
		// ex) 댓글이 있는 글을 수정할 때..???????????
		// 둘 다 실패해야 한다. 최종적으로 둘다 실패했는지 확인해봐야 함. 둘다 실패하는 것이 기대하는 것~
		
		// 호출누가 하니~~? 컨트롤러 가자
		
		// -> 둘 다 실패시 Rollaback!     run as 후 주소창에 /brd/transaction 적고 이동 후 ▼ 아래 오류 뜨는 것을 보기 위함.
		// org.springframework.dao.DataIntegrityViolationException: PreparedStatementCallback; SQL [INSERT INTO BOARD(BOARD_NO,  TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD'))]; ORA-01400: cannot insert NULL into ("SCOTT"."BOARD"."TITLE")
		//; nested exception is java.sql.SQLIntegrityConstraintViolationException: ORA-01400: cannot insert NULL into ("SCOTT"."BOARD"."TITLE")
	}
}
