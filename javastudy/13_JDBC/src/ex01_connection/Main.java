package ex01_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		
		// OracleDriver 열기(메모리에 로드하기)
		// 1. oracle.jdbc.OracleDriver
		// 2. oracle.jdbc.driver.OracleDriver
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch(ClassNotFoundException e) {
			System.out.println("OracleDriver 로드 실패");
		}
		
		
		// DriverManager로부터 Connection(DB접속) 받아오기
		Connection con = null;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";	 // DB(사용하는 오라클)마다 url은 달라짐(Oracle XE(라이트) 버전 기준.)
			String user = "SCOTT";  // 대소문자 노상관
			String password = "TIGER";  // 계정 만들 때 사용한 대소문자를 지켜야 함. 대문자 필수
			con = DriverManager.getConnection(url, user, password);
			System.out.println("DB 접속 성공");
			// 실무에선 user,password를 대놓고 쓰면 안되기 때문에, 파일에다 따로 적어두고 파일을 불러오는 식으로 사용해야 함.
			// 그 파일은 gitIgnore에 넣어두어야 함
			
		} catch(SQLException e) {
			System.out.println("DB접속정보 오류");
		}
		
		// Connection 종료.       마찬가지로 try catch 필요
		// JDBC에서는 Connection을 닫는 것이 굉장히 중요(메소드 단위로)  -> 안 닫으면 작업이 안 됨
		// 메소드 하나마다 접속하고 접속해제를 해야 함..  1.접속 2.작업 3. 접속종료
		try {
			if(con != null)
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
