package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.AdderService;
import service.MyService;
import service.NowService;
import service.TodayService;

// 디폴트 패키지. 실제로 만들어지지는 않음

@WebServlet("*.do")  // .do로 끝나는 모든 요청
// @WebServlet({"/today.do" "/now.do" "/adder.do"})  :  today.do와 now.do의 요청을 모두 받겠다 -> 요청이 생길 때마다 맵핑값을 추가해야하므로 좋은 방법 X

public class MyCotroller extends HttpServlet {    // controller 코드를 외울 필요는 없다
	
	private static final long serialVersionUID = 1L;
       
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 & 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8");
		
		// 요청 확인(today와 now 요청을 구분해야 함)
		// today가 붙어있으면 todayService를 호출, now가 붙어있으면 nowService를 호출할 수 있게끔.
		String requestURI = request.getRequestURI();	  					// requestURI   : /03_Mvc/today.do 또는 /03_Mvx/now.do
		String contextPath = request.getContextPath();    					// contextPath  : /03_Mvc
		String command = requestURI.substring(contextPath.length() + 1);    // command      : contextPath의 길이(7) + 1 -> 8번 인덱스(t / n)부터 끝까지 가져오라.
		
		// MyService 선언(모든 Model은 MyService 타입이다) MyService는 해당 작업을 생성할 때 사용하는 type이 되는거임
		MyService myService = null;
		
		// ActionForward 선언
		// 1. 모든 Model의 execute() 메소드 반환타입은 ActionForward 이다!
		// 2. Model이 필요 없는 경우 ActionForward를 직접 만든다!
		ActionForward actionForward = null;
		
		
		// 요청에 따른 Model의 선택
		switch(command) {
		// 비즈니스 로직(Model)이 필요한 경우
		case "today.do" :
			myService = new TodayService();
			//myService.execute(request, response);
			break;
		case "now.do" :
			myService = new NowService();
			//myService.execute(request, response);   => 중복으로 들어가니까 switch 밖으로 빼서 한번에!
			break;
		case "adder.do" :
			myService = new AdderService();
			break; 
			
		// 비즈니스 로직(Model)이 필요 없는 단순이동의 경우
		case "input.do" :   // model이라는 개념이 사용되지 않아도 괜찮다. input.jsp로 이동만 하면 됨. 이동할 정보를 result.jsp로 보내주믄 됨...?
							// 이동하고자 하는 view를 변수로 전달해보자
			actionForward = new ActionForward();  
			actionForward.setView("views/input.jsp");   // actionForward의 input.jsp로 이동하겠다  // 단순이동은 actionForward를 직접 만듬
			break;
		}
		
		// 선택한 Model의 실행
		//myService.execute(request, response);
		if(myService != null) {
			actionForward = myService.execute(request, response);  
		}
		// 이동(리다이렉트, 포워드)    ->(처리를 마치고 이동하기 때문에 응답의 개념이라 보면 됨)
		// 1. actionForward != null   : 리다이렉트 또는 포워드
		// 2. acitonForward == null   : response로 응답한 경우 또는 ajax 처리
		if(actionForward != null) {    // null을 받아오면 밑에 getView에 null에 getView가 되는 꼴이므로 if를 넣어준다
			// actionForward에 응답할 뷰가 들어가 있음. 따라서 actionForward에 들어있는 뷰를 가져와서(getView) 해당 경로로 이동하겠다. 반환이 아님
			if(actionForward.isRedirect()) {
				response.sendRedirect(actionForward.getView());
			} else {
				request.getRequestDispatcher(actionForward.getView()).forward(request, response);
			}
		}
		
		// request를 전달하는 이동 방식은 forward.
		//request.getRequestDispatcher("result.jsp").forward(request, response);   // doGet의 request(today)를 result.jsp로보내주겠다
		
		
		// Today랑 Now 분리할 때 하는 방식 ▼
		//TodayService todayService = new TodayService();
		// 인덱스에서 링크클릭하면 today로 오고 today가 todayService를 부름
		//todayService.execute(request, response);   // 실행메소드. 요청과 응답을 전달해서 처리할 수 있게 만들어주기
		// doGet이 실행하는게 아니라 excute()에 넘겨주는 방식으로 작업하게 되는 것. 요청을 직접 처리하지 않고 todayService의 excute로 넘김
		// ★★ doGet의 request와 execute의 request는 같은 request. 
		// 참조타입은 서로 주소값을 공유하기 때문에 하나의 request라고 보면 된다
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
