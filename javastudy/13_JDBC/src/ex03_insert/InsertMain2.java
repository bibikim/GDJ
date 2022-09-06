package ex03_insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import domain.Board;

public class InsertMain2 {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("글 제목 >> ");
			String title = sc.nextLine();
			System.out.print("글 내용 >> ");
			String content = sc.nextLine();
			
			Board board = new Board();
			board.setTitle(title);
			board.setContent(content);
			
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, HIT, CREATE_DATE) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, 0, SYSDATE)";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			
			int result = ps.executeUpdate();
			
			if(result > 0) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
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
