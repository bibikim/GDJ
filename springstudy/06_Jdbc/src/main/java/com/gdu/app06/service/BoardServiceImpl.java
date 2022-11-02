package com.gdu.app06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.repository.BoardDAO;


/*
	@Service 
	
	안녕. 난 Service에 추가하는 @Component야.
	servlet-context.xml에 등록된 <context:component-scan> 태그에 의해서 bean으로 검색되지.
	root-context.xml이나 @Configuration에 @Bean으로 등록하지 않아도 컨테이너에 만들어져.
*/


@Service  // Service가 사용하는 전용 컴포넌트 @Component! 
// 따라서, root-contex.xml이나 @Configuration, @Bean처리를 보드서비스임플/보드다오에는 하지 않아도 되는 상태!!

public class BoardServiceImpl implements BoardService {

	
	// ★ Service는 DAO를 사용합니다. 언제나!!!
	@Autowired  // 컨테이너에 생성된 bean 중에서 BoardDAO 타입의 bean을 가져오시오.
	private BoardDAO dao;   // 현재 보드다오는 컴포넌트.
	// 싱글턴 패턴 코드가 없어..! 여기엔! -> 스프링이 싱글턴으로 알아서 만들어 준다. 스프링이 컨테이너에 만들어놓는 기본적인 방법은 모두 싱글턴으로 만들어둔다.
	// 컨테이너에 있는 BoardDAO를 가져오기 위해서 @Autowired 애너테이션. -> BoardDAO객체 타입을 가지고 똑가튼거 컨테이너에서 찾아 와라!
	// 스프링이 컨테이너에 BoardDAO를 만들어 두었으니 null이 아님.. @Autowired로 필드에 주입. 스프링이 dao에다가 객체 만들어서 집어 넣었을거임
	// BoardDAO의 bean은 @Repository가 만들어둠
	
	
	
	@Override
	public List<BoardDTO> findAllBoards() {   // 목록보기
		
		return dao.selectAllBoards();
	}

	@Override
	public BoardDTO findBoardByNo(int board_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBoard(int board_no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
