package com.gdu.app08.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.gdu.app08.domain.BoardDTO;


@Repository  // DAO 전용 컴포넌트. DAO가 사용하는 @Component로, 트랜잭션 기능이 추가되어 있다. DAO에서 데이터베이터 접속을 하기 때문에 기능이 추가됨ㅇㅇ!
			// 서블릿컨텍스트가 컴포넌트스캔태그가 들어가 있어서 패키지에 등록 되어있는 컴포넌트들을 bean으로 자동 등록해준다
public class BoardDAO {
	
	/*
	   JdbcTemplate
	   
	   Connection, PreparedStatement, ResultSet을 내부에서 사용하는 스프링 클래스
	   DriverManagerDataSource에 의해서 Connection Pool 방식으로 동작. 커넥션 풀을 운영하는 스프링 객체.
	*/
	@Autowired  
	private JdbcTemplate jdbcTemplate;	// JdbcTemplate으로 만들어둔 bean 가지고 오기!
	

	// 레파지토리 계층의 이름은 "DB 친화적으로" 작성
	
	public List<BoardDTO> selectAllBoards() {
		
		String sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD ORDER BY BOARD_NO DESC";
		
		List<BoardDTO> boards = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BoardDTO.class));
		return boards;
		// 위에 두줄을 한줄 코드로 ==> return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BoardDTO.class));  // row : DB의 행 , BeanProperty : BoardDTO, mapper : 그 둘을 연결해줄게오
		// .query() 목록 반환하는 쿼리문을 이름 비교해서 같은 이름이면 알아서 매핑해주겠다! 데이터베이스 로우결과와 빈의 프로퍼티 이름이 동일하면 가져와서 저장해줄게 원하면 리스트로만들어줄게. query()메소드 자체가 만들어서 리스트를 반환해주는 애임(반환타입이 List임)
		// .query()메소드를 부르면 리스트로 만들어줌
	}
	
	/*
	 		기능을 클릭했을 때,
	 		NullPointer예외가 떴을 시에 "컴포넌트나 @Autowired"가 제대로 애너테이션 되어 있는지 확인해보자!! 
	 		
	  		java.lang.NullPointerException     // 애너테이션이 어디 하나가 안 되어 있으면 가져오는게 없으니까 null나온단 얘기..
			com.gdu.app08.service.BoardServiceImpl.findAllBoards(BoardServiceImpl.java:22)

	*/
	
	public BoardDTO selectBoardByNo(final int board_no) {  // 매개변수에 final 처리 -> 매개변수의 값을 바꿀 수 없다라는 의미. (안해도 되긴 함)
								// 예전에 매개변수 해킹 시도가 있었다. 그래서 final이 붙을 수 있다.
		String sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD WHERE BOARD_NO = ?";
		BoardDTO board = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BoardDTO.class), board_no); // 쿼리 결과가 하나다. 객체 하나 만들어준다. (sql, 변수로 전달할 값(물음표=쿼리문에 전달해줘야 할 argument))
									// queryForObject() : 쿼리 결과가 1개. 객체 하나만을 만들어줌     // └>필요한 매개변수 전달 (3번째 자리에)
		return board;
	}
	
	
	
	public int insertBoard(final BoardDTO board) {  // 변수에 들어갈 값은 여기에 들어있어야함! 들어있고!
		
		String sql = "INSERT INTO BOARD(BOARD_NO,  TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD'))";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {  // 인터페이스는 new 못한다.. 인터페이스 본문을 여기서 만드는 방식(이너타입)으로 해야 함
							// in, del, upd 는 모두 .update()로 실행한다
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// 이름이 setter -> ps로 변수 세개에 set 해라
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setString(3, board.getWriter());
				
			}
		});
		
		return result;
	}
	
	public int updateBoard(final BoardDTO board) {
		String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFY_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') WHERE BOARD_NO = ?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {  // 두번째거.
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setInt(3, board.getBoard_no());   // setValues에 전달된 ps 작업
			}
		});
		return result;
	}
	
	public int deleteBoard(final int board_no) {
		String sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, board_no);
			}
		});
		return result;
	}
	
}
