package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface BoardService {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
     // 컨트롤러가 요청/응답 처리 가능하지만 MVC에선 서비스를 불러서 서비스를 시킴
	
}
