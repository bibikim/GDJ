package com.gdu.app14.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.service.UploadService;

@Controller
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/upload/list")
	public String list(Model model) {
		model.addAttribute("uploadList", uploadService.getUploadList());   // list.jsp로 넘어가는 이름이 uploadList(items="${uploadList}"). 따라서 uploadList 이름으로 model에 실어준다
		return "upload/list";
	}
	
	@GetMapping("/upload/write")
	public String write() {
		return "upload/write";
	}
	
	@PostMapping("/upload/add")
	public void add(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {  // 첨부가 있을 때는 MultipartHttpServletRequest 사용, 첨부할 때 일반 request는 사용 불가
		uploadService.save(multipartRequest, response);
	}
	
	@GetMapping("/upload/detail")  // 매개변수에 서비스한테 넘겨줄 것이 필요. -> uploadNo를 requestParam으로, 모델
	public String detail(@RequestParam(value="uploadNo", required=false, defaultValue="0") int uploadNo, Model model) { // uploadNo가 필수는 아니다, 안 넘어오면 "0"을 주겠다
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/detail";
	}
	
	@ResponseBody                           // @RequestHeader 요청헤더를 뒤져보는 애너테이션. User-Agent 값을 String userAgent에 저장해라
	@GetMapping("/upload/download")
	public ResponseEntity<Resource> download(@RequestHeader("User-Agent") String userAgent, @RequestParam("attachNo") int attachNo) {  // @RequestParam("attachNo") 생략 가능
		// 파라미터로 attachNo는 넘겨줘야한다
		return uploadService.download(userAgent, attachNo);
	}
	
	@GetMapping("/upload/attach/remove")
	public String attachRemove(@RequestParam("uploadNo") int uploadNo, @RequestParam("attachNo") int attachNo) {  // 파라미터에 있는 attachNo를 int attachNo로 저장..?
		uploadService.removeAttachByAttachNo(attachNo);
		
		return "redirect:/upload/detail?uploadNo=" + uploadNo;  // detail로 넘어가려면 uploadNo가 필요하다! -> 파라미터로 받아와야 함(input의 data-속성으로 받아오겟슴)
	
	}
	
	// 첨부목록은 ajax로 처리하는 것이 좋다. .. . . . . . . . mvc 패턴으론 못함.... . . . ..
	
	
}
