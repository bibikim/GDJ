package com.gdu.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.notice.domain.NoticeDTO;

@Mapper
public interface NoticeMapper {
	public List<NoticeDTO> selectAllNotices();
	public int insertNotice(NoticeDTO notice);
	//    -resultType-               -parameterType-
	public NoticeDTO selectNoticeByNo(int noticeNo); 	// findNoticeByNo          // 공지 1가지가 넘어가니까 반환타입 NoticeDTO
	public int updateHit(int noticeNo);					// findNoticeByNo
	public int updateNotice(NoticeDTO notice);
	public int deleteNotice(int noticeNo);
	
	// 조회수 : mapper.xml이랑 mapper.interface -> findByNoticeNo에서 불러서 조회수 올리기
}
