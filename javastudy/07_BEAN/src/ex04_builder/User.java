package ex04_builder;

public class User {

	// 필드(Builder 객체가 가진 값을 받아 옴)
	private int userNo;
	private String id;
	private String email;
	
	// Builder 클래서의 build() 메소드가 호출하는 생성자
	public User(Builder builder) {		// builder = 맨아래 new User(this)
		this.userNo = builder.userNo;
		this.id = builder.id;
		this.email = builder.email;
		
	}
	// Builder 반환 메소드
	public static Builder builder() {
		return new Builder();
	}
	
	// User 클래스 내부에 Builder 클래스 생성
	// User 클래스를 이용해서 호출하기 위해 static 처리
	public static class Builder {
		
		// 내부 필드(여기에 값을 전달 받아서 User의 필드로 보내는 원리)
		private int userNo;
		private String id;
		private String email;
		
		// userNo() 메소드   (메소드이름을 필드이름이랑 똑같이 맞춘다)
		public Builder userNo(int userNo) {
			this.userNo = userNo;
			return this;
			
		}
		
		// id 메소드
		public Builder id(String id) {
			this.id = id;
			return this;
		}
		
		// email
		public Builder email(String email) {
			this.email = email;
			return this;
		}		
		// ----------실제론 위에 세개의 메소드를 통해 값을 받는다

		
		// build() 메소드
		public User build() {
			return new User(this);
			// this는 Builer 객체(userNo, id, email을 가지고 있는 객체)를 의미함
		}
		
	} // class Builer 여기가 빌더클래스 끝나는 자리~
	
	
	@Override
	public String toString() {
		return "Builder [userNo=" + userNo + ", id=" + id + ", email=" + email + "]";
	}
	
	
}
