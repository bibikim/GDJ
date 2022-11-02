package domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class Notice {
	private int noticeNo;
	private String title;
	private String createDate;
	
}
