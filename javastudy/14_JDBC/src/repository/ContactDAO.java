package repository;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.ContactDTO;

public class ContactDAO {

	/********************* Singleton **************************/
	
	// CONTACT 테이블(DB)에 접근하는 객체
	// 데이터베이스를 직접 갖고 왔다갔다하는게 DAO
	// DTO를 들고 왔다갔다 한다고 보면 됨
	
	// DAO는 하나의 객체만 생성할 수 있도록
	// Singleton Pattern으로 생성                (데이터베이스를 접근할 땐 혼자 다녀야 함)
	
	// Singleton 패턴 - 1
	// ContactDAO 객체를 하나 만들어 둔다.
	private static ContactDAO contactDAO = new ContactDAO();
	
	// Singleton 패턴 - 2
	// 외부에서는 ContactDAO를 못 만들도록 제한한다. (private)
	// private 생성자
	private ContactDAO() {
		
	}
	
	// Singleton 패턴 - 3
	// 만들어 둔 ContactDAO 객체를 외부에 반환한다. 클래스 메소드로 불러야 함. static
	// ▼ 클래스 메소드를 이용한 호출
	public static ContactDAO getInstance() {     // 클래스메소드(static메소드)는 static 필드값만 호출할 수 있다.
		return contactDAO;                       // static메소드로 만들어두면 호출할 필드들도 static 필드값이어야 함
												 // 따라서 객체 만들어둔 필드(singleton -1)에 static을 붙여야 함.
	}
	
	// private은 객체로 부르거나 클래스로 부르거나. 
	// ▼ 객체를 이용한 호출
	// public ContactDAO getInstance() {           <- 2번 과정때문에 객체를 이용한 getInstance()를 이용한 호출은 불가능
	//	return contactDAO;

/*	
	public static void main(String[] args) {

		// ContactDAO 객체 만들기
		// 만들어둔 ContactDAO를 받아오는 방법
		ContactDAO dao1 = ContactDAO.getInstance();
		ContactDAO dao2 = ContactDAO.getInstance();
		
		System.out.println(dao1 == dao2);  // true. 똑같은 애들. 
	}
*/
	
	
	
	
	/********************* Field **************************/
	
	// 데이터베이스에 접근할 때 사용하는 공통 요소
	private Connection con;			// DB 접속
	private PreparedStatement ps;	// 쿼리문 실행
	private ResultSet rs;  			// SELECT 결과
	private String sql;				// 쿼리문
	private int result;    			// INSERT, UPDATE, DELETE 결과
	
	/********************* Method **************************/
	
	// 모든 데이터베이스 작업(CRUD : CREATE/READ/UPDATE/DELETE)의 공통 작업은 2가지
	//                              CREATE> insert  READ> select
	// 1. Connection 객체 생성
	// 2. close
	// 위에 두 개의 작업은 공통/반복되는 작업이므로 메소드로 만들어서 처리 
	
	// 공통 메소드 - 1
	public Connection getConnection() throws Exception{    // 예외발생한건 호출할 때 처리할게~ 그러니까 여기서 던질게~
		
		// OracleDriver 클래스 로드
		Class.forName("oracle.jdbc.OracleDriver");
		
		// Connection 객체 반환
		// db.properties 파일의 접속 정보 읽기
		Properties p = new Properties();
		p.load(new FileReader("db.properties"));   // 경로가 없는 파일은 동일한 프로젝트 디렉터리에 있다는 의미(14_JDBC\\db.properties). 
		
		String url = p.getProperty("url");
		String user = p.getProperty("user");
		String password = p.getProperty("password");
		return DriverManager.getConnection(url, user, password);
		// ---------------------------------------------------------------- DB접속을 통한 Connection 객체 생성 완.
		
	}
	
	// 공통 메소드 - 2
	public void close( ) {
		try {
			if(rs != null);
			if(ps != null);
			if(con != null);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		try {
			
			ContactDAO dao = ContactDAO.getInstance();
			Connection con = dao.getConnection();
	
			System.out.println("접속 성공");
		
		} catch(Exception e) {
			System.out.println("오류");
		}
		
	}
	
	// DAO가 DB처리할 애들
	// 연락처 추가 메소드
	// 1. 매개변수 : ContactDTO
	// 2. 반환값   : 0 또는 1 (int값)  <- ps.executeUpdate()의 실행결과
	public int insertContact(ContactDTO contact) {  // 매개변수 contact. 여기에 이름, 연락처, 메일 들어있다
							
		try {
			con = getConnection();  // 필드 con
			sql = "INSERT INTO CONTACT VALUES(CONTACT_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";  // 이름은 contact에 들어있음. 변수처리하면 됨
			ps = con.prepareStatement(sql);
			ps.setString(1, contact.getName());  // 따라서 contact의 getter
			ps.setString(2, contact.getTel()); 
			ps.setString(3, contact.getEmail());  
			result = ps.executeUpdate();   // 결과는 0 아니면 1. 쿼리 실행하고 실행돼서 나오는 값(0, 1)을 result에 저장
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();   // 위에 닫아주는 메소드 close() 만든걸 가져와서 호출만 해주면 됨.
		}
	
		return result;   // result값을 그대로 반환(return)하라
	}
	
	// 연락처 수정 메소드
	// 1. 매개변수 : ContactDTO
	// 2. 반환값   : 0 또는 1 (int값)  <- ps.executeUpdate()의 실행결과
	public int updateContact(ContactDTO contact) {   // 수정할 내용이 담길 contactDTO
		
		try {
			con = getConnection();
			sql = "UPDATE CONTACT SET NAME = ?, TEL = ?, EMAIL = ? WHERE CONTACT_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getTel());
			ps.setString(3, contact.getEmail());
			ps.setInt(4, contact.getContact_no());   // contact에 4개의 정보 모아주기
			result = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	// 연락처 삭제 메소드
	// 1. 매개변수 : contact_no   삭제할 때는 연락처번호만 있으면 돼서 하나로 모아줄 필요가 x. 그냥 전화번호만 넘겨주면 됨
	// 2. 반환값   : 0 또는 1 (int값)  <- ps.executeUpdate()의 실행결과
	public int deleteContact(int contact_no) {
		
		try {
			con = getConnection();
			sql = "DELETE FROM CONTACT WHERE CONTACT_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, contact_no);   // contact_no - 삭제하려고 받아온 번호 그대로 전달
			result = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	// 연락처 조회 메소드
	// 1. 매개변수 : contact_no              -> 반환값은 매개변수와 동일한 연락처 하나의 정보
	// 2. 반환값   : ContactDTO 또는 null    -> 조회시 반환값이 없을 때 null
	public ContactDTO selectContactByNo(int contact_no) {   // 전화번호를 매개변수로 받아온다
	
		ContactDTO contact = null;
		
		try {
			con = getConnection();
			sql = "SELECT CONTACT_NO, NAME, TEL, EMAIL, REG_DATE FROM CONTACT WHERE CONTACT_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, contact_no);
			rs = ps.executeQuery();   // select문은 결과가 rs, executeQuery()로 실행.
	 	 // └> rs는 지금 아무것도 안 가리키고 있는 상태. next를 넣어줌으로써 가리킨다. 검색된 결과가 있는지 없는지 확인(if)			 
			if(rs.next()) {  // contact_no는 PK이기 때문에 중복값 없음. 반환하는게 1 아니면 0이므로 반복문이 아니라 if로.
				contact = new ContactDTO(); 		  // rs로 스캔 결과가 있으면 contactDTO를 만든다
				contact.setContact_no(rs.getInt(1));
				contact.setName(rs.getString(2));
				contact.setTel(rs.getString(3));
				contact.setEmail(rs.getString(4));
				contact.setReg_date(rs.getDate(5));	  // 조회 결과가 있으면 ContactDTO를 만들고 그 결과를 contact에 저장
			} 
			// rs로 스캔 결과가 없으면 null
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return contact;
	}
	
	// 연락처 목록 메소드
	// 1. 매개변수 : 없음
	// 2. 반환값 : List<ContactDTO>  -> ContactDTO 하나 당 연락처 하나. ContactDTO가 여러개 있다: List
	public List<ContactDTO> selectAllContacts(){ 
		
		List<ContactDTO> contacts = new ArrayList<ContactDTO>();  // 실제로는 ArrayList를 만든건 맞음
		
		try {
			
			con = getConnection();
			sql = "SELECT CONTACT_NO, NAME, TEL, EMAIL, REG_DATE FROM CONTACT";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			// 결과가 여러개니까 반복문
			while(rs.next()) {    // resultSet은 반드시 1행씩밖에 읽지 않으므로 반복문 돌리기~
				ContactDTO contact = ContactDTO.builder()
						.contact_no(rs.getInt(1))
						.name(rs.getString(2))
						.tel(rs.getString(3))
						.email(rs.getString(4))
						.reg_date(rs.getDate(5))
						.build(); 						// set과 new~대신 builder패턴을 이용해서 만들어보기! 같은거임:)
				contacts.add(contact);  // ArrayList에 저장
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return contacts; // contacts의 타입이 List, 반환타입은 arrayList -> 따라서 반환타입 오류를 잡기 위해 array를 지워준다
						 // 실제로 ArrayList에 만든건 맞음. 다만 List가 인터페이스...~~~~??
	}
	
	
	
	
}
