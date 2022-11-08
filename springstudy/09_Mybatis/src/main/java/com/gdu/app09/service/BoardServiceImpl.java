package com.gdu.app09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app09.domain.BoardDTO;
import com.gdu.app09.repository.BoardDAO;

        
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
	public BoardDTO findBoardByNo(int boardNo) {
		
		return dao.selectBoardByNo(boardNo);
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
	public int removeBoard(int boardNo) {
		// TODO Auto-generated method stub
		return dao.deleteBoard(boardNo);
	}
	
}
