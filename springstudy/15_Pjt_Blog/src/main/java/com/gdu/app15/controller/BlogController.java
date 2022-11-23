package com.gdu.app15.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.app15.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/blog/list")
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);   // model에 request를 저장하기
		blogService.getBlogList(model);    		  // model만 넘기지만 이미 model에는 request가 들어 있음  <- 그래서 서비스단에서 model로부터 request를 꺼낸거임
		// 이렇게 하면 모든 서비스의 매개변수를 model로 통일할 수 있다. (서비스 하나당 메소드 하나 있을 때 매개변수의 통일이 필요함. 모델에 request, response 다 실어보낼 수 있음..)
		return "blog/list";
	}
	
}
