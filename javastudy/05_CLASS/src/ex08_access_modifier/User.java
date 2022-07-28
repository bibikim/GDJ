package ex08_access_modifier;

public class User {

	// 필드는 private이다. (공개하지 X)
	private String id;			// 아무도 안 보여줄 id
	private String password; 	// 					pw
	private String email;		//					email
	private int point;
	private boolean isVip;	
	
	
	// 메소드는 public이다. (공개 - 내가 정해준 메소드로 접근해라.)				
	public String getId() { 							// id를 내가 정해준 메소드(getId)로 접근해라.
		return id;										// id를 반환해주겠다.
	}
	public void setId(String pId) {
		id = pId;
	}
	public String getPw() {
		return password;
	}
	public void setPw(String pPw) {
		password = pPw;
	}
	public String getEm() {
		return email;
	}
	public void setEm(String pEm) {
		email = pEm;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer pPoint) {
		point = pPoint;
		setVip(point >= 10000);				// 10000 이상이면 boolean pVip에 true, 아니면 false가 전다
	}										// 내부 기능은 내부에서 쓰게 되는 것.
	public Boolean getVip() {
		return isVip;
	}
	private void setVip(Boolean pVip) {		// public을 private로 바꾸면 유저메인에서 vip여부는 호출 불가능
		isVip = pVip;						// vip 기준을 public으로 돌려놓지 않은 것임. 기준은 private으로 public으로는 공개될 수 없게.
	}
	
	
}
