package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app04.domain.Board;

/* MyController2 : Redirect로 request 받아오기 */
/*

	return "redirect:/board/detail2?title=" +  title + "&hit=" + hit; 

	redirect는 새로운 요청을 만드는 행위. 따라서 리다이렉트("redirect:") 뒤쪽엔 새로운요청을 위한 매핑 주소(중간매핑(마이컨트롤러2)+최종매핑(밑에 detail2))가 나온다(jsp가 아님)
	기존의 요청을 가지고가지 않음. 가지고 가고 싶다면 리턴값에 파라미터(기존 요청)를 다시 붙여서 보내준다.
	
	스프링에서는 리다이렉트할 때 리퀘스트(데이터)값을 유지시키고 보내줄 수 있는 값을 만들어둠 -> 
	
*/

@RequestMapping("board")   // URL Mapping이 board로 시작하는 모든 요청을 처리하는 컨트롤러
@Controller
public class MyController2 {
	
	/*
	 	까먹으면 죽음뿐,,!
	 	
	 	1. forward  ( 포워드는 jsp로 )
	 	return "board/detail"     		 ==> 한마디로 jsp
	 	board 폴더 아래 detail.jsp로 forwrad 하시오.
	 	
	 	2. redirect  ( 리다이렉트는 매핑으로 )
		return "redirect:/board/detail";  ==> 한마디로 Mapping
		URL Mapping 값이 /board/detail인 새로운 요청으로 redirect 하시오.
	*/
	
	
	
	// <a href="${contextPath}/board/detail1?title=공지사항&hit=10">  |  ①HttpServletRequest request  (추천하는 요청에 파라미터를 받아오는 방법)
	@GetMapping("detail1")    // 첫번째 요청(최초 요청) - title과 hit값 전달함
	public String detail1(HttpServletRequest request) {
		
		String title = request.getParameter("title");
		String hit = request.getParameter("hit");
		request.setAttribute("title", title);
		request.setAttribute("hit", hit);
		
		// redirect로 파라미터가 전달되게끔 하기.  리다이렉트할 때는 데이터 전송이 필요하다면 파라미터를 붙여준다.
		return "redirect:/board/detail2?title=" +  title + "&hit=" + hit;  // 새로운 요청 /board/detail2?title=공지사항&hit=10 처리하시오.
																//-> 다시 보내고
		
		// 이렇게 오면
		//return "redirect:/board/detail2";   // urlMapping값. /board/detail2 = 새로운 요청. redirect: 요청을 두번함 -> 첫번째 요청= detail1, 두번째 요청(최종 목적지)= detail2
	}			// 새로운 두번째 요청 - 아무것도 없음. title, hit는 전달이 안됨. 원래 리다이렉트는 파라미터 전달X -> 전달해주고 싶다면 뒤에 파라미터를 붙여준느거(50행)
	
				//	↓ 두번재 요청이 요기 detail2로 전달됨.
	@GetMapping("detail2")    // 이 detail2와 바로 윗줄 코드의 detail2 같은거.  |   ②@RequestParam 에 요청 받아오기(생략 완)
	public String detail2(String title, int hit, Model model) {  // -> 다시 받아라!   detail2()안에 String title, int hit, Model model 추가
		// 리퀘스트를 변수로 받음 -> 리퀘스트 파람 생략
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		return "board/detail";
	}
	
	
	/*
		속성 (Attribute) 전달 방식                               스프링은 모든 이동 방식에서 값을 전달할 수 있다.
		
		구분		|	forward			|	redirect
		---------------------------------------------------------
		인터페이스	|	Model			|	RedirectAttributes     	-> jsp로 포워딩할 때 사용
		메소드		|	addAttributes()	|	addFlashAttribute()
											※ addAttribute()는 forward처럼 동작하므로 사용하지 말 것
 
	*/
	
	
	// <a href="${contextPath}/board/detail3?title=공지사항&hit=10">   |  ③setTitle(), setHit()
	@GetMapping("detail3")
	public String detail3(Board board
						, RedirectAttributes redirectAttributes) {   // RequestAttributes 리다이렉트할 때 속성을 보내주겠다.
			// setTitle(), setHit()가 있으면 알아서 땡겨서 받을 수 있따
		
		redirectAttributes.addFlashAttribute("board", board);  // board라는 이름으로 속성을 저장해서 넘긴 것. 리다이렉트 할 때도 데이터가 유지되어 전달 됨.
		return "redirect:/board/detail4";  // 새로운 요청에 파라미터를 추가하지 않았음.
	}
	
	@GetMapping("detail4")
	public String detail4() {
		return "board/detail";  // board 폴더 밑에 detail.jsp
	}
	
	
	// 개발 도중에는 로그를 찍어보는 게 좋다. 전달한 데이터가 잘 왔는지..
	
}
