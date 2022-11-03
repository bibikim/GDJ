package com.gdu.app06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.repository.BoardDAO;


/*
	@RunWith(SpringJUnit4ClassRunner.class)
	
	안녕. 난 테스트를 수행하는 클래스야.
	JUnit4를 이용한 테스트를 수행해.
 */
@RunWith(SpringJUnit4ClassRunner.class)


/*
 	@ContextConfiguration
 	
	안녕. 난 컨테이너에 저장된 bean이 어디에 있는지 알려주는 역할이야.
	
	1. root-context.xml에 <bean> 태그를 추가하는 경우
	   @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
	2. com.gdu.app06.config.SpringBeanConfig.java에 @Bean을 작성한 경우
	   @ContextConfiguration(classes={SpringBeanConfig.class})
	3. component-scan과 컴포넌트(@Component, @Service, @Repository 등)를 이용한 경우
	   @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})    -> 컴포넌트가 만들어져있는 위치를 알려줌
	   
	   --> 2번의 경우 @Bean을 작성한 클래스가 2개이면 ,(컴마) 찍고 클래스 이름 적어주면 됨({}로 되어있으니 가넝). 3번의 경우도 마찬가지
	
*/
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})           // 자바스크립트 배열은 [], 자바 배열은 {}

// 테스트할 때는 톰캣이 안 돈다. 그럼 컨트롤러단으로 테스트는 어떻게 하는가??? 스프링에는 mvc처럼 돌아갈 수 있게 하는 클래스가 있다! 나중에 사용해볼게용
public class BoardUnitTest {

	// DAO 단위로 Unit 테스트를 진행하기 때문에
	// BoardDAO가 필요합니다.
	
	@Autowired
	private BoardDAO dao;
	
	// @Test
	public void 삽입테스트() {
		
		BoardDTO board = new BoardDTO(0, "테스트 제목", "테스트 내용", "테스트 작성자", null, null);   // BoardDTO 생성자
									// 번호는 안 쓰겠다(0으로 쓴당)					// 입력은 어차피 3가지(title, content, writer니까 아닌건 null 처리
		assertEquals(1, dao.insertBoard(board));  // dao,.insertBoard(board)의 결과로 1을 기대한다!	
	}
	
	@Test
	public void 조회테스트() {
		
		assertNotNull(dao.selectBoardByNo(5));   // 5번글 조회
	}
	
}
