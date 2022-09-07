package ex06_select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Board;

public class SelectListMain {

	public static void main(String[] args) {
		
		/*
		 	// 여러 개의 SELECT 검색 결과는 ArrayList에 저장. List<String> list = new ArrayList<String>();
		 	// 행 하나는 Board 하나를 쓴다
		 	List<Board> boards = new ArrayList<Board>();
		 	Board board = new Board();                       <- board 객체 생성
		 	boards.add(board);                               <- boards(arrayList)에 board 객체 저장하기
		*/

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			// OracleDriver 로드
			Class.forName("oracle.jdbc.OracleDriver");
			
			// DB접속 - Connection 객체 생성
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			// 쿼리문 작성
			String sql = "SELECT BOARD_NO, TITLE, CONTENT, HIT, CREATE_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			
			// PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 쿼리문 실행
			rs = ps.executeQuery();

			// 모든 조회 결과를 저장할 ArrayList 생성
			List<Board> boards = new ArrayList<Board>();     // List, Board 전부 import 
			
			// 다중 행이 조회되기 때문에 while문을 이용하여
			// 모든 행을 순차적으로 스캔
			while(rs.next()) {     // rs.next()는 행 하나하나를 가리킨다
				
				// rs가 가리키는 행 정보를 Board 객체로 생성
				Board board = new Board();
				board.setBoard_no(rs.getInt("BOARD_NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setHit(rs.getInt("HIT"));
				board.setCreate_date(rs.getDate("CREATE_DATE"));
				
				// Board 객체를 ArrayList에 저장
				boards.add(board);
				
			}
			
			// ArrayList에 저장된 Board 확인
			for(int i = 0; i < boards.size(); i++) {    // ArrayList의 길이는 size()
				System.out.println(boards.get(i));      // 요소 하나하나는 get을 통해서 가져온다
			}
			
			/*
			- 향상 for문으로도 가능
			for(Board board : boards)
			    System.out.println(board);
			 */
				
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

}
