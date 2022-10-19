package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



	
public class Board {  // 경우에 따라 domain의 클래스 이름을 BoardDTO 혹은 BoardVo라고도 짓는다
	private int boardNo;  // db 칼럼이름과 똑같이 맞추기 귀해서 board_no인 거. 근데 프레임워크는 카멜케이스 써도 알아서 도와줌
	private String title;
	private String content;
	private Date createDate;
	
	
	
}
