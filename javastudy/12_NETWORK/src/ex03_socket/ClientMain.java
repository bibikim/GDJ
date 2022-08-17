package ex03_socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientMain {							// 클라이언트 메인을 계속 실행하는건 괜찮은데 서버메인은 계속 열려있기 때문에 계속 실행x
													// 서버메인 실행 후 클라이언트메인 실행
	public static void main(String[] args) {

		Socket clientSocket = null;// client니까 서버소켓ㄴㄴ 소켓ㅇㅇ
		
		try {
			
			// Socket 생성
			clientSocket = new Socket();
			
			// 접속할 Server의 InetSocketAddress 생성
			InetSocketAddress address = new InetSocketAddress("localhost", 9090);
			
			// 서버에 접속(할 주소 넣어주기)
			clientSocket.connect(address);
			
			System.out.println("[클라이언트] 접속 성공");
		
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(clientSocket.isClosed() == false) {
					clientSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
