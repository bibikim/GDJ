package com.gdu.app01.xml07;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml07/appCtx.xml");
		User user =  ctx.getBean("user", User.class);
		user.info();
		ctx.close();
	}

}
