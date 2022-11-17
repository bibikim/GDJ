package com.gdu.app13.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import com.gdu.app13.domain.UserDTO;
import com.gdu.app13.service.UserService;

public class KeepLoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	
	
	// 컨트롤러의 모든 요청 이전에 KeepLoginInterceptor가 개입
	// 컨트롤러의 모든 요청 이전에 개입한다는 코드를 servlet-context.xml에 작성해 둔다. 그래서 모든 요청 이전에 개입할 수 있도록 해줄 것!
	
	@Override     // prehandle = 모든 요청 이전에 수행  <->  posthandle = 모든 요청 이후에 수행
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)   // 여기서의 request, response는 컨트롤러가 사용하는 request와 response를 가로채서 사용함
			throws Exception {


		// 로그인이 되어 있지 않은 경우 + 쿠키에 keepLogin이 있는 경우 => 로그인 유지 등 동작(자동 로그인을 해줘야 되는 것)
		// 로그인이 필요한 사람인지 점검
		
		HttpSession session = request.getSession(); 
		if(session.getAttribute("loginUser") == null) {   // 로그인이 되어 있지 않다면    (로그인이 되어있다면 자동로그인 동작시킬 필요 없으니깐 안되어 있을때만 확인하면 됨)
			
			// 스프링에서는 특정 쿠기를 가져올 수 있음
			Cookie cookie = WebUtils.getCookie(request, "keepLogin"); // 특정 쿠키를 가져오는 방법(원래는 배열이라 for문으로 일치하는거 찾아와야 하는데 스프링에서는 가넝한.)
			if(cookie != null) {      // keepLogin이라는 쿠키가 없으면
				// 자동 로그인 수행할게!
				
				// map을 만들어서 service를 통해 DB로 보내서 세션아이디가 일치하는 정보를 select로 가져올 거야! (selectUserByMap)으로!!
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sessionId", cookie.getValue());
				
				UserDTO loginUser = userService.getUserBySessionId(map);
				if(loginUser != null) {           // null 아니면 (loginUser라는 이름으로 세션에 올려두면 그게 로그인.)
					session.setAttribute("loginUser", loginUser);  // 로그인한다!
				}
				
			}
	
		}
		
		return true;    // true가 반환되면 컨트롤러의 요청을 처리하는 메소드가 수행된다~~ (로그인 처리하고 그담에 요청을 수행해라)
		
	}
	// 사용자가 어디로 들어올지 모르니까. 첫페이지를 예상할 수 없기 때문에 모든 요청 이전에 동작하도록 prehandle 처리
	
}
