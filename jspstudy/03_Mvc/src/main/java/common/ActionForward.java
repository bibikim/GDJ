package common;

public class ActionForward {
	
	                                                              // 응답할 Jsp의 이름
	private String view;   		 // 응답할 Jsp의 이름     // 뷰의 이름을 저장하는 클래스
	private boolean isRedirect;  // 이동 방식(true이면 리다이렉트, false면 포워드)

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
	
	
}
