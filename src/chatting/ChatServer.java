package chatting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import User_Management.User;

public class ChatServer {
	
	ServerSocket serverSocket;
	Socket socket;
	List<ServerSocketThread> list;  // ServerSocketThread 객체 저장
	ArrayList<User> Users = new ArrayList<User>();
	
	public ChatServer() {
		list = new ArrayList<ServerSocketThread>();
		System.out.println("서버가 시작되었습니다.");
	}
	
	public int num_of_users() { return list.size(); }
	
	public void giveAndTake() {
		try {
			serverSocket = new ServerSocket(5420);		// 소켓 접속 대기
			serverSocket.setReuseAddress(true); 		// ServerSocket이 port를 바로 다시 사용한다 설정(port를 잡고있음)
			
			socket = serverSocket.accept();			// accept -> 1. 소켓 접속 대기 2. 소켓 접속 허락
			ServerSocketThread thread1 = new ServerSocketThread(this, socket);	// this -> ChatServer 자신
			addClient(thread1);		// 리스트에 쓰레드 객체 저장
			thread1.start();
			
			socket = serverSocket.accept();			// accept -> 1. 소켓 접속 대기 2. 소켓 접속 허락
			ServerSocketThread thread2 = new ServerSocketThread(this, socket);	// this -> ChatServer 자신
			addClient(thread2);		// 리스트에 쓰레드 객체 저장
			thread2.start();
			while(true) {
			while (true) {
				if(!this.WordChainGame()) {
					list.get(0).game_start = true;
					list.get(1).game_start = true;
					break;
				}
			}
			
			while (true) {
				String startword = "아버지";
				ArrayList word_list = new ArrayList();
				int word_cnt = 0;
				int playernum = 2;
	        
				Player[] play = new Player[playernum];
	        
				for(int i = 0; i<playernum; i++) play[i] = new Player();
				for(int i = 0; i<playernum; i++) play[i].name = list.get(i).getName();
				this.broadCasting("시작 단어는 아버지 입니다.");
				int i = 0, j = 0;
				while(true) {
	            
					i = j%playernum; 
	            
					int lastIndex = startword.length() - 1;
					char lastChar = startword.charAt(lastIndex);
	            
					this.broadCasting("(게임중) [" + play[i].name + "] " + "님이 입력할 순서 입니다.");
					play[i].word = list.get(i).in.readLine();
					this.broadCasting("(게임중) [" + play[i].name + "] " + play[i].word);
					
					boolean counting = play[i].checkSuccess(lastChar);
					
					if(play[i].word.length() == 1) {
						this.broadCasting("두 글자 이상 입력해야합니다.");
						this.broadCasting(play[i].name + "님이 졌습니다.");
						break;
					}else if(counting==false) {
						this.broadCasting("첫 글자가 다릅니다.");
						this.broadCasting(play[i].name + "님이 졌습니다.");
	                	break;
					}else {
						if (word_list.contains(play[i].word)) {
							this.broadCasting("이미 사용된 단어입니다.");
							this.broadCasting(play[i].name + "님이 졌습니다.");
							break;
						}else {
							word_list.add(word_cnt, play[i].word);
							word_cnt += 1;
						}
					}
					startword = play[i].word;
					j++;
				}
				break;
			}

			for(int m = 0; m < 2; m++) {
				ServerSocketThread thread = (ServerSocketThread)list.get(m);
				thread.game_end();
			}
			
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	private synchronized void addClient(ServerSocketThread thread) {
		
		list.add(thread);
		System.out.println("Client 1명 입장. 총 " + list.size() + "명");
	}		
	// 클라이언트가 퇴장 시 호출되며, 리스트에 클라이언트 담당 쓰레드 제거
	public synchronized void removeClient(Thread thread) {
		list.remove(thread);
		System.out.println("Client 1명 퇴장. 총 " + list.size() + "명");
	}
	// 모든 클라이언트에게 채팅 내용 전달
	public synchronized void broadCasting(String str) {
		for(int i = 0; i < list.size(); i++) {
			ServerSocketThread thread = (ServerSocketThread)list.get(i);
			thread.sendMessage(str);
		}
	}

	public synchronized boolean WordChainGame() {
		for(int i = 0; i < list.size(); i++) {
			ServerSocketThread thread = (ServerSocketThread)list.get(i);
			if (thread.game_start()) {
				this.broadCasting("끝말잇기 게임을 시작합니다. 진짜로.");
				return false;
			}
		}
		return true;
	}
	
}