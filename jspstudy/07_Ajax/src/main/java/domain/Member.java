package domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder


public class Member {

	private int memberNo;
	private String id, name, gender, grade, address;

}
