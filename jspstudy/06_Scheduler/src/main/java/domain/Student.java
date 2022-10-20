package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

	
public class Student {  // 경우에 따라 domain의 클래스 이름을 BoardDTO 혹은 BoardVo라고도 짓는다
	private int stuNo;  // db 칼럼이름과 똑같이 맞추기 귀해서 board_no인 거. 근데 프레임워크는 카멜케이스 써도 알아서 도와줌
	private String name;
	private int kor, eng, math;
	private double ave;
	private String grade;
	
	
	
}
