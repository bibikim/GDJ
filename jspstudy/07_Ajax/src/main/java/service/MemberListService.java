package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import repository.MemberDao;

public class MemberListService implements MemberService {
		  // MemberListServiceImpl  원래는 이름을 이렇게 주어야 함???보충때 해본댕

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 다오에서 가지고온 목록을 포워딩할 수도 없고~ ajax니까!
		// 모든 서비스들이 하는 일은 json 데이터를 만들어서 response를 해줘야 함
		// 모든 서비스는 응답을 이용해서 응답을 직접 만들어 이동할 것, 응답할 데이터는 json
		// request는 있으면 파라미터로 쓸 거고 없으면 안 씀
		// 액션포워드로 어디로 어떻게 보낼 건지 만들었었지만, 이번에는 요청을 manage.jsp <script>에서 하고
		// 요청결과가 json데이터 자체로 manage.jsp로 다시 돌아옴.
		// 요청하는 jsp와 응답받는 jsp가 동일한게 ajax통신. 요청과 응답이 동일한 곳에서 진행됨. 
		// mvc 패턴 -> a --- b     /   ajax -> a --- a
		
		// 응답 데이터 형식(JSON)
		response.setContentType("application/json; charset=UTF-8");  // 컨트롤러에 기본적으로 해둔 text/html이 있지만 그걸로 응답하는게 아님!! 덮어쓰기
	
		// 응답 데이터 만들기
		/*  
		  {    -> 중괄호로 시작하는 부분은 JSONObject로 부르면 됨. 프로퍼티가 count/members
		  		"count" : 3,         count 프로퍼티에 3이라는 값 넣어주기
		  		"members" : [
		  			{ 
		  			 	"memberNo": 1,			 각 회원 한 명당 객체 하나씩
		  				"id": "user1",
		  				"name": "회원1",
		  				"gender": "F",
		  				"grade": "gold",
		  				"address": "jeju"
		  			},
		  			{ 
		  			 	"memberNo": 2,			 각 회원 한 명당 객체 하나씩
		  				"id": "user2",
		  				"name": "회원2",
		  				"gender": "M",
		  				"grade": "silver",
		  				"address": "seoul"
		  			},
		  			{ 
		  			 	"memberNo": 3,			 각 회원 한 명당 객체 하나씩
		  				"id": "user3",
		  				"name": "회원3",
		  				"gender": "F",
		  				"grade": "bronze",
		  				"address": "bucheon"
		  			}
		  		]
		  }			-▶ 이렇게 생긴 json 데이터를 만들어야 함! 라이브러리 도움을 받아 만든당
		*/
		JSONObject obj = new JSONObject();
		obj.put("count", MemberDao.getInstance().selectAllMembersCount());  // 저장할 때 이름을 count. 프로퍼티의 이름. 값은 다오가 db에 가서 .selectAllMembersCount()로 가져올거
		obj.put("members", MemberDao.getInstance().selectAllMembers());  // mnembers가 갖고있는데이터는 [] -> JsonArray  혹은 jsonLibrary를 사용하면 리스트를 jsonArray로 알아서 바꿔줌
		// 알아서 List를 JsonArray로 바꿔서 가져옴		
		
 		// System.out.println(obj.toString());   // 잘 가져왔는 지 확인
		
		// 응답
 		PrintWriter out = response.getWriter();
		out.println(obj.toString());  // JSON 데이터를 문자열로 응답
		out.close();
		
	}

}
