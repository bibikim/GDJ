package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app13.domain.SleepUserDTO;
import com.gdu.app13.domain.UserDTO;

public interface UserService {
	// map을 반환하면 jackson이 map으로 잘 바꿔주니까!
	public Map<String, Object> isReduceId(String id);  // id 중복체크, id 넘겨주기
	public Map<String, Object> isReduceEmail(String email);  // email 중복체크, email 넘겨주기
	public Map<String, Object> sendAuthCode(String email);  // 인증코드 발송(db로 갈 것도 읎다)
	public void join(HttpServletRequest request, HttpServletResponse response); 
	// 로그인을 시킨다 -> 세션이 기본! 세션은 request를 통해서 꺼낼 수 있기 때문에 request와 response 를 매개변수로 넘겨주고, 반환타입은 void -> 즉, 응답을 직접 만들어서 가니까 어디로 갈 jsp는 없는거야~
	public void retire(HttpServletRequest request, HttpServletResponse response);
	public void login(HttpServletRequest request, HttpServletResponse response); // 로그인에선 session에 로그인정보 올려두는 것 필수!
	public void keepLogin(HttpServletRequest request, HttpServletResponse response); // 로그인 유지 담당 - keepLogin은 
	public void logout(HttpServletRequest request, HttpServletResponse response); // 로그인에선 session에 로그인정보 올려두는 것 필수!
	
	public UserDTO getUserBySessionId(Map<String, Object> map);  // KeepLoginInterceptor에서 호출(원래 서비스는 컨트롤러가 호출하는데 요건 인터셉터가 호출) - sessionId를 이용한 id 가져오기
	public Map<String, Object> confirmPassword(HttpServletRequest request);  // json으로 반환해서 보내줄거니까 서비스단에서 Map으로 만들어주는게 좋다

	public void modifyPassword(HttpServletRequest request, HttpServletResponse response); 
	
	// 휴면처리
	public void sleepUserHandle(); // SleepUserScheduler에서 호출. -> 자동으로 동작하는 애들을 batch라고 함
	public SleepUserDTO getSleepUserById(String id);  // interceptor에서 부른다 컨트롤러로 따지면 @postMapping("user/login") 수행하려고 할 때 요 인터셉터가 들어와야 함!! 
													  // -> 인터셉터가 id를 갈취해서 sleep_users에 있는지 확인하고 옴 (true=휴면이 아니다, 로긴 완.  false=휴면이다, 로긴 X, 여기서 지정한 다른 루트로 가는 것)

	// 휴면계쩡 복구
	public void restoreUser(HttpServletRequest request, HttpServletResponse response);

	// 네이버 로그인 1
	public String getNaverLoginApiURL(HttpServletRequest request);  // 세션이 필요함 -> request나 session 넘겨주기
	// 네이버 로그인 2
	public UserDTO getNaverLoginTokenNProfile(HttpServletRequest request);
}
