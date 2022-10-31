package com.gdu.app03.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {

	
	// 여기서 메소드 이름과 MyController에서 필드 이름을 맞춰야 내부적으로 @Qualifier가 작동해서 둘을 연결시킴
	
	@Bean     // setter 이용
	public Notice notice1() {			// <bean id="notice1" class="Notice">
		Notice notice = new Notice();
		notice.setNoticeNo(1);			// <property name="noticeNo" value="1"/>
		notice.setTitle("일반공지");	// <property name="title" value="일반공지"/>
		return notice;
	}
	
	
	@Bean     // 생성자 이용
	public Notice notice2() {			// <bean id="notice2" class="Notice">
		return new Notice(2, "긴급공지"); 	// <constructor-arg value="2"/>
											// <constructor-arg value="긴급공지"/>
	}
	
	
}
