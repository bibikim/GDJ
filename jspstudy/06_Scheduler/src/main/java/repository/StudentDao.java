package repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Student;

public class StudentDao {

	// 필드 - SqlSessionFactory
	private SqlSessionFactory factory;
	
	
	// DAO는 기본적으로 singleton - pattern
	private static StudentDao dao = new StudentDao();
	private StudentDao() {
		// factory를 만드는 작업은 Dao안에서!  -> 다오를 호출하면 팩토리가 만들어짐! 그래서 팩토리로 메소드 동작하게끔.
		// SqlSessionFactory 빌드하기 (https://mybatis.org/mybatis-3/ko/getting-started.html 참고.)
		try {
			String resource = "mybatis/config/mybatis-config.xml";     // mybatis-config.xml의 경로
			InputStream in = Resources.getResourceAsStream(resource);  // ibatis.io 임폴트 -> 위 파일을 읽어내는 작업 코드.
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// ▲ 외부에선 접근 못하게 생성자를 private으로 막아버리고 
	// ▼ 외부에서 접근할때는 static으로 만들어둔걸 반환하는 getInstance()
	public static StudentDao getInstance() {
		return dao;
	}
	
	// method
	
	String mapper = "mybatis.mapper.student.";  // 스트링 앞에 final 처리해서 고칠수 없게 만들어줄 수도 있음!
	
	// 1. 학생 목록
	public List<Student> selectAllStudents() {    // 반환타입 List, 매개변수 x
		SqlSession ss = factory.openSession();
		List<Student> students = ss.selectList(mapper + "selectAllStudents");  // 이런식으로도 사용 가능
		ss.close();
		return students;
	}
	
	// 2. 전체 학생 수
	public int selectAllStudentsCount() {		// 반환타입 int
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(mapper + "selectAllStudentsCount");
		ss.close();
		return count;
	}
	
	// 3. 전체 평균
	public double selectAllStudentsAverage() {	// 반환타입 double
		SqlSession ss = factory.openSession();
		double average = ss.selectOne("mybatis.mapper.student.selectAllStudentsAverage");
		ss.close();
		return average;
	}
	
	// 4. 학생등록
	public int insertStudent(Student student) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert(mapper + "insertStudent", student);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 5. 평균범위조회
	public List<Student> selectStudentsByAve(Map<String, Double> map) {   // <>로 타입적어 주는걸 generic이라 함. generic은 첫글자 대문자
		SqlSession ss = factory.openSession();
		List<Student> students = ss.selectList(mapper + "selectStudentsByAve", map);   // map 전달
		ss.close();
		return students;
	}
		
	// 6. 평균범위조회 : 개수
	public int selectStudentsByAveCount(Map<String, Double> map) {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(mapper + "selectStudentsByAveCount", map);
		ss.close();
		return count;
	}
	
	// 7. 평균범위조회 : 평균
	public double selectStudentsByAveAverage(Map<String, Double> map) {
		SqlSession ss = factory.openSession();
		double average = ss.selectOne(mapper + "selectStudentsByAveAverage", map);
		ss.close();
		return average;
	}
	
	// 8. 학생 삭제
	public int deleteStudent(int stuNo) {  // 전달될 파라미터는 stuNo
		SqlSession ss = factory.openSession(false);
		int result = ss.delete(mapper + "deleteStudent", stuNo);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	
}
