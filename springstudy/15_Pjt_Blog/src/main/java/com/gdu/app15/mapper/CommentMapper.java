package com.gdu.app15.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app15.domain.CommentDTO;

@Mapper
public interface CommentMapper {

	public int selectCommentCount(int blogNo);
	public int insertComment(CommentDTO comment);  // content와 blogNo 받아와야 하니까 코멘트디티오로 받아오기
	public List<CommentDTO> selectCommentList(Map<String, Object> map); //blogNo begin end를 넘겨야하기 때문에 Map으로 전달
	
}
