package main;

import chatting.ChatServer;

public class Main {
	
	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		new LoginScreen();
		server.giveAndTake();
	}
}