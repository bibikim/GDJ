package ex03_insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import domain.Board;

public class InsertMain {

	public static void main(String[] args) {
		
		// 게시판 정보를 입력 받아서 BOARD 테이블에 저장하기
		// 1. Scanner 클래스로 정보 입력 받기
		// 2. Board 클래스 타입의 객체에 입력 받은 정보 저장하기
		
		Scanner sc = new Scanner(System.in);
		
		// 1. 입력받을 것 : 제목, 내용
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		System.out.print("내용 >>> ");
		String content = sc.nextLine();
		
		// 2. 객체에 정보 저장(제목과 내용을 하나로 모으겠다는 의도)
		Board board = new Board(); // 패키지가 다르기 때문에 import 필수
		board.setTitle(title);
		board.setContent(content);
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			// OracleDriver 클래스 로드
			// OracleDriver 클래스가 저장된 ojdbc6.jar 파일을 Class path에 등록
			Class.forName("oracle.jdbc.OracleDriver");  // oracle.jdbc 패키지에 저장되어있는 OracleDriver.class
			
			// DB접속 - Connection 객체 생성
			String url = "jdbc:oracle:thin:@localhost:1521:xe";  // 쓰고 있는 Oracle 버전에 따라 다름
			String user = "SCOTT";
			String password = "TIGER";
			// DriverManager 클래스
			con = DriverManager.getConnection(url, user, password);
			
			// 쿼리문 작성(변수 처리할 부분은 ?로 처리)
			String sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, HIT, CREATE_DATE) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, 0, SYSDATE)";
			// ? = 변수
		
			// PreparedStatement 객체 생성(쿼리문Statement을 미리 준비하라~)
			// SQL Injection으로부터 공격을 막 대비를 해놓은 
			// 역할 : 쿼리문 실행을 담당
			ps = con.prepareStatement(sql);
			
			// 쿼리문에 포함된 ?(변수)에 변수 전달하기
			// 쿼리문에 작성된 ?의 ^순서^대로 명시하며 값을 채워주기
			ps.setString(1, board.getTitle());     // 첫번째 ?에 board.getTitle() 전달하기(setString : title이 String이므로)
			ps.setString(2, board.getContent());   // 두번째 ?에 board.getContent() 전달하기(setString : content가 String이므로)
			
			// INSERT문의 실행
			// 1. executeUpdate() 메소드 사용하기
			// 2. executeUpdate() 메소드의 반환값은 int 타입
			// 3. 반환값
			//    1) 1이 반환되는 경우 : 1개의 행이 INSERT되었다는 의미. 성공을 의미함
			//    2) 0이 반환되는 경우 : 0개의 행이 INSERT되었다는 의미. 실패를 의미함
			int result = ps.executeUpdate();
			if(result == 1) {    // if(result > 0) 일반적으로 이걸로 많이 씀. 
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
