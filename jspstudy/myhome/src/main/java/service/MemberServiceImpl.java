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
			return new ActionForward(request.getContextPath(), true);  // 리다이렉트 : 서버 전체경로를 알려줌(컨텍스트패스부터) <-> 포워드 : 서버안에서의 이동이기 때문에 컨패 밑에부터의 경로를 알려준다!
			
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
	public void register(HttpServletRequest request, HttpServletResponse response) {   // request에 4개의 파라미터가 들어있다더라~~ 꺼내보자
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		Member member = Member.builder()       // db로 보낼 애들 member에 모으기~
				.id(id)			// id() == setId() 같은 느낌. builder 내부에서 set을 씀
				.pw(pw)
				.name(name)
				.email(email)
				.build();
		
		int result = MemberDao.getInstance().insertMember(member);
		
		try {
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(result > 0) {
				// 회원가입하면 로그인 처리하기! -> 세션 필요
				// 회원가입한 회원의 정보를 DB에서 가져온 뒤, session에 login이라는 이름으로 올리기
				HttpSession session = request.getSession();   // 저장하기로한 이름 똑같이 맞춰야됨. login이라는 이름으로 세션에 올려놓는것이 login의 정체!
				session.setAttribute("login", MemberDao.getInstance().login(member));  // 로그인할 때 쓴 메소드(login())에 member(회원가입할때 쓴 Member member) 전달
										// 로그인(select)할때는 아이디, 비번만 있으면 됨ㅇㅇ ->  회원가입(insert)할 때도 아디비번 있으니까 갖다 쓰면 됨
										// 일부 칼럼(=registedDate)이 빠지기 때문에 그냥 다오가 붙지 않는 member만은 적절치 않음. db를 갔다 와야함
				/*
				하나의 서비스가 두번 이상의 다오를 호출하는 경우가 종종 있음.
				가입은 insert, 로그인은 select라 문제가 없음
				근데 둘다 insert라면?   -> 트랜잭션... 둘 중 하나만 성공하면 아예 다 실패해버리게 만드는 거임. 그래야만 함.. 하나만 성공해버리면 이상해짐~
			 	insert, update, delete가 하나의 서비스에서 두번 이상 호출될 때, ^트랜잭션^의 대상이 된다
				
				*/
				
				out.println("alert('환영합니다.');");
				out.println("location.href='" + request.getContextPath() + "';");   // location.href = 자바스크립트의 이동
				// 첫화면으로 보내주기 : getContextPath() --> welcome파일로 이동해라. 즉 index.jsp
			} else {
				out.println("alert('회원가입에 실패했습니다');");
				out.println("history.back();");
			}
			out.println("</script>");
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void cancel(HttpServletRequest request, HttpServletResponse response) {    
			// request로 꼭 삭제할 정보가 넘어와야 하는가? -> session에 로그인 있음. 여기에 모든 칼럼 다 들어있음. 세션에 있기 때문에 파라미터를 안 받아도 된다.
			// 세션에 올라가 있는 정보는 지워지지 않은 정보! 로그아웃 하거나 브라우저 닫았을 때 지워짐. 자동으로 세팅된 시간 지나면 지워진다.
			// 따라서 dao를 갔다오지 않고 session에서 꺼내서 확인해보면 된다????!?!?!?!
			// 회원번호로 탈퇴시킬거였으면 a링크의 href="cancel.me?" ?를 붙여서 전달하는 값을 줘야함 
		
		// session에 저장된 login 정보에서 탈퇴할 회원의 정보를 추출
		HttpSession session = request.getSession();
		Member login = (Member)session.getAttribute("login");      // 세션에 있는 정보 꺼낼 땐 반드시 캐스팅!! 멤버 꺼낼거면 Member로 캐스팅!
		int memberNo = login.getMemberNo();
		
		int result = MemberDao.getInstance().deleteMember(memberNo);
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(result > 0) {
				// 탈퇴 성공하면 session 초기화. 탈퇴했으니까 초기화!
				session.invalidate(); 
				out.println("alert('이용해 주셔서 감사합니다.');");
				out.println("location.href='" + request.getContextPath() + "';");   // location.href = 자바스크립트의 이동
				// 첫화면으로 보내주기 : getContextPath() --> welcome파일로 이동해라. 즉 index.jsp
			} else {
				out.println("alert('탈퇴에 실패했습니다');");
				out.println("history.back();");
			}
			out.println("</script>");
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 실무에서는 탈퇴(delete)하면 끝인게 아니라 탈퇴자 테이블로 또 insert 시켜줘야 한다.
		// 그렇게 되면 delete와 insert가 동시에 돌아야되니까 transaction 필요
		
		
	}
}
