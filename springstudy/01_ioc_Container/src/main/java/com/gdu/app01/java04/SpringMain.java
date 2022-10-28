package com.gdu.app01.java04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		// 자바 annotation을 통해서 ctx 
		// xml로 만들었으면 Generic~~ 불러야되는데! 자바 annotation 했기 때문에 AnnotationConfigApplicationContext 쓴거양
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
		
		Soldier soldier = ctx.getBean("soldier", Soldier.class);
		soldier.info();
		
		ctx.close();
	}
	
}
