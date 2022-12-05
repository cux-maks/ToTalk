package chatting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSocketThread extends Thread {
	Socket socket;
	ChatServer server;
	BufferedReader in;		// 입력 담당 클래스
	PrintWriter out;		// 출력 담당 클래스
	String name = "";
	String threadName;
	boolean game_start = false;
	
	public ServerSocketThread(ChatServer server, Socket socket) {
		
		this.server = server;
		this.socket = socket;
		threadName = super.getName();	// Thread 이름을 얻어옴
		System.out.println(socket.getInetAddress() + "님이 입장하였습니다.");	// IP주소 얻어옴
		System.out.println("Thread Name : " + threadName);
		
	}
	// 클라이언트로 메시지 출력
	public void sendMessage(String str) {
		out.println(str);
	}
	public boolean game_start() { return game_start; }
	public void game_end() { this.game_start = false; }
	// 쓰레드
	@Override
	public void run() {

		try {
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// true : autoFlush 설정
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
			sendMessage("사용할 닉네임을 입력해 주세요.");
			
			name = in.readLine();
			
//			server.broadCasting("[" + main.LoginScreen.getId() + "]님이 입장하셨습니다.");
			server.broadCasting("[" + name + this.getName() + "]님이 입장하셨습니다.");
			
			while(true) {
				String str_in;
				if (this.game_start != true) {
					str_in = in.readLine();
					if(!str_in.equals("")) {server.broadCasting("[" + name + "] " + str_in);}
					if (str_in.equals("끝말잇기 게임을 시작합니다.")) {
						this.game_start = true;
						System.out.println("test");
					}
				}
				
			}
		} catch (IOException e) {
			System.out.println(threadName + " 퇴장했습니다.");
			server.removeClient(this);
			//e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}