package repository;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Member;

public class MemberDao {

	// 필드 - SqlSessionFactory
	private SqlSessionFactory factory;
	
	
	// DAO는 기본적으로 singleton - pattern
	private static MemberDao dao = new MemberDao();
	private MemberDao() {
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
	public static MemberDao getInstance() {
		return dao;
	}

	String mapper = "mybatis.mapper.member.";
	
	public Member login(Member member) {   // id와 pw가 들어있는게 파라미어 member, db로 갔는데 정보가 있다? -> 그럼 아이디비번이름이메일 모든 정보를 가져온다.
		SqlSession ss = factory.openSession();
		Member login = ss.selectOne(mapper + "login", member); // member전달, id는 UNIQUE 이기 때문에 한 명만 나옴!
		return login;
	}
	
	
	public int insertMember(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert(mapper + "insertMember", member);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	public int deleteMember(int memberNo) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete(mapper + "deleteMember", memberNo);   // Mapped Statements collection does not contain value for mybatis.mapper.member. => mybatis.mapper.member의 id와 일치하는게 없다! 
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// PersistenceException: DB에서 문제가 있을 때 나는 오류.
	// mamper 뒤에 delete id=""인  deleteMember를 붙여주지 않아서 뜬 오류,,,
	
}
