package ex05_lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// lombok 이용하기		@ge, se, no, all, bui, To + ctrl space 

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
	
public class User {
	
	private int userNo;
	private String id;
	private String email;
	
	
}
