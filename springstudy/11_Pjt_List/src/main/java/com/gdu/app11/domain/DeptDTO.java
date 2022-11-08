package com.gdu.app11.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeptDTO {
	private int departmentId;
	private String departmentName;
	private int managerId;
	private int locationId;
	// 테이블 칼럼 타입 NUMBER(?.?) 보고 int인지, long인지, double인지 판단  --> 첫번째 ? : 정수 최대 몇자리, 두번째 ? : 0 = 정수/ n = 소숫점 n자리
	// 정수면 int, 최대 자리수가 22억자리쯤이면 long, 소숫점 있으면 double
}
