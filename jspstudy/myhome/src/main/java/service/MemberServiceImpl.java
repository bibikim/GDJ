package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import domain.Member;
import repository.MemberDao;

public class MemberServiceImpl implements MemberService {          // MemberServiceImpl : 멤버서비스 구현체!

	// 로그인 
	@Override
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) {
		// login의 반환값을 actionforward  
		// 서비스임플이 바뀌면 서비스도 바껴야함
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		Member member = Member.builder()
						.id(id)
						.pw(pw)
						.build();
						
		Member login = MemberDao.getInstance().login(member);  // 로그인 된 회원 정보를 가져온다.
		
		// if절 자체가 응답!
		
		if(login != null) {    // login를 session에 저장. 브라우저 닫기 전까지는 정보가 유지되는 session에 저장!! session은 request로 알아낼 수 있다.
			HttpSession session = request.getSession();
			session.setAttribute("login", login);   // 해당 session에 login이라는 이름으로 DB에서 넘어온 정보인 member 저장
			return new ActionForward(request.getContextPath(), true);  // 리다이렉트   서버 전체경로를 알려줌(컨텍패부터) <->  포워드-서버안에서의 이동이기 때문에 컨패 밑에부터의 경로를 알려준다!
			//return new ActionForward("/index.jsp", false);   // 포워드  // 로그인 성공하면ContextPath로 이동하겠다
			// index는 경로가 webapp이기 때문에 앞에 경로 없음!! (context 밑에 index.jsp로 보내기) 포워드 하기로 했으니까 jsp로 보낸다.
		} else {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 실패');");
				out.println("history.back()");  // 응답했을 때 메시지 만들고 history.back() 만들어줘라~! (alert창이랑 location.href/history.back()은 세트라고 보면 됨)
				out.println("</script>");
				out.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			return null;    // if안에서는 return  actionforward 인데 왜 반환 안하냐~~~  하고 오류 뜸.
			// if, else 둘다 return을 넣어줘야 한당! 하나만 넣어주면 왜 반환 안하냐고 머라 함. 사실 나는 history.back이 응답(반환)한건데 컴파일에러가 남. 따라서 null로 반환
			
		}
		
		
	}

	// 로그아웃
	@Override
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		
		// 로그아웃 : session을 초기화해서 날려버리면 된다!
		HttpSession session = request.getSession();
		session.invalidate(); // session의 초기화
		
		// 어디로 어떻게.
		return new ActionForward(request.getContextPath(), true);   // 리다이렉트

	}

	@Override
	public void register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
