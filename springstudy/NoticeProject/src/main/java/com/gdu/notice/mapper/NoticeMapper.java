package com.gdu.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.notice.domain.NoticeDTO;

@Mapper
public interface NoticeMapper {
	public List<NoticeDTO> selectAllNotices();
	public int insertNotice(NoticeDTO notice);
}
