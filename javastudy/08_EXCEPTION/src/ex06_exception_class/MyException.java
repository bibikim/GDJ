package ex06_exception_class;


// 사용자가 정의한 예외 클래스
// Exception 클래스를 상속 받는다

// Serialiazable 인터페이스 : 이 인터페이스를 구현하면 직렬화가 가능. serialVersionUID 값을 가져야 함.(추천)
//    ↑
// Throwable 클래스 : serialVersionUID 값이 필요함
//    ↑
// Exception 클래스 : serialVersionUID 값이 필요함
//    ↑
// MyException 클래스 : serialVersionUID 값이 필요함
// 상속관계이기 때문에 그렇다.

public class MyException extends Exception{

	private static final long serialVersionUID = -7774118171104436322L;   // static final이니까 안 바뀌는 값.
	
	private int errorCode;

	public MyException(String message, int errorCode) {   // 메시지를 Exception에서 전달 받고 super클래스(message) 값을 출력한다
		super(message);
		this.errorCode = errorCode;
	}
// ㄴ errorCode 필드로 생성자 만들고, 슈퍼클래스인 Exception이 가진 String message를 가져오기 위해 앞에 넣어준 뒤, super() 안에 message 써준다 
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	

// Exception       String message;   에러난 이유. Exception은 항상 String message값을 갖고 있다고 생각하면 된다.
//   ↑
// MyException     int errorCode;	  - 상속관계이니까(+ String message까지)
	
	
	
}
