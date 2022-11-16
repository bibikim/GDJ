package com.gdu.staff.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.staff.domain.StaffDTO;

@Mapper
public interface StaffMapper {
	public List<StaffDTO> selectStaffList();   // 반환하는거 : 사원 3명 목록 List
	public int insertStaff(StaffDTO staff);
	public StaffDTO selectBySno(String sno);
}
