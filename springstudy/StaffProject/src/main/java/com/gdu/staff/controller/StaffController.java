package com.gdu.staff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.service.StaffService;

@Controller   // 컨트롤러는 simple한게 작업하기 좋다
public class StaffController {

	@Autowired  // 나는 서비스가 필요해용
	private StaffService staffService;
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@ResponseBody  // 이 컨트롤러가 반환하는 것은 json 데이터당~ ajax
	@GetMapping(value="/list.json", produces="application/json; charset=UTF-8")
	public List<StaffDTO> allStaff() {   		// List를 반환해야 잭슨이 그걸 배열로 바꿔준다
		return staffService.getStaffList();
	}

	// 파라미터 받아오는 3가지 방법 : request, @RequestParam, StaffDTO 객체
	@ResponseBody
	@PostMapping(value="/add", produces="text/plain; charset=UTF-8")  // ajax 응답 데이터 타입 : text. 따라서 text/plain!
	public ResponseEntity<String> add(HttpServletRequest request) {   // ajax 전용 객체 ResponseEntity<스트링>으로 반환   // () 안으로 son, name, dept 세가지 들어온다.
		String sno = request.getParameter("sno");
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");
		
		StaffDTO staff = new StaffDTO(sno, name, dept, 0);   // salary는 서비스에서 넣기 때문에 여기선 x
		
		return staffService.addStaff(staff);

	}
	/*
	public ResponseEntity<String> add(@RequestParam(value="sno") String sno
									, @RequestParam(value="name", required=false) String name    // name 필수 아니므로(not null) required=false 꼭! 넣어줘야 한다.
									, @RequestParam(value="dept") String dept) {  
		
		StaffDTO staff = new StaffDTO(sno, name, dept, 0);
		return staffService.addStaff(staff);
		
	}
	public ResponseEntity<String> add(StaffDTO staff) {  
		return staffService.addStaff(staff);
	}
	*/
	
	@ResponseBody
	@GetMapping(value="/query", produces="application/json; charset=UTF-8")
	public StaffDTO oneStaff(HttpServletRequest request) {
		String sno = request.getParameter("sno");
		return staffService.lookupStaff(sno);
	}
	
}
