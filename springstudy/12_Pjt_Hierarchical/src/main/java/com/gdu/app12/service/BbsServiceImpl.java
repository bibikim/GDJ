package com.gdu.app12.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.gdu.app12.domain.BbsDTO;
import com.gdu.app12.mapper.BbsMapper;
import com.gdu.app12.util.PageUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor  // 생성자를 이용한 자동주입!
@Service   // bean으로 등록하기 위해 @service 애너테이션
public class BbsServiceImpl implements BbsService {
	
	//@Autowired  // service는 Mapper(DAO역할)를 사용
	// 필드가 여러 개(여긴 2개)이면, Autowired를 여러개 쓰는 것 보다는, 생성자를 이용해서 자동주입 하는 것이 좋다.
	private BbsMapper bbsMapper;
	private PageUtil pageUtil;

	@Override
	public void findAllBbsList(HttpServletRequest request, Model model) {
		
		// 파라미터 page, 전달되지 않으면 page=1로 처리
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("page"));  
		int page = Integer.parseInt(opt1.orElse("1"));		 // page 파라미터가 null이면 대신 1을 써라
		
		// request에 recordPerPage가 파라미터로 포함 되어 있음
		// 파라미터 recordPerPage, 전달되지 않으면 recordPerPage=10으로 처리
		Optional<String> opt2 = Optional.ofNullable(request.getParameter("recordPerPage"));  
		int recordPerPage = Integer.parseInt(opt2.orElse("10"));
		
		
		// 전체 개시글 개수
		int totalRecord = bbsMapper.selectAllBbsCount();
		
		// 페이징에 필요한 모든 계산 완료
		pageUtil.setPageUtil(page, recordPerPage, totalRecord);
		
		// DB로 보낼 Map(begin + end)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// DB에서 목록 가져오기
		List<BbsDTO> bbsList = bbsMapper.selectAllBbsList(map);

		// 뷰로 보낼 데이터
		model.addAttribute("bbsList", bbsList);  // "bbsList"라는 이름으로 bbsList를 model에 실어준다~
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/bbs/list"));
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		model.addAttribute("recordPerPage", recordPerPage); 
	}

	@Override
	public int addBbs(HttpServletRequest request) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String ip = request.getRemoteAddr();
		
		BbsDTO bbs = new BbsDTO();
		bbs.setWriter(writer);
		bbs.setTitle(title);
		bbs.setIp(ip);
		
		return bbsMapper.insertBbs(bbs);
	}

	/*
	 	@Transactional
	 	
	 	안녕. 난 트랜잭션을 처리하는 애너테이션이야.
	 	INSERT/UPDATE/DELETE 중 2개 이상이 호출되는 서비스에 추가하면 돼
 
	*/
	
	@Transactional    // 8장의 aop를 이용한 트랜잭션 처리 or 여기서의 @Transactional을 활용한 트랜잭션 처리 둘 중 하나 이용!
	@Override
	public int addReply(HttpServletRequest request) {   // addReply()메소드 안에서 update와 insert가 동시 진행 -> 둘중 하나만 실패or성공하면 rollback처리 해버리는 트랜잭션을 넣어주기
		
		// 파라미터로 받아오는 것 : writer, title, ip
		// 사용자 입력 정보 : 작성자, 제목
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		
		// ip
		String ip = request.getRemoteAddr();
			
		// 원글의 정보가 있어야 답글의 뎁스,그릅넘버,그룹오더도 알 수 있음
		// 원글의 정보에서 필요한건 위 세가지의 값. ->> 가져오는 2가지 방법
		// 1. DB가서 가지고 오기 -> 원글의 no로 DB를 갔다 온다.
		// 2. 파라미터로 넘겨서 받아오기 -> 원글의 정보에서 각각 빼서(bbs.depth ...) 답글 작성할 때 넘겨주면 굳이 DB를 가지 않아도 됨. 원글의 정보가 view에 있음.
		
		// 원글의 DEPTH, GROUP_NO, GROUP_ORDER 파라미터로 받아오기
		int depth = Integer.parseInt(request.getParameter("depth"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		int groupOrder = Integer.parseInt(request.getParameter("groupOrder"));
		
		// 원글DTO 만들기 (updatePreviousReply를 위함)
		BbsDTO bbs = new BbsDTO();
		bbs.setDepth(depth);
		bbs.setGroupNo(groupNo);
		bbs.setGroupOrder(groupOrder);
		
		// 그다음 updatePreviousReply 쿼리 실행~
		bbsMapper.updatePreviousReply(bbs);
		
		// 답글DTO 만들기
		BbsDTO reply = new BbsDTO();
		reply.setWriter(writer);
		reply.setTitle(title);
		reply.setIp(ip);
		reply.setDepth(depth + 1);     		  // 답글depth = 원글depth + 1
		reply.setGroupNo(groupNo);			  // 답글groupNo = 원글groupNo
		reply.setGroupOrder(groupOrder + 1);  // 답글groupOrder = 원글groupOrder + 1
		
		// 답글DTO 만들었으니 insertReply 쿼리 실행
		// 답글의 삽입 결과(insertReply) 반환!
		return bbsMapper.insertReply(reply);
		
		// 트랜잭션 대상의 메소드에 @Transactional 애너테이션
	}

	
	
	
	@Override
	public int removeBbs(int bbsNo) {
		// TODO Auto-generated method stub
		return bbsMapper.delete(bbsNo);
	}

}
