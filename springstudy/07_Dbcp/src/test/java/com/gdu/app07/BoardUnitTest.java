package com.gdu.app07;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.app07.config.BoardAppContext;
import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.repository.BoardDAO;

// JUnit4를 사용한다. (SpringJUnit4ClassRunner)
// 커스터마이징한 MySpringJUnit4ClassRunner를 사용한다
@RunWith(MySpringJUnit4ClassRunner.class)

// Bean은 BoardAppContext에 정의되어 있다.
@ContextConfiguration(classes = {BoardAppContext.class})    // 빈 생성 방법이 달라졌기 때문에 6장의 Test와 다른 부분!

public class BoardUnitTest {
	
	@Autowired
	private BoardDAO dao;   // 다오를 만들어 둬야 테스트 가넝한겅
	
	@Test
	public void 삽입테스트() {
		BoardDTO board = new BoardDTO(0, "테스트제목", "테스트내용", "테스트작성자", null, null);   // 테스트할 boardDTO 하나 만들어서 테스트하쟝   
		assertEquals(1, dao.insertBoard(board));
	}
	// => 테스트 실패!! 이유는?? context.xml을 읽어들이는 건 '톰캣'. 
	// 단위테스트를 진행할 때는 서버를 구동시키지 않기 때문에(톰캣없이) context.xml 파일을 읽어들이지 못한다.
	// mvnrepository.com에서 Tomcat DBCP 검색 -> 버전에 맞는 dependency 찾아서(9.0.68) 복사후 pom.xml에 넣어주기
	// SpringJUnit4ClassRunner를 상속받는 테스트 클래스 생성 MySpringJUnit4ClassRunner

}
