package prac2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


public class ClientMain {

	public static void main(String[] args) {
		
		Socket socket = null;
		Scanner sc = null;
		BufferedWriter out = null;
		
		try {
			
			socket = new Socket();
			socket.connect(new InetSocketAddress("localhost", 9090));
			
			Client client = new Client(socket);  // 소켓이 클라이언트클래스로 넘어감.
			client.start();
		
			sc = new Scanner(System.in);
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // socket.~ : 서버와 연결된 socket
			
			
			while(true) {
				
				String message = sc.nextLine();
				out.write(message + "\n");   // 여기 out이 보낸 메시지를 Client.java의 in.readLine();이 받음
				out.flush();
				
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null) 
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
