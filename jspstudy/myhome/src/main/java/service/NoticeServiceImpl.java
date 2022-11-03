package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.Notice;
import repository.NoticeDao;

public class NoticeServiceImpl implements NoticeService {
// 페이징 처리의 기본 : 목록 가져오기를 하고, 전달되는 데이터가 파라미터로 page가 온다 -> 따라서 request
	
	@Override
	public ActionForward findAllNotices(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 파라미터 page 확인
		// 파라미터가 없으면 page=1로 처리해 줄 필요가 있다 --> 파라미터가 없으면 "page=1" 왱? 목록보기의 기본은 1페이지
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
				
		// 전체 목록의 갯수
		NoticeDao dao = NoticeDao.getInstance();
	    int totalRecordCnt = dao.selectAllNoticesCnt();
		
		// 한 페이지에 표시할 목록의 개수 (목록개수 하나하나를 record)
		int recordPerPage = 10;   // 한 페이지당 게시글 10개
		
		// 특정 page에 표시할 목록의 시작번호와 끝번호
		//			   begin     end  -> rownum(정렬 후 있는애들로 번호붙이기)으로 역순으로.   noticeNo으로 하면 게시글 삭제했을 때 문제생김
		// page = 1 : 	 1       10 
		// page = 2 :    11      20
		// page = 3 :    21      30
		// page = 4 :    31      35   -> 전체 게시글이 35개란 뜻.  (전체목록이 35인 경우) -> end랑 전체갯수 비교해서 작은거 선택하기.
		// 여기서 begin과 end는 ROWNUM을 의미한다.
		int begin = (page - 1) * recordPerPage + 1;
		int end = begin + recordPerPage - 1;
		if(end > totalRecordCnt) {
			end = totalRecordCnt;   // end값 고정
		}
		
		// begin + end를 Map으로 만들어서 NoticeDao에게 전달해야 함
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", begin);
		map.put("end", end);
		
		// begin에서 end 사이 목록 가져오기
		List<Notice> notices = dao.selectAllNotices(map);   // map 전달. 가져오기!
		
		// 목록을 forward 하기 위해서 request에 저장
		request.setAttribute("notices", notices);
		
		// block 개념 이해하기   < 1 2 3 4 5 >  < 2 3 4 5 6 >   <- 5개 이거야 블럭 개념이..
	    // 1block 당 3page가 표시되는 상황   -> 변수 선언해서 정하고
	    // 전체 7페이지가 있는 상황  -> 계산
	    //           beginPage endPage   page-> 파라미터로 받아서 알고 있다
	    // 1 block :    1         3      1   2   3      
	    // 2 block :    4         6      4   5   6
		// 3 block :    7         7      7
		// 비긴페이지를 구한다 - 비긴페이지를 이용해서 엔드페이지를 구한다 - 전체페이지보다
		// 마지막 블럭 : 작은걸 엔드페이지로 한다.
		
		// 각 block의 beginPage와 endPage를 알아내기 위한 과정
		// 1) 전체 page의 개수를 구한다. (totalPageCnt)
		// 2) 한 block 당 표시할 page의 개수를 임의로 정한다.
		// 3) 현재 page와 전체 page 개수와 1 block 당 표시할 page 개수로 beginPage를 구한다.  (pagePerBlock)
		// 4) beginPage를 이용해서 endPage를 구한다.
		// 5) endPage와 전체 page 개수를 비교해서 작은 값을 endPage로 확정한다.
		
		int totalPageCnt = totalRecordCnt / recordPerPage; 
		if(totalRecordCnt % recordPerPage > 0) {  
			totalPageCnt++;    // 나눠봐서 나머지가 1~9 사이(0보다 큰) 애들은 전체페이지개수 +1을 해준다.
		}
		
		int pagePerBlock = 3;  // 2)
		
		int beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;  // 3)  n번째 블락의 시작페이지는 m이다
		int endPage = beginPage + pagePerBlock - 1;  // 4)
		if(endPage > totalPageCnt) {
			endPage = totalPageCnt;  // 5)
		}
		
		// 페이징 처리에 필요한 정보를 list.jsp로 전달
		request.setAttribute("page", page);
		request.setAttribute("beginPage", beginPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPageCnt", totalPageCnt);
		request.setAttribute("pagePerBlock", pagePerBlock);
	
		
		
		// board 폴더의 list.jsp로 forward 하자~~~~~~~
		return new ActionForward("/notice/list.jsp", false);
	}
}
