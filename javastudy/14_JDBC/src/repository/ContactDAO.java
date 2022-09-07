package repository;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

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
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
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
	
	
	
}
