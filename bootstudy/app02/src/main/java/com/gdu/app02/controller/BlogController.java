package com.gdu.app02.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app02.service.BlogService;

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
	
	@GetMapping("/blog/write")
	public String write() {
		return "blog/write";
	}
	
	@PostMapping("/blog/add")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		blogService.saveBlog(request, response);
	}
	
	@ResponseBody
	@PostMapping(value="/blog/uploadImage", produces="application/json")   // 이미지를 받아올 수 있는 request => MultipartRequest
	public Map<String, Object> uploadImage(MultipartHttpServletRequest multipartRequest) { // json 반환하기 만만한거 Map -> jackson 디펜던시 넣었으니깐
		return blogService.saveSummernoteImage(multipartRequest);
	}
	
	// 조회수 먼저 시키고 조회수 증가 성공하면 상세보기 하는 걸로 맨든당
	@GetMapping("/blog/increase/hit")
	public String increaseHit(@RequestParam(value="blogNo", required=false, defaultValue="0") int blogNo) {     // required=false, defaultValue="0" 짝꿍 => 파라미터를 안보내면 0을 쓰기로 했기 때문에 조회수증가 없음
		int result = blogService.increaseBlogHit(blogNo);
		if(result > 0) {   // 조회수 증가 성공하면 상세보기로 이동
			return "redirect:/blog/detail?blogNo=" + blogNo;   // update -> detail = redirect:/blog/detail  상세보기로 가야되니까 blogNo를 매핑값+파라미터로 변수를 보내줘야지!!!
		} else {   // 조회수 증가 실패하면 목록보기로 이동
			return "redirect:/blog/list";   // 매핑값이 /blog/list 인 곳을 가겠다
		}
		
	}
	
	// 상세보기
	@GetMapping("/blog/detail")
	public String detail(@RequestParam(value="blogNo", required=false, defaultValue="0") int blogNo, Model model) {
		
		// 편집하러 갈때 상세보기 한번 더 하좌놩 model에 실어넣으면 재활용 해야돼~
		model.addAttribute("blog", blogService.getBlogByNo(blogNo));
		return "blog/detail2"; // select한 다음에 forward => blog밑에 detail2.jsp로 가자
	}
	
	@PostMapping("/blog/edit")
	public String edit(int blogNo, Model model) { // blogNo가 안올 수 없다. <input>에 name에다가 blog.blogNo를 줬으니까 파라미터로 무조건 넘어온다.
		model.addAttribute("blog", blogService.getBlogByNo(blogNo));   //수정하러 갈 때 조회수가 증가하는걸 막을 수 있음 increaseBlogHit는 상세보기에서만 쓰기 위해 만든거니까
		return "blog/edit";
		// 상세보기 -> 상세보기 detail() + 조회수 늘리기 increaseHit()
		// 편집 -> 상세보기 detail()
		// ??????????????????????????
	}
	
	@PostMapping("/blog/modify")
	public void modify(HttpServletRequest request, HttpServletResponse response) {
		blogService.modifyBlog(request, response);  // 수정 후 상세보기로
	}
	
	@PostMapping("/blog/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		blogService.removeBlog(request, response);  // 삭제 후 목록보기로
	}
	
	
}
