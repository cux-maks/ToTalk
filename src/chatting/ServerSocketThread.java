package chatting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import main.LoginScreen;
import game.WordChainGame;

public class ServerSocketThread extends Thread {
	Socket socket;
	ChatServer server;
	BufferedReader in;		// 입력 담당 클래스
	PrintWriter out;		// 출력 담당 클래스
	String name;
	String threadName;
	
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
	// 쓰레드
	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// true : autoFlush 설정
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
//			sendMessage("님이 입장하셨습니다.");
			
			name = in.readLine();
//			server.broadCasting("[" + main.LoginScreen.getId() + "]님이 입장하셨습니다.");
			server.broadCasting("[" + name + "]님이 입장하셨습니다.");
			
			while(true) {
				
				String str_in = in.readLine();
				server.broadCasting("[" + name + "] " + str_in);
				
				if (str_in.equals("끝말잇기 게임을 시작합니다.")) {
					server.broadCasting("시작 단어는 아버지 입니다.");
					
					String startword = new String("아버지");
		          String name[] = {"유저 1", "유저 2"};
		          
		          ArrayList word_list = new ArrayList();
		          int word_cnt = 0;
		          
		          System.out.println("끝말잇기 게임을 시작합니다...");
		          Scanner scan = new Scanner(System.in);
		          int playernum = 2;
		          
		          System.out.printf("시작하는 단어는 %s입니다.", startword);
		          
		          int i = 0, j = 0;
		          while(true) {
		              
		              i = j%playernum; 
		              
		              int lastIndex = startword.length() - 1;
		              char lastChar = startword.charAt(lastIndex);
		              
		              server.broadCasting(name[i] + "차례 입니다.");
		              System.out.println(name[i] + ">> ");
		              
		              
//		              String word = scan.next();
		              
		              String[] word_buf = in.readLine().split("] ");
		              String word = word_buf[1];
		              
		              boolean counting = (lastChar == word.charAt(0));
		              
		              if(word.length() == 1) {
		            	  server.broadCasting("두 글자 이상 입력해야합니다.");
		            	  server.broadCasting(name[i] + "님이 졌습니다.");
//		              	System.out.println("두 글자 이상 입력해야합니다.");
//		              	System.out.println(name[i] + "님이 졌습니다.");
		              	break;
		              }else if(counting==false) {
		            	  server.broadCasting("첫 글자가 다릅니다.");
		            	  server.broadCasting(name[i] + "님이 졌습니다.");
//		              	System.out.println("첫 글자가 다릅니다.");
//		                  System.out.println(name[i] + "님이 졌습니다.");
		                  break;
		              }else {
		              	if (word_list.contains(word)) {
		              		server.broadCasting("이미 사용된 단어입니다.");
			            	  server.broadCasting(name[i] + "님이 졌습니다.");
//		              		System.out.println("이미 사용된 단어입니다.");
//		              		System.out.println(name[i] + "님이 졌습니다.");
		              		break;
		              	}else {
		              		word_list.add(word_cnt, word);
		              		word_cnt += 1;
		              	}
		              }
		             startword = word;
		              j++;
		          }
		          scan.close();
		          
//		          WordChainGame gameStart = new WordChainGame(name1, name2);
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