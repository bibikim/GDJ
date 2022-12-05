package com.gdu.app01.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app01.domain.BoardDTO;

public interface BoardService {

	// 서비스 계층의 이름은 "사용자 친화적으로" 작성
	public List<BoardDTO> findAllBoards();
	public BoardDTO findBoardByNo(int boardNo); // 넘버가 전달되어야 한다
	public int saveBoard(BoardDTO board); // 타입이 baordDTO인 board
	public int modifyBoard(BoardDTO board);
	public int removeBoard(int boardNo); // 삭제할 보드의 넘버

	public void removeBoardList(HttpServletRequest request, HttpServletResponse response);
	
}
