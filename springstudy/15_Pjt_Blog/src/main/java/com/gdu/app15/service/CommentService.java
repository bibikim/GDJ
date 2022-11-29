package com.gdu.app15.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gdu.app15.domain.CommentDTO;

public interface CommentService {
	public Map<String, Object> getCommentCount(int blogNo);
	public Map<String, Object> addComment(CommentDTO comment); // commentDTO 전달~
	public Map<String, Object> getCommentList(HttpServletRequest request);  // blogNo, begin, end 다 필요함 -> commentDTO 불가! request 그대로 써서 꺼내쓰자
	public Map<String, Object> removeComment(int commentNo);
	public Map<String, Object> addReply(CommentDTO reply);
	
}
