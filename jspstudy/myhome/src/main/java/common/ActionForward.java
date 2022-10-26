package common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// actionforward에 생성자까지 만들어주기

public class ActionForward {
	private String view;
	private boolean isRedirect;
}
