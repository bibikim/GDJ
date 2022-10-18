package common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ActionForward {
	// 어디로 어떻게 갈 것인지 상태값을 저장하는 클래스
	private String view;   		  // 작업이 끝난 후 어디로 갈 건지 jsp 이름 적어주는 변수
	private boolean isRedirect;   // 리다이렉트 해서 보낼거면 true, 아니면 포워드로 보내겠다 false
}
