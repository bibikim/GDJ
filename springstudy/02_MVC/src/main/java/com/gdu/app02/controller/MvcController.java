package com.gdu.app02.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 	@Controller
 	
 	안녕. 난 컨트롤러야. 
 	@Component에 의해서 자동으로 Bean으로 만들어 주고 스프링에 의해서 사용되고 있어.
*/


@Controller
public class MvcController {

	// jsp할 때처럼 서블릿으로 여는게 X. 스프링은 그냥 자바클래스파일로 컨트롤러를 만들어준다 -> @Controller 애너테이션! 매핑값을 받아서 동작한다.
	// 클래스에 들어갈 수 있는건 필드와 메소드
	
	// * 컨트롤러의 메소드 1개 : 요청 1개와 응답 1개를 처리하는 단위 *
	// 이전에 스위치로 분리하던걸 메소드로 분리하면 된다!
	
	/*
	 	@RequestMapping
	 	
	 	안녕. 난 요청을 인식하는 애너테이션이야.
	 	URL 매핑과 요청 메소드(GET/POST)를 인식하지.
	 	
	 	속성
	 		1) value  : URL Mapping
	 		2) method : RequestMethod
	*/
	
	// welcome 파일 작업하기
	// URLMapping으로 "/"를 요청하면 "/WEB-INF/views/index.jsp"를 열어준다.  웹루트부터 시작해야되는건뎅 기본적으로 웹루트=웹앱(/).  /WEB-INF/views
	
	@RequestMapping(value="/", method=RequestMethod.GET)       // 메소드부터 만든 후에 method="" 작업해야 requestMapping이 import 됨.
	
	
	// 메소드 작성 방법
	// 1. 접근 권한 public
	// 2. 반환타입 : String (응답할 뷰(JSP)의 이름을 반환)     하기 때문에 string을 반환하는 것이 기본
	// 3. 메소드명 : 아무 일도 안 함. 마음대로 작성하라!
	// 4. 매개변수 : 선택 (요청이 있으면 request, 응답을 만들거면 response 등)
	
	public String welcome() {	// 메소드가 생겼다 => @RequestMapping 임폴트가 된다!
		return "index";     // 반환값이 DispatcherServlet의 ViewResolver에 의해 해석된다. 
							// prefix="/WEB-INF/views/"			앞에 붙는 값(경로)
							// suffix=".jsp"                    뒤에 붙는 값(확장자)     --> 경로와 확장자를 ViewResolver가 붙여줘서 index.jsp의 경로가 나오는 것
							// prefix와 suffix에 의해서 파일명만 적어도 "/WEB-INF/views/index.jsp"와 같이 해석되고 처리된다.
							// 이 설명은 servlet-context.xml 파일에 있음
							// return은 jsp 이름으로! return "뫄뫄"랑 views폴더 아래 웰컴파일로 열어줄 jsp파일 이름 맞춰주면 됨.
		// index.jsp로 forward했을까? redirect했을까?
		// 정답 : forward했다.  
		// forward는 jsp파일명만 적어주고 반환, redirect할 때는 return "redirect:경로"; 처럼 반환한다.   ex) return "redirect:index";
		//     -> forward와 redirect의 구구절절 코드는 없어졌다고 보면 된다~!~!
	}
	
	
	// <a href="${cotextPath}/animal">을 요청하는 메소드 만들기
	@RequestMapping(value="/animal", method=RequestMethod.GET)
	public String 동물보러가기() {
		// /WEB-INF/view/ + gallery/animal + .jsp      앞뒤로 경로, 확장자 붙여줘서 경로를 잡아주는 것이 viewResolver의 일
		return "gallery/animal";
	}
	
	
	// @RequestMapping(value="/animal", method=RequestMethod.GET)
	// @RequestMapping(value="animal", method=RequestMethod.GET)    슬래시가 없어도 됩니다.
	// @RequestMapping(value="/animal")								GET은 없어도 됩니다. (POST는 필수)
	// @RequestMapping("/animal")									value로 인식합니다.  (GET생략, value=도 생략 가능)
	// @RequestMapping("animal")									최종버전!

	// <a href="${cotextPath}/flower"> 
	@RequestMapping("flower")
	public String 꽃보러가기() {
		
		// return "/gallery/flower"        슬래시(/)가 있어도 됩니다.
		
		return "gallery/flower";		// 슬래시(/)가 없어도 됩니다.
	}
	
	
	// <a href="${cotextPath}/animal/flower"> 
	@RequestMapping("animal/flower")
	public String 동물보고꽃보고() {
		// redirect: 다음에는 항상 다른 URL Mapping을 적어준다.     animal로 가진 않았으나, @RequestMapping("animal/flower") -> 1차 요청, "redirect:/flower" -> 2차 요청
		return "redirect:/flower";      // /flower은 jsp가 아닌 URLMapping값. redirect 뒤엔 항상 mapping이 와야 한다..!
	}
	
	
	// <a href="${contextPath}/want/aniaml?filename=animal5.jsp">
	@RequestMapping("want/animal")
	public String 동물보기5(HttpServletRequest request) {		// 요청 파라미터는 request로 전달된다. 메소드의 매개변수에 httpServletRequest 필요에 의해 선언 가능
		
		System.out.println(request.getParameter("filename"));
		
		return "gallery/animal5";
		
		// 파라미터는 리퀘스트를 써서 전다, 포워드는 그 리퀘스트를 한번 더쓸수 있게 그대로 전달하는게 포워드
		// 파라미터로 전달했느냐 속성으로 전달했느냐에 따라 꺼내보기가 다름     
		// 속성으로 전달되었다면, 그냥 EL ${} 열어서 보면 되고,
		// 파라미터로 전달했다면, ${param.@@} 로 해서 봐야됨!
	}
	
	// <a href="${contextPath}/response">
	@RequestMapping("response")
	public void 응답만들기(HttpServletRequest request, HttpServletResponse response) {
		
		// 응답을 만들 때는 return을 하지 않는다. return 없이! 반환타입 없을 땐 void
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('동물보러 가자');");
			out.println("location.href='" + request.getContextPath() + "/animal';");
			out.println("</script>");
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
