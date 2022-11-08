package com.gdu.app08.config;

import java.util.Collections;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;


/*
  	@EnableTransactionManagement
  	안녕. 난 트랜잭션매니저를 허용하는 애너테이션이야.
*/
@EnableTransactionManagement

/*
  	@EnableAspectJAutoProxy
  	안녕. 난 Aspect를 자동으로 동작시키는 에너테이션이야.       <- aop 기능
*/
@EnableAspectJAutoProxy


@Configuration   // 컨테이너에 빈을 만들어둘 수 있게 해주는 컨피그레이셔어언~
public class DBConfig {

	// SpringJdbc 처리를 위한 DriverManagerDataSource와 JdbcTemplate을 Bean으로 등록한다.
												// JdbcTemplate : con, ps, rs 갖고 있는 애
	@Bean
	public DriverManagerDataSource dataSource() {   // jdbc 커넥션 만드는애, 커넥션풀 관리하는 객체  -> jdbc 기반의 DataSource라고 생각하면 된다.
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("SCOTT");
		dataSource.setPassword("TIGER");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() { // 스프링프레임워크가 스프링jdbc를 지원하기 위한 것
		return new JdbcTemplate(dataSource());   // dataSource() 의존 관계 처리  -> bean 만든걸 다른 bean에 주입
	}
	
	
	// 트랜잭션 처리를 위한 TransactionManager를 Bean으로 등록한다.
	
	@Bean
	public TransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource()); // 인터페이스라서 new를 만들순 없고 트랜잭션의 구현체중에 하나인 DataSourceTransactionManager로 해준다.
											// 트랜잭션할 때도 dataSource()의 호출이 필요하다.
	}
	
	// 트랜잭션 인터셉터를 Bean으로 등록한다.
	// 인터셉터 -> 어떠한 데이터 흐름이 진행되고 있었을 때 정상적인 흐름을 깨고 강제로 들어가서 데이터 흐름을 뺏는게 인터셉터.
	// 트랜잭션 처리는 인터셉터를 걸어야 한다. 
	@Bean
	public TransactionInterceptor transactionInterceptor() {
		
		// 내부적으로 동작할 일은 모든 Exception이 발생하면 Rollback을 수행하시오.
		RuleBasedTransactionAttribute attribute = new RuleBasedTransactionAttribute();
		attribute.setName("*");
		attribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));  // Exception 타입이면 전부 다 동작한다. 즉 모든 예외에 동작
		
		MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		source.setTransactionAttribute(attribute);
		
		return new TransactionInterceptor(transactionManger(), source);   // 모든 익셉션이 나오면 동작하는 규칙이라고 보면 된다
		
	}
	
	// 트랜잭션 인터셉터를 Advice로 등록하는 Advisor를 Bean으로 등록한다.
	// 만들어둔 인터셉터를 동작할 수 있도록 하는 메소드 advisor()
	@Bean
	public Advisor advisor() {
		
		/*
		  	AOP 기본 용어
		  	1. 조인포인트 : AOP를 동작시킬 수 있는 메소드 전체 		(목록, 상세, 삽입, 수정, 삭제 메소드)
		  	2. 포인트컷   : 조인포인트 중에서 AOP를 동작시킬 메소드 (삽입, 수정, 삭제 메소드)
		  	3. 어드바이스 : 포인트컷에 동작시킬 AOP 동작 자체       (로그, 트랜잭션 등)
		*/
		
		
		// 언제 Advisor를 동작시킬 것인가?
		// 포인트컷을 결정하란 의미. PointCut(전체메소드에서 선택한 메소드)을 결정해야 한다.
		
		// 포인트컷 표현식
		/*
		 	1. 기본 형식
		 		execution(리턴타입 패키지, 클래스, 메소드(매개변수))
		 	2. 의미
		 		1) 리턴타입
		 			(1) * (모든 반환타입. 반환타입 상관없다)
		 			(2) void
		 			(3) !void (void가 아닌 애들)
		 		2) 매개변수
		 			(1) .. 모든 매개변수
		 			(2) *  1개의 모든 매개변수
		*/
		
		AspectJExpressionPointcut pointCut = new AspectJExpressionPointcut();
		// ▼ pointCut.getPointcutExpression("execution(* com.gdu.app08.service.클래스.메소드(매개변수");
		pointCut.setExpression("execution(* com.gdu.app08.service.*Impl.*Transaction(..))");  // *Impl : 모든 임플, *Transaction : 모든 트랜잭션으로 끝나는 메소드(.. : 상관없음)
		
		return new DefaultPointcutAdvisor(pointCut, transactionInterceptor());  // advice == 트랜잭션 인텁세터로 등록
		
	}
	
	// 컨트롤러의 모든 메소드가 동작할 때마다 로그를 찍어보는 수업을 해볼것임미다 22/11/07
	
}
