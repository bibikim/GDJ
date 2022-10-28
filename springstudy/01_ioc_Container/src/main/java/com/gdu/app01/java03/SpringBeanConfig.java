package com.gdu.app01.java03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {

	
	@Bean
	public Publisher publisher1() {			// 반환타입: Publisher -> 출판사를 반환하겠다
		Publisher publisher = new Publisher(); // setter로 생성하기로 했으니까 디폴트로 만든다음에 
		publisher.setName("한국출판사"); 	// setName, setTel 호출
		publisher.setTel("010-555-8888");
		
		return publisher;
	}
	
	@Bean
	public Book book1() {
		Book book = new Book();
		book.setTitle("소나기");
		book.setAuthor("황순원원");
		book.setPublisher(publisher1());	// 퍼블리셔는 퍼블리셔1메소드로 호출해주기!
		
		return book;
	}
	

	
}
