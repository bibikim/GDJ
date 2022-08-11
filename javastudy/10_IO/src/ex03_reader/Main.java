package ex03_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void m1() {	// 공부는 s1로~
		
		
		
		File file = new File("c:\\storage", "m2.txt");
		FileReader fr = null;   // exception 처리 필요
		try {
			// FileReader 클래스 생성
			// file 객체에 등록된 파일이 없으면 FileNotFoundException 발생     -> 출력스트림과 다른 점! 
			fr = new FileReader(file);
			
			// FileReader 입력 데이터
			// 1. 1글자 : int
			// 2. 여러 글자 : char[]          (스트링 불가)
			
			// 1글자를 저장할 변수
			int c;
			
			// read() 메소드
			// 1. 읽은 문자를 반환
			// 2. 모두 읽어서 읽은 문자가 없으면 -1 반환 (쭉 읽다가 다 읽으면 -1을 반환)
			
			// String str에 파일 내용 저장하기
			StringBuilder sb = new StringBuilder(); // StringBuilder 생성
			while(true) {
				c = fr.read();  // 한 글자 읽어옴
				if(c == -1) {	
					break;
				}
				sb.append((char)c); // => str += ((char)c);	 스트링빌더에 읽어온 글자 누적
				// c의 타입은 int니까 글자로 읽어올 수 있게 char타입으로 캐스팅	
			}
			String str = sb.toString();
			System.out.println(str);	// 읽은걸 str에 저장해서 출력
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 스트링에 += 연산을 많이 하면 성능이 떨어짐 -> Stringbuilder 쓰자
		// 스트링빌더에 누적시켜주고 마지막에 출력~
	}
	
	public static void m2() {
		
		File file = new File("c:\\storage", "m3.txt");
		FileReader fr = null;
		
		try {
			
			fr = new FileReader(file);
			
			// 5글자를 저장할 배열
			char[] cbuf = new char[5];	// 배열의 길이만큼 글자를 읽어옴 > 5글자씩 읽음
			
			// read(char[]) 메소드 : read메소드에 캐릭터배열이 들어가는 메소드
			// 1. 읽은 글자는 cbuf 배열에 저장
			// 2. 실제로 읽은 글자 수를 반환
			// 3. 읽은 글자가 없으면 -1을 반환
			// ex) 13글자를 읽어와야 한다면, 1번째-5글자 2번째-5글자 3번째-3글자 4번째 -1 반환.
			// cbuf.length로 반복문을 돌리면 
			
			// m3.txt 파일 읽는 과정
			//          readCnt       cbuf(배열)
			// 1         5글자       a  p  p  l  e	   배열을 재활용 하고 있는거라
			// 2         5          \n  m  a  n  g									(읽을 때 줄바꿈\n까지 읽음)
			// 3 		 2           o  \n a  n  g     앞에 두글자를 덮어쓰기로 읽고 아까 읽었던거 뒤에 3개는 그대로 남음
			// 4        -1							   따라서 for문에서 readCnt만큼만 반복하게끔 해줘야 한다.
			
			while(true) {
				
				int readCnt = fr.read(cbuf); // readCnt 읽은 갯수 = 5 
											 // cbuf(읽어주는글자) = 마녀 배달(5글자)
											 // 배열의 길이로 반복돌렸을 때에 생길 잘못된 출력을 피하기 위해
											 // 배열의 길이만큼 readCnt를 지정해준다.
				if(readCnt == -1) {
					break;
				}
				
				for(int i =0; i < readCnt; i++) {	 //★★ 읽은 글자 수(readCnt)만큼 반복
					System.out.print(cbuf[i]);
				}
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null);
					fr.close();
		
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void m3() {	// 공부는 s2로~
		
		// m3.txt 읽어서 String str에 저장하기
		File file = new File("c:\\storage", "m3.txt");
		FileReader fr = null;
		
		try {
			
			fr = new FileReader(file);
			
			char[] cbuf = new char[5];
			StringBuilder sb = new StringBuilder();
			
			while(true) {
				int readCnt = fr.read(cbuf);   // 읽어준 텍스트는 배열에 있고 그걸 sb에 저장해야됨
				if(readCnt == -1) {
					break;
				}
				sb.append(cbuf, 0, readCnt); // cbuf 배열의 0부터 readCnt개만 출력하겠다		
			}
			String str = sb.toString();
			System.out.println(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	
	}

	
	public static void m4() {
		
		// FileReader는 느리기 때문에
		// BufferedReader를 추가해서 속도를 향상시킨다.
		
		// BufferedReader는 readLine() 메소드를 지원한다.
		// readLine() 메소드는 한 줄씩 읽어서 String에 저장한다
		// 읽은 내용이 없으면 null을 반환한다.
		
		File file = new File("c:\\storage", "m3.txt");
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) { 	// redaLine은 줄마다의 줄바꿈을 못 가져오기 때문에
				sb.append(line).append("\n");			// .append("\n") 추가 (or .append(line + "\n"))
			}
			String str = sb.toString();
			System.out.println(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}	
	}
	
	public static void m5() {
		
		// try - catch - resources문으로 m4() 다시 풀기
		
		// 서버의 컬렉션을 내려받는 코드라고 보면 됨. 외우면 좋다!
		
		                                          //____________________________________ 이 부분을 서버의 컬렉션으로 바꿀거고 그걸 str(xml/json)으로 읽어주는 것
		try (BufferedReader br = new BufferedReader(new FileReader("c:\\storage\\m3.txt")))	{  // filereader 임폴트 string fileName
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			String str = sb.toString();
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// try 옆에 스트림 생성을 해주면 finally와 close 없어도 된다~!
	}
	
	public static void s1() {
		
		// m1이랑 같은 거 while문을 조금 더 짧고 깔끔하게 정리한 코드.
	
		File file = new File("c:\\storage", "m2.txt");
		FileReader fr = null;   // exception 처리 필요
		
		try {
			fr = new FileReader(file);
		
		int c;
		StringBuilder sb = new StringBuilder();
		while((c = fr.read()) != -1) {
			sb.append((char)c);
		}
		
		String str = sb.toString();
		System.out.println(str);	// 읽은걸 str에 저장해서 출력
		
	} catch(IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if(fr != null) {
				fr.close();
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	
	public static void s2() {
		
		File file = new File("c:\\storage", "m3.txt");
		FileReader fr = null;
		
		try {
			
			fr = new FileReader(file);
			
			char[] cbuf = new char[5];
			StringBuilder sb = new StringBuilder();
			int readCnt;
			while((readCnt = fr.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCnt); // cbuf 배열의 0부터 readCnt개만 출력하겠다		
			}
			String str = sb.toString();
			System.out.println(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}
		
	}
	
	
	public static void main(String[] args) {
		m5();

	}

}
