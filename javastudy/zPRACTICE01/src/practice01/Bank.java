package practice01;



public class Bank {

	// 필드
	private String accNo;
	private long balance;
	
	// 생성자 (생성자 이름 = 클래스 이름)
	public Bank(String accNo, long balance) {     // Bank b = new Bank("1234", 5000) : new이후가 생성자를 호출하는 부분. 그 부분이 처리하는 애가 Bank(Str acc, long bal)
		this.accNo = accNo;
		this.balance = balance;
	
	}

	// 1. 입금(마이너스 입금 불가)
	public void deposit(long money) { // deposit(50000) : 50000원 입금할게용 = 입금하는 방식을 밖에서 받아오는 형식
					// ㄴ 이걸 호출해올 수 있는 객체 생성하기 : b.deposit(5000);
	 
		if(money <=0 ) {
			return;	// 반환 타입이 void일 때만 return으로 메소드를 종료할 수 있다. 한 쌍임
		}
		balance += money;	
	}

	// 2. 출금(마이너스 출금 + 잔액보다 큰 출금 불가) --- 깃 풀이는 출금에 실패할 수도 있고 성공 할 수도 있음을 true/false로 한거
	// 실제로 출금된 금액을 반환 - 실제로 출금이 될 수도 있고, 안 될수도 있는걸 구현
	 public long withdrawl(long money) { // long 출금액 = b.withdrawl(50000); 5만원 출금 요청을 했는데 출금되는 금액은 오만원 or 0원	// 실제 출금 금액은 돈이니까 long타입
		 if(money <= 0 || money > balance) {
			 return 0;   // 0을 반환시킨다는 것을 롱타입으로 만들어줘야됨. 그냥 return은 반환이 롱타입이기 때문에 오류
		 }
		 balance -= money;
		 return money;   // 출금금액(money) 반환
	 }
	// 3. 이체(출금 먼저 -> 입금)
	 public void transfer(Bank other, long money) {  // 누구에게 얼마나 이체할거냐. 아더 통장엔 +, 내 통장은 - 
		 // 내 통장에서 출금된 금액만큼 상대 계좌에 입금한다.
		 long withdrawlMoney = withdrawl(money);  
		 						// ㄴ 얘가 출금 코드 0이나 money를 반환하게 됨
		 other.deposit(withdrawlMoney);
		 						// 입금 출금 코드 다 있기 때문에 이체 코드 새로 생성이 아닌 저 둘을 호출하는 방식
		 
	 	}
		 // = other.deposit(withdrawl(money));    위에 두 줄과 같은 코드..\
		 //			|				|
		 //	상대 계좌 입금		출금(금액)
		 
	 // 4. 계좌 정보 확인
		public void accInfo() {
			System.out.println("계좌번호 :" + accNo + ", 잔액 : " + balance + "원");
		
		}	 
		 
		
		
	 // 5. 메인 생성.. (한 페이지 내에서 만들 순 있는데 그다지 추천 x)
	
	  public static void main(String[] args) {
		  
		  Bank me = new Bank("1234", 50000);
		  Bank mom = new Bank("4567", 100000);
		  
		  mom.transfer(me, 50000);
		  
		  me.accInfo();
		  mom.accInfo();
		  
		  
		  
		  
	  
		  
	  }
		 
		 
		
	 }
	 
	 
	 


