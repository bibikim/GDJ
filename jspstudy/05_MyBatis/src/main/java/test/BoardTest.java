package test;

import static org.junit.Assert.*;
// Assert클래스 밑에 있는 모든 애들(*)을 쓸 수 있다. 즉, testCase에서 쓰는 전용 메소드들 사용 가능하게 기본으로 임폴트가 되어있다.

import java.util.List;

import org.junit.Test;

import domain.Board;
import repository.BoardDao;

/*
	JUnit 단위 테스트
	
	1. DAO를 만들고 DAO의 메소드 단위로 단위 테스트를 수행한다.    (단위테스트는 서버로 돌리지 않음)
	2. Service 실행 결과(ActionForward)가 A.F가 아니고 "특정값"일 때가 있는데, 그런 경우에 Service를 대상으로 단위 테스트를 수행할 수 있다. 
	3. 프로젝트의 Build Path에 JUnit 라이브러리를 추가하고 사용한다.
	4. 테스트 수행
		프로젝트 실행 : Run - JUnit
	5. 주요 애너테이션
		1) @Test   : 단위 테스트를 수행하는 메소드     ->이게 붙어있어야 테스트 수행함
		2) @Before : 단위 테스트 수행 이전에 실행하는 메소드(보통 @Before어쩌고저쩌고 로 생겨먹음)
		3) @After  : 단위 테스트 수행 이후에 실행하는 메소드
*/

public class BoardTest {

	
	@Test
	public void 목록테스트() {   // 메소드명으로 한글을 많이 사용한다. 개발자 스스로 자기가 해보는 테스트이기 때문에,,
		
		// 목록의 개수가 5개이면 성공, 아니면 실패
		List<Board> boards = BoardDao.getInstance().selectAllBoards();
		assertEquals(5, boards.size());   // 기대하는 list의 갯수, 실제 갯수
		// 빨간색 바 - 실패 (실패 메시지가 뜸. expected:<@> but was:<#>)
		// 초록색 바 - 성공 (@, #) => 이 둘의 갯수가 같으면 성공! (테이블 안에 들어있는 행의 갯수)
	}
	
	
	@Test
	public void 상세조회테스트() {
		
		// 9번 게시글의 제목이 공지2이면 테스트 성공, 아니면 실패
		/*
		Board board = BoardDao.getInstance().selectBoardByNo(9);
		assertEquals("공지2", board.getTitle());
		*/
		
		// 9번 게시글을 가진 게시글이 있으면 테스트 성공, 아니면 실패
		Board board = BoardDao.getInstance().selectBoardByNo(9);
		assertNotNull(board);  // board가 not null이면 성공
	}
	
	@Test
	public void 게시글삽입테스트() {
		// 제목 : 테스트
		// 내용 : 테스트 내용      추가되는지 테스트
		Board board = new Board();
		board.setTitle("테스트");
		board.setContent("테스트 내용");	
		int result = BoardDao.getInstance().insertBoard(board);
		assertEquals(1, result);   // INSERT, UPDATE, DELETE는 1이 나오면 성공이기 때문에 1과 result의 결과가 같으면 삽입 성공
	}
	
	
	
	@Test
	public void 게시글수정테스트() {
		// 제목: 테스트2
		// 제목: 테스트 내용2
		Board board = new Board();
		board.setTitle("테스트2");
		board.setContent("테스트 내용2");
		board.setBoardNo(13);   // 게시글삽입테스트 결과로 삽입된 게시글의 번호
		int result = BoardDao.getInstance().updateBoard(board);
		assertEquals(1, result);  // 1을 기대하는데 result 는 얼마인가~
	}
	

	@Test
	public void 게시글삭제테스트() {
		// 게시글삽입테스트로 삽입한 게시글 삭제
		int result = BoardDao.getInstance().deleteBoard(13);
		assertEquals(1, result);
	}

	
	/*
	 * insert 삽입 테스트 - @before
	 * update 목록/상세같은 업데이트 테스트- @test
	 * delete 삭제 테스트 - @after  
	 * 
	 		==> 이렇게 하면 수행 순서가 잡힘
	 * */
	
	
}
