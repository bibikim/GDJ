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
	public int updateMember(Map<String, Object> map); // 받아오는거 서비스와 맞춰야 함
	public int deleteMemberList(List<String> memberNo); // 이쪽으로 전달할 값은 List, 안에 들어있는 값의 타입은 String으로 보낸당

}
