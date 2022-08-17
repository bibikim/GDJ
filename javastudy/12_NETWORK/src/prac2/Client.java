package prac2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Client extends Thread {

	private Socket socket; // client는 접속을 위해 socket을 쓰니까 필드 생성
	private BufferedReader in;

	public Client(Socket socket) {
		try {
			this.socket = socket;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void run() {
		
		try {
			while(true) {
				String message = in.readLine();
				if(message.equalsIgnoreCase("exit")) {
					break;
				}
				System.out.println(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) {
					in.close();
				}
				if(socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
}
