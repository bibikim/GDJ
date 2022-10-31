package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/* MyController2 : Redirect로 request 받아오기 */

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
	
	
	
	// <a href="${contextPath}/board/detail1?title=공지사항&hit=10">  |  HttpServletRequest request
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
	}		// 새로운 두번째 요청 - 아무것도 없음. title, hit는 전달이 안됨. 원래 리다이렉트는 파라미터 전달X
	
				//	↓ 두번재 요청이 요기로 전달.
	@GetMapping("detail2")    // 이 detail2와 바로 윗줄 코드의 detail2 같은거.
	public String detail2(String title, int hit, Model model) {  // -> 다시 받아라!   detail2()안에 String title, int hit, Model model 추가
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		return "board/detail";
	}
	
	// 개발 도중에는 로그를 찍어보는 게 좋다. 전달한 데이터가 잘 왔는지..
	
}
