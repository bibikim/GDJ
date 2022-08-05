package quiz03_bank;

public class Main {

	public static void main(String[] args) {
		
		Bank me = new Bank("1111", 10_000); 	// 내 계좌번호, 잔액
		Bank mom = new Bank("2222", 100_000);	// 엄마 계좌번호, 잔액
		
		
		try {
		mom.transfer(me, 30_000);				// 엄마가 나한테 돈을 3마넌 보내용
		} catch (BankException e) {
			System.out.println(e.getMessage() + ", " + e.getErrorCode());
		}
		
		me.inquiry();
		mom.inquiry();
		
		
		

	}

}
