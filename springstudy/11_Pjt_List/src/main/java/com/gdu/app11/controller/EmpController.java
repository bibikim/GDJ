package com.gdu.app11.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.app11.service.EmpService;

@Controller
public class EmpController {

	@Autowired   // 서비스를 사용하는 것은 controller
	public EmpService empService;
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/emp/list")  // 요청했으면 요청한 주소 그대로 복붙하는 게 좋다. (제일 앞에 /는 있어도, 없어도 그만)
	public String list(HttpServletRequest request, Model model) {
		empService.findAllEmployees(request, model);
		return "employee/list";  // 폴더이름 앞에 / 는 붙여도, 안 붙여도 노상관
	}
}
