package com.gdu.app13.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app13.service.UserService;

import oracle.jdbc.proxy.annotation.Post;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/user/agree")
	public String agree() {
		return "user/agree";
	}
	
	@GetMapping("/user/join/write")
	public String joinWrite(@RequestParam(value="location", required=false) String location, @RequestParam(value="promotion", required=false) String promotion
							, Model model) {    // ()에 파라미터 받기
							
		model.addAttribute("location", location);     // 스프링에서 jsp로 값을 넘겨주는 방식 model
		model.addAttribute("promotion", promotion); 
		return "user/join";   // 파라미터 location, promotion 넘겨받은거 join으로 넘겨줘야 함. ┘ 	  
	}
	
	@ResponseBody   // ajax 처리하기 위한 애너테이션
	@GetMapping(value="/user/checkReduceId", produces=MediaType.APPLICATION_JSON_VALUE)  // produces 써줘야 json데이터를 잭슨이 바꿔옴 (mediaType은 springframework)
	public Map<String, Object> checkReduceId(String id) {
		return userService.isReduceId(id);
	}
	
	@ResponseBody   // ajax 처리하기 위한 애너테이션
	@GetMapping(value="/user/checkReduceEmail", produces=MediaType.APPLICATION_JSON_VALUE)  // produces 써줘야 json데이터를 잭슨이 바꿔옴 (mediaType은 springframework)
	public Map<String, Object> checkReduceEmail(String email) {
		return userService.isReduceEmail(email);
	}
	
	@ResponseBody   // ajax 처리하기 위한 애너테이션
	@GetMapping(value="/user/sendAuthCode", produces=MediaType.APPLICATION_JSON_VALUE)  // 요청 : sendAuthCode
	public Map<String, Object> sendAuthCode(String email) {
		return userService.sendAuthCode(email);
	}
	
	@PostMapping("/user/join")
	public void join(HttpServletRequest request, HttpServletResponse response) {  // request, response는 컨트롤러에서 선언하고 서비스로 넘겨주는 방식
		userService.join(request, response);
	}
	
	
}
