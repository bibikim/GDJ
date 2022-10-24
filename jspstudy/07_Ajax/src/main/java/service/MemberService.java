package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	// af를 통해서 이동하는 방법 1. af는 포워드할거냐? 리다이렉트할거냐?를 정해줌.
	// 에작 통신은 페이지가 안 바뀌는 통신이기때문에 redirect도 forward도 안 함. 둘 다 안하는 통신 방식.
	// jsp 딱 하나 쓸 거. 한 jsp에서 데이터만 주고받는게 ajax. 따라서 반환타입이 void. af의 반환이 없단 얘기는 af로 이동하지 않는다는 이야기!
	// index.jsp에서 member.jsp 쓸 때 af 한번 쓰고 그거 말고는 안 씀!
	// ajax 통신은 페이지 이동이 없는~! 통신!!!!! 쭝요~~~~~
	// mvc는 페이지 이동이 있고, ajax는 페이지 이동이 없는 방식
	// 페이지 이동이 있으면 새로 넘어갈 때 화면이 깜빢! <- mvc
	// 화면이 가만히 있는 애들은 ajax
}
