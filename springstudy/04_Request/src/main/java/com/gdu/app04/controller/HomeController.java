package com.gdu.app04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomController {

	@GetMapping("/")  // 프로젝트의 최초 실행 경로 "/"
	public String welcome() {
		return "index";
	}
	
}
