package com.gdu.app07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app07.repository.BoardDAO;
import com.gdu.app07.service.BoardService;
import com.gdu.app07.service.BoardServiceImpl;

@Configuration   // 이 자바클래스가 bean을 만들수 있게 만들어주는 애너테이션
public class BoardAppContext {

	// 06_Jdbc의 @Repository 대신 추가한 Bean
	@Bean
	public BoardDAO dao() {
		return new BoardDAO();   // 레파지토리 애너테이션 방법이 아닌 다른 방법으로 bean 만들기
	}  // 이쪽의 뉴 보드다오가 아래 dao()로 불림.
	
	// 06_Jdbc의 @Service 대신 추가한 Bean
	@Bean
	public BoardService boardService() {
		return new BoardServiceImpl(dao());     
		// Impl에서 @Autowired를 없애고 @all~ 생성자 만들어준 후에는 () 안에 dao()를 넣어줘야 실행이 된다. <-- 생성자를 이용해 bean을 가져오는 방법!! (3장 팜고)
		// 생성자를 이용하면 @autowired를 붙이지 않아도 생성자의 매개변수로 자동 주입(dao()) 된다.
		// @AllArgsConstructor 생성자의 존재만으로도 오토와이어드 처리가 가능하다.
	}
	
}
