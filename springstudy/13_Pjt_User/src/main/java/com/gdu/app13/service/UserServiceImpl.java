package com.gdu.app13.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app13.domain.RetireUserDTO;
import com.gdu.app13.domain.UserDTO;
import com.gdu.app13.mapper.UserMapper;
import com.gdu.app13.util.SecurityUtil;

import lombok.AllArgsConstructor;

//@AllArgsConstructor // 필드들의 autowired 역할을 한번에 처리함!
@PropertySource(value= {"classpath:email.properties"})
@Service
public class UserServiceImpl implements UserService {

	// 이메일을 보내는 사용자 정보
	@Value(value="${mail.username}")
	private String username;   // 본인 지메일 주소
	
	@Value(value="${mail.password}")
	private String password;   // 발급 받은 앱 비밀번호
	
	@Autowired  // -> 필드가 2개니까 autowired 안하고 allArgsCon~으로
	private UserMapper userMapper;
	@Autowired
	private SecurityUtil securityUtil;   // 인증코드, 비밀번호 암호화를 위해 선언
	
	@Override
	public Map<String, Object> isReduceId(String id) {
		
		// selectUserByMap 사용하기 위해 map 선언해서 id 값 넣어주기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id); 
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		// selectUserByMap 수정 부분
		// result.put("isUser", userMapper.selectUserById(id) != null);    // select 결과가 null이 아니면(조회 되었으면) true - 회원이다
		result.put("isUser", userMapper.selectUserByMap(map) != null);
		
		result.put("isRetireUser", userMapper.selectRetireUserById(id) != null);    // select 결과가 null이 아니면(조회 되었으면) true - 탈퇴한 회원의 아이디
		return result;
	}

	@Override
	public Map<String, Object> isReduceEmail(String email) {
		
		// selectUserByMap 사용하기 위해 map 선언해서 email 값 넣어주기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		// selectUserByMap 수정 부분
		// result.put("isUser", userMapper.selectUserByEmail(email) != null);  // 검색결과가 null이 아니면 회원인 것! 기존 회원이 가지고 있다는것
		result.put("isUser", userMapper.selectUserByMap(map) != null); 
		return result;
	}
	
	@Override
	public Map<String, Object> sendAuthCode(String email) {
		
		// 인증코드 만들기
		String authCode = securityUtil.getAuthCode(6);                // 수동으로 우리가 만든거
		//String authCode = securityUtil.generateRandomString(6);       // 스프링-디펜던시가 지원해줌. 둘중 뭘 쓰든 상관없다
		System.out.println("발송된 인증코드 : " + authCode);
		
		// 이메일 전송을 위한 필수 속성을 Properties 객체로 생성
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");  // 구글 메일로 보냄(보내는 메일은 구글 메일만 가능)
		properties.put("mail.smtp.port", "587");             // 구글 메일로 보내는 포트 번호
		properties.put("mail.smtp.auth", "true");            // 인증된 메일
		properties.put("mail.smtp.starttls.enable", "true"); // TLS 허용
				
		/*
		 	이메일 보내기 API 사용을 위한  사전 작업
		 	
		 	1. 구글 로그인
			2. [Google 계정] - [보안]
			    1) [2단계 인증]  - [사용]
			    2) [앱 비밀번호]
			        (1) 앱 선택   : 기타
			        (2) 기기 선택 : Windows 컴퓨터
			        (3) 생성 버튼 : 16자리 앱 비밀번호를 생성해 줌(이 비밀번호를 이메일 보낼 때 사용)
		 	
		 */

		
		// 사용자 정보를 javax.mail.Session에 저장 (디펜던시에서 지원)
		Session session = Session.getInstance(properties, new Authenticator() { 	// Authenticator 
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(username, password);
			}
		});
		
		// 이메일 작성 및 전송
		try {
			
			Message message = new MimeMessage(session);
		
			message.setFrom(new InternetAddress(username, "인증코드관리자"));  			  // 보내는사람(username)
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));   // 받는 사람은 (String email)
			message.setSubject("[한비마켓]인증 요청 메일입니다.");   					  // 제목
			message.setContent("인증번호는 <strong>" + authCode + "</strong>입니다.", "text/html; charset=UTF-8"); // 본문  - 뒤에는 (,) 앞의 mime-type 써주면 된다
			
			Transport.send(message);  // 이메일 전송
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// ajax이기 때문에 join.jsp로 반환할 데이터( -> 생성한 인증코드를 보내줘야 한다.)
		// 그래야 사용자가 입력한 인증코드와 비교를 할 수 있음! -> map으로 반환. json으로 넘기고 있기 때문에 map으로 넘기는게 낫당
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("authCode", authCode);
		return result;            // success: function(resData)까지 authCode를 보내주는 것임
	}
	
	@Transactional   // INSERT, UPDATE, DELETE 중 2개 이상이 호출되는 서비스에서 필요함. insertUser, updateAccessLog
	@Override
	public void join(HttpServletRequest request, HttpServletResponse response) {

		// 파라미터(넘어오는 파라미터가 궁금하면 join.jsp 참고!)
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");   // 비밀번호는 암호화처리 필요!    re_pw는 넘길필요가 없어서 넘기지 않음
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String mobile = request.getParameter("mobile");
		String birthyear = request.getParameter("birthyear");
		String birthmonth = request.getParameter("birthmonth");
		String birthdate = request.getParameter("birthdate");
		String postcode = request.getParameter("postcode");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		String email = request.getParameter("email");    // authCode(인증코드)는 db에 저장할 필요 없으므로 넘길 필요 없다!
		String location = request.getParameter("location");
		String promotion = request.getParameter("promotion");
		
		// 일부 파라미터는 DB에 넣을 수 있도록 가공
		pw = securityUtil.sha256(pw);
		detailAddress = securityUtil.preventXSS(detailAddress);   // 상세주소에도 스크립팅 방지!!
		name = securityUtil.preventXSS(name);  // <scpript> alert('')</script> 등의 태그 먹히는 것 방지를 위함
		String birthday = birthmonth + birthdate;  // birthmonth, birthdate를 합쳐서 birtday로 재구성 해야 함(DB에는 BIRTHYEAR, BIRTHDAY가 들어가있기 때문에)).
												  // DB에 BIRTHDAY는 varchar2 4바이트로 설정. 자바스크립트에서 month와 date는 각각 두자리수로 들어오도록 해놨기 때문에 +로 4바이트 만들어주기
		int agreeCode = 0;  // 필수 동의만 체크
		if(!location.isEmpty() && promotion.isEmpty()) {       		// 입력요소에 name을 포함시켰을 땐 null이 될 수 없다 -> null이 아니라 공백검사를 해야 함. 파라미터로 넘길 때 체크하면 on/ 체크안하면 ''공백
			agreeCode = 1;  // 필수 + 위치 동의
		} else if(location.isEmpty() && !promotion.isEmpty()) {		// null : 아예 안 넘어오는 것 <-> 빈문자열 : '' 공백이 넘어옴. 따라서 isEmpty()로 검사해주는 것이 맞다
			agreeCode = 2;	// 필수 + 프로모션 동의
		} else if(!location.isEmpty() && !promotion.isEmpty()) {	// !location.isEmpty() = 넘어온 게 있다(on이 넘어옴),  location.isEmpty() = 넘어온게 없다(공백이 넘어옴)
			agreeCode = 3;  // 필수 + 위치 + 프로모션 모두 동의
		}
		
		// DB로 보낼 UserDTO 만들기
		UserDTO user = UserDTO.builder()
			.id(id)
			.pw(pw)
			.name(name)
			.gender(gender)
			.email(email)
			.mobile(mobile)
			.birthyear(birthyear)
			.birthday(birthday)
			.postcode(postcode)
			.roadAddress(roadAddress)
			.jibunAddress(jibunAddress)
			.detailAddress(detailAddress)
			.extraAddress(extraAddress)
			.agreeCode(agreeCode)
			.build();
		
		// 회원가입처리
		int result = userMapper.insertUser(user);
		
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result > 0) {
				
				// selectUserByMap 추가 부분
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);  // map에 "id"라는 이름으로 id전달
				
				// 가입 성공 
				// 로그인 처리를 위해서 session에 로그인 된 사용자 정보를 올려둠
				request.getSession().setAttribute("loginUser", userMapper.selectUserByMap(map));   // session에 올려두기(setAttribute) <- DB로부터 사용자정보 가져오기(mapper에서 selectUserByMap 쿼리를 실행한 것)
															// selectUserByMap쿼리를 실행해서 나온 값을 map에 넣고 loginUser라는 변수로 session에 저장. 맞나?..
				
				
				// selectUserByMap 수정 부분
				//request.getSession().setAttribute("loginUser", userMapper.selectUserById(id));  // session에 올려두기(setAttribute), db로부터 사용자 정보 가져오기 -> 매퍼에서 selectUserById 쿼리를 실행한 것!
				// 서비스 목적을 가지고 DB 쿼리문의 id를 적는 것은 실무에서 좋지 않다! 어떤 목적으로 쓴다는 의미의 이름보다는 최대한 일반적인 이름을 쓰는 것이 좋다.(selcet~가 나음요)
				
				// 로그인 기록 남기기
				int updateResult = userMapper.updateAccessLog(id);
				if(updateResult == 0) {
					userMapper.insertAccessLog(id);   // 기존의 로그 기록이 없다(==0, update할 필요가 없다) -> insertAccessLog를 하겠다
				}
				
				out.println("<script>");
				out.println("alert('회원 가입 되었습니다')");
				out.println("location.href='" + request.getContextPath()  + "';");  // contextPath = /app13
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 가입에 실패했습니다')");
				out.println("histroy.go(-2);");  // 두 칸 돌려보내기! 2칸 전으로 가라! history.go(-1); == history.back();
				out.println("</script>");
			}
			out.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 로그인한다 = 로그인한 사용자의 정보를 session에 올려둔다는 뜻
	}
	
	
	@Transactional   // INSERT, UPDATE, DELETE 중 2개 이상이 호출되는 서비스에서 필요함.  deleteUser, insertRetireUser
	@Override
	public void retire(HttpServletRequest request, HttpServletResponse response) {
		// 삭제할 때 넘겨줘야하는 데이터 userNo, id, joinDate -> session에서 꺼내 쓰면 된다. 세션에 있는 로그인 정보에서 꺼내 쓰자 (request에 파라미터 하나도 안 실었기 때문에 request는 아님)
		// 탈퇴할 회원의 userNo, id, joinDate는 session의 loginUser에 저장되어 있다.
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		// 탈퇴할 회원 RetireUserDTO 생성
		RetireUserDTO retireUser = RetireUserDTO.builder()
				.userNo(loginUser.getUserNo())
				.id(loginUser.getId())
				.joinDate(loginUser.getJoinDate())
				.build();
		
		// 탈퇴처리
		int deleteResult = userMapper.deleteUser(loginUser.getUserNo());
		int insertResult = userMapper.insertRetireUser(retireUser);               // -> delete와 insert가 한번에 두번 호출! 따라서 transaction 필요
		
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(deleteResult > 0 && insertResult > 0) {
				
				// session 초기화(로그인 사용자 loginUser 삭제를 위해서)
				// 회원탈퇴시에도 session에는 로그인 정보는 그대로 남아있기 때문에 초기화를 해줘야 한다. 로그아웃도 당연히!
				session.invalidate();
				
				out.println("<script>");
				out.println("alert('회원 탈퇴 되었습니다')");
				out.println("location.href='" + request.getContextPath()  + "';");  // contextPath = /app13
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 탈퇴에 실패했습니다')");
				out.println("history.back();");  // 두 칸 돌려보내기! 2칸 전으로 가라! history.go(-1); == history.back();
				out.println("</script>");
			}
			out.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 로그인을 했다 -> 로그인한 정보가 세션에 저장되어 있다 ->  탈퇴를 할거다 -> 세션에서 로그인 정보를 초기화 해야한다.(지우지 않으면 세션은 서버메모리상의 정보이기 때문에 탈퇴해도 로그인이 되어있다)
		
	}
	
	@Override  
	public void login(HttpServletRequest request, HttpServletResponse response) {   // session을 꺼내쓰기 위해 request로 파라미터 받아오기
		// 자기 스스로 이동할 코드(response.sendRedirect(url); )가 있기 때문에 void 처리한 것
		
		// 받아오는 파라미터 3개  <- login.jsp에서 확인
		String url = request.getParameter("url");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// pw는 DB에 저장된 데이터와 동일한 형태로 가공(암호화)
		pw = securityUtil.sha256(pw);
		
		// DB로 보낼 UserDTO 생성 (Map으로 대체)
		/*
		UserDTO user = UserDTO.builder()
				.id(id)
				.pw(pw)
				.build();
		*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
		
		// id, pw가 일치하는 회원을 DB에서 조회하기
		UserDTO loginUser = userMapper.selectUserByMap(map);
		// UserDTO loginUser = userMapper.selectUserByIdPw(user);
		
		
		// id, pw가 일치하는 회원이 있다 : 로그인 기록 남기기 + session에 loginUser 저장
		if(loginUser != null) {
		
			// 로그인 기록 남기기 (access_log 테이블로 insert)
			int updateResult = userMapper.updateAccessLog(id);
			if(updateResult == 0) {   // 업데이트를 먼저 시켜놓고, 업뎃 실패하면 인서트~
				userMapper.insertAccessLog(id);  
			}
			
			// 로그인 처리를 위해서 session에 로그인 된 사용자 정보를 올려둠.
			request.getSession().setAttribute("loginUser", loginUser);
			
			// 이동 (로그인 페이지 이전 페이지로 되돌아가기)
			// 자기 스스로 이동할 코드(response.sendRedirect(url);)가 있기 때문에 void 처리한 것
			try {
				response.sendRedirect(url);  // redirect할 주소 url
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		// id, pw가 일치하는 회원이 없다 : 로그인 페이지로 돌려 보내기
		else {
			
			// 응답
			try {
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
					
				out.println("<script>");
				out.println("alert('일치하는 회원 정보가 없습니다')");
				out.println("location.href='" + request.getContextPath()  + "';");  // contextPath = /app13
				out.println("</script>");
				out.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
}
