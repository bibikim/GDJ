package ex06_select;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Board;

public class SelectOneMain {

	public static void main(String[] args) {
		
		// SELECT문 실행
		// 1. executeQuery() 메소드 이용
		// 2. ResulSet 객체 이용  -> 실행 결과를 저장할 객체

		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;          // ResultSet 인터페이스
		
		try {
			
			// OracleDriver 로드
			Class.forName("oracle.jdbc.OracleDriver");
			
			// DB접속을 통한 Connection 객체 생성
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			// 쿼리문 작성
			// SELECT문의 결과가 단일 행인 경우
			// 1. WHERE조건절에서 PK 또는 UNIQUE 칼럼과 동등비교(=)를 수행.   ex) ID 로그인하는 경우! 
			// 2. 집계함수(합계, 평균, 최대, 최소) 결과를 조회    -> WHERE절 없이 단일 행
			String sql = "SELECT BOARD_NO, TITLE, CONTENT, HIT, CREATE_DATE FROM BOARD WHERE BOARD_NO = 3";
			
			// PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 쿼리문 실행
			rs = ps.executeQuery();
			
			// 조회 결과를 행 단위로 스캔하는 rs 객체
			// rs.next() 메소드 호출로 조회 결과를 스캔할 수 있음
			// rs.next() 메소드 호출 1건 = 1행 스캔
			// rs.next() 메소드는 스캔 성공 시 true, 스캔 실패 시 false 반환
			
			// 조회 결과 1개인 경우
			// rs.next() 메소드 호출은 1번
			// 		목록 하나만 보는 상세보기, 하나의 정보만 가지고 오면 될 때 사용  -> Board 객체 하나에 저장
			// <──> 목록 전체 보기는 while(rs.next())      							  -> List<Board> 에 저장
			if(rs.next()) {   // 스캔 성공(조회된 데이터(결과)가 있으면)
				
				/*
					rs 객체는 행 전체를 가리키는 포인터 역할
					rs 객체를 통해서 행을 구성하는 열(칼럼) 정보를 가져올 수 있음
					
					| BOARD_NO | TITLE | CONTENT | HIT | CREATE_DATE |
					|    3     |  안뇽 |  내용3  |  0  |   22/09/07  |   <= rs.next() 호출 시 rs 포인터의 위치
					|    4     |  님들 |  내용4  |  0  |   22/09/07  |
					
					rs 객체의 칼럼 정보 가져오기
					1. 칼럼의 이름 이용
					   1) rs.getInt("BOARD_NO")
					   2) rs.getString("TITLE")
					   3) rs.getString("CONTENT")
					   4) rs.getInt("HIT")
					   5) rs.getDate("CREATE_DATE")
					
					2. 칼럼의 순서(번호) 이용
						1) rs.getInt(1)
						2) rs.getString(2)
						3) rs.getString(3)
						4) rs.getInt(4)
						5) rs.getDate(5)
				*/
				
				// 각 칼럼의 정보
				int board_no = rs.getInt("BOARD_NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int hit = rs.getInt("HIT");
				Date create_date = rs.getDate("CREATE_DATE");
				
				// 모든 칼럼의 정보를 하나의 Board 객체로 만듦
				// 다섯개 모두 하나에 저장하기 위해 생성자 혹은 Setter를 쓴다
				Board board = new Board();  // -> 객체 생성
				board.setBoard_no(board_no);
				board.setTitle(title);
				board.setContent(content);
				board.setHit(hit);
				board.setCreate_date(create_date);
				
				// 확인
				System.out.println(board);  // Board 클래스의 toString() 메소드 동작
				                // 어떤 객체값을 결과로 출력하려면 toString()을 만들어야 한다. 
								// ∴ Board 클래스에 Generate toString()
				
				
			} else {
				
				System.out.println("조회 결과가 없습니다.");  // rs.next() 결과가 없는 것. 스캔 실패
				
			}
			
			
			// rs => 1행씩 반환
			// rs.next() => 행이 있으면 true. 한번 호출하면 행이 존재할 때 true 반환
			// rs는 해당 ROW로 이동하게 됨. 그 데이터 정보를 rs를 통해서 끄집어낼 수 있는 것
			// rs가 데이터를 가지고 올 때 타입을 붙여서 불러옴. rs.getInt("BOARD_NO")
			// -> rs가 가리키고 있는 행의 board_no를 가지고 오는 것
			// rs.getSting("TITLE")   -> title을 가져옴
			// 1. rs에게 next()를 호출해서 행을 찾아보라고 시킴 rs.next()
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();     // finally 안에 있으면 con.close만으론 호출이 안됨
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
