package repository;

import java.io.InputStream;
import java.util.List;

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
	
	// method
	
	String mapper = "mybatis.mapper.member.";  // 스트링 앞에 final 처리해서 고칠수 없게 만들어줄 수도 있음!
	
	// 1. 회원목록
	public List<Member> selectAllMembers() { // mapper에서 id랑 메소드 이름을 맞춰주는게 규칙이 됨. 안 지키면 문제 됑용
		SqlSession ss = factory.openSession();
		List<Member> members = ss.selectList(mapper + "selectAllMembers");
		ss.close();
		return members;
	}
	
	// 2. 회원수
	public int selectAllMembersCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(mapper + "selectAllMembersCount");
		ss.close();
		return count;
	}
	
	// 3. 회원상세
	public Member selectMemberByNo(int memberNo) {
		SqlSession ss = factory.openSession();
		Member member = ss.selectOne(mapper + "selectMemberByNo", memberNo);
		ss.close();
		return member;
	}
	
	// 4. 회원등록
	public int insertMember(Member member) {
		SqlSession ss = factory.openSession(false); // 내가 커밋할게~
		int result = ss.insert(mapper + "insertMember", member);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
}
