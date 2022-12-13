package com.gdu.mysql.domain;


import java.util.Date;

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
	private Date createDate;   // sql, util 둘다 됨!
	private Date modifyDate;   // 그러나 sql은 시간까진 안 나옴. util은 시간까지 가능
	private int attachCnt;
}
