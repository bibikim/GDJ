package service;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;

public interface NoticeService {
	public ActionForward findAllNotices(HttpServletRequest request);     // Notice가 들어있고 공지가 여러개 들어있을 List라 해석햐
	// Map<String, Object> map : map이 페이지당 몇 번begin부터 몇 번end 가져오라고! 어디서부터 어디까지 가져오라고 담겨있음
	
	//public int getAllNoticesCnt();   // 전체 가져오는 것이기 때문에 특별히 전달할 매개변수 X
}
