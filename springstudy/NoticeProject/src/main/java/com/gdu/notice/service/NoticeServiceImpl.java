package com.gdu.notice.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.notice.domain.NoticeDTO;
import com.gdu.notice.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper mapper;
	
	@Override
	public void findAllNotices(Model model) {
		model.addAttribute("notices", mapper.selectAllNotices());
	}

	@Override
	public void findNoticeByNo(int noticeNo, Model model) {   // 공지글 넘버를 직접   // detail.jsp로 넘어갈때 모델에 포워딩할 공지 정보를 담으라고 model이 있음
		
		int result = mapper.updateHit(noticeNo);   // -> update를 하면 redirect를 해야함
		if(result > 0) {																  // mapper.selectNoticeByNo(noticeNo); ==> mapper로부터 받아온 결과
			model.addAttribute("notice", mapper.selectNoticeByNo(noticeNo));  // selectNoticeByNo()를 호출한 결과를 가지고 와야하므로 NoticeDTO를 noticeNo를 통해 "notice"라는 이름으로 가져온다
			//컨트롤러가 서비스임플로 noticeNo, model 넘겨줌
		} else {
			model.addAttribute("notice", null);
		}
			
		// http://localhost:9090/notice/ntc/detail?noticeNo=2 로 직접 입력해서 들어갔을 때 조회수가 올라가지 않음. 왜? 먼저 받아온 다음에 model에 싣고 조회수가 올라가버리니까
		// 따라서 업데이트 시켜놓고(조회수를 먼저 늘리고) 가지고 오게 하는 것임!!
		
	}

	@Override
	public void addNotice(HttpServletRequest request, HttpServletResponse response) {
		NoticeDTO notice = new NoticeDTO();
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		int result = mapper.insertNotice(notice);
		response.setContentType("text/html; charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			if(result > 0) {  // if(result == 1) {
				out.println("<script>");
				out.println("alert('새로운 공지사항이 등록되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "/ntc/list';");
				// out.println("location.href='/notice/ntc/list';");  당장은 되지만 미래를 위해서 사용하지 않는다.
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('공지사항이 등록되지 않았습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifyNotice(HttpServletRequest request, HttpServletResponse response) {
		// 리퀘, 리스판스로 받아온 이유 -> addNotice처럼 코드를 짜고 try-catch문을 만들기 위해. 입력창 뜨게 하려고!
		NoticeDTO notice = new NoticeDTO();
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		notice.setNoticeNo(Integer.parseInt(request.getParameter("noticeNo")));
		
		int result = mapper.updateNotice(notice);
		response.setContentType("text/html; charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			if(result > 0) {  // if(result == 1) {
				out.println("<script>");
				out.println("alert('공지사항이 수정되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "/ntc/list';");  // 수정후 목록보기로 가면 조회수 신경쓰지 않아도 됨. but 상세보기로 가면 조회수를 신경써야 함.. update하는 서비스 및 detail.jsp / update안하는 서비스 및 detail.jsp 두개로 쪼개야 함
				// out.println("location.href='/notice/ntc/list';");  당장은 되지만 미래를 위해서 사용하지 않는다.
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('공지사항이 수정되지 않았습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeNotice(HttpServletRequest request, HttpServletResponse response) {


		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		int result = mapper.deleteNotice(noticeNo);
		response.setContentType("text/html; charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			if(result > 0) {  // if(result == 1) {
				out.println("<script>");
				out.println("alert('공지사항이 삭제되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "/ntc/list';");  
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('공지사항이 삭제되지 않았습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
