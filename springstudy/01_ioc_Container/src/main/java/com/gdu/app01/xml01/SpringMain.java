package com.gdu.app01.xml01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		// 기존 개발자
		//  ┌> 이 방식은 개발자가 Bean을 만든 방식.
		// Calculator calculator = new Calculator();

		// 새로운 프레임워크
		// 프레임워크가 만든 Bean을 가져다 쓴다.
		
		// XML에 저장된 Bean 가져오는 클래스
		// GenericXmlApplicationContext
		// classPathXmlApplicationContext
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:xml01/appCtx.xml");  // classpath:는 생략 가능
			// └>컨테이너에 저장되어있는 bean을 가져다 쓸 때 쓰는 거             // └ 내가 만든 xml 파일 경로
		Calculator calculator = ctx.getBean("calculator", Calculator.class);   // 컨테이너에서 빈을 가져오겠다
		                      // 가져올 빈의 이름(getBean)("<bean>의 id랑 맞추기", <bean>의 class )
													// <!-- 쉽게 말해서 id가 객체고 class가 객체 타입? -->
		calculator.add(5, 2);
		calculator.sub(5, 2);
		calculator.mul(5, 2);
		calculator.div(5, 2);
		
		Student student = ctx.getBean("haksang", Student.class);    // ==> Student student = (Student)ctx.getBean("haksang")  타입을 맞추기 위해 캐스팅
		System.out.println(student.getName());
		System.out.println(student.getSchool());
		student.getCalculator().add(7, 3);  // 학생이 가지고 있는 계산기를 가져와서 계산하겠다
		student.getCalculator().sub(7, 3);
		student.getCalculator().mul(7, 3);
		student.getCalculator().div(7, 3);
		
		// 이처럼, 직접 new를 만들지 않고 bean은 컨테이너에 만들라고 적어주면 스프링이 만들어주고 우리가 가져다 쓰는 것!
		// 제어의 역전. ioc.  개발자가 직접 제어하던걸 프레임워크가 제어한다.?
		
		ctx.close();   // 생략 가능
		
	}

}
