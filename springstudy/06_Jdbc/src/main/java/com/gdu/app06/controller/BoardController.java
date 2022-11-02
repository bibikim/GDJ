package com.gdu.app06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.app06.service.BoardService;


@Controller
public class BoardController {

	// ★ Controller는 Service를 사용합니다.
	
	// serviceImpl에 컴포넌트는 만들어져 있음 (@Service)에 의해서. 따라서 @Autowrired로 불러오기 가넝한.
	@Autowired	// 컨테이너에 생성된 bean 중에서 BoardService 타입의 bean을 가져오시오.   
				// BoardService 타입이면 보드서비스임플도 포함되는거! 상속 관계니까요~
	private BoardService boardService;   // null 아님! @Service에 의해 만들어져 있음! null일 수가 없어!
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("brd/list")   // 매핑값. 폴더이름이 아니얄..! // jsp에서 .do 하던 자리! 스프링서 안해도 됨
	public String list(Model model) {   // forward 할 속성(attribute)을 저장할 때 사용한다.  // forward할 때 쓰는거 : Model. request보다 보안이 향상된 객체
										// 목록보기는 목록 가지고 가서 forward하는게 기본
		model.addAttribute("boards", boardService.findAllBoards());   // 목록 전체boardService.findAllBoards()를 이 속성 이름"boards"으로 model에 담아 둔 것.
		return "board/list";  // board 폴더의 list.jsp로 forward
	}
	
	
}
