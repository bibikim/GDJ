package com.gdu.rest.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.rest.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public int insertMember(MemberDTO member);  // 전달은 MemberDTO 타입으로~
	public int selectMemberCount();
	public List<MemberDTO> selectMemberListByMap(Map<String, Object> map);
	public MemberDTO selectMemberByNo(int memberNo);

}
