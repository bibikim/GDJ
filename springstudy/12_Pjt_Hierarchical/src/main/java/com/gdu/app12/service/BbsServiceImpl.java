package com.gdu.app12.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
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
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));		 // page 파라미터가 null이면 대신 1을 써라
		
		// 전체 개시글 개수
		int totalRecord = bbsMapper.selectAllBbsCount();
		
		// 페이징에 필요한 모든 계산 완료
		pageUtil.setPageUtil(page, totalRecord);
		
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
	
	}

	@Override
	public int addBbs(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addReply(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBbs(int bbsNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
