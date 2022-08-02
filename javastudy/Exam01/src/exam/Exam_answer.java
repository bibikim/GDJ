package exam;

import java.util.Scanner;


public class Exam_answer {
	
	public static void main(String[] args) {
				q10();								// 사용을 위해 호출하는 것. 만들어 두는 것과 사용하는건 다름
													// 호출 순서가 실행 순서 or 실행하고 싶은 것만 적어줘도 됨
				
				
	}

		
	
	// 문제4. 0부터 Scanner 클래스를 이용해서 입력 받은 정수까지 모두 더한 뒤 평균을 출력하시오.
	// 만약, 0보다 작은 정수가 입력되면 평균은 그냥 0을 출력하시오.
	// 예시1
	// 마지막 정수 입력 >>> 5
	// 0부터 5사이 모든 정수의 평균 : 3.0     -> 15를 5로 나눠서 3이 나온것
	// 예시2
	// 마지막 정수 입력 >>> -5
	// 평균 : 0
	public static void q4() {
		int begin = 0;  // 0
		int end = 0;    // 입력 받은 정수
		Scanner sc = new Scanner(System.in);
		System.out.println("마지막 정수 입력 >>> ");

		if(end<0) {
			System.out.println("평균 : 0");
		} else {
			int total = 0;
			for(int n = begin; n <= end; n++) {
				total += n;
			}
			System.out.println(begin + "부터 " + end + "사이 모든 정수의 평균 : " + (double)total/end);
					}
	
	
	}
	
	
	// 문제6. 통장(balance)에 5000원이 있다. 1원부터 1000원 사이의 난수를 발생시켜서 해당 난수만큼 10번 출금 처리하시오.
	// 마이너스 통장이므로 잔액은 음수일 수 있다.
	// 예시
	// 출금 전 5000원, 1회 출금 1원, 출금 후 4999원
	// 출금 전 4999원, 2회 출금 10원, 출금 후 4989원
	// ...
	// 출금 전 100원, 10회 출금 500원, 출금 후 -400원
	public static void q6() {		// 만들어두는건 위치 중요하지X, 위치는 상관X 메인 메소드 밑에 있어도 괜찮다
		int balance = 5000;
		// for(int cnt = 1; cnt <= 10; cnt++) 	 	 >> 1~10 10번 카운트. 같은 식이지만 실무에선 잘 안 씀. 시작도 안했는데 어케 1?
			
		for(int cnt = 0; cnt < 10; cnt++) {			//>> 0~9 10번 카운트. 실무에서 많이 쓰는 방식
			int money = (int)(Math.random() * 1000) + 1;
			System.out.println("출금 전 " + balance + "원, " + (cnt + 1) + "회 출금 " + money + "원, 출금 후 " + (balance-=money) + "원");
																// ㄴ예시에 시작이 1회 출금이니깐.
		}
							// balance -= money; 	>> 굳이 다음 줄로 넘어가지 말고 위에 식에서 -= 해도 괜찮음.
		
		}
		
		
		// 문제7. Scanner 클래스를 이용해서 입력 받은 문자열에 문자 'h'가 몇 개 포함되어 있는지 갯수를 구해서 출력하시오.
		// next() 메소드를 이용해서 문자열을 입력 받으시오.
		// 예시
		// 문자열 입력 >>> happy
		// happy에 포함된 h는 1개입니다.
		public static void q7() {
			int cnt = 0;  // 'h'의 갯수
			Scanner sc = new Scanner(System.in);
			System.out.println("문자열 입력 >>> ");
				String str = sc.next();						
				
				for(int i = 0; i < str.length(); i++) {
						// index 활용
				if(str.charAt(i) == 'h') {
						cnt++;			// h라면 카운트 하겠다
				}
				}
				System.out.println(str + "에 포함된 h는" + cnt + "개입니다.");
			}
			
			
			//System.out.println(str + "에 포함된 h는" + cnt + "개입니다.");
			
		// 문제8. 다음 기준에 따라서 파일명을 변환하시오.
		// Scanner 클래스의 next() 메소드를 이용해서 파일명을 입력 받은 뒤 파일명 마지막에 밑줄(_)과 타임스탬프 값을 붙이시오.
		// 예시
		// 변환 전 파일명 >>> happy.jpg
		// 변환 후 파일명 = happy_1658792128410.jpg
		//public static void q8() {
			//Scanner sc = new Scanner(System.in);
			//System.out.println("변환 전 파일명 >>> ");
			//String beforeName = sc.next();
			//String[] arr = beforeName.split("\\.");		// 정규식 regular expression에서 문자 마침표는 \\. 로 해줘야 한다.
														// arr = {"aaa", "bbb", "jpg"}
														// split = . 기준으로 쪼개줄게 .기준으로 나뉨
			//String[] temp = new String[arr.length -1];
			//System.arraycopy(arr, 0, temp, 0, temp.length);		// temp = {"aaa", "bbb"}
			//String extName = arr[arr.length - 1];
			//String fileName = String.join(".", temp);
			//fileName += "_" + System.currentTimeMillis();
			//String afterName = fileName + "." + extName;

			// System.out.println("변환 후 파일명 = " + afterName);			
			// 배열의 마지막 요소는 언제나 length - 1 !! extName = jpg 
		
		// 문제9. Scanner 클래스의 next() 메소드를 이용해서 사람 이름을 입력 받은 뒤
		// 아는 사람의 이름이면 "반갑다 친구야"를 출력하고, 
		// 모르는 사람의 이름이면 "안녕하세요 처음뵙겠습니다"를 출력하시오.
		// 아는 사람은 "전지현", "정우성", "한지민" 뿐이다.
		// 예시1
		// 이름 입력 >>> 정우성
		// 반갑다 친구야
		// 예시2
		// 이름 입력 >>> 유재석
		// 안녕하세요 처음뵙겠습니다
			public static void q9() {
				String name = "";
				Scanner sc = new Scanner(System.in); 
				System.out.println("이름 입력 >>> ");
				String str = sc.next();
				if(name.equals("전지현") || name.equals("한지민") || name.equals("정우성")) {   // String은 ==가 아닌 .equals으로 비교
					System.out.println("반갑다 친구야");
				} else {
					System.out.println("안녕하세요 처음 뵙겠습니다");
				}
				
				
				// switch로 접근한 답
				switch(name) {
				case "전지현":										//전지현 넣고 끝나는게 아니라 break 만날 때까지 실행
				case "한지민":										//반갑다 실행되고 break
				case "정우성": System.out.println("반갑다 친구야"); break;
				default: System.out.println("안녕하세요 처음뵙겠습니다");
				}
				
				
				
				// 배열로 접근한 답
				String[] friends = {"전지현", "한지민", "정우성"};
				for(String friend : friends) {
					if(name.equals(friend)) {
						System.out.println("반갑다 친구야");
						return; 	// q9() 메소드 종료.
					}
				}
				System.out.println("안녕하세요 처음뵙겠습니다");
			}

			
			// 문제10. 다음 조건에 따라 비밀번호 체크 프로그램을 구현하시오.
			// 1. 비밀번호는 최대 5번까지 입력이 가능하다.
			// 2. 비밀번호는 "1234abcd"로 가정한다.
			// 3. 비밀번호 입력이 성공하면 "성공"을 출력하고 프로그램을 종료한다.
			// 4. 비밀번호 입력이 실패하면 "실패"를 출력하고 다시 비밀번호를 입력 받는다.
			// 5. 5번째 비밀번호 입력 결과가 실패하면 "횟수 초과"를 출력하고 프로그램을 종료한다.
			public static void q10() {
				Scanner sc = new Scanner(System.in);
				for(int cnt = 0; cnt <5; cnt++) { 			// 0~4 다섯번 반복한다는 뜻
					System.out.println("비밀번호 입력(" + (cnt + 1) + "회) >>> " );
					String pw = sc.next();				    // ㄴ0번 입력은 없으니까 +1 해준거.
					if(pw.equals("1234abcd")) {
						System.out.println("성공");
						break;			// or return;도 가능
					}else {
						if(cnt == 4) {						// 5번째 돌렸을 때. 0부터니까 4가 다섯번째임
							System.out.println("횟수 초과");
						} else {
							System.out.println("실패");
						}
					}
				}
				
				
			}
			// 실무에서 비밀번호 비교는 데이터베이스 활용으로 씀
			
			
			
			
			
			
			
			}
