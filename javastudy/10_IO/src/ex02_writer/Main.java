package ex02_writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
// ctrl + shift + o   -> import 정리해주는 단축키(필요한게 없으면 넣어주고, 잘못 들어간건 지워줌)

public class Main {

	public static void m1() {
		// 스트림까지 뚫어서 파일을 만드는 것. 스트림fw 통해서 내용을 채울 수도 있고 읽어줄 수도 있다.
		// file객체를 통해서 file과 연결해서 출력stream 만들기
		
		File dir = new File("c:\\storage");
		
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		File file = new File(dir, "m1.txt");	// 경로가 dir(storage)다.	 parent - 경로(폴더), child - 파일
		
		FileWriter fw = null;	// m1내부.
		try {
			// 출력 스트림 (c:\\storage\\m1.txt 파일과 연결되는 문자 출력 스트림 생성)
			// 출력 스트림이 생성되면 파일도 생성된다. (따라서 file.createNewFile 쓸 일은 별로 없음)
			fw = new FileWriter(file); // try블록 내부.     new FileWriter("c:\\storage\\m1.txt")와 같음.    파일 객체로 전달하는 법
			
			// 내가 만든 FileWriter fw 도 닫아줘야 된다. fw.close();
		} catch (IOException e) {
			e.printStackTrace();	// 스트림의 생성도 언제나 예외처리가 필요하다. IOE~
		} finally {		//언제나 마지막에 한번 실행되는 블록
			try {
				if(fw != null) {
					fw.close();		// m1내부.      또다시 try-catch가 필요
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 출력스트림을 이용해서 파일을 생성하면 원래 있던건 삭제하고 언제나 새로 생성이 된다.
		// fw.close();는 finally 블록 안에 있어야 된다. try블록 안에 있으면 예외 발생시에 fw.close까지 동작이 안될 수 있기 때문에..!!
		
		// try블록1에서 선언했는데 fw.close가 밖으로 나갔으니 오류가 남. Unhandled exception~
		// 선언은 밖에서(null값주고) 필요하고 생성만 try안에서.
		// 실무에서는 fw.close를 그냥 try1블록에서 하고 새로운 try블럭 안 만들고 그냥..처리하기도 함. 예외가 거의 발생 안 해서..
		//try {
		//	FileWriter fw = new FileWriter(file);
		//	fw.close();
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		
	}
	
	public static void m2() {
		
		File file = new File("C:\\storage", "m2.txt");
		FileWriter fw = null;
		try {
			// 출력 스트림 생성(파일도 함께 생성)
			fw = new FileWriter(file);
	
			// 출력할 데이터
			// 1. 1글자 : int
			// 2. 여러 글자 : char[], String  >> ChraSequence
			int c = 'I';
			char[] cbuf = { ' ', 'a', 'm'};
			String str = " HarryPotter";
			
			// 출력 스트림으로 보내기(출력)
			fw.write(c);
			fw.write(cbuf);
			fw.write(str);	

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fw != null) {
					fw.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m3() {
		
		// try - catch - resources문
		// 1. resources는 자원을 의미함
		// 2. 여기서 자원은 스트림(stream)을 의미
		// 3. 스트림의 종료(close)를 자동으로 처리하는 try-catch문을 의미함
		// 4. 형식
		//     try(stream 생성 코드) {				fw = new
		//           코드
		//    } catch(Exception e) {
		//         e.printStackTrace();             finally의 존재 이유는 close()때문임
		//    }									
		// 이렇게 하면 공식적으로 finally를 안 적어도 됨. close를 자동으로 진행!
		
		File file = new File("c:\\storage", "m3.txt");
		try (FileWriter fw = new FileWriter(file)) {
			
			fw.write("마녀 배달부 키키");
			fw.write("\n");		// 줄바꿈
			fw.write("하울의 움직이는 성\n");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		// try 옆에 스트림 생성을 해주면 finally와 close 없어도 된다~!
		
	}
	
	public static void m4() {
		
		// ★★ 중요! 사용법 잘 익히기~
		
		File file = new File("C:\\storage", "m4.txt");
		
		try(FileWriter fw = new FileWriter(file)) {
			
			char[] cbuf = {'a', 'b', 'c', 'd', 'e'};
			String str = "abcde";
			
			fw.write(cbuf, 0, 2); 	// 인덱스 0부터 2글자만 씀
			fw.write(str, 2, 3);	// 인덱스 2부터 3글자만 씀
		
		} catch(IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void m5() {
		
		// FileWriter는 느리기 때문에
		// 빠른 속도가 필요한 경우 Buffered Writer를 추가해서 함께 사용한다.
		
		File flie = new File("c:\\storage", "m5.txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			// 출력 담당 출력 메인 스트림
			fw = new FileWriter(flie);
			
			// 속도 향상을 위한 보조 스트림(메인에 끼우는 보조)
			// 메인 스트림이 없으면 사용 불가
			bw = new BufferedWriter(fw);
			
			bw.write("뉴진스 Hype boy");
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 메인 스트림은 닫을 필요가 없음.(자동으로 메인 스트림이 닫히므로)
				if(bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m6() {
		
		// PrintWriter 클래스는 write() 메소드 이외에
		// print(), println() 메소드를 지원한다.       <- println을 쓰기 위해 PrintWriter를 사용한다
		
		File file = new File("c:\\storage", "m6.txt");  // writer는 텍스트를 만들 때 쓰는 것
		PrintWriter out = null;
		
		try {
			
			out = new PrintWriter(file);
			
			// write() 메소드는 줄 바꿈을 "\n"으로 처리한다.
			out.write("안뇽하세요~\n");
			
			// println() 메소드는 자동으로 줄 바꿈이 삽입된다.
			out.println("똑바로 읽어도 거꾸로 읽어도 우영우");
			out.println("기러기 스위스 토마토 인도인 별똥별 우영우");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null)
					out.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		m6();
		

	}

}
