package com.gdu.app05.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.domain.Member;
import com.gdu.app05.service.MemberService;
import com.gdu.app05.service.MemberServiceImpl;

@Controller
public class MyController1 {

	@GetMapping("/")
	public String index() {
		return "index";		// index.jsp로 forward
	}
	
	@GetMapping("member")
	public String member() {
		return "member";	// member.jsp로 forward
	}
	
	// field
	@Autowired   // container에서 bean을 가져오겠다!  -> container 역할하는 2가지 1. xml(root-context.xml) 2. java(config 패키지) <- java 추천
				 // 여기선Container(root-context.xml)에서 타입(class)이 일치하는 bean을 가져오세요.
	private MemberService memberService;

	//private MemberService memberService = new MemberServiceImpl();   // 인터페이스는 타입으로만 쓰고 new를 만드는건 impl
	//-------------------------------->> bean은 개발자가 만들지 않는다.
	// ㄴ>스프링 입장에서 어이없는 코드. 위오ㅏ 같이 바꿔준다.
	
	
	/*
		@ResponseBody
		안녕. 난 ajax 처리하는 메소드야.
		내가 반환하는 값은 뷰(JSP)이름이 아니고, 어떤 데이터(text, json, xml 등)야
	*/
	
	@ResponseBody    // ajax 통신은 요청과 응답을 동일한 페이지 내에서 처리함. 받아온 데이터는 text(String타입). 받아오는 str를 jsp로 인식하지 않도록 만들어줘야한다 
	@GetMapping(value="member/detail1"	// fn_ajax1() 요청
			, produces="text/plain; charset=UTF-8")  // produces : 응답 데이터 타입(MIME-TYPE)을 적어주는 것.  
	public String detail1(HttpServletRequest request, HttpServletResponse response) {    // 전달되는 파라미터 request로 받아보기
		String str = memberService.execute1(request, response);
		return str;	 // str.jsp로 해석됨 -> @reponseBody로 jsp로 인식되지 않게 설정
	}

	
	@ResponseBody
	@GetMapping(value="member/detail2"
				, produces="application/json; charset=UTF-8")
	public Member detail2(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) {   // 파라미터를 변수로 받기. @RequestParam(value="id") 생략 가능
		Member member = memberService.execute2(id, pw);	// 멤버서비스의 execute2 실행
		return member;  // member 객체를 {"id":아이디, "pw":패스워드} 형식의 JSON 데이터로 바꿔서 전달한다. (입력된 아이디와 패스워드를 Jackson이 제이슨데이터로 바꿔서 해줌! 자바) 
		// 응답 타입은 Member인데 응답 데이터 타입을 제이슨이라고 알려줌(application/json) -> Jackson Databind 작업해둔 라이브러리가 알아서 바꿔줌
		/*
			추억의 코드
			JSONObject obj = new JSONObject(member);
			return obj.toStirng()    				<- 이제 이 코드 안 쓰는거영
		*/
	}
	
	@ResponseBody
	@GetMapping(value="member/detail3"
				, produces=MediaType.APPLICATION_JSON_VALUE)  // MediaType : MIME-TYPE 저장소
	public Map<String, Object> detail2(Member member) {       // 반환타입 맵, 매개변수 member
		Map<String, Object> map = memberService.execute3(member);
		return map;
		// 위에 두줄 => return memberService.execute3(member);
	}
	
	
	
	/*
	 	@RequestBody
	 	안녕. 난 요청 데이터가 Body에 포함되어 있다고 알려주는 일을 해.
	 	요청 파라미터에서는 사용할 수 없고, 
	 	POST 방식으로 파라미터 없이 데이터가 전달될 때 사용할 수 있어.
	*/
	
	@ResponseBody
	@PostMapping(value="member/detail4"
				, produces=MediaType.APPLICATION_JSON_VALUE)
	public Member execute4(@RequestBody Member member) {   // @RequestBody를 붙여야 json데이터 꺼내서 해석할 수 있다
		return memberService.execute4(member);
	}
	
	
	
}
