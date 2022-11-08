package com.gdu.app11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpController {

	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/emp/list")  // 요청했으면 요청한 주소 그대로 복붙하는 게 좋다. (제일 앞에 /는 있어도, 없어도 그만)
	public String list() {
		
		return "employee/list";  // 폴더이름 앞에 / 는 붙여도, 안 붙여도 노상관
	}
}
