package main;

import chatting.ChatServer;
import java.util.ArrayList;

import User_Management.User;

public class Main {
	
	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		new LoginScreen();
		server.giveAndTake();
	}
}