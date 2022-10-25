package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDao;

public class MemberModifyService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String grade = request.getParameter("grade");
		String address = request.getParameter("address");
		
		
		// DB로 보낼 Member member 생성
		Member member = Member.builder()
							.id(id)
							.name(name)
							.gender(gender)
							.grade(grade)
							.address(address)
							.build();
		
		// 수정
		int result = 0;  // member 전달
		
		try {
			
			// 수정
			result = MemberDao.getInstance().updateMember(member); // 여기서 예외발생하면 catch로 넘어가는거! 
			
			// 성공 응답 데이터 삽입 : JSON
			response.setContentType("application/json; charset=UTF-8");
			
			/*
			 	수정 성공 응답 데이터
			 	{"isSuccess": true}   isSuccess 라는 프로퍼티를 주고 성공했을 때는 값을 true
			 	
			 	수정 실패 응답 데이터
			 	{"isSuccess": false}  isSuccess 라는 프로퍼티를 주고 실패했을 때는 값을 false
			*/
			JSONObject obj = new JSONObject();
			obj.put("isSuccess", result > 0);  //obj에 만들어줄 프로퍼티,key는 isSuccess.  isSuccess의 값은 result에 달려있다! result가 0보다 크면 true, 아니면 false
			
			
			// 응답   -> $.ajax의 success 프로퍼티로 전달되는 데이터(try블럭에서 응답 시)
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.close();
			
			// 익셉션은 발생되지 않았는데 수정이 안 된 경우 : 만족하는 아이디가 없을 때(WHERE절) 작업하지 않았기 때문에 0을 품고 돌아옴
			
		} catch(Exception e) {   // 성공과 실패는 익셉션의 여부에 따라 달라지기 때문에 예외 처리해준당
			
			// 예외 발생 시 응답(UNIQUE 제약조건 위반, NOT NULL 위반, 칼럼의 크기 위반 등)
			// 아이디 중복 체크할 때 예외발생여부로 중복 체크 가능!!
			
			// 응답 데이터 형식 : 일반 텍스트
			response.setContentType("text/plain; charset=UTF-8");
			
			// 응답   -> $.ajax의 error 프로퍼티로 전달되는 데이터(catch블럭에서 응답 시)
			PrintWriter out = response.getWriter();
			out.println("회원 정보가 수정되지 않았습니다. \n입력 정보를 확인하세요");
			out.close();
			
		}
		
		
		
	}

}
