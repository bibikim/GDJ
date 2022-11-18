package com.gdu.app13.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gdu.app13.domain.SleepUserDTO;
import com.gdu.app13.service.UserService;


@Component
public class SleepUserCheckingInterceptor implements HandlerInterceptor {
	
	// servlet-context.xml에 bean만들기작업
	
	@Autowired
	private UserService userService;
	
	@Override   // 로그인 직전에 처리!
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 여기서 id = 로그인하려고 사용자가 입력창에 입력한 아이디
		String id = request.getParameter("id");  
		
		// 해당 아이디가 휴면테이블에 있는지 확인
		SleepUserDTO sleepUser = userService.getSleepUserById(id);  // users.xml에서 반환(selectSleepUserId)한 데이터 다섯개
	
		// session에 휴면계정 정보를 올려둠
		HttpSession session = request.getSession();
		session.setAttribute("sleepUser", sleepUser);   // users.xml에서 반환(selectSleepUserId)한 데이터 다섯개가 여기로 와서 세션에 올려둔 것! db 안 갔다 와도 됨. 
		
		// 휴면회원이면 복원을 위한 과정(/user/sleep/display)을 진행함
		if(sleepUser != null) {
			response.sendRedirect(request.getContextPath() + "/user/sleep/display");
			return false;  // session에 올려둔 id, pw를 
		}
		
		// 휴면회원이 아니면 로그인(/user/login)을 진행함
		else {
			return true;
		}
	}
	
}
