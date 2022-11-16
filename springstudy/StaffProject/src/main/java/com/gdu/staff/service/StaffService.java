package com.gdu.staff.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gdu.staff.domain.StaffDTO;

public interface StaffService {

	// 구현할 메소드~
	
	public List<StaffDTO> getStaffList();    
	public ResponseEntity<String> addStaff(StaffDTO staff); // 응답 responseEntity 안에 실제로 응답할 데이터는 string
	public StaffDTO lookupStaff(String sno);
}
