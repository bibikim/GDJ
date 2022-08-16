package ex05_inputstream;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.List;

import ex04_outputstream.User;


public class Main {

	public static void m1() {
		
		File file = new File("c:\\storage", "b1.bin");
		FileInputStream fis = null;
		
		try {
			
			// 바이트 입력 스트림 생성
			fis = new FileInputStream(file);
			
			// 입력 데이터 단위
			// 1. 1개 : int
			// 2. 여러 개 : byte[]
			
			// 모든 정보를 읽어서 StringBuilder에 저장한 뒤 확인
			StringBuilder sb = new StringBuilder();
			byte[] b = new byte[5]; // 5바이트씩 읽기 위해 준비
			int readByte = 0; // 읽어준 실제 바이트 수
			
			// int read(byte[] b)    -> read()메소드
			// 읽은 내용은 byte배열 b에 저장
			// 읽은 바이트 수를 반환
			// 읽어올 내용이 없으면 -1 반환
			// 13바이트가 있다. 5바이트씩 읽고 5를 반환, 남은 3바이트만 읽어서 3바이트만 반환하게끔(readByte는 이거땜에 쓰는거임)
			
			while((readByte = fis.read(b)) != -1) { // 첫 5개 바이트 b배열에 저장, 반환도 5개
				// ㄴ 읽어준 내용이 있으면! (-1이 아니면~이니까)
				sb.append(new String(b, 0, readByte));
				// new String() -> 스트링을 만드는 방법중 하나. 이걸 사용 (바이트배열, 0(int offset)부터, readByte(int length)까지)
				// char[] 경우에는 sb.append에 있었지만.. 바이트는 없어서 new String() 이용
				
			}
			
			// 문자를 바이트 스트림으로 읽었기 때문에
			// 문제가 발생
			System.out.println(sb.toString());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m2() {
		
		// InputStreamReader 바이트로 전송된 문자의 변환기..?!                - FileReader의 부모
		// 바이트 입력 스트림을 문자 입력 스트림으로 변환하는 InputStreamReader
		
		File file = new File("c:\\storage", "b2.bin");
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			//여기서 BufferedReader 생성하고 끼워도 됨. 속도 향상
			
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[5];  // 5글자씩 읽기 위해서. 	바이트 배열이 아니라 char 배열로 바꿔줘야 한다. 
			int readCnt = 0;
			
			while((readCnt = isr.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCnt);    // 읽어준 만큼(readCnt)만 추가해라!
			}
			
			System.out.println(sb.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(isr != null)
					isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void simsim() {
		
		// System.in  바이트 스트림(input스트림)
		// 바이트 스트림을 문자 스트림으로 바꿔주는 inputstreamreader를 사용해서.. 바로 입력받기 가넝
		
		try {
			
			// System.in : 키보드와 연결된 바이트 스트림
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 파일이 아닌 키보드에 연결한 것
			
			System.out.println("입력 >>> ");
			String str = br.readLine();  // br을 썼으니 readLine 사용 가능. 한줄씩 읽어오기
			
			System.out.println(str);
		
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void m3() {
		
		// 변수를 그대로 입력 받는 DataInputStream
		
		File file = new File("c:\\storage", "b3.dat");
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			
			fis = new FileInputStream(file);
			dis = new DataInputStream(fis);
			
			String name = dis.readUTF();
			int age = dis.readInt();
			double height = dis.readDouble();
			
			System.out.println(name + ", " + age + ", " + height);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(dis != null)
					dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	// FileInputstream -> DataInputstream    -> **readUTF      읽어오기   	   => 둘다 바이트배열인데 한글문자가 깨지지 않고 잘 넘어옴.
	// FileOutputstream -> DataOutputstream  -> **writeUTF	  만들어 써넣기	     
	// 원래 바이트는 Reader로 바꿔서 하는건데,
	// 데이터인풋아웃풋스트림으로 접근하면 문자가 깨지지 않고 써넣고 읽기 가능하다.
	
	// ★★ 바이트 배열의 문자 처리를 Data(In/Output)Stream으로 할 수 있다. 문자처리 용도로 많이 씀!!
	
	
	
	public static void m4() {
		
		// 객체를 그대로 입력 받는 ObjectInputStream
		File file = new File("c:\\storage", "b4.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			
			fis = new FileInputStream(file);	//file 전달
			ois = new ObjectInputStream(fis);	//fis 전달
			
			List<User> users = (List<User>)ois.readObject();    // 타입이 안 맞으니까 캐스팅
			User user = (User)ois.readObject();
			
			for(User u : users) {
				System.out.println(u);		// List에 담긴 user들s
			}
			System.out.println(user);		// 하나 받아오기 (4, yoo, 51)
			
		} catch(ClassNotFoundException e) {   // readObject메소드 땜에 필요한 exception. (readObject 마우스갖다 대면 나오는 예외 2개)
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ois != null)
					ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
	
	
	public static void main(String[] args) {
		m4();
	}

}
