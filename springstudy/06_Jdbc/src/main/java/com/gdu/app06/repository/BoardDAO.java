package com.gdu.app06.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gdu.app06.domain.BoardDTO;


// BoardDAO가 컨테이너의 bean으로 등록되고자 한다
// 1. root-context.xml에 <bean>을 추가한다
// 2. @configuration에 @Bean을 추가한다
// 3. BoardDAO를 Component라고 알려준다.


/*
  	@Repository
  	
  	안녕. 난 DAO에 추가하는 @Component야.
  	servlet-context.xml에 등록된 <context:component-scan> 태그에 의해서 bean으로 검색되지.
  	root-context.xml이나 @Configuration에 @Bean으로 등록하지 않아도 컨테이너에 만들어져.
*/


// @Component   // BoardDAO를 bean으로 만들어 둬라!   -> component가 제대로 작동하려면 servlet-context에 포함되어 있음

@Repository  // DAO 전용 컴포넌트. DAO가 사용하는 @Component로, 트랜잭션 기능이 추가되어 있다. DAO에서 데이터베이터 접속을 하기 때문에 기능이 추가됨ㅇㅇ!
public class BoardDAO {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private String sql;

	
	// DAO에 필요한 것.
	// private 메소드
	// 이 메소드는 BoardDAO에서만 사용한다
	// 다른 메소드들의 시작 (모든 DB접속 작업)
	private Connection getConnection() {
		Connection con = null;
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");   // 원래 드라이버 패키지가 3수준으로 들어있음. 따라서 driver 안 적어도 됨
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	// private 메소드
	// 이 메소드는 BoardDAO에서만 사용할 메소드다!!
	// 다른 메소드들의 끝 (모든 DB접속 작업)
	private void close() {
		
		
		try {
			
			if(rs != null) { rs.close(); }
			if(ps != null) { ps.close(); }
			if(con != null) { con.close(); }
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 레파지토리 계층의 이름은 "DB 친화적으로" 작성
	
	public List<BoardDTO> selectAllBoards() {
		
		List<BoardDTO> boards = new ArrayList<BoardDTO>();
		
		try {   // 메소드마다 접속(con)을 만들고(getConnection) 해제(close) 하는게 좋다. 접속의 단위는 메소드인 것이 가장 안전한 데이터베이스 접근 방법
			
			con = getConnection();   // 빨간색 아이콘 private 이라는 뜻
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();  // 한줄씩 찾아 나가기 : 커서를 한 행씩 옮겨간다. 10개의 행이 있다 -> 10번 이동 -> next() 10번 호출 필요. next() 한번 당 한 행씩 순서대로 검색!
			// 여러번 진행한다? -> 목록보기 while문 작업. 검색이 되면 true. 검색된 데이터(select절)를 가지고 6개 순서대로 getInt(1), getString(2)(3)(4)(5)(6) 
			// get@@() 하나당 한 열씩!
			while(rs.next()) {
				BoardDTO board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)); // select절의 순서와 맞춰야 한다.
				boards.add(board); // arrylist에 board 넣어주기!
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return boards;
	}
	
	public BoardDTO selectBoardByNo() {
		BoardDTO board = null;
		
		return board;
	}
	
	public int insertBoard(BoardDTO board) {
		int result = 0;
		
		return result;
	}
	public int updateBoard(BoardDTO board) {
		int result = 0;
		
		return result;
	}
	public int deleteBoard(int board_no) {
		int result = 0;
		
		return result;
	}
	
}
