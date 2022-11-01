package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app04.domain.Member;

/* MyController1 : Forward로 request 받아오기 */



// 중간 매핑을 member로 하는 경우 컨트롤러에 중간 mapping값을 아래와 같이 컨트롤러단으로 빼줄 수 있다.
@RequestMapping("member")   // URL Mapping이 member로 시작하는 모든 요청을 처리하는 컨트롤러
@Controller
public class MyController1 {

	// <a href="${contextPath}/member/detail">   |   ①파라미터 받아오는 방식 HttpServletRequest
	@GetMapping("detail1")   // member/detail1 요청을 처리하시오.
	public String detail(HttpServletRequest request) {		// member/detail 요청을 처리하시오.  요청은 () 안에 매개변수로 간다. 파라미터 저장하는 객체 HttpServletRequest
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member member = new Member(id, pw);
		
		// forward할 데이터를 request에 담아 두는 방법
		request.setAttribute("member", member);
		
		return "member/detail";  // member 폴더 아래 detail.jsp로 forward 하시오.
	}
	// url매핑값을 하나는 컨트롤러가, 하나는 메소드가 가져감 - > 분리 사용 가능
	// 회원을 만들겠다 -> 컨트롤러 도메인 서버 레파지토리 jsp 다 한사람이 만듦, member로 중간매핑으로 두고 
	
	
	
	// location.href='${contextPath}/member/detail2?id=admin&pw=1234';   |  ②파라미터 받아오는 방식 @RequestParam.  파라미터를 개별변수에 받아온다.
	@GetMapping("detail2")
	public String detail2(@RequestParam(value="id", required=false, defaultValue="master") String id  // 파라미터 id를 String id에 저장하시오. / required=false : id가 꼭 필요하진 않다.
						, @RequestParam(value="pw", required=false, defaultValue="1111") String pw  // 파라미터 pw를 String pw에 저장하시오. / required=false : pw가 꼭 필요하진 않다.
							// └=> request.getParameter("")  +  파라미터의 필수 여부를 required=false 통해서 조정할 수 있다.
						, Model model) {
		/*
			@RequestParam
			1. value 		: 파라미처 이름
			2. required 	: 파라미터의 필수 여부(디폴트는 true)
			3. defaultValue : 파라미터가 없을 때 사용할 값
		*/
		Member member = new Member(id, pw);    
		
		// forward할 데이터를 model에 담아 두는 방법(이것이 스프링의 방식)
		// request를 이용하는 방식에 비해 보안이 향상 되었다.
		model.addAttribute("member", member);  // model에 실어주는 것도 내부동작으로는 request의 속성으로 실어주는거임. 
									  // model은 request를 저장소로 사용된다. 
		return "member/detail";  // detail.jsp 의미
	}
	
	
	// location.href='${contextPath}/member/detail3?id=admin&pw=1234';   |  ③파라미터 받아오는 방식 Model객체 생성.   파라미터를 저장할 수 있는 객체(Model)를 받을 수 있다
	@GetMapping("detail3")
	public String detail3(String id  // @RequestParam은 생략할 수 있다. 파라미터 id가 없는 경우 null이 저장된다.
						, String pw  // @RequestParam은 생략할 수 있다. 파라미터 pw가 없는 경우 null이 저장된다.
						, Model model) {  // model은 포워드할 데이터를 담아둘 곳
		
		Member member = new Member(id, pw);
		model.addAttribute("member", member);
		
		return "member/detail";  
	}
	
	
	// <form action="${contextPath}/member/detail4" method="get">   |   
	@GetMapping("detail4")
	public String getDetail4(Member member  	// 파라미터 id, pw를 setId(), setPw() 메소드를 이용해서 member 객체에 저장해 준다.
							, Model model) {	// 일일히 String id, pw 만들어줄 필요가 없이 바로 선언해주면 되는거임!
		model.addAttribute("member", member);
		
		return "member/detail";
	}
	
	// <form action="${contextPath}/member/detail4" method="post">
	// 동일한 요청인데, post방식으로 바꾸면서 구분해서 작업할 수 있는가?
	// 데이터도 정상적으로 간다. post이기 때문에 주소에 파라미터가 붙지 않을 뿐!!
	@PostMapping("detail4")		// 요청 : urlMapping값 + 요청메소드    -> 요청메소드가 다르다면 동일한 요청도 다른 걸로 인식한다. 같은 요청이어도 메소드가 다르면 상관없다.
	public String postDetail4(@ModelAttribute(value="member") Member member) {	 // 파라미터 id, pw를 이용해 Member member를 만들고, Model에 member라는 이름의 속성으로 저장하시오.
							// └> 모델에 속성을 담아주겠다. 속성의 이름은 member다
		
		
		// 메소드 방법에 따라 하는 일이 달라진다. 동일한 요청이어도 메소드가 다르면 다른 요청으로 인식한다
		return "member/detail";
	}
	
	
}
