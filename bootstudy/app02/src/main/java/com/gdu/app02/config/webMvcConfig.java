package com.gdu.app02.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webMvcConfig implements WebMvcConfigurer {  // 임플먼트 특징: 컨트롤스페이스 누르면 구현해야하는 목록이 나옴

	
	/*
		 ▼ servlet-context.xml 에서 사용하던 resource 태그들 자바파일로 만들어주기
		
		 <resources mapping="/resources/**" location="/resources/" />
		 <resources mapping="/load/image/**" location="file:///C://upload/"/>
		 
		// interceptor 태그 해주는 것은 addInterceptors로. (13장으로 적용 가능)
	 */
	
	
	// resource 태그 매핑을 잡아주는 것이 addResourceHandlers
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {  // 각종 환경을 registry에 등록
		
		registry.addResourceHandler("/load/image/**")   // 매핑 적기
			.addResourceLocations("file:///C:/summernoteImage/");

	}
	
	
	
	
}
