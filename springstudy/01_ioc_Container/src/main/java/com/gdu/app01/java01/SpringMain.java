package com.gdu.app01.java01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMain {

	public static void main(String[] args) {

		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeanConfig.class);   // bean을 등록시켜놓은 자바파일을 ()안에 주면 된다.
										// @Bean을 애너테이션 했으니까 요 메소드 사용
		
		Singer s1 = ctx.getBean("singer1", Singer.class);
		// info메소드로 만들어놓지 않았으니까 아래작업
		System.out.println(s1.getName());
		System.out.println(s1.getSong().getTitle());
		System.out.println(s1.getSong().getGenre());
		
		
		Singer s2 = ctx.getBean("singer2", Singer.class);
		// info메소드로 만들어놓지 않았으니까 아래작업
		System.out.println(s2.getName());
		System.out.println(s2.getSong().getTitle());
		System.out.println(s2.getSong().getGenre());
		
		
		Singer s3 = ctx.getBean("singer3", Singer.class);
		// info메소드로 만들어놓지 않았으니까 아래작업
		System.out.println(s3.getName());
		System.out.println(s3.getSong().getTitle());
		System.out.println(s3.getSong().getGenre());
	}

}
