package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;

public class NoticeServiceImpl implements NoticeService {
// 페이징 처리의 기본 : 목록 가져오기를 하고, 전달되는 데이터가 파라미터로 page가 온다 -> 따라서 request
	
	@Override
	public ActionForward findAllNotices(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 파라미터 page 확인
		// 파라미터가 없으면 page=1로 처리해 줄 필요가 있다 --> 파라미터가 없으면 "page=1" 왱? 목록보기의 기본은 1페이지
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
				
		// 한 페이지에 표시할 목록의 개수 (목록개수 하나하나를 record)
		int recordPerPage = 10;   // 한 페이지당 게시글 10개
		
		// 특정 page에 표시할 목록의 시작번호와 끝번호
		//			   begin     end
		// page = 1 : 	 1       10
		// page = 2 :    11      20
		// page = 3 :    21      30
		// page = 4 :    31      35   -> 전체 게시글이 35개란 뜻 
		int begin = (page - 1) * recordPerPage + 1;
		//int end = (page)
		
		return null;
	}
}
