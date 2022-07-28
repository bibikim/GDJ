package ex08_access_modifier;

public class UserMain {

	public static void main(String[] args) {
		
		
		User user = new User();
		
		//System.out.println(user.); 		id, pw 다 찍어봐도 안 뜸. 왜냐면 private이니까. 이런식의 접근을 불허함
		// user.id = "admin"; 				원래는 이렇게 id를 직접 불러왔는데 프라이빗에선 불가
		
		System.out.println(user.getId());		// getId는 찍어보면 나옴. public이니까. 
												// 직접 확인이 아니라 메소드를 통한 우회작업으로 확인
		
		user.setId("admin");					// id세팅(setId) 메소드로 들어가서 프라이빗필드로 접근하여 id를 출력
		System.out.println(user.getId());		// admin 인수는 매개변수 string pId로 전달돼서 프라이빗 id로 접근
		
		user.setPw("2341214");
		System.out.println(user.getPw());
		
		user.setEm("kukyo@gmail.com");
		System.out.println(user.getEm());
		
		user.setPoint(10000);					// 1000 넣으면 false, 10000을 넣으면 true
		System.out.println(user.getPoint());
		
		//user.setVip(false);					
		System.out.println(user.getVip());
		
		
		
		
		
	}

}
