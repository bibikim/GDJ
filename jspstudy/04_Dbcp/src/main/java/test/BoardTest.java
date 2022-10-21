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
		
	}

}
