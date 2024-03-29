package com.gdu.app01.xml08;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml08/appCtx.xml");

		Member member = ctx.getBean("Member", Member.class);
		member.info();
		ctx.close();
		
	}

}
