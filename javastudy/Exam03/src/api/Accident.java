package api;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString

public class Accident {

	private String occrrncDt;
	private String occrrncDayCd;
	private int dthDnvCnt;
	private int injpsnCnt;
	
	
	
}
