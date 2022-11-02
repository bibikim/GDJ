package repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Notice;

public class NoticeDao {

	// 필드 - SqlSessionFactory
	private SqlSessionFactory factory;
	
	
	// DAO는 기본적으로 singleton - pattern
	private static NoticeDao dao = new NoticeDao();
	private NoticeDao() {
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
	public static NoticeDao getInstance() {
		return dao;
	}

	String mapper = "mybatis.mapper.notice.";
	
	public int selectAllNoticesCnt() {
		SqlSession ss = factory.openSession();
		int cnt = ss.selectOne(mapper + "selectAllNoticesCnt");
		ss.close();
		return cnt;
	}
	
	public List<Notice> selectAllNotices(Map<String, Object> map) {
		SqlSession ss = factory.openSession();
		List<Notice> notices = ss.selectList(mapper + "selectAllNotices", map);
		ss.close();
		return notices;
	}
	
	
}
