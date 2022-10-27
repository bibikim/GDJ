package com.gdu.app01.java01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 	@Configuration
 	안녕. 난 Bean을 만드는 Java 파일이야.
 	Spring Bean Configuration File하고 하는 일이 같지.
*/

// xml 처리를 수행하는 역할을 함

@Configuration                                 // --> 제일 먼저 xml 파일을 대신할, Bean을 등록시킬 자바 클래스에 @onfiguration 애너테이션을 해준다
public class SpringBeanConfig {

	// 메소드 하나당 Bean 하나를 맡아서 생성한다.
	
	/*
		@Bean
		안녕. 난 Bean을 만드는 메소드야.
		메소드 이름이 Bean의 이름(id)이고,
		반환타입이 Bean의 타입(class)야.
	*/
	/*
	    bean으로 노래를 하나 만든다.
	 	<bean id="song1" class="Song">
	 		<property name="title" value="피땀눈물"/>
	 		<property name="genre" value="dance"/>
	 	</bean>
	*/
	@Bean                   // xml에서의 <bean>태그 없이 @Bean 애너테이션 하나로 자바를 이용해서 역할 수행 가능!
	public Song song1() {	// 메소드 이름(song1)하고 bean의 id(song1)는 같다고 그랬음. 같은거 확인 가넝!
		Song song = new Song();
		song.setTitle("피땀눈물");   // 프로퍼티 태그는 setter를 사용한다고 햇음. 따라서 set으로 데이터가 주입됨!
		song.setGenre("dance");
		return song;
	}
	
	
	/*
	    
	 	<bean id="song2" class="Song">
	 		<property name="title" value="피땀눈물"/>
	 		<property name="genre" value="dance"/>
	 	</bean>
	*/
	@Bean(name="song2")      // @Bean에 name값을 지정하면 id로 사용된다.
	public Song zehfodi() {	 // └> 그래서 메소드 이름 이상하게 마음대로 줘도 괜찮은거!
		Song song = new Song();
		song.setTitle("불타오르네");   // 프로퍼티 태그는 setter를 사용한다고 햇음. 따라서 set으로 데이터가 주입됨!
		song.setGenre("dance");
		return song;
	}
	
		
	/*
	   	생성자를 이용한 주입(Constructor Injection) 
	 	<bean id="song3" class="Song">
	 		<property name="title" value="봄날"/>
	 		<property name="genre" value="발라드"/>
	 	</bean>
	*/
	@Bean 
	public Song song3() {
		return new Song("봄날", "발라드");
	}

	
	// 미션
	// song1이 가지는 singer1을 만들어 보자
	// setter injection 이용
	@Bean
	public Singer singer1() {
		Singer singer = new Singer();
		singer.setName("뱅탠");
		singer.setSong(song1());     // song1() 메소드 호출!
		return singer;
	}
	
	
	// song2를 가지는 singer2를 name값으로 만들어 보자
	@Bean(name="singer2")
	public Singer bts() {
		Singer singer = new Singer();
		singer.setName("방탄");
		singer.setSong(zehfodi());
		return singer;
	}
 	
	
	// song3을 가지는 singer3을 만들어보자
	// constr
	@Bean
	public Singer singer3() {
		return new Singer("봥퇀", song3());
	}
	
}
