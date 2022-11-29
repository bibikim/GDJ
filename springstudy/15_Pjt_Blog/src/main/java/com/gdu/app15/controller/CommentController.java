package com.gdu.app15.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app15.domain.CommentDTO;
import com.gdu.app15.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@GetMapping(value="/comment/getCount", produces="application/json")  // 데이터에 한글 포함되는거 없어서 미디어타입안해도 괜춘
	public Map<String, Object> getCount(@RequestParam("blogNo") int blogNo) {  // blogNo를 파람으로 받아와서 int blogNo로 저장
		return commentService.getCommentCount(blogNo);
	}
	
	@ResponseBody  // ajax 반환!
	@PostMapping(value="/comment/add", produces="application/json")
	public Map<String, Object> add(CommentDTO comment) {
		return commentService.addComment(comment);
	}
	
	@ResponseBody
	@GetMapping(value="/comment/list", produces="application/json")
	public Map<String, Object> list(HttpServletRequest request) { // request로 넘겨줘쓰니까 여기서 request 선언해주기
		return commentService.getCommentList(request);
		
	}
	
	@ResponseBody
	@PostMapping(value="/comment/remove", produces="application/json")
	public Map<String, Object> remove(@RequestParam("commentNo") int commentNo) { // commentNo만 받으면 되니까 리퀘파람으로 바로 받기
		return commentService.removeComment(commentNo);
	}
	
	@ResponseBody
	@PostMapping(value="/comment/reply/add", produces="application/json")
	public Map<String, Object> replyAdd(CommentDTO reply) {
		return commentService.addReply(reply);
	}
	
	
}
