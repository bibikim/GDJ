package com.gdu.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.mapper.StaffMapper;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired   // 나는 mapper가 필요해융
	private StaffMapper staffMapper;
	
	@Override
	public List<StaffDTO> getStaffList() {

		return staffMapper.selectStaffList();
	}
	
	@Override
	public ResponseEntity<String> addStaff(StaffDTO staff) {   // staff에는 sno, name, dept가 들어있음! fn_add()의 ajax data 속성 참고
		
		try {
		
			// => 매퍼에선 salary(Not Null처리)도 받아왔는데 안 넘겨주면 null -> 부적합열유형 오류 떨어짐
			// staff에 salary 넣기 : 기획부 1000, 개발부 2000, 영업부 3000, 나머지 4000  -> if나 switch로 코드

			
			switch(staff.getDept()) {
			case "기획부":
				staff.setSalary(5000);
				break;
			case "개발부":
				staff.setSalary(6000);
				break;
			case "영업부":
				staff.setSalary(7000);
				break;
			default :
				staff.setSalary(4000);
			}
			System.out.println(staff);
			int result = staffMapper.insertStaff(staff);
			System.out.println(result);
			// success로 넘어가는 응답
			return new ResponseEntity<String>("사원 등록이 성공했습니다.", HttpStatus.OK);  // 성공했을 때 반환값.   T(template타입) = String, T body = 응답할 데이터 자체를 의미
											// └> 받아오는 dataType이 text인 이유. 응답을 여기서 직접 text로 보내기 때문!
		
		} catch(Exception e) {   // exception 떨어질 때, 실패할 때
			
			// error로 넘어가는 응답
			return new ResponseEntity<String>("사원 등록이 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);  // 실패사유 그냥 내부 서버 오류로 에러메시지 보내기..
			
		}	
	}
	
	@Override
	public StaffDTO lookupStaff(String sno) {
		
		return staffMapper.selectBySno(sno);
	}

}
