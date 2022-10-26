package com.gdu.app01.xml04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml04/appCtx.xml");   // xml04 폴더의 appCtx.xml 파일을 실행하겠다!
		
		/*
		// 1)
		Dao dao1 = new Dao();
		Dao dao2 = new Dao();
		Dao dao3 = new Dao();   // 자바는 new가 나오면 무조건 새로 만들기 때문에 이 dao1,2,3들은 다른 Dao임!
		*/
		
		// 2)
		Dao dao1 = ctx.getBean("dao", Dao.class);
		Dao dao2 = ctx.getBean("dao", Dao.class);
		Dao dao3 = ctx.getBean("dao", Dao.class);
		// 객체 3개가 같은 객체!  --> 같으면 싱글톤패턴임. 하나를 가지고 있고 하나를 줬다!
		// bean 태그에 scope속성을 생략함으로써 싱글톤패턴으로 dao가 만들어짐
		
		
		System.out.println(dao1 == dao2);
		System.out.println(dao2 == dao3);
		System.out.println(dao1 == dao3);
		// 1)은 all true, 2)는 all false
		
	}

}
