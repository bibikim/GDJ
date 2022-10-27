package com.gdu.app01.xml06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml06/appCtx.xml");
		Person p = ctx.getBean("human", Person.class);
		p.info();
		ctx.close();
		
		// <property> ㅌㅐ그는 setter 메소드를 이용해서 데이터를 넣어줌(주입해줌)!!!  ==> 세터 주입(setter injection) -->
		
	}

}
