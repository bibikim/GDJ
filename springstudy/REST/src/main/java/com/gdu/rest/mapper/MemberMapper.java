package com.gdu.rest.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.rest.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public int insertMember(MemberDTO member);  // 전달은 MemberDTO 타입으로~
}
