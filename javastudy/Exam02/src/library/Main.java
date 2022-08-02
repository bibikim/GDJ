package library;

public class Main {

	public static void main(String[] args) {
		
		Library library = new Library();
		library.manage(); 		// 외부에서는 manage만 보인다.

		// new Library().manage();    위에 두줄 한줄로 만들기
		
	}

}


