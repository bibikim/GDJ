package domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Notice {
	private int noticeNo;
	private String title;
	private String createDate;
	
}
