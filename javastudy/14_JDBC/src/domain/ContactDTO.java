package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data    // @Getter, @Setter, @ToString 등등 들어있음
@NoArgsConstructor
@AllArgsConstructor
@Builder

// 데이터 전송 객체
public class ContactDTO {
	
	private int contact_no;
	private String name;
	private String tel;
	private String email;
	private Date reg_date;

	// DTO로 만들어서 DB로 보내주고
	// 반대로 해도 ㅇㅇ
	// DB로 보내주는 것도 DTO에 저장
	// DB에서 넘겨주는 것도 DTO
	// 칼럼과 1:1로 작업

}
