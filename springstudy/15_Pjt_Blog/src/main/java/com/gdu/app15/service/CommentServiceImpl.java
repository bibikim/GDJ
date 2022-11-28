package com.gdu.app15.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app15.domain.CommentDTO;
import com.gdu.app15.mapper.CommentMapper;
import com.gdu.app15.util.PageUtil;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private PageUtil pageUtil;
	
	
	@Override
	public Map<String, Object> getCommentCount(int blogNo) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("commentCount", commentMapper.selectCommentCount(blogNo));
		// map에 저장할 이름 commentCount  잭슨입장에선 맵을 반환하면 제이슨으로 변환시켜쥼
		// map에 저장할 이름 == resData로 넘어갈 데이터 이름
		
		return result;
	}
	
	@Override
	public Map<String, Object> addComment(CommentDTO comment) {
		
		// 반환할 Map
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isAdd", commentMapper.insertComment(comment) == 1);  // 삽입의 결과가 1이면 true, 아니면 false 반환
		return result;
	}
	
	@Override
	public Map<String, Object> getCommentList(HttpServletRequest request) {
		
		int blogNo = Integer.parseInt(request.getParameter("blogNo"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		// 전체 댓글 갯수
		int commentCount = commentMapper.selectCommentCount(blogNo);
		pageUtil.setPageUtil(page, commentCount);  // paging에 필요한 계산 완!
		
		// db로 넘기기 -> map에 담고
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogNo", blogNo);
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// 결과 반환하기 위헤 map을 Map result에 담고 페이징을 위한 pageUtil도 담아서 result 반환
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("commentList", commentMapper.selectCommentList(map));	// 목록
		result.put("pageUtil", pageUtil);
		
		return result;
	}
	
	
}
