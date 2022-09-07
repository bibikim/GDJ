package ex04_update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import domain.Board;

public class UpdateMain2 {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("수정할 글 번호 >> ");
			int board_no = sc.nextInt();
			sc.nextLine();
			System.out.println("수정할 제목 >> ");
			String title = sc.nextLine();
			
			
			Board board = new Board();
			board.setBoard_no(board_no);
			board.setTitle(title);
			
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "oracle.jdbc.OracleDriver";
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "UPDATE BOARD SET TITLE = ? WHERE BOARD_NO = ?";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, board.getTitle());
			ps.setInt(2, board.getBoard_no());
			
			int result = ps.executeUpdate();
			
			if(result > 0) {
				System.out.println("업뎃 성공");
			} else {
				System.out.println("다시 해");
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
				if(ps != null) ps.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		

	}

}
