package com.gdu.app11.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("/emp/search")
	public String search(HttpServletRequest request, Model model) {
		empService.findEmployees(request, model);
		return "employee/list";	// 동일한 list.jsp페이지 보여주기
	}
	
	@ResponseBody  // return이 jsp가 아니라고 알려주는 애너테이션. ajax 처리시 필수!!!
	@GetMapping(value="/emp/autoComplete", produces="application/json")   // 다른거 안 적어줄 때는 value를 안 붙여도 되는데, 적어줄 땐 붙여야 함
	// Map이 잭슨에 의해서 json데이터로 알아서 바뀔 것! 반환하는 타입(produces)
	public Map<String, Object> autoComplete(HttpServletRequest request) {
		return empService.findAutoCompleteList(request); 
		      // ---------> 요기가 jsp로 가는게 아니라는걸 알려주기 위해 @ResponseBody
	}
}
