package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor		// 디폴트 생성자
@AllArgsConstructor     // 필드를 이용한 생성자
@Getter	
@Setter
@Builder				// 빌더패턴. 객체를 만들 때 사용하는 것
@ToString				// toString() 메소드


public class Board {
	private int board_no;   // 맵핑을 위해서 이름을 맞춰주자
	private String title;
	private String content;
	private Date create_date;
}
