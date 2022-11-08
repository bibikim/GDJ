package com.gdu.app09.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.gdu.app09.domain.BoardDTO;


@Repository  // DAO 전용 컴포넌트. DAO가 사용하는 @Component로, 트랜잭션 기능이 추가되어 있다. DAO에서 데이터베이터 접속을 하기 때문에 기능이 추가됨ㅇㅇ!
			// 서블릿컨텍스트가 컴포넌트스캔태그가 들어가 있어서 패키지에 등록 되어있는 컴포넌트들을 bean으로 자동 등록해준다
public class BoardDAO {
	
	/*
	   SqlSessionTemplate
	   
	   Mybatis에서 지원하는 매퍼 처리 클래스 (마바는 매퍼방식으로 동작하니까!) 
	*/
	@Autowired  
	private SqlSessionTemplate sqlSessionTemplate;	// SqlSessionTemplate으로 만들어둔 bean 가지고 오기!
	

	// 레파지토리 계층의 이름은 "DB 친화적으로" 작성
	
	public List<BoardDTO> selectAllBoards() {
		
		return sqlSessionTemplate.selectList("mybatis.mapper.board.selectAllBoards");
	}
	
	/*
	 		기능을 클릭했을 때,
	 		NullPointer예외가 떴을 시에 "컴포넌트나 @Autowired"가 제대로 애너테이션 되어 있는지 확인해보자!! 
	 		
	  		java.lang.NullPointerException     // 애너테이션이 어디 하나가 안 되어 있으면 가져오는게 없으니까 null나온단 얘기..
			com.gdu.app08.service.BoardServiceImpl.findAllBoards(BoardServiceImpl.java:22)

	*/
	
	public BoardDTO selectBoardByNo(int boardNo) { 
		
		return sqlSessionTemplate.selectOne("mybatis.mapper.board.selectBoardByNo", boardNo);
	}
	
	
	
	public int insertBoard(BoardDTO board) {  // 변수에 들어갈 값은 여기에 들어있어야함! 들어있고!
		
		return sqlSessionTemplate.insert("mybatis.mapper.board.insertBoard", board);  // mappernamespace + 쿼리문 id
	}
	
	public int updateBoard(BoardDTO board) {
		
		return sqlSessionTemplate.update("mybatis.mapper.board.updateBoard", board);
	}
	
	public int deleteBoard(int boardNo) {
		
		return sqlSessionTemplate.delete("mybatis.mapper.board.deleteBoard", boardNo);
	}
	
}
