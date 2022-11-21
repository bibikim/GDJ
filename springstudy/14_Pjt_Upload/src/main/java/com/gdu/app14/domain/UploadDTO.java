package com.gdu.app14.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UploadDTO {
	private int uploadNo;
	private String title;
	private String content;
	private Timestamp createDate;
	private Timestamp modifyDate;  // DATE보다 세밀한 시간 설정이 가능한 Timestamp -> DB에서는 TIMESTAMP
	private int attachCnt;  // table에는 없고 DTO에만 있음. 항상 테이블의 칼럼과 필드가 일치하는 것은 아님. 필요에 의해 필드에만 선언할 수도 있는것
			// 매퍼에서 attach_cnt -> 첨부파일의 개수에 대한 칼럼에 필드에 동일한 별명을 줘야 DTO와 mapper.xml의 쿼리문과 매핑이 됨.
}
