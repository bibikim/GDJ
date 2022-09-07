package ex06_select;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Board;

public class SelectPractice {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "SCOTT";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT BOARD_NO, TITLE, CREATE_DATE FROM BOARD WHERE BOARD_NO = 5";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {  //  rs.next()가 스캔을 성공한다면 아래 데이터들을 가져와라
				
				int board_no = rs.getInt("BOARD_NO");
				String title = rs.getString("TITLE");
				Date create_date = rs.getDate("CREATE_DATE");
				
				// 모든 칼럼의 정보를 하나의 Board 객체로 만듦
				// 다섯개 모두 하나에 저장하기 위해 생성자 혹은 Setter를 쓴다
				Board board = new Board();
				board.setBoard_no(board_no);
				board.setTitle(title);
				board.setCreate_date(create_date);
				
				System.out.println(board);
				
			}  // else 없어도 되는 상황 
		
			
			
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
