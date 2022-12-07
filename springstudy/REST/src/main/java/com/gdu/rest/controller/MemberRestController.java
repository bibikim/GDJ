package com.gdu.rest.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gdu.rest.domain.MemberDTO;
import com.gdu.rest.service.MemberService;

/*
  	REST : REpresentational State Transfer
  	
  	1. 자원을 정의하고 자원의 주소를 지정하는 방식
  	2. REST 방식을 따르는 시스템을 "RESTful하다"라고 표현한다.
  	3. 동작의 결정을 URL + Method 조합으로 결정한다.             // 똑같은 매핑 2개 줄 수도 있음. 왜냐면 메소드에 따라 달라지는 방식이라서~
  	4. 파라미터가 URL에 경로처럼 포함된다. (?를 사용하지 않고 /로 연결해서 사용)
 	5. CRUD 처리 방식
 			       URL		    Method
 		1) 삽입   /members      POST     => 이 조합이면 'insert'하겠다란 의미
 		2) 
*/


@RestController   // 이 컨트롤러는 모든 메소드에 @ResponseBody 애너테이션을 추가한다
public class MemberRestController {

	@Autowired   // MemeberServiceImpl에 @Service 애너테이션을 했기 때문에 오토와이어드 가능
	private MemberService memberService;
	
	// 삽입
	@PostMapping(value="/members", produces="application/json")    //responseBody 애너테이션은 하지 않아도 된다~ 레스트컨트롤러 덕분에~
	public Map<String, Object> addMember(@RequestBody MemberDTO member, HttpServletResponse response) {  // @RequestBody = 요청 본문을 뒤지면 그안에 member를 찾아서 MemberDTO에 끼워넣어라!
		// 요청데이터가 본문에 이름없이 포함되어 넘어온다. 따라서 @RequestBody (요청이 본문에 들어있다~ 그래야 본문에서 요청데이터를 찾아온다.)
		// response는 ajax의 에러를 받아서 보여주는 용도로 사용할 것임둥. ResponseEntity는 비교적 최신에 나온거라... 사용하지 않고 map을 사용한 것...
	
		return memberService.register(member, response);   // 결과 맵 반환은 impl에서 해놨으니깐! 
	
	}
	
	// 삽입할 정보가 어떻게 넘어옴? -> ajax 처리할 때 form 불러서 .serialize() 해서 파라미터로 보냈음 (get 방식이면 ?@@=##&@@=## 이렇게 나타나짐)
	// restful은 다르당
	// 본문에 포함되어 있는 data:member(let member)를 어떻게 받을 것인가?   addMemeber() <- 괄호안에!
	// 1. MemberDTO로 받아오기   2. Map으로 받아오기
	// 여기선 MemberDTO로 받아오겠다.
	// 여러개 필드를 저장하겠다 ? ? ? -> DTO 아니면 Map 중에 고르면 된다.
	
	
	
	
	
	
}
