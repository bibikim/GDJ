package ex06_select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectOneMain2 {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT COUNT(*) AS 총개수 FROM BOARD";   // 칼럼 이름 안주고 COUNT(*)로도 가넝. 대신 rs.getInt("COUNT(*)")
			
			ps = con.prepareStatement(sql);
			
			// 쿼리문 실행
			rs = ps.executeQuery();

			if(rs.next()) {
				
				/*
				 	 | 총개수 |    
				 	 |    3   |   <= rs.next() 호출로 인해 현재 rs 포인터의 위치
				 	 
				 	 rs.getInt("총개수")    - 칼럼의 이름으로 접근
				 	     또는
				 	 rs.getInt(1)           - 칼럼(열) 번호로 접근
				 */
				
				int count = rs.getInt("총개수");
				System.out.println(count);
				
			}   // COUNT(*) 집계 함수의 결과로 else가 나올 수 있을까? 게시글이 하나도 없으면 0을 반환하는데. 
				// ∴ COUNT(*) 집계 함수의 결과는 else 처리할 필요가 없음. 안 적어도 되고~ 적어도 되고~
			
			
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
