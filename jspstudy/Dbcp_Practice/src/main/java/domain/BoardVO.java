package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class BoardVO {
	
	private int no;
	private String writer;
	private String ip;
	private int hit;
	private Date createdate;
	private Date modifydate;
	private String title;
	private String content;
}
