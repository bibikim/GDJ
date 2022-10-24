package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDao;

public class MemberDetailService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("memberNo"));
		int memberNo = Integer.parseInt(opt.orElse("0"));
		
		// 응답 데이터 형식을 JSON으로~
		response.setContentType("application/json charset=UTF-8");
		
		// 응답 데이터 만들기
		/*
		 	조회된 회원이 있는 경우
		 	{
		 		"exists": true,
		 		"member": {
		 				"memberNo": 1,			 ◀- 조회된 회원이 있으면 이걸 만들고
		  				"id": "user1",
		  				"name": "회원1",
		  				"gender": "F",
		  				"grade": "gold",
		  				"address": "jeju"
		 		}
		 	}
		 	
		 	조회된 회원이 없는 경우
		 	{ 
		 		"exists": false
		 	}
		*/
		Member member = MemberDao.getInstance().selectMemberByNo(memberNo);
		boolean exists = member != null;   // 멤버가 null이 아니면 존재
				
		JSONObject obj = new JSONObject();
		obj.put("exists", exists);  // 성공, 실패 가리지 않고 넣어줄 값이 exists
		if(exists) {
			obj.put("member", new JSONObject(member));  // 존재하면 가져온 member를 넣어주겠다 (프로퍼티, 값) -> 값에 member만 넣으면 제대로 못 가져옴.. jsonObject 해주기
		}
		
		// 응답
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
		out.close();
		
	}

}
