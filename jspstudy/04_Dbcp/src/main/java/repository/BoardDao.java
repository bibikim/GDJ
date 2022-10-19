package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.Board;

public class BoardDao {
	
	
	// field
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs; // select를 돌릴 때만 사용하는 애
	private String sql;   // 쿼리문 작성할 sql
	
	// Connection Pool 관리
	private DataSource dataSource;
	
	
	// DB 접근은 혼자해야됨 - 싱글턴패턴
	
	// singleton - pattern
	private static BoardDao dao = new BoardDao(); // 하나를 미리 만들어둔다 static
	// DAO를 만들 때 내부에서만 만들 수 있다. 외부에서는 만들지 못한다 -> private이기 때문에
	private BoardDao() {
		// new BoardDao를 할 때 private BoardDao(){}생성자가 만들어지면서 dataSource도 만들어짐!!
		try {
			// DataSource 객체 생성
			// context.xml에서 name="jdbc/oracle11g"인 Resource를 찾아서 생성(JNDI)
			Context ctx = new InitialContext(); // context.xml 읽어들일 때 쓰는것
			Context envCtx = (Context)ctx.lookup("java:comp/env"); 		// 타입이 안 맞으므로 context 캐스팅
			dataSource = (DataSource)envCtx.lookup("jdbc/oracle11g");	// 
			// dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/oralce11g");
		} catch(NamingException e) {  // 원하는 이름을 못 찾아서 생기는 예외
			e.printStackTrace();
		}
	}
	public static BoardDao getInstance() {	
	// 미리 만들어놓은 다오를 반환하는 메소드. 내부에서 static필드를 써야하기 때문에 메소드도 static이어야 함
		return dao;   // 반환은 위에 미리 만들어둔 dao로 반환!
	}
	// 외부(public)에서 공개되는 것(DAO를 사용할 서비스)은 BoardDao를 호출할 때 클래스메소드로 한다. BoardDao.getInstance()
	// static이 붙어있는 애들은 클래스 이름으로 호출하는 클래스메소드이기 때문이다!
	
	// method
	// 1. 접속/자원 해제(를 위해 만든 close메소드)
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {ps.close();}
			if(con != null) {con.close();}   // Connection Pool의 close()는 Connection의 종료가 아닌 Connection 반환의 개념.
		} catch (Exception e) {
			e.printStackTrace();
		}  // 모든 메소드는 제일 먼저 데이터소스로부터 커넥션 얻어내고 마지막으로 하는게 반납(close())
	}
	
	// 2. 목록 반환하기
	public List<Board> selectAllBoards() {  // 리스트 안에 들어가 있는 하나하나는 board
		List<Board> boards = new ArrayList<Board>();
		try {
			con = dataSource.getConnection();  // Connection Pool(dataSource)로부터 Connection 대여(얻어내기)
			sql = "SELECT BOARD_NO, TITLE, CONTENT, CREATE_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql); // 쿼리문 실행하는 애.   -> prepare미리준비해라statement(sql)쿼리문을.
			rs = ps.executeQuery();  // SELECT문은 executeQuery() 사용.    실행 결과로 rs가 나옴! 그래서 rs=@@@
			// rs야 첫번째 행 가져와라. rs.next() 한 번 호출할 때마다 한 행씩 포인팅. next()하기 전에는 아무것도 포인팅하지 않은 상태
			// 포인팅 후에 rs.getInt("board_no") == rs.getInt(1)  -> 첫번째 데이터인 board_no 가져옴
			// rs.getString(), rs.getDate() ...
			
			while(rs.next()) {  // 목록보기는 while문 처리
				// Board board는 한 개의 게시글을 의미함
				Board board = new Board();
				board.setBoard_no(rs.getInt(1));   		// rs.getInt("BOARD_NO")
				board.setTitle(rs.getString(2));   		// rs.getString("TITLE")
				board.setContent(rs.getString(3)); 		// rs.getString("CONTENT")
				board.setCreate_date(rs.getDate(4));  	// rs.getDatd("CREATE_DATE")     ()숫자는 열을 의미!
				// 가져온 게시글을 리스트에 추가함
				boards.add(board);   // 게시글의 갯수만큼 while문이 동작하도록!
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);   // null이 아니면(사용했으면) close 할게~
		}
		return boards;  // 보드의 List 값을 넘겨준다
	}
	
	// 3. 상세보기
	public Board selectBoardByNo(int board_no) { // 게시글 번호(PK)를 받아서 그걸로 검색, 따라서 board_no를 외부에서 받아와야 함
		Board board = null; // 
		try {
			con = dataSource.getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, CREATE_DATE FROM BOARD WHERE BOARD_NO=?";  // 변수는 ? 처리
			ps = con.prepareStatement(sql); // 쿼리 실행 객체ps에 sql 전달
			ps.setInt(1, board_no);   	// 1번째 물음표(?)에 board_no 전달하겠다(변수 ? 처리).   int값이니까 setInt()메소드
			rs = ps.executeQuery();		// select문이므로 executeQuery()
			// 가져오는 행(row)의 갯수 = 0 or 1   --> 따라서 rs.next()는 한번 필요. while문 노쓸모
			if(rs.next()) {
				// rs.next()가 있으면 == 행이 하나가 있으면
				board = new Board(); // board를 가져와서 만들어주겠다(조회된 결과가 있으면 new Board를 만듦)
				board.setBoard_no(rs.getInt(1));   		// rs.getInt("BOARD_NO")
				board.setTitle(rs.getString(2));   		// rs.getString("TITLE")
				board.setContent(rs.getString(3)); 		// rs.getString("CONTENT")
				board.setCreate_date(rs.getDate(4));  	// rs.getDatd("CREATE_DATE")   
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);     // 사용한 자원들 다 반납
		}
		
		return board;  // 로직에 따라 board는 null값(일치하는 board_no가 없는 경우)이 나올 수도 있으므로 null로 초기 값 설정
	}
	
	// 4. 게시글 삽입    삽입된 행의 갯수는 int값.    insert 성공시 1, 실패시 0
	public int insertBoard(Board board) {  // 게시글 정보를 다 담고 있는 board를 매개변수로.
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			result = ps.executeUpdate();	// INSERT문은 executeUpdate() 메소드 사용
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);   // rs는 select문에서만 사용하는 애라 4에선 rs를 사용하지 않음 -> 따라서 null 주기
		}
		
		return result;
	}
	
	// 5. 게시글 수정
	public int updateBoard(Board board) {  // 업데이트할 게시글에 정보가 담겨있어야 함 -> board를 받자
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE BOARD_NO = ?";   // 번호가 일치하는 게시글의 제목과 글 업데이트
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());   	// 1번째 변수 = 보드에 들어있는 타이틀
			ps.setString(2, board.getContent());	// 2번째 변수 = 보드에 들어있는 컨텐트
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate();  // UPDATE문은 executeUpdate() 메소드 사용
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	// 6. 게시글 삭제
	public int deleteBoard(int board_no) {       // 글을 pk인 보드넘버를 넘겨받아서 삭제
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);   		// 첫번째 ?에 board_no 넘기겠다~
			result = ps.executeUpdate();	// DELETE문은 executeUpdate() 메소드 사용    - DML(Insert, Update, Delete 는 executeUpdate()
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	
	
}
