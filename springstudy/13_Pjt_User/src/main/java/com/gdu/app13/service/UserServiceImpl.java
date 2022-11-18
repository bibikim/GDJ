package com.gdu.app13.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.Date;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app13.domain.RetireUserDTO;
import com.gdu.app13.domain.SleepUserDTO;
import com.gdu.app13.domain.UserDTO;
import com.gdu.app13.mapper.UserMapper;
import com.gdu.app13.util.SecurityUtil;

import lombok.AllArgsConstructor;
import netscape.javascript.JSObject;

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
				out.println("alert('회원 탈퇴 되었습니다');");
				out.println("location.href='" + request.getContextPath()  + "';");  // contextPath = /app13
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 탈퇴에 실패했습니다');");
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
			
			// 로그인 성공을 했는데 로그인 유지를 체크한 사람이다! -> 로그인 유지는 로그인이 성공한 경우 한정이기 때문에 이 위치가 맞다. 
			// 로그인 유지 처리는 keepLogin 메소드가 따로 처리함
			keepLogin(request, response); // keepLogin에 request, response 넘겨줘서 login()이 keepLogin() 너가 해라~~ 난 로그인만 할거양!! 하고 맡김!!!
			
		
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
	
	
	@Override
	public void keepLogin(HttpServletRequest request, HttpServletResponse response) {  // 로그인할 때 수행
		/*
		 	로그인 유지를 체크한 경우
		 	
		 	1. session_id를 쿠키에 저장해 둔다.
		 		(쿠키명 : keepLogin)
		 	2. session_id를 DB에 저장해 둔다.
		 		(SESSION_ID라는 칼럼에 session_id를 저장하고, SESSION_LIMIT_DATE(로그인 유지 유효기간) 칼럼에 현 시점으로부터 15일 후 날짜를 저장한다.)
			
			두 개의 데이터를 꺼내서 비교하는 것
		*/
		
		/*
			로그인 유지를 체크하지 않은 경우	
			
			1. 쿠키 또는 DB에 저장된 정보를 삭제한다.
			   편의상 쿠키명 keepLogin을 제거하는 것으로 처리한다.
				
				-> 로그인 유지가 두 개의 데이터를 꺼내서 비교하는 방식이기 때문에 하나만 삭제해도 가능
		*/
		
		
		// 파라미터
		String id = request.getParameter("id");
		String keepLogin = request.getParameter("keepLogin");
	
		// 체크박스 체크 하면 name과 value 모두 같이 전달
		// 로그인 유지를 체크한 경우
		if(keepLogin != null) {
			
			// session_id
			String sessionId = request.getSession().getId();
			
			// session_id를 쿠키에 저장하기 -> 서버가 쿠키를 굽는당
			Cookie cookie = new Cookie("keepLogin", sessionId); // session의 id값
			cookie.setMaxAge(60 * 60 * 24 * 15);  // 쿠키에 15일 저장
			cookie.setPath(request.getContextPath()); // 쿠키에 contextPath 저장하기  (쿠키 저장/삭제하는 장소 : contextPath)
			/*
			 	쿠키를 컨텍스트패스에 저장했기 때문에 (app13으로 시작하는 모든 경로에) 컨텍스트패스값을 사용하는 모든 경로에서 
			 	keepLogin이라는 값을 볼 수 있다!!
			  
			*/
			
			
			
			// 서버가 만든 쿠키를 클라이언트로 보낸다!
			response.addCookie(cookie);
			//-----------------------------------------> session_id 쿠키에 저장 완.
			
			// session_id를 DB에 저장하기
			// 기존회원의 정보중에서 DB의 SESSION_ID 칼럼에 update로 쿼리짜준다!
			UserDTO user = UserDTO.builder()
					.id(id)
					.sessionId(sessionId)
					.sessionLimitDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 *15))   // long타입의 Date 타입 = 타임스탬프값 전달해줌(1/1000초) >> System.currentTimeMillis() 현시점의 timeStamp값
												// 1/1000초니까 *1000해서 1초 만들어주고 15일의 시간을 만들기 위한 계산 해주면 됨
					.build();
			
			userMapper.updateSessionInfo(user);
			
		}
		
		// 체크 안하면 둘다 아예 전달 x -> null 값
		// 로그인 유지를 체크하지 않은 경우
		else {
			
			// keepLogin 쿠키 제거하기
			Cookie cookie = new Cookie("keepLogin", ""); // 빈문자열
			cookie.setMaxAge(0);  // 쿠키 유지 시간이 0이면 삭제를 의미함
			cookie.setPath(request.getContextPath()); // 쿠키에 contextPath 저장하기
			
			// 기존의 쿠키가 덮어쓰기 되면서 바뀌자마자 유지시간 없기 때문에 삭제되는 것!
			response.addCookie(cookie);
		}
		
	}
	
	@Override    // 로그아웃은 세션초기화만 하면 된다. 다른거 필요 없ㅋ엉ㅋ
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		
		// 로그아웃 처리
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {   // session에 loginUser라는 값이 있으면!
			session.invalidate();  // 세션 초기화할게!!
		}
		
		// 로그인 유지 풀기  (풀기 전에 keepLogin이 있는지 확인해보고 풀거야)   
		// 쿠키는 클라이언트에, 지금 이곳은 서버! -> 쿠키를 다(배열의 형태로) 가지고 와서 확인해야 함
		/*
		Cookie[] cookieList = request.getCookies();  // 클라이언트 정보가 서버로 넘어오는 것 = request
		if(cookieList == null) {
			return;     
		}
		Cookie cookie = null;
		for(int i=0; i < cookieList.length; i++) {
			if(cookieList[i].getName().equals("keepLogin")) {  // 쿠키리스트에 keepLogin이라는 Name을 가진 쿠키가 있으면
				cookie = new Cookie("keepLogin", "");
				cookie.setMaxAge(0);  // 쿠키 유지 시간이 0이면 삭제를 의미함  -> 쿠키 삭제할게!!
				cookie.setPath(request.getContextPath()); // 쿠키에 contextPath 저장하기 (쿠키 저장/삭제하는 장소 : contextPath)
				break;
			}
		}
		response.addCookie(cookie);
		*/
		// 단순하게 로그인 유지 풀 때의 코드 ▼
		Cookie cookie = new Cookie("keepLogin", ""); // 빈문자열
		cookie.setMaxAge(0);  // 쿠키 유지 시간이 0이면 삭제를 의미함
		response.addCookie(cookie);
		
	}
	
	// 쿠키 저장하는 장소 선택할 수 있음. 13장에선 contextPath 경로에 저장해서 가져올 수 있도록 구현할 것임다
	
	//keepLogin의 값을 DB에 저장까지 해야 완벽하게 로그인유지 구현 가능
	
	
	@Override
	public UserDTO getUserBySessionId(Map<String, Object> map) {
		return userMapper.selectUserByMap(map);   // 인터셉터에서 map을 받아옴
		// 대개 서비스를 만들면 컨트롤러로 가서 서비스르 부르는데 이 서비스의 경우 인터셉터에서 부른다
	}
	
	@Override
	public Map<String, Object> confirmPassword(HttpServletRequest request) {
		
		// 파라미터 pw + SHA-256 처리
		String pw = securityUtil.sha256(request.getParameter("pw"));
		
		// pw 확인하려면 id도 같이 넘겨줘야 됨 -> id는 세션에 있다. 이미 로그인 완료한 후에 마이페이지에서 비밀번호 확인 한번 들어가는 거니까!
		HttpSession session =request.getSession();
	    String id=((UserDTO)session.getAttribute("loginUser")).getId();  // 로그인 한 사람의 id 꺼냄!
		
	    // 조회 조건으로 사용할 Map
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("id", id);
	    map.put("pw", pw);
	    
	    // id, pw가 일치하는 회원 조회
	    UserDTO user = userMapper.selectUserByMap(map);  // 조회된 결과는 user가 됨
	    
	    // 결과 반환
	    Map<String, Object> result = new HashMap<String, Object>();
	    result.put("isUser", user != null);  // 조회된 결과(user)가 null이 아니면 isUser(사이트 회원이란 의미)
 
		return result;
	}
	
	@Override
	public void modifyPassword(HttpServletRequest request, HttpServletResponse response) {
		
		// 현재 로그인 된 사용자
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser"); 
		
		// 파라미터
		String pw = securityUtil.sha256(request.getParameter("pw"));
		
		// 동일한 비밀번호로 변경 금지
		if(pw.equals(loginUser.getPw())) {   // request에서 꺼내온 loginUser의 Pw와 새로입력한 pw가 동일하면 변경 금지
			
			// 응답
			try {
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
					
				out.println("<script>");
				out.println("alert('현재 비밀번호와 동일한 비밀번호로 변경할 수 없습니다')");
				out.println("history.back();"); 
				out.println("</script>");
				out.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		// 회원번호(세션에 들어있다)
		int userNo = loginUser.getUserNo();
		
		// DB로 보낼 UserDTO
		UserDTO user = UserDTO.builder()
				.userNo(userNo)
				.pw(pw)
				.build();
		
		// 비밀번호 수정
		int result = userMapper.updateUserPassword(user);
		
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result > 0) {
				
				// session에 저장된 loginUser 업데이트
				loginUser.setPw(pw);
				
				out.println("<script>");
				out.println("alert('비밀번호가 수정되었습니다.');");
				out.println("location.href='" + request.getContextPath()  + "';");  // contextPath = /app13
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('비밀번호가 수정되지 않았습니다.');");
				out.println("history.back();"); 
				out.println("</script>");
			}
			out.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// ▲위까지 db에는 바뀐 비밀번호가 갱신됐지만 세션에는 이전 pw데이터가 그대로 있음!
		
	}
	
	@Transactional // 휴면처리 : insert와 delete 동시 진행되기 때문에 트랜잭션 처리 필요
	@Override
	public void sleepUserHandle() {
		int insertCount = userMapper.insertSleepUser();    // inserCount로 반환되는게 3인거.
		if(insertCount > 0) {
			userMapper.deleteUserForSleep();
		}
	
		
	}
	
	
	@Override
	public SleepUserDTO getSleepUserById(String id) {

		return userMapper.selectSleepUserId(id);
	}
	
	
	@Transactional  // insert, delete
	@Override
	public void restoreUser(HttpServletRequest request, HttpServletResponse response) {
							// 이쪽으로 전달된 파라미터 pw 하나.
		
		// 계정 복원을 원하는 사용자와 아이디
		HttpSession session = request.getSession();
		SleepUserDTO sleepUser = (SleepUserDTO)session.getAttribute("sleepUser");
		String id = sleepUser.getId();
		

		// 계정 복구 진행
		int insertCount = userMapper.insertRestoreUser(id);
		int deleteCount = 0;
		if(insertCount > 0) {
			deleteCount = userMapper.deleteSleepUser(id);
		}
			
			// 응답
			try {
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				if(insertCount > 0 && deleteCount > 0) {
					out.println("<script>");
					out.println("alert('휴면계정이 복구되었습니다. 휴면 계정 활성화를 위해 곧바로 로그인을 해 주세요!');");
										// 로그인 해야만 휴면계정으로 다시 안 빠지게끔 설정해놓음
					out.println("location.href='" + request.getContextPath()  + "/user/login/form';");  // contextPath = /app13
					out.println("</script>");
				} else {
					
					out.println("<script>");
					out.println("alert('휴면계정 복구에 실패했습니다.');");
					out.println("history.back();"); 
					out.println("</script>");
					
				}
				
				out.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		
			
	}
	
	
	@Override
	public String getNaverLoginApiURL(HttpServletRequest request) {
		
		String apiURL = null;
		
		try {
			
		    String clientId = "DJxqiwv2wqFur2KBtCxx";//애플리케이션 클라이언트 아이디값";
		    String redirectURI = URLEncoder.encode("http://localhost:9090" + request.getContextPath() + "/user/naver/login", "UTF-8"); // 네이버 로그인 Callback URL에 작성한 주소 입력
		    										// ㄴ정보를 넘겨줄 곳
		    SecureRandom random = new SecureRandom();
		    String state = new BigInteger(130, random).toString();
		    apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		    apiURL += "&client_id=" + clientId;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&state=" + state;
		    HttpSession session = request.getSession();
		    session.setAttribute("state", state);
		    
		} catch (Exception e) {
			
		}
		    
		return apiURL;
	}
	
	@Override
	public UserDTO getNaverLoginTokenNProfile(HttpServletRequest request) {
		
		 	// access_token 받기
		 	String clientId = "DJxqiwv2wqFur2KBtCxx";//애플리케이션 클라이언트 아이디값";
		    String clientSecret = "nq6dLkY86Z";//애플리케이션 클라이언트 시크릿값";
		    String code = request.getParameter("code");
		    String state = request.getParameter("state");
		    String redirectURI = null;    
		    try {                                        
		    	redirectURI = URLEncoder.encode("http://localhost:9090" + request.getContextPath(), "UTF-8");  // 6가지 정보 받아서 또 어디로 갈거에여?(=> callback)
		    } catch(UnsupportedEncodingException e) {
		    	e.printStackTrace();
		    }
		    
		    // 네이버 아이디로 로그인 요청 할게요~ 로그인을 위해 필요한 정보를 저 주소로 보내주세요~ 
		    //     -> 그쪽으로 데이터를 받은게 request, 거기에 들어있는게  code, state. 이 정보랑 개인정보 풀셋을 정보를 받아오기 위한 token 주소로 다시 요청, 
		    // 네이버가 최종적으로 토큰 두개와 토큰타입, expires_in 값을 줌 <<= 네이버 로그인 API를 통해 접근토큰을 전달받음
		    
		    // 토큰 받고 프로필 받을거야!
		    
		    // : 네이버측에서 이 사람한테 네이버 프로필을 제공해줘도 되는지 확인하는 과정이었던 것
		    
	    	StringBuffer res = new StringBuffer();  // StringBuffer는 StringBuilder와 동일한 역할 수행
		    
	    	String access_token = "";
	    	//String refresh_token = "";
	    	
		    try {
		    	
			    String apiURL;
			    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
			    apiURL += "client_id=" + clientId;
			    apiURL += "&client_secret=" + clientSecret;
			    apiURL += "&redirect_uri=" + redirectURI;
			    apiURL += "&code=" + code;
			    apiURL += "&state=" + state;
			    System.out.println("apiURL="+apiURL);
		    	
		    	URL url = new URL(apiURL);
		    	HttpURLConnection con = (HttpURLConnection)url.openConnection();
		    	con.setRequestMethod("GET");
		    	int responseCode = con.getResponseCode();
		    	BufferedReader br;

		    	if(responseCode==200) { // 정상 호출
		    		br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
		    	} else {  // 에러 발생
		    		br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		    	}
		    	
		    	String inputLine;  // 한줄씩 읽어들이기

		    	while ((inputLine = br.readLine()) != null) {
		    		res.append(inputLine);
		    	}
		    	
		    	br.close();
		    	con.disconnect();
		 
		    	//System.out.println(res.toString());  -> json객체로 넘어오는 토큰값 확인을 위한 코드
		    	
			    /*
			    
			     res.toString()
			    
			     {
				     "access_token":"AAAAONdwFCQM5uZeoFTHwE3d_DqC9YNyV9CWgJoFMA46pF1YkeqQzugcIBhIq2TQy4mg3VZmZ-oQutlQQwpCCAwLsKA",
				      "refresh_token":"Amdhhcyy62vI1ipy7NI1Hy79QQLOwOMiibfisbMXKCFHGU0dhiit9X0CKXQtZWExmtfV9caj844is5bQf6YwVlYhZkoaS6K5DpOS8V3jrF8CGislKFgkU86BQhcna4ipFkjXQkE",
				      "token_type":"bearer",
				      "expires_in":"3600"
			      }
		
			    */
		    } catch(Exception e) {
		    	e.printStackTrace();
		    }

		    	
		    	JSONObject obj = new JSONObject(res.toString());
		    	access_token = obj.getString("access_token");
		    	//refresh_token = obj.getString("refresh_token");  사용안해서 주석처리
		    	
		    	
		    	// access_token을 이용해서 profile 받기
		    	String header = "Bearer " + access_token; // Bearer 다음에 공백 추가

		    	StringBuffer sb = new StringBuffer();
		    	
		    try {

		        String apiURL = "https://openapi.naver.com/v1/nid/me";   //
		        
		    	URL url = new URL(apiURL);
		    	HttpURLConnection con = (HttpURLConnection)url.openConnection();  
		    	con.setRequestMethod("GET");
		    	con.setRequestProperty("Authorization", header);   // header값 넣기 -> access_token을 주소에 넘겨주는건 위험하니까 header에 담아서 보내준다

		    	int responseCode = con.getResponseCode();
		    	BufferedReader br;

		    	if(responseCode==200) { // 정상 호출
		    		br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
		    	} else {  // 에러 발생
		    		br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		    	}
		    	
		    	String inputLine;  // 한줄씩 읽어들이기

		    	while ((inputLine = br.readLine()) != null) {
		    		sb.append(inputLine);
		    	}
		    	
		    	br.close();
		    	con.disconnect();
		    	
		    	System.out.println(sb.toString());  // 사용자의 프로필 정보
		    	
		    	/*
		    	 
		    	 // 네이버 아이디 로그인을 시도한 사용자의 프로필 정보가 sb에 담겨있다.
		    	 
		    	 {
				    	 "resultcode":"00",
				    	  "message":"success",
				    	  "response":{
				    	  		"id":"sdfdgafhrjthgkjhgddfaefad",
				    	  		"gender":"F",
				    	  		"email":"dfadsfararg",
				    	  		"mobile":"010-1111-1111",
				    	  		"mobile_e164":"+8210111111",
				    	  		"name":"\uae40\ud55c\ube44",
				    	  		"birthday":"04-08",
				    	  		"birthyear":"1992"
			    	  		}
		    	  	}

		    	  
		    	  => 이제 네이버api의 할 일은 끝남. 이 정보들을 DB에 넣는 작업만 하면 됨. (UserDTO로 만들어서 반환)
		    	*/
		    	
		    	
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		
		    // 받아온 profile을 UserDTO로 만들어서 반환!
		    
		   
		    
		return null;
	}
	
}
