package ex04_socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) {
		
		// ServerMain은 1번만 실행(서버는 한번만 열어주면 됨)
		// ClientMain은 게스트3까지 가능케 했으니 3번 실행!
		
		// 서버에 접속되면 Server가 Client에게 Welcome 메시지를 보냄
		// client가 Scanner를 이용해 입력받은 메시지를 Server로 메시지를 보낸다.
		
		// serverSocket <---------- clientSocket
		// clientSocket.getOutputStream()
		// 클라이언트에 데이터를 보낼 수 있는 정보가 들어있기 때문에.
		
		Socket clientSocket = null;
		
		try {
			
			// Socket 생성
			clientSocket = new Socket();
			
			// 서버 접속
			clientSocket.connect(new InetSocketAddress("localhost", 9099));
			
			// 서버에 접속되면 Welcome 메시지가 넘어옴  -> 받아!
			// 서버가 DataOutputStream의 writeUTF()로 메시지를 전송하므로
			// 클라이언트는 DataInputStrema의 readUTF()로 메시지를 받음
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			String message = in.readUTF();
			System.out.println("[클라이언트]" + message);
			
			
			
			// Scanner 클래스를 이용해 입력 받은 데이터를 서버로 보내주기
			// 한글을 보내는 거니까 Reader 사용
			Scanner sc = new Scanner(System.in);
			System.out.print("서버로 전송할 메시지 >>> ");
			String send = sc.nextLine();
			OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());	// 클라이언트에 데이터를 보낼 수 있는 정보가 들어있기 때문에
			out.write(send); 
	
			// 바이트 스트림이 나오면 Reader !!로 바꿔서 보내줘야함
			
			// 입출력 스트림 종료
			out.close();
			in.close();  // 먼저 만든걸 나중에 닫, 나중에 만든걸 먼저 닫는게 기본적인 그 열고닫는 룰..?!
			sc.close();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(clientSocket.isClosed() == false) {
					System.out.println("[클라이언트] 클라이언트가 종료되었습니다");
					clientSocket.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}


	
	
	
	
}
