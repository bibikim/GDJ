package com.gdu.app01.java03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// 스프링빈컨피그를 가져오려면 원래는 @con 애너테이션 하지만
		// 이미 appCtx로 가져왔기 때문에 GenericXmlApplicationContext로 불러준다.
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("java03/appCtx.xml");
		
		Book book1 = ctx.getBean("book1", Book.class);
		System.out.println(book1.getTitle());
		System.out.println(book1.getAuthor());
		System.out.println(book1.getPublisher().getName()); 
		System.out.println(book1.getPublisher().getTel());   // 이거는 자바파일에 만들어둔 book1인데 xml로 들어갔는지 확인!
		
		Book book2 = ctx.getBean("book2", Book.class);
		System.out.println(book2.getTitle());
		System.out.println(book2.getAuthor());
		System.out.println(book2.getPublisher().getName()); 
		System.out.println(book2.getPublisher().getTel());	// 이거는 xml파일에 만들어둔 book2
		
	}

}
