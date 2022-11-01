package com.gdu.app05.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.domain.Board;
import com.gdu.app05.service.BoardService;

@Controller
public class MyController2 {

	@GetMapping("board")
	public String board() {
		return "board";    // board.jsp로 forward
	}
	
	// BoardServiceImpl의 execute1() 메소드 호출
	@Autowired
	private BoardService boardService;
	
	@ResponseBody
	@GetMapping(value="board/detail1"
				, produces=MediaType.APPLICATION_JSON_VALUE)   // response.setContentType 과 같은 코드라고 보면 된다.
	public ResponseEntity<Board> detail1(HttpServletRequest request) {
		return boardService.execute1(request);
	}
	
	
	@ResponseBody
	@GetMapping(value="board/detail2")  // produces가 없음을 주의! 반환값 ResponseEntity에 관련 코드를 작성하였음.
	public ResponseEntity<Board> detail2(@RequestParam(value="title") String title, @RequestParam(value="content") String content) {
		return boardService.execute2(title, content);
	}
	
	
	/*
	  	produces=MediaType.APPLICATION_JSON_VALUE  : controller의 매핑값에 써줄 때.
	  	
	  	HttpHeaders header = new HttpHeaders();
		header.add("Content-type", MediaType.APPLICATION_JSON_VALUE);  :  impl에 써줄 때.
		
		둘은 같은 역할을 하는 코드. 어디에 코드를 쓰느냐에 따라 생김새가 조금 다른것 뿐이다.
		produces랑 header 모두 생략 가능은 하지만, 권장하지 않는다
		반드시 한 곳에서는 코드를 짜주어야 한다.
	  	
	*/
	
	
	@ResponseBody
	@GetMapping(value="board/detail3")
	public ResponseEntity<Board> detail3(Board board) {
		return boardService.execute3(board);
	}
	
	
}
