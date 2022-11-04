package com.gdu.app08.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration   // 컨테이너에 빈을 만들어둘 수 있게 해주는 컨피그레이셔어언~
public class DBConfig {

	// SpringJdbc 처리를 위한 DriverManagerDataSource와 JdbcTemplate을 Bean으로 등록한다.
	
	@Bean
	public DriverManagerDataSource dataSource() {   // jdbc 커넥션 만드는애, 커넥션풀 관리하는 객체  -> jdbc 기반의 DataSource라고 생각하면 된다.
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("SCOTT");
		dataSource.setPassword("TIGER");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() { // 스프링프레임워크가 스프링jdbc를 지원하기 위한 것
		return new JdbcTemplate(dataSource());   // dataSource() 의존 관계 처리  -> bean 만든걸 다른 bean에 주입
	}
	
	
	
}
