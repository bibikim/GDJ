package com.gdu.app12.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app12.service.BbsService;

@Controller
public class BbsController {

	@Autowired   // 서비스를 사용하는 것은 controller
	public BbsService bbsService;
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/bbs/list")
	public String list(HttpServletRequest request, Model model) {
		bbsService.findAllBbsList(request, model);      // request에 recordPerPage가 포함 되어 있음
		return "bbs/list"; // 이동할 장소 bbs 폴더 아래 list.jsp
	}
	
	@GetMapping("/bbs/write")
	public String write() {
		return "bbs/write"; // bbs 폴더 아래 write.jsp
	}
	
	@PostMapping("/bbs/add")
	public String add(HttpServletRequest request) {
		bbsService.addBbs(request);
		return "redirect:/bbs/list";
	}
	
	@PostMapping("/bbs/remove")
	public String remove(@RequestParam("bbsNo") int bbsNo) {
		bbsService.removeBbs(bbsNo);
		return "redirect:/bbs/list";
	}
	
	@PostMapping("/bbs/reply/add")
	public String replyAdd(HttpServletRequest request) {
		bbsService.addReply(request);
		return "redirect:/bbs/list";
		// service에서 여기의 request에서 받아옴. 따라서 컨트롤러에서 리퀘스트를 선언해줘야 함
	}
	
}
