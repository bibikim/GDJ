package com.gdu.app07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.service.BoardService;


@Controller
public class BoardController {

	// ★ Controller는 Service를 사용합니다.
	
	
	
	// -------생성자를 만들어 두면 생성자의 매개변수(BoardService boardService)로 오토와이어드가 자동으로 들어온다 ---------------------------------
	
	private BoardService boardService;   // null 아님! @Service에 의해 만들어져 있음! null일 수가 없어!
	
	// Generate Constructor Using field == @AllArgsConstructor
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	
	// ------------------------------------------------------------------------------------------------------
	
	
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
	
	
	@PostMapping("brd/add")
	public String add(BoardDTO board) {  // 파라미터 전달되는건 name속성. title,         파라미터 받는 방법 => 1. request, 2. 각각의 변수로 받자(@RequestParam String) 3. bean으로 받자(BoardDTO)
		boardService.saveBoard(board);   // saveBoard()로부터 0 or 1이 반환되지만 처리하지 않았다.         컨트롤러는 서비스를 부르기 때문에 bo
		return "redirect:/brd/list";	// insert update delete는 리다이렉트. 삽입후 목록으로 가기 -> brd/list. ***redirect: 뒤에는 무조건 매핑값!!!!!!!
	}
	
	@GetMapping("brd/detail")
	public String detail(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no      // board_no가 null일 수도(안 올 수도) 있다(false), 
									 , Model model) {     // 포워드할 데이터는 model에 저장
		model.addAttribute("board", boardService.findBoardByNo(board_no));   // 호출 결과(boardService.findBoardByNo(board_no))를 board에 담아두기
		return "board/detail";
		
	}
	
	@PostMapping("brd/edit")   // 수정화면으로의 단순 이동
	public String edit(int board_no, Model model) {
		model.addAttribute("board", boardService.findBoardByNo(board_no));   // borad라는 이름으로 검색결과를 가져온 후에 board/edit로 forward 하겠다
		return "board/edit";
	}
	
	@PostMapping("brd/modify")  // 수정페이지에서의 수정 과정 및 완료 
	public String modify(BoardDTO board) {   // modifyBoard 서비스는 BoardDTO로 받고 있으니까 이것도 BoardDTO로 받는게 편하다.
	
		boardService.modifyBoard(board); // modifyBoard()로부터 0 or 1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/list?board_no=" + board.getBoard_no();	// 수정 후에 redirect 하겠다
										// 상세보기 요청은 board_no를 붙여서 보내줘야 함!!!(list.jsp 46행 참고) 만약 붙여서 보내지 않으면 0을 쓴다(defaultValue="0").
	}
	
	@PostMapping("brd/remove")
	public String remove(int board_no) {   // 삭제하고자 하는 게시들의 번호가 파라미터로 넘어온다
		boardService.removeBoard(board_no);   // removeBoard()로부터 0 or 1이 반환되지만 처리하지 않았다.   => 삭제한다요이~
		return  "redirect:/brd/list";   // 삭제 후 목록보기
	}
	
	
	
	@GetMapping("brd/write")
	public String write() {
		return "board/write";  // board 폴더의 write.jsp로 forward
	}

	
	
	
}
