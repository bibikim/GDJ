package ex04_update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import domain.Board;

public class UpdateMain {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			Scanner sc = new Scanner(System.in);
			
		// UPDATE할 게시글의 번호 입력 받기
			System.out.print("수정할 글 번호 >>> ");
			int board_no = sc.nextInt();
			sc.nextLine();  // 엔터 먹기.   
			  // nextInt()는 nextLine과 달리 엔터를 따로 안 먹어서 nextLine으로 엔터를 먹여줘야 함. 안그럼 엔터가 다음 입력에 입력받아서 넘어간다.
		
		// UPDATE할 게시글의 내용(CONTENT) 입력 받기
			System.out.print("수정할 글 내용 >>> ");
			String content = sc.nextLine();
		
		// UPDATE할 번호 + 내용을 가진 Board 객체 생성
		// DB로 보낼 게 두 개니까 하나로 모으는 작업
			Board board = new Board();
			board.setBoard_no(board_no);
			board.setContent(content);
		//------------------------------------------┘ 객체 생성 부분이 DTO라 할 수 있음(온전한 DTO는 아님)
			
			
		// ------------------------------------------- ▼ DB처리
		// Connection 생성
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "SCOTT";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
		// 쿼리문 작성
			String sql = "UPDATE BOARD SET CONTENT = ? WHERE BOARD_NO = ?";
			
		// PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
		// 쿼리문의 ?에 변수 전달하기
			ps.setString(1, board.getContent());  
			ps.setInt(2, board.getBoard_no());   // board_no의 타입은 NUMBER이기 때문에 setInt

		// 쿼리문 실행
			int result = ps.executeUpdate();
			
		// 실행 결과
		// 업데이트 성공, 업데이트 실패
			if(result > 0) {
				System.out.println("업뎃 성공");
			} else {
				System.out.println("업뎃 실패");
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
