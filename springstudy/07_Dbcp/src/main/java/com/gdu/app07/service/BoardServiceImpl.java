package com.gdu.app07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import com.gdu.app07.config.BoardAppContext;
import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.repository.BoardDAO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

// 필드를 이용한 생성자를 만들어 두면,
// 생성자의 매개변수로 컨테이너의 Bean이 자동 주입(@Autowired)되므로
// 필드에 @Autowired 처리할 필요가 없다.                                 
// 필드가 많을 때, bean을 만들 때는 오토와이어드 서너개 붙여놓는 것 보다는 @AllArgsConstructor 하나 붙여놓는 것이 좋긴 하다.
// 오토와이어드가 없는 경우에는 생성자를 많이 이용한다

//@AllArgsConstructor // == Generate Constructor Using field 모든 아규먼트를 사용하는 컨스트럭터.
					  // 필드를 그대로 사용하는 생성자.         
public class BoardServiceImpl implements BoardService {

	
	// ★ Service는 DAO를 사용합니다. 언제나!!!
	
	private BoardDAO dao;   // ("가져올 빈의 이름 = 메소드의 이름", 타입)

	
	// 생성자의 매개변수 BaordDAO dao로 new BoardDAO()가 주입되고 있다.
	// BoardAppContext.java를 참고
	public BoardServiceImpl(BoardDAO dao) {
		super();
		this.dao = dao;
	}

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

}
