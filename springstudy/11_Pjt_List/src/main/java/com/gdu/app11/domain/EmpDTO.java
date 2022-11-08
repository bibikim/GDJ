package com.gdu.app11.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpDTO {

	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private double salary;
	private double commissionPct;
	private int managerId;
	private DeptDTO deptDTO;   // 외래키. DeptDTO의 PK이자 EmpDTO의 FK는 DeptDTO 타입으로 잡아준다 
	    	// └> myBatis에서 조인된 데이터(int departmentId) -> 부서에 관련된 모든 정보가 들어가는 거라 정보 조회가 편해진다.
	
}
