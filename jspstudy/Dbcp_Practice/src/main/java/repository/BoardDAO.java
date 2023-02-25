package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import domain.BoardVO;

public class BoardDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private DataSource ds;
	
	private static BoardDAO dao = new BoardDAO();
	
	private BoardDAO() {
		
		try {
			
			Context ctx = new InitialContext(); // context.xml 읽어들이기
			Context envCtx = (Context)ctx.lookup("java:comp/env");
			// jdbc의 oracle11g에 해당하는 객체를 찾아서 ds에 저장
			ds = (DataSource)envCtx.lookup("jdbc/oracle11g");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BoardDAO getInstance() {
		return dao;
	}
	
	// 접속/자원 해제 혹은 반납을 위해 만든 close 메소드
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(con != null) {con.close();}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 1. 목록 보기
	public List<BoardVO> selectAllBoards() {
		List<BoardVO> boards = new ArrayList<BoardVO>();
		try {
			con = ds.getConnection();
			sql = "SELECT NO, WRITER, IP, HIT, CREATEDATE, MODIFYDATE, TITLE, CONTENT FROM BOARD ORDER BY NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardVO board = BoardVO.builder()
									.no(rs.getInt(1))
									.writer(rs.getString(2))
									.ip(rs.getString(3))
									.hit(rs.getInt(4))
									.createdate(rs.getDate(5))
									.modifydate(rs.getDate(6))
									.title(rs.getString(7))
									.content(rs.getString(8))
									.build();
				boards.add(board);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return boards;
	}
	
	// 2. 게시글 추가
	public int insertBoard(BoardVO board) {
		int result = 0;
		try {
			con = ds.getConnection();
			sql = "INSERT INTO BOARD VALUES(?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getWriter());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getContent());
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	
	
}
