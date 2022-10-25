package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import repository.MemberDao;

public class MemberRemoveService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		try {
			
			Optional<String> opt = Optional.ofNullable(request.getParameter("memberNo"));  // 파라미터memeberNo
			int memberNo = Integer.parseInt(opt.orElse("0"));
			
			// 삭제
			int result = MemberDao.getInstance().deleteMember(memberNo);
			
			// 응답 데이터 형식 : JSON
			response.setContentType("application/json");   // 어차피 데이터에 한글 없어서 charset=UTF-8 없어도 쌉가넝
	
			// 응답 데이터
			/*
				 성공 응답 데이터
				 {"isSuccess": true}   isSuccess 라는 프로퍼티를 주고 성공했을 때는 값을 true
				 	
				 실패 응답 데이터
				 {"isSuccess": false}  isSuccess 라는 프로퍼티를 주고 실패했을 때는 값을 false
			*/
			JSONObject obj = new JSONObject();
			obj.put("isSuccess", result > 0);
			
			// 응답
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.close();
	
		} catch(Exception e) {   // memberNo를 한글로 입력했을 때 예외 발생(numberformat) 포인트 : Integer.parseInt(opt.orElse("0")) 
		
			// 예외 응답 데이터 형식 : 일반 텍스트
			response.setContentType("text/plain; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("잘못된 회원번호가 전달되었습니다.");
			out.close();

		}
	}
}
