package com.gdu.app05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.service.GalleryService;

@Controller
public class MyController5 {

	
	@GetMapping("gallery")    // 반환값이 void인 경우, mapping을 뷰(JSP)로 인식한다. 통상적으로 응답을 직접 만들 때 void 처리함
	public void gallery() {
	
	}
	
	@Autowired
	private GalleryService galleryService;
	
	@ResponseBody   // 저장되어 있는 응답을 제대로 가져오기 위해 필요
	@GetMapping("image/display")
	public ResponseEntity<byte[]> display(@RequestParam String path, @RequestParam String filename) {  // 바이트배열이 저장되어있는 응답이다!
		return galleryService.imageDisplay(path, filename); // 디스플레이 메소드 내부에 갤러리 서비스가 갖고 있는 이미지 디스플레이를 호출하고 거기에 패스오 ㅏ파일네임 그대로 전달. 이미지 배열의 바이트 변환능 임플에서 담당! 컨트롤러는 연결만
	}
	
	
	
	
}
