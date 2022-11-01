package com.gdu.app05.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app05.service.BoardService;
import com.gdu.app05.service.BoardServiceImpl;
import com.gdu.app05.service.MovieService;
import com.gdu.app05.service.MovieServiceImpl;

@Configuration
public class SpringBeanConfig {

	@Bean  // root-context.xml에 <bean>태그 만든거랑 차이 1도 없음! 둘 중 하나만 해주면 됨.
	public BoardService boardService() {
		return new BoardServiceImpl();
	}
	
	
	@Bean
	public MovieService movieService() {
		return new MovieServiceImpl();
	}
	
	
}