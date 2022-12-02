package com.gdu.app11.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.app11.service.EmpListService;

@Controller
public class EmpListController {

	@Autowired
	private EmpListService empListService;
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/emp/change/list")
	public String changeList(HttpServletRequest request, int recordPerPage) {
		// 세션에 recordPerPage를 변경해서 올린 뒤 다시 목록으로 돌아감
		request.getSession().setAttribute("recordPerPage", recordPerPage);
		return "redirect:" + request.getHeader("referer");
	}
	
	@GetMapping("/emp/list")
	public String list(HttpServletRequest request, Model model) {
		empListService.getAllEmployees(request, model);
		return "employee/list";
	}
	
}
