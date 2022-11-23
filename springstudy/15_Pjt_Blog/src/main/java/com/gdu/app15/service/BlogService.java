package com.gdu.app15.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BlogService {
	// 모델을 받아와서 필요한거 실어줄겡~
	public void getBlogList(Model model);  // 컨패도 쓰고 파라미터도 써얗니 request,  request를 매개변수로 안 보냈는데 보낼 수 있음. 여기서 해볼거임
	
	
}
