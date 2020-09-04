package moneymoney.Chatting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static void main(String[] args) {
		ServerSocket server;
		try {
			server = new ServerSocket(7777);
			while(true) {
				Socket client = server.accept();
				System.out.printf("client가 %s로 접속\n", client.getInetAddress().getHostAddress());
				ChatHandler chatHandler = new ChatHandler(client);
				chatHandler.initStart();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
