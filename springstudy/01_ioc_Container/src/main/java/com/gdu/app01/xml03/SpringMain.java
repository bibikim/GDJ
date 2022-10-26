package com.gdu.app01.xml03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml03/appCtx.xml");
		Person person = ctx.getBean("personnn", Person.class);
		
		System.out.println(person.getName());
		System.out.println(person.getAge());
		Address addr = person.getAddr();
		
		System.out.println(addr.getJibun());
		System.out.println(addr.getRoad());
		System.out.println(addr.getZipCode());

		ctx.close();
	}

}
