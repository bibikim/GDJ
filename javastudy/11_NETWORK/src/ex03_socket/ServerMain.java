package ex03_socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	// ServerSocket
	// 서버가 클라이언트와 통신할 때 사용하는 클래스 (stream개념으로 접근)
	
	// InetSocketAddress 
	// Socket을 사용할 때 호스트(메인주소)이름과 포트번호(호스트 뒤에 :포트번호)를 관리하는 클래스
	
	// Socket
	// 클라이언트가 서버와 통신할 때 사용하는 클래스
	// server    <-- localhost:80 ----   client
	// accept()
	// 서버가 허용accept()해주면 그 정보가 소켓이고 클라이언트의 정보를 인식할 수 있게 됨.
	
	public static void main(String[] args) {		
		
		// ServerMain은 한번만 실행
		
		ServerSocket serverSocket = null;
		
		try {
			
			// ServerSocket 생성	
			serverSocket = new ServerSocket();
			
			// InetSocketAdderess 생성
			InetSocketAddress address = new InetSocketAddress("localhost", 9090);  // http://localhost:9090으로 연결된다.
			
			// ServerSocket에 InetsocketAddress 연결
			serverSocket.bind(address);
			
			// serverSocket는 꺼지지 말라고 무한루프로 구현             
			while(true) {
				 
				System.out.println("[서버] 클라이언트 접속을 기다리는 중");
				Socket client = serverSocket.accept();
				InetSocketAddress clientaddress = (InetSocketAddress)client.getRemoteSocketAddress();	// 캐스팅
				System.out.println("접속이 허용된 클라이언트 : " + clientaddress.getHostName());
				
			}
			
		} catch(IOException e){
			e.printStackTrace();
		} finally {
			try {
				if(serverSocket.isClosed() == false) {	// 닫히지 않았으면
					serverSocket.close();				// 닫아주라
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		

	}

}
