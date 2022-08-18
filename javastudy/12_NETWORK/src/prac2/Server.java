package prac2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Server extends Thread {

	private Socket client;
	private BufferedReader in;
	private BufferedWriter out;

	public Server(Socket client) {
		try {
			this.client = client;
			in = new BufferedReader(new InputStreamReader(client.getInputStream())); // 바이트기반이니까 Reader로
			out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream())); // 클라이언트가 보낸거 다른 클라이언트가 받는다
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) throws IOException {   // 서버메인의 server.sendMessage(message)에 던진다
		out.write(message);
		out.flush();
	}
	
	
	@Override
	public void run() {
		
		try {
			InetSocketAddress address = null;
			while(true) {
				String message = in.readLine();
				if(message == null || message.equalsIgnoreCase("exit")) { 	// 채팅창에 exit 입력하면 채팅 종료
					break;
				}
				// 모든 클라이언트에게 메시지 출력
				address = (InetSocketAddress)client.getRemoteSocketAddress();  // 타입이 안 맞으니 캐스팅
				for(Server server : ServerMain.servers) {
					server.sendMessage(message);
				}
			}
			
			// List<Server> servers에서 등록된 서버 제거
			ServerMain.servers.remove(this);
			System.out.println(address.getHostName() + " 채팅종료");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	
}
