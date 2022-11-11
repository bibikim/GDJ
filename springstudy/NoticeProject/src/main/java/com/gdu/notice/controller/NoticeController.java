package com.gdu.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.notice.service.NoticeService;

import oracle.jdbc.proxy.annotation.Post;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/ntc/list")
	public String list(Model model) {
		noticeService.findAllNotices(model);
		return "notice/list";
	}
	
	@GetMapping("/ntc/write")
	public String write() {
		return "notice/write";
	}
	
	@PostMapping("/ntc/add")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		noticeService.addNotice(request, response);
	}
	
	@GetMapping("/ntc/detail")    // /ntc/detail?notice=    <- 상세보기니까 파라미터를 받아온다. 
	public String detail(@RequestParam(value="noticeNo", required=false, defaultValue="0") int noticeNo, Model model) {   // 서비스에서도 그냥 int noticeNo만 보내주고 있으므로 @RequestParam으로 받아오는게 좋다   
		                            // 파라미터 이름을 value에 적는거!
									// 파라미터로 노티스넘이 넘어오지 않는다 -> required = false 로 하면 400 오류는 안 남! (서버중지되는게 아니라 보여주지 않는 걸로 작업을 해놓은 것)
									// 대신에 defaultValue로 기본값 주기 -> 파라미터로 noticeNo이 넘어오지 않으면 0으로 하겠다. 없는 번호를 줘서 조회가 안 되게끔 하는 것
		// request response model을 최초로 선언할 수 있는 곳은 controller 뿐!
		// 최초 선언 자리는 컨트롤러!
		
			noticeService.findNoticeByNo(noticeNo, model);
			// 이제 model에 상세보기할 내용까지 저장된 것   -> 이 상태에서 포워드만 하면 됨!  (selectNoticeByNo()의 결과가 "notice"라는 이름으로 model에 실려있다)
			return "notice/detail";  // notice폴더 아래 detail.jsp로 가겠다~
	}
	
	
	@PostMapping("/ntc/modify")
	public void modify(HttpServletRequest request, HttpServletResponse response) {  // 응답을 만들었을 땐 void 처리 (try-catch문 안에 처럼 한거 말하는거당)
		noticeService.modifyNotice(request, response);  // 그대로 전달해서 응답까지! "location.href='" + request.getContextPath() + "/ntc/list';"
	}
	
	@PostMapping("/ntc/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		noticeService.removeNotice(request, response);  // 그대로 전달해서 응답까지 만들어 줬다!!! "location.href='" + request.getContextPath() + "/ntc/list';"
	}
	
	
}
