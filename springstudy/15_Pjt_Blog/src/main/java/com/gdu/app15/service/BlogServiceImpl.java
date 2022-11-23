package com.gdu.app15.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app15.mapper.BlogMapper;
import com.gdu.app15.util.PageUtil;

@Service
public class BlogServiceImpl implements BlogService {

	// @컴포넌트~ 스프링이 뉴 페이지유틸 해서 갖고 있단 의미. 스프링이 페이지유틸 객체를 가지고 있따는 의미
	// 가져다 쓰는게 @오토와이어드  
	private BlogMapper blogMapper;
	private PageUtil pageUtil;
	
	@Autowired    // autowired가 적용되는 곳은 아래 매개변수. 매개변수로 주입된다	-> @Autowired를 하나만 쓸 때 이런식으로 작성한다.  
	public void set(BlogMapper blogMapper, PageUtil pageUtil) {
		this.blogMapper = blogMapper;
		this.pageUtil = pageUtil;
	}
	
	@Override
	public void getBlogList(Model model) {
		
		// Model에 저장된 request 꺼내기
		Map<String, Object> modelMap = model.asMap();   // model을 map으로 변신
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");  //
		
		// 페이징처리하면 꼭 들어와야하는게 page!!! 
		// 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 전체 블로그 개수
		int totalRecord = blogMapper.selectBlogListCount();   // 전체 블로그 갯수는 db에서 구해온다.
		
		// 페이징 처리에 필요한 변수 계산
		pageUtil.setPageUtil(page, totalRecord);
		
		// 조회 조건으로 사용할 Map 만들기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin()); // 페이지유틸에 계산 끝났으니까 begin값은 페유틸에 들어있음당
		map.put("end", pageUtil.getEnd());
		
		// 뷰로 전달할 데이터를 model에 저장하기
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("blogList", blogMapper.selectBlogListByMap(map));
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage()); // 순번만들때 필요한거 모델에 실어드릴게
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/blog/list"));    // 완성된 번호 텍스트로 받아서 넘기깅(getPaging()에 경로path만 넘겨주면 다 작성되게 String으로 넘겨줌)
																					// 뭘 누르든 어차피 blogList니까 /blog/list로 가게 한대.. 이해모태모태
	}
	
	
	
	
}
