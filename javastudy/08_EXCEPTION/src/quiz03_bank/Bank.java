package quiz03_bank;

// BankException
// 마이너스 입금 불가, 코드값 1000
// 마이너스 출금 불가, 코드값 2000
// 잔액보다 큰 출금 불가, 코드값 2001

// deposit() throws BankException
// withdrawal() throws BankException
// transfer() throws BankException


public class Bank {
	
	private String accNo;
	private long balance;
	
	public Bank(String accNo, long balance) {
		super();
		this.accNo = accNo;
		this.balance = balance;
	}
	
	
	public void deposit(long money) throws BankException {  // 돈을 long타입으로 받아오겠다~
		if(money < 0) {
			throw new BankException("마이너스 입금 불가", 1);
		}
		balance += money;
	}
	
	public long withdrawal(long money) throws BankException {			// long타입으로 반환
		if(money < 0) {
			throw new BankException("마이너스 출금 불가", 2);
		} else if(money > balance) {
			throw new BankException("잔액 부족", 3);
		}
		balance -= money;
		return money;
	}
	
									
	public void transfer(Bank acc, long money) throws BankException {	// 누구에게 얼마를 뺄거냐
		acc.deposit(withdrawal(money));    	// 예외가 이쪽으로 던져짐. throws Bank~를 붙여서 다시 던져줌.
											// main에 try-catch문 넣어주면 오류 해결
	}

	public void inquiry() {
		System.out.println(this);  	// 현재 객체를 출력.....? 객체를 대신해서 참조값이 시스템을 만나면 toString하면 나옴..?
	}


	@Override
	public String toString() {
		return "Bank [accNo=" + accNo + ", balance=" + balance + "]";
	}
	
	
	
	
	
}
