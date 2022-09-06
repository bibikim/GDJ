package ex05_delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import domain.Board;

public class DeleteMain {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			// 삭제할 게시글 번호 입력 받기 PK로 삭제해야 하니깐 게시글 번호로 삭제
			Scanner sc = new Scanner(System.in);
			System.out.print("삭제할 글 번호 >>> ");
			int board_no = sc.nextInt();
			
			// connection 생성
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "SCOTT";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			Board board = new Board();
			board.setBoard_no(board_no);    
			// 선생님 코드의 경우, 입력받을게 하나뿐이라 객체 생성 따로 안 한건데 통상적으론 함ㅇㅇ
			// └> board 객체 생성을 안 하면, 변수 전달에서 getBoard_no으로 안하고 그냥 board_no으로 부르기 가능. 생성을 안 했으니깐~
			
			// 쿼리문 생성
			String sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			
			// preparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 쿼리문의 ?에 변수 전달하기
			ps.setInt(1, board.getBoard_no());
					
			// 쿼리문 실행
			int result = ps.executeUpdate();
			
			// 실행 결과
			// 삭제 성공, 삭제 실패
			if(result > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// con, ps 닫기
			try {
				if(con != null) con.close();
				if(ps != null) ps.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}
