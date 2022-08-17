package prac2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
	 	// Server : 스레드
		// Client : 스레드
		// Server 하나가 Client 하나를 담당
	
	// 생성된 Server 목록
	public static List<Server> servers = new ArrayList<Server>();  // 메인에서 쓸 필드이기 때문에 static을 붙여야한다
														// ㄴ <>만 살리고 안엔 생략 가능하다
	
	
	// 모든 Server에 메시지 전송
	public static void sendMessage(String message) throws IOException {
		//for(int i = 0, length = servers.size(); i < length; i++) {
		//	servers.get(i).sendMessage(message);
		//}      ▼ 같은 for문 ▲
		for(Server server : servers) {
			server.sendMessage(message);
		}
	}
	
	
	public static void main(String[] args) {
		
		ServerSocket server = null;
		Socket client = null;
		
		try {
			
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", 9090));	// bind 접속
			System.out.println("♧♣♧채팅 서버 오픈♧♣♧");
			
			while(true) {
				
				client = server.accept(); // 서버가 접속을 허용해주면 클라이언트 정보가 넘어온다.
				
				// 클라이언트를 담당할 서버 만들기
				Server s = new Server(client);
				servers.add(s);	
				
				s.start();  // 스레드 시작
				
				System.out.println("현재 접속 중인 클라이언트 " + servers.size() + "명");
				
			} 
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(server.isClosed() != false) {
					server.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}

}
