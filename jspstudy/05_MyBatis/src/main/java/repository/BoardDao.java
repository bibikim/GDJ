package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Board;

public class BoardDao {

	// 필드 - SqlSessionFactory
	private SqlSessionFactory factory;
	
	
	// DAO는 기본적으로 singleton - pattern
	private static BoardDao dao = new BoardDao();
	private BoardDao() {
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
	public static BoardDao getInstance() {
		return dao;
	}
	
	// method
	// 모든 method는 SqlSessionFactory로부터 SqlSession을 얻어서 사용   <- Mybatis 기본적인 동작 원리. 따라서 가장 먼저 factory를 만듦
	
	// Tip. 메소드이름을 실행할 쿼리문의 id와 맞추자! (selectAllBoards)
	
	// 1. 게시글 목록
	public List<Board> selectAllBoards() {
		SqlSession ss = factory.openSession();		// SELECT(커밋이 필요 없는 경우)     (-> jdbc는 오토커밋이 디폴트라 커밋을 안해도 됐었음)
											// ┌> 어떤 쿼리문을 실행하겠다고 알려주는 것이 네임스페이스와 쿼리문의 아이디를 이어붙이는 작업!!!
		List<Board> boards = ss.selectList("mybatis.mapper.board.selectAllBoards");		// mybatis.mapper.board(<mapper namespace="">) 매퍼의 selectAllBoards(<slect id="">) 아이디를 가진 쿼리문 실행!
		// selectList()에 sql, ps, rs 작업 다 들어가 있는 것! 아주 짧고 좋음
		// select의 결과가 여러 개 -> 따라서 selectList    <->       select의 결과가 한 개 -> selectOne
		ss.close();		// 메소드마다 매번 닫아 주어야 한다.
		return boards;
	}
	
	// 2. 게시글 상세 보기
	public Board selectBoardByNo(int boardNo) {
		SqlSession ss = factory.openSession();
		Board board = ss.selectOne("mybatis.mapper.board.selectBoardByNo", boardNo);   // (mapper의 id, 파라미터)   파라미터 전달이 있으면 파라미터 적어주고 없으면 id만 적어준다.
		ss.close();
		return board;
	}
	
	// 3. 게시글 삽입
	public int insertBoard(Board board) { 	// mapper에서는 적어주진 않았지만 어쨌든 반환타입은 int가 맞으므로 여기선 적는다
		SqlSession ss = factory.openSession(false);  // INSERT(커밋이 필요한 경우) // insert를 돌린다는건 커밋이 필요하다는 것. 커밋을 직접 실행시키기 위해 false
		int result = ss.insert("mybatis.mapper.board.insertBoard", board);  // (실행할 쿼리문(mapper경로+메소드 이름), board(parameterType)를 파라미터로 전달)
		// Board 타입에 title과 content가 없으면 오류가 나는 상태
		
		if(result > 0) {  // insert 성공 시
			ss.commit();  // commit하겠다. 수동커밋을 위해 오픈세션에 false 넣어준거
		}
		ss.close();
		return result;
	}
	
	// 4. 게시글 삭제
	public int deleteBoard(int boardNo) {  // 삭제할때 boardNo 필요!
		SqlSession ss = factory.openSession(false);  // DELETE(커밋이 필요한 경우)
		int result = ss.delete("mybatis.mapper.board.deleteBoard", boardNo);  // 전달 해줄거는 boardNo
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 5. 게시글 수정
	public int updateBoard(Board board) {
		SqlSession ss = factory.openSession(false);   // UPDATE(커밋이 필요한 경우)
		int result = ss.update("mybatis.mapper.board.updateBoard", board);    // board 매퍼의 updateBoard를 실행하라
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
}
