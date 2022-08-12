package ex04_outputstream;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void m1() {
		
		// 바이트 기반의 파일 생성 스트림 = OutputStream
		// 출력스트림으로 비어있는 파일이 만들어진다. 파일이 있건 없건 새로 파일을 만든다.
		
		File file = new File("c:\\storage", "b1.bin");
		FileOutputStream fos = null;	// 생성하려면트라이캐치가 필요하니까 null잡고 try블럭 안에서 생성
		
		try {
			
			// c:\\storage\\b1.bin 파일과 연결되는 바이트 기반 출력스트림 생성
			fos = new FileOutputStream(file);
			
			// 출력할 데이터 단위
			// 1. 1개 : int 
			// 2. 여러 개 : byte[] 
			
			// 출력할 데이터
			int c = 'A';
			String str = "pple Mango 맛있다.";	// 한글은 3바이트
			byte[] b = str.getBytes(StandardCharsets.UTF_8); 	// str.getBytes("UTF-8")
			// string을 byte배열로 바꾸는 메소드. ex)문자 인코딩 방식charset UTF-8같은..
			// 한글이 포함 되어 있으니까 charset.utf_8 찍어주기
			
			// 출력
			fos.write(c);
			fos.write(b);
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fos != null) 
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void m2() {
		
		// 출력 속도 향상을 위한 BufferedOutPutStream
		
		File file = new File("c:\\storage", "b2.bin");
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			
			String str = "안녕하세요 반갑습니당.";
			byte[] b = str.getBytes("UTF-8");	// 스트링을 utf-8인코딩 방식으로 전달
			
			bos.write(b);   // bos 만들어뒀으니 fos 쓰지 말고 바로 bos 사용하자
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bos != null);	// 보조스트림만 클로즈 하면 된다. fos 닫는 코드가 들어가도 문제는 없지만 넣을 필요 없음
					bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m3() {
		
		// ^변수^를 그대로 출력하는 DataOutPutStream (보조 스트림)
		
		File file = new File("c:\\storage", "b3.dat");
		FileOutputStream fos = null;
		DataOutputStream dos = null;  // 보조스트림이기때문에 메인스트림인 fos를 생성하고 쓸 수 있다
		
		try {
			
			fos = new FileOutputStream(file);
			dos = new DataOutputStream(fos);
			
			// 출력할 변수
			String name = "장워녕";
			int age = 19;
			double height = 172.5;	// double은 데이터로 저장하기 힘들지만 DataOutputStream 사용해서 보낼 수 있게 된다.
			
			// 출력
			dos.writeUTF(name); // 문자String 보내기
			dos.writeInt(age);
			dos.writeDouble(height); // double 보내기
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(dos != null);
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m4() {
 
		// 객체를 그대로 출력하는 ObjectOutputStream (보조스트림)
		
		File file = new File("c:\\storage", "b4.dat");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			
			// 1. User를 3개 저장한 ArrayList
			List<User> users = Arrays.asList(		// 리스트에 user 저장
					new User(1, "kim", 30),
					new User(2, "lee", 20),
					new User(3, "cho", 27)
					);
			
			// 2. User 1개
			User user = new User(4, "yoo", 51);
			
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(users);  // 리스트 통째로 보냄
			oos.writeObject(user);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(oos != null);
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
		
		
		
	
	
	public static void main(String[] args) {
		m4();
		
		
	}

}
