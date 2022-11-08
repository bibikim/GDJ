package com.gdu.app11.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*
 	@MapperScan
 	안녕. 난 @Mapper로 등록된 인터페이스를 bean으로 등록할 수 있도록
 	@Mapper의 위치를 알려주는 애너테이션이야.
*/

@MapperScan(basePackages = {"com.gdu.app11.mapper"}) // 이 패키지를 찾으면 mapper가 들어있따!

/*
 	@PropertySource
 	안녕. 난 프로퍼티 파일을 참조할 수 있는 애너테이션이야. (9장에선 db.properties 파일을 읽어주는 역할)
 	
*/
@PropertySource(value = {"classpath:mybatis/config/mybatis.properties"})   // 2가지 이상 들어갈 수 있어서 {} , value에는 읽어들일 property 경로 적기
// 프로퍼티태그소스로 바꾸기

@EnableTransactionManagement
@EnableAspectJAutoProxy
@Configuration   // 컨테이너에 빈을 만들어둘 수 있게 해주는 컨피그레이셔어언~
public class DBConfig {

	// db.properties 파일을 읽어서 변수에 저장하기
	// ${프로퍼티명}   -> EL로 저장한다.
	// hikariCP를 사용하기 위해서는 변수명 앞에 hikari.를 붙여줘야 한다(db.properties 파일도 마찬가지)
	@Value(value="${hikari.driver}") // 스프링프레임워크가 지원하는 Value
	private String driver;
	
	@Value(value="${hikari.url}")
	private String url;
	
	@Value(value="${hikari.username}")
	private String username;
	
	@Value(value="${hikari.password}")
	private String password;
	
	@Value(value="${mapper.locations}")
	private String mapperLocations;
	
	@Value(value="${config.location}")
	private String configLocation;

	// HikariCP 사용을 위한 세팅
	// HikariConfig  => 클래스 이름
	@Bean
	public HikariConfig config() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driver);
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		return config;                   // db.properties 읽어들이기
	}
	
	// HikariDataSource
	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource() {
		return new HikariDataSource(config()); // hikariConfig를 받아오는 HikariDataSource
	}
	
	
	// myBatis 사용을 위한 세팅
	// SqlSessionFatory  -> sql팩토리에서 발생한 오류를 sql템플릿에서 처리할 수 있도록 던짐!
	public SqlSessionFactory factory() throws Exception {
		SqlSessionFactoryBean bean  = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());  // conncetionPool 정보 넘겨주기.    hikariCP가 지원하는 datasource
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));   // mapper.xml 위치 // mapper가 여러개가 나올 것이므로 es!
		bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));  // mybatis-config.xml의 위치 // config
		return bean.getObject(); // 팩토리빈 -> 팩토리를 만들 수 있는 빈! 빈에서 팩토리 꺼내기 getObject()
	}
	
	// SqlSessionTemplate  -> 필요하고 사용할 애는 sqltemplate이다. bean의 이름(sqlSessionTemplate()) 얘가 다 하는거!
	@Bean					// 또 예외 걍 던져벌임
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(factory());  // factory를 넣어달라!
	}
	
	
	// jsp에서 사용했던 mybatis-config.xml에서의 몇몇의 소스태그는 이쪽으로 이관되어 사용하게 된다.  -> config.xml에서는 이관된 관련 태그 지워주면 된다!
	
	
	// 트랜잭션 처리를 위한 TransactionManager를 Bean으로 등록한다.
	// TransactionManager
	@Bean
	public TransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource()); // 인터페이스라서 new를 만들순 없고 트랜잭션의 구현체중에 하나인 DataSourceTransactionManager로 해준다.
											// 트랜잭션할 때도 dataSource()의 호출이 필요하다.
	}

}
