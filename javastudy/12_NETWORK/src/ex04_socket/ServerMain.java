package ex04_socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	public static void main(String[] args) {

		// serverSocket  -------output-------->   clientSocket
		// clientSocket.getOutputStream() : 클라이언트 소켓의 출력스트림. 데이터를 보내려면 스트림이 필요하니까~
		// 출력을 만들 때는 항상 목적지 기준. 그렇기 때문에 clientSocket의 출력스트림을 쓴다
		
		// 콘솔목록에 <terminated> -> 실행끝난것
		
		ServerSocket serverSocket = null;
		
		try {
			// ServerSocket 생성
			serverSocket = new ServerSocket();
			
			// ServerSocket의 호스트/포트번호 설정
			serverSocket.bind(new InetSocketAddress("localhost", 9099));
			
			// 접속한 클라이언트 갯수
			int clientCnt = 0;	//

			// 서버는 종료 없이 무한루프 
			while(true) {
				
				System.out.println("[서버] 클라이언트 접속 기다리는 중");
				
				// 클라이언트 접속 및 카운팅
				Socket clientSocket = serverSocket.accept();
				clientCnt++;	// 접속이 허용되었다면 ++하겠다
				
				// 클라이언트에게 Welcome 메시지 전송
				// 바이트 출력 스트림 중에서 DataOutputStream은 writeUTF() 메소드를 이용해서		 // ~output/inputStream으로 끝나는 스트림들은 byte기반
				// 한글을 깨짐 없이 보낼 수 있다.
				//OutputStream out = clientSocket.getOutputStream();
				DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
				out.writeUTF("[서버] 게스트" + clientCnt +"님 환영합니다 :)");   // 서버 접속하면 클라이언트측으로 보내주는 메시지~
		
				
				
				// 클라이언트가 보낸 메시지 확인
				// 클라이언트가 OutputStream의 write()로 보냈으므로
				// InputStreamReader의 read()로 확인
				InputStreamReader in = new InputStreamReader(clientSocket.getInputStream()); // 클라이언트측에서 보낸걸 읽어주는 것
				   // 바이트 배열을 준비해야 받아볼 수 있다.
				char[] cbuf = new char[5];
				int readCnt = 0;  // 실제로 읽은 바이트 수
				StringBuilder sb = new StringBuilder();
				
				while((readCnt = in.read(cbuf)) != -1) {   // readByte를 in read에 b를 저장한 값
					sb.append(cbuf, 0, readCnt);   // 3바이트만 읽었으면 3바이트만 기록
				}
				System.out.println("[서버] 클라이언트가 보낸 메시지 : " + sb.toString());

				// 입출력 스트림 종료
				in.close();
				out.close();	// 스트림이 닫히면서 clientSocket도 닫아버리기 때문에 나중에 닫아준다
				
				
			
				// 클라이언트 접속 종료
				clientSocket.close();	// 메시지 보내주고 접속된 클라이언트 접속 끊어주기
				
				// 접속한 클라이언트가 3명이면 무한루프 종료
				if(clientCnt == 3) {
					System.out.println("[서버] 서버가 종료되었습니다.");
					serverSocket.close(); // 서버 종료
					break;	// 무한루프 종료
				}
				
			} // while(true)문 종료
		
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(serverSocket.isClosed() == false) {
					System.out.println("[서버] 서버가 종료되었습니다.");
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
