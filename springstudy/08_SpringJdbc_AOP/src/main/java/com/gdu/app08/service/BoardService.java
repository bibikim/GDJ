package com.gdu.app08.service;

import java.util.List;

import com.gdu.app08.domain.BoardDTO;

public interface BoardService {

	// 서비스 계층의 이름은 "사용자 친화적으로" 작성
	public List<BoardDTO> findAllBoards();
	public BoardDTO findBoardByNo(int board_no); // 넘버가 전달되어야 한다
	public int saveBoard(BoardDTO board); // 타입이 baordDTO인 board
	public int modifyBoard(BoardDTO board);
	public int removeBoard(int board_no); // 삭제할 보드의 넘버
	
}
